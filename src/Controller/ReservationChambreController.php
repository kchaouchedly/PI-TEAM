<?php

namespace App\Controller;

use App\Entity\Hotel;
use App\Entity\Resactivites;
use App\Entity\ResChambre;
use App\Form\ResactivitesType;
use App\Form\ResChambreType;
use App\Repository\ResChambreRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReservationChambreController extends AbstractController
{
    /**
     *@Route ("/reservation/chambre", name="reservation_chambre")
     */
    public function index(): Response
    {
        return $this->render('reservation_chambre/index.html.twig', [
            'controller_name' => 'ReservationChambreController',
        ]);
    }

    /**
     * @Route("/afficheResChambre", name="afficheResChambre")
     */
    public function afficheResChambre(ResChambreRepository $Repository)
    {
        $resChambre= $Repository->findAll();
        return $this->render('reservation_chambre/listResChambres.html.twig', [
            'resChambre' => $resChambre,
        ]);
    }

    /**
     * @Route("/imprimResChambre/{id}", name="imprimResChambre")
     */
    public function imprimResChambre($id)
    {

        $repository = $this->getDoctrine()->getRepository(ResChambre::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $resChambre = $repository->find($id);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('reservation_chambre/ResChambrePdf.html.twig', array("afficheResChambre" => $resChambre));

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

    /**
     * @Route("/addResChambre", name="addResChambre")
     */
    public function addResChambre(Request $request )
    {

        $reservation = new ResChambre();
        $form = $this->createForm (ResChambreType::class, $reservation);
        $form -> handleRequest($request);
        $id=$request->get("id");
        if ($form->isSubmitted() && $form->isValid()){
            $reservation->getChambre()->setDispo("Non Disponible");
            $em= $this->getDoctrine()->getManager();
            $em->persist ($reservation);
            $em->flush();
            return $this->redirectToRoute('afficheResChambre');

        }
        return $this->render('reservation_chambre/AjoutResChambres.html.twig',array("formResChambre"=>$form->createView()));

    }


    /**
     * @Route("/updateResChambre/{id}", name="updateResChambre")
     */
    public function updateResChambre(Request $request, $id )
    {   $em= $this->getDoctrine()->getManager();
        $reservation= $em ->getRepository (ResChambre::class)->find ($id);
        $form =$this->createForm (ResChambreType::class, $reservation);
        $form ->handleRequest($request);
        if ($form->isSubmitted() && $form-> isValid ()){
            $em->flush();
            return $this->redirectToRoute('afficheResChambre');
        }
        return $this->render('reservation_chambre/updateResChambres.html.twig',array("formResChambre"=>$form->createView()));

    }

    /**
     * @Route("/deleteResChambre/{id}", name="deleteResChambre")
     */
    public function deleteResChambre($id)
    {   $em= $this->getDoctrine()->getManager();
        $reservation= $em ->getRepository (ResChambre::class)->find ($id);
        $em-> remove ($reservation);
        $em->flush();
        return $this->redirectToRoute('afficheResChambre');

    }





}
