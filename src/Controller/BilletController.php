<?php

namespace App\Controller;

use App\Entity\Billet;
use App\Entity\Classroom;
use App\Entity\Hotel;
use App\Entity\ResBillet;
use App\Entity\ResChambre;
use App\Entity\Student;
use App\Form\BilletType;
use App\Form\ResBilletType;
use App\Form\ResChambreType;
use App\Form\SearchBilletType;
use App\Form\SearcheStudentType;
use App\Form\SearchHType;
use App\Form\StudentType;
use App\Repository\BilletRepository;
use App\Repository\HotelRepository;
use App\Repository\VolRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;



class BilletController extends AbstractController
{
    /**
     * @Route("/billet", name="billet")
     */
    public function index(): Response
    {
        return $this->render('billet/index.html.twig', [
            'controller_name' => 'BilletController',
        ]);
    }

    /**
     * @Route("/listBillet", name="listBillet")
     */
    public function listBillet(){
        $billets = $this->getDoctrine()->getRepository(Billet::class)->findAll();
        return $this->render('billet/listBillet.html.twig',array("listBillets"=>$billets));
    }

    /**
     * @Route("/addBillet", name="addBillet")
     */
    public function addBillet(Request $request)
    {
        $billet=new Billet();
        $form=$this->createForm(BilletType::class,$billet);
        $form->handleRequest($request);
        if($form->isSubmitted()&&($form->isValid())){

            $file = $billet->getImageBillet();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);

            $em=$this->getDoctrine()->getManager();
            $billet->setImageBillet($fileName);

            $em->persist($billet);
            $em->flush();
            return $this->redirectToRoute("showBillet");
        }

        return $this->render('billet/addBillet.html.twig',array("formBillet"=>$form->createView()));

    }

    /**
     * @Route("/deleteBillet/{id}", name="deleteBillet")
     */
    public function deleteBillet($id){
        $billet=$this->getDoctrine()->getRepository(Billet::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($billet);
        $em->flush();
        return $this->redirectToRoute("showBillet");
    }

    /**
     * @Route("/updateBillet/{id}", name="updateBillet")
     */
    public function updateBillet(Request $request,$id)
    {
        $billet = $this->getDoctrine()->getRepository(Billet::class)->find($id);
        $form=$this->createForm(BilletType::class,$billet);
        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){

            $file = $billet->getImageBillet();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);


            $em=$this->getDoctrine()->getManager();
            $billet->setImageBillet($fileName);

            $em->flush();
            return $this->redirectToRoute("showBillet");
        }

        return $this->render('billet/updateBillet.html.twig',array("formBillet"=>$form->createView()));

    }

    /**
     * @Route("/showBillet", name="showBillet")
     */
    public function showBillet(Request $request){
        $billets = $this->getDoctrine()->getRepository(Billet::class)->findAll();
        $form=$this->createForm(SearchBilletType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $numBillet=$form['numB']->getData();
            $result=$this->getDoctrine()->getRepository(Billet::class)->searchBillet($numBillet);
            return $this->render('billet/showBillet.html.twig',array("showBillets"=>$result,"formBillet"=>$form->createView()));

        }
        return $this->render('billet/showBillet.html.twig',array("showBillets"=>$billets,"formBillet"=>$form->createView()));
    }


    /**
     * @Route("/triBilletPrix",name="triBilletPrix")
     */
    public function triBilletPrix(BilletRepository $s,Request $request){
        $form=$this->createForm(SearchBilletType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $numBillet=$form['numB']->getData();
            $result=$this->getDoctrine()->getRepository(Billet::class)->searchBillet($numBillet);
            return $this->render('billet/showBillet.html.twig',array("showBillets"=>$result,"formBillet"=>$form->createView()));

        }
        $billetsByPrix= $s->orderByPrixV();
        return $this->render("billet/showBillet.html.twig",
            array("showBillets"=>$billetsByPrix,"formBillet"=>$form->createView()));

    }

    /**
     * @Route("/triBilletDate",name="triBilletDate")
     */
    public function triBilletDate(BilletRepository $s,Request $request){
        $form=$this->createForm(SearchBilletType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $numBillet=$form['numB']->getData();
            $result=$this->getDoctrine()->getRepository(Billet::class)->searchBillet($numBillet);
            return $this->render('billet/showBillet.html.twig',array("showBillets"=>$result,"formBillet"=>$form->createView()));

        }
        $billetByDate= $s->orderBydateV();
        return $this->render("billet/showBillet.html.twig",
            array("showBillets"=>$billetByDate,"formBillet"=>$form->createView()));

    }

    /**
     * @Route("/volDispo/{id}", name="volDispo")
     */
    public function volDispo(BilletRepository $billetRepository,$id,FlashyNotifier $flashyNotifier,Request $request)
    {

        $reservation = new ResBillet();
        $id = $request->get("id");
        $vol = $billetRepository->find($id);

        $form = $this->createForm(ResBilletType::class, $reservation);
        $form->handleRequest($request);
        $pourcent=$vol->getPrix() * 0.2;
        $pourcent1=$vol->getPrix() * 0.3;



        if ($reservation->getClasse() == "First Class")  :
            $reservation->setTarif(($reservation->getNbrPas()) * ( $pourcent +$vol->getPrix() ) );


        elseif ($reservation->getClasse() == "Business Class")  :
            $reservation->setTarif(($reservation->getNbrPas()) * ( $pourcent1 + $vol->getPrix() ) );
        else :
            $reservation->setTarif(($reservation->getNbrPas()) * $vol->getPrix()  );

        endif;



        if ($form->isSubmitted() && $form->isValid()){
            $vol->getVol()->setNbrPlace(($vol->getVol()->getNbrPlace()) - 1);
            $reservation->setBillet($vol);
            $em= $this->getDoctrine()->getManager();
            $em->persist ($reservation);
            $em->flush();
            $flashyNotifier->primaryDark('billet reservé','#');
            return $this->redirectToRoute('showVol');

        }
        return $this->render('res_billet/AjoutResbillet.html.twig',array("formResBillet"=>$form->createView()));
    }


}
