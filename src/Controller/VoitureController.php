<?php

namespace App\Controller;

use App\Entity\Voiture;
use App\Form\VoitureType;
use App\Repository\VoitureRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Vich\UploaderBundle\Form\Type\VichImageType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;

class VoitureController extends AbstractController
{
    /**
     * @Route("/voiture", name="voiture")
     */
    public function index(): Response
    {
        return $this->render('voiture/index.html.twig', [
            'controller_name' => 'VoitureController',
        ]);
    }
    /**
     * @Route("/addvoiture", name="addvoiture")
     */
    public function add(Request $request)
    { $voiture =new Voiture();

        $form=$this->createForm(VoitureType::class,$voiture);
        $form->handleRequest($request);
        if ($form->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($voiture);
            $em->flush();

        }
        return $this->render("voiture/add_voiture.html.twig",array("formvoiture"=>$form->createView()));

    }
    /**
     * @Route("/listvoiture", name="Listvoiture")
     */
    public function listvoiture()
    {
        $voiture=$this->getDoctrine()->getRepository(Voiture::class)->findAll();
        return $this->render("voiture/listvoiture.html.twig",array ("tabvoiture"=>$voiture));
    }
    /**
     * @Route("/deletevoiture{id}",name="deletevoiture")
     */

    public function deletevoiture($id)
    {
        $voiture=$this->getDoctrine()->getRepository(Voiture::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($voiture);
        $em->flush();
        return $this->redirectToRoute("Listvoiture");
    }

    /**
     * @Route("/updatevoiture{id}", name="updatevoiture")
     */
    public function update(Request $request ,$id)
    {
        $voiture =$this->getDoctrine()->getRepository(Voiture::class)->find($id);
        $form=$this->createForm(VoitureType::class,$voiture);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listvoiture");
        }
        return $this->render("voiture/updatevoiture.html.twig",array("formupdatevoiture"=>$form->createView()));

    }
    /**
     * @Route("/listvoituref", name="Listvoituref")
     */
    public function listvoituref()
    {
        $voiture=$this->getDoctrine()->getRepository(Voiture::class)->findAll();
        return $this->render("voiture/listvoituref.html.twig",array ("tabvoiture"=>$voiture));
    }
    /**
     * @Route("/rating{id}",name="rating")
     */
    public function ratingvoiture($id)
    {
        //$rating = $_POST["rating"];

        $sql= "INSERT INTO voiture (rate) VALUES ('4') WHERE id=8 ";

        return $this->redirectToRoute("Listvoituref");

    }

}
