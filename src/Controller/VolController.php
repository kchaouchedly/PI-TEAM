<?php

namespace App\Controller;

use App\Entity\Billet;
use App\Entity\Hotel;
use App\Entity\Vol;
use App\Form\BilletType;
use App\Form\SearchHType;
use App\Form\SearchVolType;
use App\Form\VolType;
use App\Repository\HotelRepository;
use App\Repository\VolRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class VolController extends AbstractController
{
    /**
     * @Route("/vol", name="vol")
     */
    public function index(): Response
    {
        return $this->render('vol/index.html.twig', [
            'controller_name' => 'VolController',
        ]);
    }

    /**
     * @Route("/listVol", name="listVol")
     */
    public function listVol(Request $request){
        $vols = $this->getDoctrine()->getRepository(Vol::class)->findAll();
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        return $this->render('vol/listVol.html.twig',array("listVols"=>$vols,"formVol"=>$form->createView()));
    }
    /**
     * @Route("/showVol", name="showVol")
     */
    public function showVol(){
        $vols = $this->getDoctrine()->getRepository(Vol::class)->findAll();

        return $this->render('vol/showVol.html.twig',array("showVols"=>$vols));
    }


    /**
     * @Route("/addVol", name="addVol")
     */
    public function addVol(Request $request)
    {
        $vol=new Vol();
        $form=$this->createForm(VolType::class,$vol);
        $form->handleRequest($request);
        if($form->isSubmitted()&&($form->isValid())){
            $em=$this->getDoctrine()->getManager();
            $em->persist($vol);
            $em->flush();
            return $this->redirectToRoute("listVol");
        }

        return $this->render('vol/addVol.html.twig',array("formVol"=>$form->createView()));

    }

    /**
     * @Route("/deleteVol/{id}", name="deleteVol")
     */
    public function deleteVol($id){
        $vol=$this->getDoctrine()->getRepository(Vol::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($vol);
        $em->flush();
        return $this->redirectToRoute("listVol");
    }


    /**
     * @Route("/updateVol/{id}", name="updateVol")
     */
    public function updateVol(Request $request,$id)
    {
        $vol = $this->getDoctrine()->getRepository(Vol::class)->find($id);
        $form=$this->createForm(VolType::class,$vol);
        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){
            $em=$this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("listVol");
        }

        return $this->render('vol/updateVol.html.twig',array("formVol"=>$form->createView()));

    }
    /**
     * @Route("/triVolNum",name="triVolNum")
     */
    public function triVolNum(VolRepository $s,Request $request){
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        $volByNum= $s->orderByNum();
        return $this->render("vol/listVol.html.twig",
            array("listVols"=>$volByNum,"formVol"=>$form->createView()));

    }

    /**
     * @Route("/triVolDatedep",name="triVolDatedep")
     */
    public function triVolDatedep(VolRepository  $s,Request $request){
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        $volByDate= $s->orderByDateDep();
        return $this->render("vol/listVol.html.twig",
            array("listVols"=>$volByDate,"formVol"=>$form->createView()));

    }

    /**
     * @Route("/imprimVol", name="imprimVol")
     */
    public function imprimVol()
    {

        $repository=$this->getDoctrine()->getRepository(Vol::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $vols=$repository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('vol/volPdf.html.twig', array("listVols" => $vols));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Vol.pdf", [
            "Attachment" => true
        ]);


    }

}
