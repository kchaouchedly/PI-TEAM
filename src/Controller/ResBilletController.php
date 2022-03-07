<?php

namespace App\Controller;

use App\Entity\ResBillet;
use App\Entity\ResChambre;
use App\Form\ResBilletType;
use App\Form\ResChambreType;
use App\Repository\ResBilletRepository;
use App\Repository\ResChambreRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ResBilletController extends AbstractController
{
    /**
     * @Route("/res/billet", name="app_res_billet")
     */
    public function index(): Response
    {
        return $this->render('res_billet/index.html.twig', [
            'controller_name' => 'ResBilletController',
        ]);
    }


    /**
     * @Route("/afficheResBillet", name="afficheResBillet")
     */
    public function afficheResBillet(ResBilletRepository $Repository)
    {
        $resBillet= $Repository->findAll();
        return $this->render('res_billet/listResBillet.html.twig', [
            'resBillet' => $resBillet,
        ]);
    }


    /**
     * @Route("/addResBillet", name="addResBillet")
     */
    public function addResBillet(Request $request )
    {

        $reservation = new ResBillet();
        $form = $this->createForm (ResBilletType::class, $reservation);
        $form -> handleRequest($request);
        $id=$request->get("id");
        if ($form->isSubmitted() && $form->isValid()){
           // $reservation->getChambre()->setDispo("Non Disponible");
            $em= $this->getDoctrine()->getManager();
            $em->persist ($reservation);
            $em->flush();
            return $this->redirectToRoute('afficheResBillet');

        }
        return $this->render('res_Billet/AjoutResBillet.html.twig',array("formResBillet"=>$form->createView()));

    }




    /**
     * @Route("/updateResBillet/{id}", name="updateResBillet")
     */
    public function updateResBillet(Request $request, $id )
    {
        $em= $this->getDoctrine()->getManager();
        $reservation= $em ->getRepository (ResBillet::class)->find ($id);
        $form =$this->createForm (ResBilletType::class, $reservation);
        $form ->handleRequest($request);
        if ($form->isSubmitted() && $form-> isValid ()){
            $em->flush();
            return $this->redirectToRoute('afficheResBillet');
        }
        return $this->render('res_billet/updateResBillet.html.twig',array("formResBillet"=>$form->createView()));

    }

    /**
     * @Route("/deleteResBillet/{id}", name="deleteResBillet")
     */
    public function deleteResBillet($id)
    {   $em= $this->getDoctrine()->getManager();
        $reservation= $em ->getRepository (ResBillet::class)->find ($id);
        $em-> remove ($reservation);
        $em->flush();
        return $this->redirectToRoute('afficheResBillet');

    }

    /**
     * @Route("/imprimResBillet/{id}", name="imprimResBillet")
     */
    public function imprimResBillet($id)
    {

        $repository = $this->getDoctrine()->getRepository(ResBillet::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $resBillet= $repository->find($id);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('res_billet/ResBilletPdf.html.twig', array("afficheResBillet" => $resBillet));

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
