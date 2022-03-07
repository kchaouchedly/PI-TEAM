<?php

namespace App\Controller;

use App\Entity\Billet;
use App\Entity\Chambre;
use App\Entity\Hotel;
use App\Entity\Student;
use App\Entity\Vol;
use App\Form\BilletType;
use App\Form\HotelType;
use App\Form\SearcheStudentType;
use App\Form\SearchHotelType;
use App\Form\SearchHType;
use App\Repository\ChambreRepository;
use App\Repository\HotelRepository;
use App\Repository\StudentRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Component\Serializer\Normalizer\NormalizableInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Knp\Component\Pager\PaginatorInterface;

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
     * @Route("/weather", name="weather")
     */
    public function weather(): Response
    {
        return $this->render('hotel/weather.html.twig', [
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
            $file = $hotel->getImageHotel();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('images_directory'), $fileName);

            $em = $this->getDoctrine()->getManager();
            $hotel->setImageHotel($fileName);
            $hotel->setNbrChambre(0);

            $em->persist($hotel);
            $em->flush();
            return $this->redirectToRoute("showHotel");
        }

        return $this->render('hotel/addHotel.html.twig', array("formHotel" => $form->createView()));
    }


    /**
     * @Route("/deleteHotel/{id}", name="deleteHotel")
     */
    public function deleteHotel($id)
    {
        $hotel = $this->getDoctrine()->getRepository(Hotel::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($hotel);
        $em->flush();
        return $this->redirectToRoute("showHotel");
    }

    /**
     * @Route("/updateHotel/{id}", name="updateHotel")
     */
    public function updateHotel(Request $request, $id)
    {
        $hotel = $this->getDoctrine()->getRepository(Hotel::class)->find($id);
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $hotel->getImageHotel();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('images_directory'), $fileName);
            $hotel->setImageHotel($fileName);
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("showHotel");
        }

        return $this->render('hotel/updateHotel.html.twig', array("formHotel" => $form->createView()));

    }

    /**
     * @Route("/showHotel", name="showHotel")
     */
    public function showHotel(Request $request)
    {
        $hotels = $this->getDoctrine()->getRepository(Hotel::class)->findAll();
        $form = $this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $NomHotel = $form['NomHotel']->getData();
            $result = $this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig', array("showHotels" => $result, "formHotel" => $form->createView()));

        }
        return $this->render('hotel/showHotel.html.twig', array("showHotels" => $hotels, "formHotel" => $form->createView()));
    }


    /**
     * @Route("/triHotelCode",name="triHotelCode")
     */
    public function triHotelCode(HotelRepository $s, Request $request)
    {
        $form = $this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $NomHotel = $form['NomHotel']->getData();
            $result = $this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig', array("showHotels" => $result, "formHotel" => $form->createView()));

        }

        $hotelsByCode = $s->orderByCodeH();
        return $this->render("hotel/showHotel.html.twig",
            array("showHotels" => $hotelsByCode, "formHotel" => $form->createView()));

    }

    /**
     * @Route("/triHotelEtoiles",name="triHotelEtoiles")
     */
    public function triHotelEtoiles(HotelRepository $s, Request $request)
    {
        $form = $this->createForm(SearchHType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $NomHotel = $form['NomHotel']->getData();
            $result = $this->getDoctrine()->getRepository(Hotel::class)->searchHotel($NomHotel);
            return $this->render('hotel/showHotel.html.twig', array("showHotels" => $result, "formHotel" => $form->createView()));

        }
        $hotelsBynbrE = $s->orderByNombreEtoiles();

        return $this->render("hotel/showHotel.html.twig",
            array("showHotels" => $hotelsBynbrE, "formHotel" => $form->createView()));

    }

    /**
     * @Route("/imprimHotel", name="imprimHotel")
     */
    public function imrpimHotel()
    {

        $repository = $this->getDoctrine()->getRepository(Hotel::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $hotels = $repository->findAll();

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

    /**
     * @Route("/listChambreByHotel/{id}", name="listChambreByHotel")
     */
    public function listChambreByHotel(ChambreRepository $repository, $id)
    {
        $chambres = $repository->listChambreByHotel($id);
        return $this->render("chambre/listChambre.html.twig", array("listChambres" => $chambres));
    }


    /**
     * @Route("/listChambreByHotell/{id}", name="listChambreByHotell")
     */
    public function listChambreByHotell(ChambreRepository $repository, $id,PaginatorInterface $paginator,Request $request)
    {
        $chambres = $repository->listChambreByHotel($id);
        $chambres = $paginator->paginate(
            $chambres,
            $request->query->getInt('page',1),
            2
        );
        return $this->render("chambre/showChambre.html.twig", array("showChambres" => $chambres));
    }




    /**
     * @Route("/AllHotels", name="AllHotels")
     */
    public function AllHotels(NormalizerInterface $normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Hotel::class);
        $hotels = $repository->findAll();
        $jsonContent = $normalizer->normalize($hotels, 'json', ['groups' => 'post:read']);

        //return $this->render('hotel/allHotelsJSON.html.twig',['data'=>$jsonContent]);

        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/addHotelJSON/new", name="addHotelJSON")
     */
    public function addHotelJSON(Request $request, NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $hotel = new Hotel();
        $hotel->setNomHotel($request->get('NomHotel'));
        $hotel->setCodeH($request->get('CodeH'));
        $hotel->setNbrChambre($request->get('NbrChambre'));
        $hotel->setAdresse($request->get('Adresse'));
        $hotel->setNumTel($request->get('NumTel'));
        $hotel->setNbrEtoiles($request->get('NbrEtoiles'));
        $hotel->setEmail($request->get('email'));
        $hotel->setImageHotel($request->get('ImageHotel'));

        $em->persist($hotel);
        $em->flush();

        $jsonContent = $normalizer->normalize($hotel, 'json', ['groups' => 'post:read']);
        return new Response(json_encode($jsonContent));


    }

    /**
     * @Route("/hotelStat", name="hotelStat")
     */
    public function indexAction()
    {
        $repository = $this->getDoctrine()->getRepository(Hotel::class);
        $hotel = $repository->findAll();

        $nbIb = 0;
        $nbGol= 0;
        $nbTab = 0;
        $nbBr=0;
        $nbLaB=0;
        $nbRam=0;
        $nbRoy=0;
        $nbF=0;

        foreach ($hotel as $hotel) {

            if ( ($hotel->getNomHotel() == "iberostar")  )  :
                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbIb += 1;
                    endif;
                }



            elseif ( ($hotel->getNomHotel() == "Goldanyasmin")  )  :

                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbGol += 1;
                    endif;
                }

            elseif ( ($hotel->getNomHotel() == "TabarcaThalasou")  ):

                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbTab+= 1;
                    endif;
                }


            elseif ( ($hotel->getNomHotel() == "BravoDjerba") && ($hotel->getChambre()=="Non Disponible") ):
                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbBr += 1;
                    endif;
                }

            elseif ( ($hotel->getNomHotel() == "LaBadira")  ):
                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbLaB += 1;
                    endif;
                }

            elseif ( ($hotel->getNomHotel() == "RamadaPlaza")  ):
                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbRam += 1;
                    endif;
                }


            elseif ( ($hotel->getNomHotel() == "RoyalThalassa")  ):
                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbRoy += 1;
                    endif;
                }


            elseif ( ($hotel->getNomHotel() == "forSeasonTes") ) :

                foreach ($hotel->getChambre() as $ch){
                    if ($ch->getDispo()=="Non Disponible"):
                        $nbF += 1;
                    endif;
                }

            endif;

        }


        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Hotels visités', 'STATISTIQUES'],
                ['iberostar', $nbIb],
                ['Goldanyasmin', $nbGol],
                ['TabarcaThalasou', $nbTab],
                ['BravoDjerba', $nbBr],
                ['LaBadira', $nbLaB],
                ['RamadaPlaza', $nbRam],
                ['RoyalThalassa', $nbRoy],
                ['forSeasonTes', $nbF]

            ]
        );
        $pieChart->getOptions()->setTitle('Top Hotels visités');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(1250);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(50);

        return $this->render('hotel/stat.html.twig', array('piechart' => $pieChart));
    }
}