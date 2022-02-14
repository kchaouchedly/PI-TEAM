<?php

namespace App\Controller;

use App\Entity\Billet;
use App\Entity\Hotel;
use App\Entity\Student;
use App\Form\BilletType;
use App\Form\HotelType;
use App\Form\SearcheStudentType;
use App\Form\SearchHotelType;
use App\Form\SearchHType;
use App\Repository\HotelRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class HotelController extends AbstractController
{
    /**
     * @Route("/hotel", name="hotel")
     */
    public function index(): Response
    {
        return $this->render('hotel/index.html.twig', [
            'controller_name' => 'HotelController',
        ]);
    }


    /**
     * @Route("/listHotel", name="listHotel")
     */
    public function listHotel()
    {
        $hotels = $this->getDoctrine()->getRepository(Hotel::class)->findAll();
        return $this->render('hotel/listHotel.html.twig', array("listHotels" => $hotels));
    }


    /**
     * @Route("/addHotel", name="addHotel")
     */
    public function addHotel(Request $request)
    {
        $hotel = new Hotel();
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);
        if ($form->isSubmitted() && ($form->isValid())) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($hotel);
            $em->flush();
            return $this->redirectToRoute("showHotel");
        }

        return $this->render('hotel/addHotel.html.twig',array("formHotel"=>$form->createView()));
    }


    /**
     * @Route("/deleteHotel/{id}", name="deleteHotel")
     */
    public function deleteHotel($id){
        $hotel=$this->getDoctrine()->getRepository(Hotel::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($hotel);
        $em->flush();
        return $this->redirectToRoute("showHotel");
    }

    /**
     * @Route("/updateHotel/{id}", name="updateHotel")
     */
    public function updateHotel(Request $request,$id)
    {
        $hotel = $this->getDoctrine()->getRepository(Hotel::class)->find($id);
        $form=$this->createForm(HotelType::class,$hotel);
        $form->handleRequest($request);
        if( $form->isSubmitted() && $form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("showHotel");
        }

        return $this->render('hotel/updateHotel.html.twig',array("formHotel"=>$form->createView()));

    }

    /**
     * @Route("/showHotel", name="showHotel")
     */
    public function showHotel(Request $request)
    {
        $hotels = $this->getDoctrine()->getRepository(Hotel::class)->findAll();
        $form=$this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NomHotel=$form['NomHotel']->getData();
            $result=$this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig',array("showHotels"=>$result,"formHotel"=>$form->createView()));

        }
        return $this->render('hotel/showHotel.html.twig', array("showHotels" => $hotels,"formHotel"=>$form->createView()));
    }






    /**
     * @Route("/triHotelCode",name="triHotelCode")
     */
    public function triHotelCode(HotelRepository $s,Request $request){
        $form=$this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NomHotel=$form['NomHotel']->getData();
            $result=$this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig',array("showHotels"=>$result,"formHotel"=>$form->createView()));

        }

        $hotelsByCode= $s->orderByCodeH();
        return $this->render("hotel/showHotel.html.twig",
            array("showHotels"=>$hotelsByCode,"formHotel"=>$form->createView()));

    }

    /**
     * @Route("/triHotelEtoiles",name="triHotelEtoiles")
     */
    public function triHotelEtoiles(HotelRepository  $s,Request $request){
        $form=$this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NomHotel=$form['NomHotel']->getData();
            $result=$this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig',array("showHotels"=>$result,"formHotel"=>$form->createView()));

        }
        $hotelsBynbrE= $s->orderByNombreEtoiles();

        return $this->render("hotel/showHotel.html.twig",
            array("showHotels"=>$hotelsBynbrE,"formHotel"=>$form->createView()));

    }
    /**
     * @Route("/imprimHotel", name="imprimHotel")
     */
    public function imrpimHotel()
    {

        $repository=$this->getDoctrine()->getRepository(Hotel::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $hotels=$repository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('hotel/HotelPdf.html.twig', array("showHotels" => $hotels));

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
