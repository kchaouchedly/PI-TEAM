<?php

namespace App\Controller;

use App\Entity\Activites;
use App\Entity\Billet;
use App\Entity\Chambre;
use App\Entity\Hotel;
use App\Entity\ResChambre;
use App\Form\ChambreType;
use App\Form\HotelType;
use App\Form\ResChambreType;
use App\Form\SearchBilletType;
use App\Form\SearchChambreType;
use App\Repository\BilletRepository;
use App\Repository\ChambreRepository;
use App\Repository\ResChambreRepository;
use Knp\Component\Pager\Pagination\SlidingPagination;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

class ChambreController extends AbstractController
{
    /**
     * @Route("/chambre", name="chambre")
     */
    public function index(): Response
    {
        return $this->render('chambre/index.html.twig', [
            'controller_name' => 'ChambreController',
        ]);
    }

    /**
     * @Route("/listChambre", name="listChambre")
     */
    public function listChambre(Request $request)
    {
        $chambres = $this->getDoctrine()->getRepository(Chambre::class)->findAll();
        $form=$this->createForm(SearchChambreType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {

            $NumCh=$form['NumCh']->getData();
            $result=$this->getDoctrine()->getRepository(Chambre::class)->searchChambre($NumCh);
            return $this->render('chambre/listChambre.html.twig',array("listChambres"=>$result,"formChambre"=>$form->createView()));

        }
        return $this->render('chambre/listChambre.html.twig', array("listChambres" => $chambres,"formChambre"=>$form->createView()));
    }


    /**
     * @Route("/showChambre", name="showChambre")
     */
    public function showChambre(PaginatorInterface $paginator,Request $request):Response
    {
        $chambres = $this->getDoctrine()->getRepository(Chambre::class)->findAll();
        $chambres = $paginator->paginate(
            $chambres,
            $request->query->getInt('page',1),
            4
        );
        return $this->render('chambre/showChambre.html.twig', array("showChambres" => $chambres));
    }

    /**
     * @Route("/addChambre", name="addChambre")
     */
    public function addChambre(Request $request)
    {
        $chambre = new Chambre();
        $form = $this->createForm(ChambreType::class, $chambre);
        $form->handleRequest($request);
        if ($form->isSubmitted() && ($form->isValid())) {
            $chambre->getHotel()->setNbrChambre(($chambre->getHotel()->getNbrChambre()) + 1 );
            $file = $chambre->getImageCh();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);

            $em = $this->getDoctrine()->getManager();
            $chambre->setImageCh($fileName);
            $em->persist($chambre);
            $em->flush();
            return $this->redirectToRoute("listChambre");
        }

        return $this->render('chambre/addChambre.html.twig',array("formChambre"=>$form->createView()));
    }

    /**
     * @Route("/deleteChambre/{id}", name="deleteChambre")
     */
    public function deleteChambre($id){
        $chambre=$this->getDoctrine()->getRepository(Chambre::class)->find($id);

        $chambre->getHotel()->setNbrChambre(($chambre->getHotel()->getNbrChambre()) - 1 );
        $em=$this->getDoctrine()->getManager();
        $em->remove($chambre);
        $em->flush();
        return $this->redirectToRoute("listChambre");
    }

    /**
     * @Route("/updateChambre/{id}", name="updateChambre")
     */
    public function updateChambre(Request $request,$id)
    {
        $chambre = $this->getDoctrine()->getRepository(Chambre::class)->find($id);
        $form=$this->createForm(ChambreType::class,$chambre);
        $form->handleRequest($request);
        if( $form->isSubmitted() && $form->isValid() ){
            $chambre->getHotel()->setNbrChambre(($chambre->getHotel()->getNbrChambre()) + 1 );
            $file = $chambre->getImageCh();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);

            $em=$this->getDoctrine()->getManager();
            $chambre->setImageCh($fileName);
            $em->flush();
            return $this->redirectToRoute("listChambre");
        }

        return $this->render('chambre/updateChambre.html.twig',array("formChambre"=>$form->createView()));

    }
    /**
     * @Route("/triChambreBloc",name="triChambreBloc")
     */
    public function triChambreBloc(ChambreRepository $s ,Request $request){
        $form=$this->createForm(SearchChambreType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumCh=$form['NumCh']->getData();
            $result=$this->getDoctrine()->getRepository(Chambre::class)->searchChambre($NumCh);
            return $this->render('chambre/listChambre.html.twig',array("listChambres"=>$result,"formChambre"=>$form->createView()));

        }
        $chambreByBloc= $s->orderByBloc();
        return $this->render("chambre/listChambre.html.twig",
            array("listChambres"=>$chambreByBloc,"formChambre"=>$form->createView()));

    }

    /**
     * @Route("/triChambrePrix",name="triChambrePrix")
     */
    public function triChambrePrix(ChambreRepository $s,Request $request){
        $form=$this->createForm(SearchChambreType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumCh=$form['NumCh']->getData();
            $result=$this->getDoctrine()->getRepository(Chambre::class)->searchChambre($NumCh);
            return $this->render('chambre/listChambre.html.twig',array("listChambres"=>$result,"formChambre"=>$form->createView()));

        }
        $chambreBYPrix= $s->orderByPrix();
        return $this->render("chambre/listChambre.html.twig",
            array("listChambres"=>$chambreBYPrix,"formChambre"=>$form->createView()));

    }

    /**
     * @Route("/dispoChambre/{id}", name="dispoChambre")
     */
    public function dispoChambre(ChambreRepository $chambreRepository ,$id,FlashyNotifier $flashyNotifier,Request $request,\Swift_Mailer $mailer )
    {
        $reservation = new ResChambre();
        $id=$request->get("id");

        $ch=$chambreRepository->find($id);
        $chH=$chambreRepository->find($id);

        $form = $this->createForm (ResChambreType::class, $reservation);
        $form -> handleRequest($request);
        $contact=$form->getData();
        $reservation->setTarif(   ($reservation->getNbrJ())* ($ch->getPrix()));
        if ($form->isSubmitted() && $form->isValid()){


            foreach ($chH->getResChambre() as $chH){
                if ($chH->getDateRes() ==$reservation->getDateRes() ):
                    $flashyNotifier->primaryDark('Chambre Deja reservé','#');
                    return $this->redirectToRoute('listHotel');
                endif;
            }

            $ch->setDispo("Non Disponible");
            $reservation->setChambre($ch);
            $em= $this->getDoctrine()->getManager();
            $em->persist ($reservation);
            $em->flush();

            $message = (new \Swift_Message('New'))

                ->setFrom('bechir.pastore@gmail.com')

                ->setTo('ferjanihejer53@gmail.com')

                ->setSubject('Réservation chambre ajouté !')


                ->setBody(
 $this->renderView('emails/reservationchambre.html.twig', compact('contact')),
              'text/html'
              );

            $mailer->send($message);


            $flashyNotifier->primaryDark('Chambre Reservée','#');
            return $this->redirectToRoute('listHotel');

        }

        return $this->render('reservation_chambre/AjoutResChambres.html.twig',array("formResChambre"=>$form->createView()));

    }








}
