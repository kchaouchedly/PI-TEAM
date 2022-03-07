<?php

namespace App\Controller;

use App\Entity\Billet;
use App\Entity\Hotel;
use App\Entity\Vol;
use App\Form\BilletType;
use App\Form\SearchHType;
use App\Form\SearchVolType;
use App\Form\VolType;
use App\Repository\BilletRepository;
use App\Repository\ChambreRepository;
use App\Repository\HotelRepository;
use App\Repository\VolRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\TreeMapChart\TreeMapChartOptions;


class VolController extends AbstractController
{
    /**
     * @Route("/vol", name="vol")
     */
    public function index(): Response
    {
        return $this->render('vol/index.html.twig', [
            'controller_name' => 'VolController',
        ]);
    }

    /**
     * @Route("/listVol", name="listVol")
     */
    public function listVol(Request $request){
        $vols = $this->getDoctrine()->getRepository(Vol::class)->findAll();
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        return $this->render('vol/listVol.html.twig',array("listVols"=>$vols,"formVol"=>$form->createView()));
    }
    /**
     * @Route("/showVol", name="showVol")
     */
    public function showVol(){
        $vols = $this->getDoctrine()->getRepository(Vol::class)->findAll();

        return $this->render('vol/showVol.html.twig',array("showVols"=>$vols));
    }

    /**
     * @Route("/addVol", name="addVol")
     */
    public function addVol(Request $request)
    {
        $vol=new Vol();
        $form=$this->createForm(VolType::class,$vol);
        $form->handleRequest($request);
        if($form->isSubmitted()&&($form->isValid())){
            $file = $vol->getImageVol();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);


            $em=$this->getDoctrine()->getManager();
            $vol->setImageVol($fileName);
            $em->persist($vol);
            $em->flush();
            return $this->redirectToRoute("listVol");
        }

        return $this->render('vol/addVol.html.twig',array("formVol"=>$form->createView()));

    }

    /**
     * @Route("/deleteVol/{id}", name="deleteVol")
     */
    public function deleteVol($id){
        $vol=$this->getDoctrine()->getRepository(Vol::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($vol);
        $em->flush();
        return $this->redirectToRoute("listVol");
    }


    /**
     * @Route("/updateVol/{id}", name="updateVol")
     */
    public function updateVol(Request $request,$id)
    {
        $vol = $this->getDoctrine()->getRepository(Vol::class)->find($id);
        $form=$this->createForm(VolType::class,$vol);
        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){

            $file = $vol->getImageVol();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('images_directory'),$fileName);
            $em=$this->getDoctrine()->getManager();
            $vol->setImageVol($fileName);
            $em->flush();
            return $this->redirectToRoute("listVol");
        }

        return $this->render('vol/updateVol.html.twig',array("formVol"=>$form->createView()));

    }
    /**
     * @Route("/triVolNum",name="triVolNum")
     */
    public function triVolNum(VolRepository $s,Request $request){
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        $volByNum= $s->orderByNum();
        return $this->render("vol/listVol.html.twig",
            array("listVols"=>$volByNum,"formVol"=>$form->createView()));

    }

    /**
     * @Route("/triVolDatedep",name="triVolDatedep")
     */
    public function triVolDatedep(VolRepository  $s,Request $request){
        $form=$this->createForm(SearchVolType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $NumVol=$form['NumVol']->getData();
            $result=$this->getDoctrine()->getRepository(Vol::class)->searchVol($NumVol);
            return $this->render('vol/listVol.html.twig',array("listVols"=>$result,"formVol"=>$form->createView()));

        }
        $volByDate= $s->orderByDateDep();
        return $this->render("vol/listVol.html.twig",
            array("listVols"=>$volByDate,"formVol"=>$form->createView()));

    }

    /**
     * @Route("/imprimVol", name="imprimVol")
     */
    public function imprimVol()
    {

        $repository=$this->getDoctrine()->getRepository(Vol::class);
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $vols=$repository->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('vol/volPdf.html.twig', array("listVols" => $vols));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Vol.pdf", [
            "Attachment" => true
        ]);


    }

    /**
     * @Route("/listBilletByVol/{id}", name="listBilletByVol")
     */
    public function listBilletByVol(BilletRepository $repository,$id,PaginatorInterface $paginator,Request $request)
    {
        $billets=$repository->listBilletByVol($id);
        $billets = $paginator->paginate(
            $billets,
            $request->query->getInt('page',1),
            2
        );
        return $this->render("billet/listBillet.html.twig",array("listBillets"=>$billets));
    }


    /**
     * @Route("/listBilletByVoll/{id}", name="listBilletByVoll")
     */
    public function listBilletByVoll(BilletRepository $repository,$id)
    {
        $billets=$repository->listBilletByVol($id);
        return $this->render("billet/showBillet.html.twig",array("showBillets"=>$billets));
    }


    /**
     * @Route("/volStat", name="volStat")
     */
    public function indexAction()
    {
        $repository = $this->getDoctrine()->getRepository(Vol::class);
        $vol = $repository->findAll();
        $em = $this->getDoctrine()->getManager();

        $nbP = 0;
        $nbD= 0;
        $nbT = 0;
        $nbC=0;
        $nbA=0;
        $nbEs=0;
        $nbEg=0;
        $nbIt=0;

        foreach ($vol as $vol) {

            if ($vol->getVilleArrive() == "Paris")  :
                $nbP += 1;


            elseif ($vol->getVilleArrive() == "Dubai")  :

                $nbD += 1;
            elseif ($vol->getVilleArrive() == "Turquie"):

                $nbT += 1;
            elseif ($vol->getVilleArrive() == "Chine"):

                $nbC += 1;
            elseif ($vol->getVilleArrive() == "Allemagne"):

                $nbA += 1;
            elseif ($vol->getVilleArrive() == "Espagne"):

                $nbEs += 1;

            elseif ($vol->getVilleArrive() == "Egypte"):

                $nbEg += 1;

            else :
                $nbIt += 1;

            endif;

        }


        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['Villes visités', 'STATISTIQUES'],
                ['Paris', $nbP],
                ['Dubai', $nbD],
                ['Egypte', $nbEg],
                ['Chine', $nbC],
                ['Italie', $nbIt],
                ['Espagne', $nbEs],
                ['Allemagne', $nbA],
                ['Turquie', $nbT]

            ]
        );
        $pieChart->getOptions()->setTitle('Top Pays visités');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(1250);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(50);

        return $this->render('vol/stat.html.twig', array('piechart' => $pieChart));
    }




}
