<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Form\EvenementType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class EvenementController extends AbstractController
{
    /**
     * @Route("/evenement", name="evenement")
     */
    public function index(): Response
    {
        return $this->render('evenement/index.html.twig', [
            'controller_name' => 'EvenementController',
        ]);
    }
    /**
     * @Route("/addevenement", name="addevenement")
     */
    public function add(Request $request)
    { $evenement =new Evenement();
        $form=$this->createForm(EvenementType::class,$evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();
            //return $this->redirectToRoute("classroomList");
        }
        return $this->render("evenement/add_evenement.html.twig",array("formevenement"=>$form->createView()));

    }
    /**
     * @Route("/listevenement", name="Listevenement")
     */
    public function listevenement()
    {
        $evenement=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
        return $this->render("evenement/listevenement.html.twig",array ("tabevenement"=>$evenement));
    }
    /**
     * @Route("/delete{id}",name="deleteevenement")
     */
    public function delete($id)
    { $evenement=$this->getDoctrine()->getRepository(Evenement::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($evenement);
        $em->flush();
        return $this->redirectToRoute("Listevenement");

    }
    /**
     * @Route("/updateevenement{id}", name="updateevenement")
     */
    public function update(Request $request ,$id)
    {
        $evenement =$this->getDoctrine()->getRepository(Evenement::class)->find($id);
        $form=$this->createForm(EvenementType::class,$evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listevenement");
        }
        return $this->render("evenement/updateevenement.html.twig",array("formupdateevenement"=>$form->createView()));

    }
    /**
     * @Route("/listevenementf", name="Listevenementf")
     */
    public function listevenementf()
    {
        $evenement=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
        return $this->render("evenement/listevenementf.html.twig",array ("tabevenement"=>$evenement));
    }


}
