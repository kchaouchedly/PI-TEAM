<?php

namespace App\Controller;

use App\Entity\AgenceVoiture;
use App\Form\AgenceVoitureType;
use App\Repository\AgenceVoitureRepository;
use App\Repository\VoitureRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Vich\UploaderBundle\Form\Type\VichImageType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;

class AgenceVoitureController extends AbstractController
{
    /**
     * @Route("/agence/voiture", name="agence_voiture")
     */
    public function index(): Response
    {
        return $this->render('agence_voiture/index.html.twig', [
            'controller_name' => 'AgenceVoitureController',
        ]);
    }


    /**
     * @Route("/addagence", name="addagence")
     */
    public function add(Request $request, FlashyNotifier $flashy)
    {
        $agence = new AgenceVoiture();
        $form = $this->createForm(AgenceVoitureType::class, $agence);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($agence);
            $em->flush();
            $this->redirectToRoute("Listagence");
            $flashy->success('Agence ajoutée', '#');
            return $this->redirectToRoute("Listagence");
        }
        return $this->render("agence_voiture/add_agence.html.twig", array("formagence" => $form->createView()));

    }

    /**
     * @Route("/listagence", name="Listagence")
     */
    public function listAgence()
    {
        $agence = $this->getDoctrine()->getRepository(AgenceVoiture::class)->findAll();
        return $this->render("agence_voiture/listagence.html.twig", array("tabagence" => $agence));
    }

    /**
     * @Route("/delete{id}",name="deleteagence")
     */
    public function deleteAgence($id)
    {
        $agence = $this->getDoctrine()->getRepository(AgenceVoiture::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($agence);
        $em->flush();
        return $this->redirectToRoute("Listagence");

    }

    /**
     * @Route("/updateagence{id}", name="updateagence")
     */
    public function update(Request $request, $id)
    {
        $agence = $this->getDoctrine()->getRepository(AgenceVoiture::class)->find($id);
        $form = $this->createForm(AgenceVoitureType::class, $agence);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->flush();
            return $this->redirectToRoute("Listagence");
        }
        return $this->render("agence_voiture/updateagence.html.twig", array("formupdateagence" => $form->createView()));

    }

    /**
     * @Route("/listagencef", name="Listagencef")
     */
    public function listAgencef()
    {
        $agence = $this->getDoctrine()->getRepository(AgenceVoiture::class)->findAll();
        return $this->render("agence_voiture/listagencef.html.twig", array("tabagence" => $agence));
    }

    /**
     * @Route("/listvoitureby/{id}",name="listvoitureby")
     */
    public function listvoiturebyagence(VoitureRepository $repositery, $id)
    {
        $voiture = $repositery->listvoiturebyagence($id);
        return $this->render("agence_voiture/listvoitureby.html.twig", array("tabvoitureby" => $voiture));

    }


    /**
     * @Route("/listvoiturebyf/{id}",name="listvoiturebyf")
     */
    public function listvoiturebyagencef(VoitureRepository $repositery, $id)
    {
        $voiture = $repositery->listvoiturebyagence($id);
        return $this->render("agence_voiture/listvoiturebyf.html.twig", array("tabvoitureby" => $voiture));

    }

    /**
     * @Route("/allagence", name="allagence")
     */
    public function all()
    {

        $agence = $this->getDoctrine()->getManager()->getRepository(AgenceVoiture::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($agence);

        return new JsonResponse($formatted);

    }

    /**
     * @Route("/statsAgence", name="statsAgence")
     */
    public function statistiques(AgenceVoitureRepository $Repo)
    {
        // On va chercher toutes les catégories
        $agences = $Repo->findAll();

        $agenceNom = [];
        $agenceColor = [];
        $agenceCount = [];


        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach ($agences as $agence) {
            $agenceNom[] = $agence->getNomAgence();
            $agenceColor[] = $agence->getColor();
            $agenceCount[] = count((array)$agence->getNomAgence());
        }
        return $this->render('agence_voiture/stat.html.twig', [
            'agenceNom' => json_encode($agenceNom),
            'agenceColor' => json_encode($agenceColor),
            'agenceCount' => json_encode($agenceCount),]);
    }

}

