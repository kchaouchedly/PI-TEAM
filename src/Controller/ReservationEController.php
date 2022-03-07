<?php

namespace App\Controller;

use App\Entity\ReservationE;
use App\Form\ReservationEType;
use App\Repository\EvenementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReservationEController extends AbstractController
{
    /**
     * @Route("/reservation/e", name="reservation_e")
     */
    public function index(): Response
    {
        return $this->render('reservation_e/index.html.twig', [
            'controller_name' => 'ReservationEController',
        ]);
    }
    /**
     * @Route("/addreservationevenement/{id}", name="addreservationevenement")
     */
    public function add(Request $request,EvenementRepository $repositery, $id)
    { $reservation =new ReservationE();

        $form=$this->createForm(ReservationEType::class,$reservation);

        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {  $evenement= $repositery->find($id);
            $reservation->setEvenement($evenement);
            $em=$this->getDoctrine()->getManager();
            $em->persist($reservation);
            $em->flush();
            return $this->redirectToRoute("Listevenementf");

        }
        return $this->render("reservation_e/add_reservation_e.html.twig",array("formreservation_e"=>$form->createView()));

    }
    /**
     * @Route("/listreservationevenement", name="listreservationevenement")
     */
    public function listRservation()
    {
        $reservation=$this->getDoctrine()->getRepository(ReservationE::class)->findAll();
        return $this->render("reservation_e/listreservation_e.html.twig",array ("tabreservation_e"=>$reservation));
    }
}
