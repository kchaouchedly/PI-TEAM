<?php

namespace App\Controller;

use App\Entity\Offres;
use App\Form\OffresType;
use App\Repository\OffresRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;


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
    {
        $off = new Offres();
        $form = $this->createForm(OffresType::class, $off);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($off);
            $em->flush();
            return $this->redirectToRoute("Listoffre");

        }
        return $this->render("offres/add_off.html.twig", array("formoffres" => $form->createView()));

    }

    /**
     * @Route("/listoffre", name="Listoffre")
     */
    public function listoffre()
    {
        $off = $this->getDoctrine()->getRepository(Offres::class)->findAll();
        return $this->render("offres/listeoffre.html.twig", array("taboffre" => $off));
    }


    /**
     * @Route("/updateoffre{id}", name="updateoffre")
     */
    public function update(Request $request, $id)
    {
        $evenement = $this->getDoctrine()->getRepository(Offres::class)->find($id);
        $form = $this->createForm(OffresType::class, $evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listoffre");
        }
        return $this->render("offres/updateoffre.html.twig", array("formuupdateoffre" => $form->createView()));

    }

    /**
     * @Route("/listfo", name="listfo")
     */
    public function affichage()
    {
        $offf = $this->getDoctrine()->getRepository(Offres::class)->findAll();
        return $this->render("offres/listoff.html.twig", array("taboffre" => $offf));
    }

    /**
     * @Route("/deleteoffres/{id}",name="deleteoffres")
     */
    public function deleteoffre($id)
    {

        $off = $this->getDoctrine()->getRepository(Offres::class)->find($id);
        $e = $this->getDoctrine()->getManager();
        $e->remove($off);
        $e->flush();
        return $this->redirectToRoute("Listoffre");

    }


    /**
     * @Route("/imprimoff", name="imprimoff")
     */
    public function imprimevent()
    {

        $repository = $this->getDoctrine()->getRepository(Offres::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $ev = $repository->findAll();

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

    /**
     * @Route("/checkoutoffpai", name="checkoutoffpai")
     */
    public function checkout()
    {
        $em = $this->getDoctrine()->getManager();
        $Offres = $em->getRepository(Offres::class);

        \Stripe\Stripe::setApiKey('sk_test_51KXp29GUISMMBN90zjbpqtEs7FC6EqXpAtDNcjj9WdMqTNxUxfvVStIvOjwPIG5a3eqAf1FjU8BVFETNVYgsAH4V00UMPbJR6L');


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
            'success_url' => $this->generateUrl('succes', [], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url' => $this->generateUrl('erreur', [], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return $this->redirect($session->url, 303);
    }

    /**
     * @Route("/succes", name="succes")
     */
    public function succes(): Response
    {
        return $this->render('offres/succes.html.twig');
    }

    /**
     * @Route("/erreur", name="erreur")
     */
    public function erreur(): Response
    {
        return $this->render('offres/cancel.html.twig'
        );


    }
}
