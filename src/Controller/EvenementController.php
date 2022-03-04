<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Form\EvenementType;
use App\Form\SearchType;
use App\Repository\OffresRepository;
use App\Repository\EvenementRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Vich\UploaderBundle\Form\Type\VichImageType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;


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
    public function add(Request $request )
    { $evenement =new Evenement();
        $form=$this->createForm(EvenementType::class,$evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();
            return $this->redirectToRoute("Listevenement");
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
    {   $evenement=$this->getDoctrine()->getRepository(Evenement::class)->find($id);
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

    /**
     * @Route("/listoffresbyevenement/{id}",name="listoffresbyevenement")
     */
    public function listoffresbyevenement(OffresRepository $repository,$id){
        $s=$repository->listOffressbyEvenement($id);
        return $this->render("Evenement/listoffresbyevenement.html.twig",array("taboffre"=>$s));
    }

    /**
     * @Route("/listoffresbyevenementfront/{id}",name="listoffresbyevenementfront")
     */
    public function listoffresbyevenementfront(OffresRepository $repository,$id){
        $s=$repository->listOffressbyEvenement($id);
        return $this->render("Evenement/listoffresbyevenementfront.html.twig",array("taboffre"=>$s));
    }

    /**
     * @Route("/imprimevent", name="imprimevent")
     */
    public function imprimevent()
    {

        $repository = $this->getDoctrine()->getRepository(Evenement::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $ev = $repository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('evenement/printeve.html.twig', array("tabevenement" => $ev));

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
     * @Route("/trievetype",name="trievetype")
     */
    public function trievetype(EvenementRepository $s,Request $request){
        $form=$this->createForm(SearchType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $Type=$form['Type']->getData();
            $result=$this->getDoctrine()->getRepository(Evenement::class)->searchE($Type);

            return $this->render('evenement/listevenement.html.twig',array("listevenement"=>$result,"formevenement"=>$form->createView()));

        }
        $evebyty= $s->orderByType();
        return $this->render("evenement/listevenement.html.twig",
            array("listVols"=>$evebyty,"formevenement"=>$form->createView()));

    }
    /**
     * @Route("/displayEvenement", name="displayEvenement")
     */
    public function all(NormalizerInterface $normalizer)
    {

        $eve = $this->getDoctrine()->getManager()->getRepository(Evenement::class)->findAll();
        $formatted = $normalizer->normalize($eve,'json',['groups'=>'ev']);

        return new Response(json_encode($formatted));

    }

    /**
     * @Route("/stats", name="stats")
     */
    public function statistiques(EvenementRepository $eveRepo , OffresRepository $offRepo)
    {
        // On va chercher toutes les catégories
        $events = $eveRepo->findAll();

        $eventsNom = [];
        $eventsColor = [];
        $eventsCount = [];



        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach ($events as $event) {
            $eventNom[] = $event->getType();
            $eventColor[] = $event->getColor();
            $eventCount[] = count((array)$event->getType());
        }

        // On va chercher le nombre d'annonces publiées par date
        $offs = $offRepo->countByDate();

        $DateDebutOffres = [];
        $offCount = [];

        // On "démonte" les données pour les séparer tel qu'attendu par ChartJS
        foreach($offs as $off){
            $DateDebutOffres[] = $off['DateDebutOffres'];
            $offCount[] = $off['count'];
        }

        return $this->render('evenement/stats.html.twig', [
            'eventNom' => json_encode($eventNom),
            'eventColor' => json_encode($eventColor),
            'eventCount' => json_encode($eventCount),
            'DateDebutOffres' => json_encode($DateDebutOffres),
            'offCount' => json_encode($offCount),

        ]);
    }






}
