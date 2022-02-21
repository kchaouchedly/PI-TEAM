<?php

namespace App\Controller;

use App\Entity\Offres;
use App\Form\OffresType;
use App\Repository\OffresRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Dompdf\Dompdf;
use Dompdf\Options;



class OffresController extends AbstractController
{
    /**
     * @Route("/offres", name="offres")
     */
    public function index(): Response
    {
        return $this->render('offres/index.html.twig', [
            'controller_name' => 'OffresController',
        ]);
    }
    /**
     * @Route("/addoffre", name="addoffre")
     */
    public function add(Request $request)
    { $off =new Offres();
        $form=$this->createForm(OffresType::class,$off);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isSubmitted())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($off);
            $em->flush();
            return $this->redirectToRoute("Listoffre");

        }
        return $this->render("offres/add_off.html.twig",array("formoffres"=>$form->createView()));

    }
    /**
     * @Route("/listoffre", name="Listoffre")
     */
    public function listoffre()
    {
        $off=$this->getDoctrine()->getRepository(Offres::class)->findAll();
        return $this->render("offres/listeoffre.html.twig",array ("taboffre"=>$off));
    }



    /**
     * @Route("/updateoffre{id}", name="updateoffre")
     */
    public function update(Request $request ,$id)
    {
        $evenement =$this->getDoctrine()->getRepository(Offres::class)->find($id);
        $form=$this->createForm(OffresType::class,$evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listoffre");
        }
        return $this->render("offres/updateoffre.html.twig",array("formuupdateoffre"=>$form->createView()));

    }

    /**
     * @Route("/listfo", name="listfo")
     */
    public function affichage()
    {
        $offf=$this->getDoctrine()->getRepository(Offres::class)->findAll();
        return $this->render("offres/listoff.html.twig",array ("taboffre"=>$offf));
    }

    /**
     * @Route("/deleteoffres{id}",name="deleteoffres")
     */
    public function supp($id){

        $o=$this->getDoctrine()->getRepository(Offres::class)>find($id);
        $e=$this->getDoctrine()->getManager();
        $e->remove($o);
        $e->flush();
        return $this->redirectToRoute("Listoffre");

    }




    /**
     * @Route("/imprimoff", name="imprimoff")
     */
    public function imprimevent()
    {

        $repository=$this->getDoctrine()->getRepository(Offres::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $ev=$repository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('offres/printoff.html.twig', array("taboffre" => $ev));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);


    }



}
