<?php

namespace App\Controller;

use App\Entity\Voiture;
use App\Form\RatingType;
use App\Form\VoitureType;
use App\Repository\VoitureRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
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
    public function add(Request $request, FlashyNotifier $flashy)
    {
        $voiture = new Voiture();

        $form = $this->createForm(VoitureType::class, $voiture);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $voiture->getAgencevoiture()->setNbrVoiture($voiture->getAgencevoiture()->getNbrVoiture() + 1);
            $em = $this->getDoctrine()->getManager();
            $em->persist($voiture);
            $em->flush();
            $flashy->success('voiture ajoutée', 'http://localhost:90/ProjectPiDev_v0.1/public/index.php/listvoiture');

        }
        return $this->render("voiture/add_voiture.html.twig", array("formvoiture" => $form->createView()));

    }

    /**
     * @Route("/listvoiture", name="Listvoiture")
     */
    public function listvoiture()
    {

        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->findAll();
        return $this->render("voiture/listvoiture.html.twig", array("tabvoiture" => $voiture));
    }

    /**
     * @Route("/deletevoiture/{id}",name="deletevoiture")
     */

    public function deletevoiture($id)
    {
        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($voiture);
        $em->flush();
        return $this->redirectToRoute("Listvoiture");

    }

    /**
     * @Route("/updatevoiture{id}", name="updatevoiture")
     */
    public function update(Request $request, $id)
    {
        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->find($id);
        $form = $this->createForm(VoitureType::class, $voiture);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listvoiture");
        }
        return $this->render("voiture/updatevoiture.html.twig", array("formupdatevoiture" => $form->createView()));

    }

    /**
     * @Route("/listvoituref", name="Listvoituref")
     */
    public function listvoituref()
    {
        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->findAll();
        return $this->render("voiture/listvoituref.html.twig", array("tabvoiture" => $voiture));
    }

    /**
     * @Route("/rating/{id}",name="rating" ,methods="GET")
     */
    public function ratingvoiture($id, Request $request)
    {
        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->find($id);
        $rating = $request->request->get('rating');

        $rating = ($voiture->getRate() + $rating) / 2;
        $voiture->setRate($rating);
        $em = $this->getDoctrine()->getManager();
        $em->persist($voiture);
        $em->flush();
        //$rating=$request->query->get('rating');
        // $rating = $request->request->get('rating');


        //return $this->redirectToRoute("Listvoituref");
        return $this->render("voiture/listvoituref.html.twig", array("tabvoiture" => $voiture));
    }

    /**
     * @Route("/listvoiturerate", name="Listvoiturerate")
     */
    public function listvoiturerate()
    {
        $voiture = $this->getDoctrine()->getRepository(Voiture::class)->orderByrate();
        return $this->render("voiture/listvoiture.html.twig", array("tabvoiture" => $voiture));
    }

    /**
     * @Route("/create-checkout-sessionV", name="checkoutoffpaiV")
     */
    public function checkout()
    {
        $em = $this->getDoctrine()->getManager();
        $Offres = $em->getRepository(Voiture::class);

        \Stripe\Stripe::setApiKey('sk_test_51KZg1OJFn6DTW5uS4y2jdkJVumenKJICV3LE2Cw0JuNnAx5HgWOmb7vMxz6g3vKHz1GdtlUz53JTW00q3Q9MKSHj00HPoZatyS');


        $session = \Stripe\Checkout\Session::create([
            'payment_method_types' => ['card'],
            'line_items' => [[
                'price_data' => [
                    'currency' => 'usd',
                    'product_data' => [
                        'name' => 'activite',
                    ],
                    'unit_amount' => '200',
                ],
                'quantity' => 1,
            ]],
            'mode' => 'payment',
            'success_url' => $this->generateUrl('succesV', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url' => $this->generateUrl('erreurV', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return $this->redirect($session->url, 303);
    }

    /**
     * @Route("/succesV", name="succesV")
     */
    public function succes(): Response
    {
        return $this->render('voiture/succesV.html.twig');
    }

    /**
     * @Route("/erreurV", name="erreurV")
     */
    public function erreur(): Response
    {
        return $this->render('voiture/erreurV.html.twig'
        );

    }
}
