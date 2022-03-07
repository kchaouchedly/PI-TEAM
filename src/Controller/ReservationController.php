<?php

namespace App\Controller;

use App\Form\ReservationType;
use Dompdf\Dompdf;
use Dompdf\Options;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Reservation;
use App\Entity\Voiture;

use App\Repository\VoitureRepository;
use Symfony\Component\HttpFoundation\Request;

class ReservationController extends AbstractController
{
    /**
     * @Route("/reservation", name="reservation")
     */
    public function index(): Response
    {
        return $this->render('reservation/index.html.twig', [
            'controller_name' => 'ReservationController',
        ]);
    }

    /**
     * @Route("/addreservation/{id}", name="addreservation")
     */
    public function add(Request $request,VoitureRepository $repositery, $id, FlashyNotifier $flashy)
    { $reservation =new reservation();

        $form=$this->createForm(ReservationType::class,$reservation);

        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {  $voiture= $repositery->find($id);
            $reservation->setVoiture($voiture);
            $em=$this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();
            $flashy->success('Reservation effectuer', '#');
             return $this->redirectToRoute('Listvoituref');
        }
        return $this->render("reservation/add_reservation.html.twig",array("formreservation"=>$form->createView()));

    }
    /**
     * @Route("/listreservation", name="listreservation")
     */
    public function listRservation()
    {
        $reservation=$this->getDoctrine()->getRepository(Reservation::class)->findAll();
        return $this->render("reservation/listreservation.html.twig",array ("tabreservation"=>$reservation));
    }

    /**
     * @Route("/pdfreservation/{id}", name="pdfreservation")
     */
    public function pdf($id)
    {   $re=$this->getDoctrine()->getRepository(Reservation::class)->find($id);
        $voiture=$re->getVoiture();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('reservation/mypdf.html.twig',array("voiture"=>$voiture) ,[
            'title' => "Welcome to our PDF Test"
        ]);

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
