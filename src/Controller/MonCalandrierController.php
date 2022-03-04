<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\CalendarRepository;

class MonCalandrierController extends AbstractController
{
    /**
     * @Route("/mon/calandrier", name="mon_calandrier")
     */
    public function index(): Response
    {
        return $this->render('mon_calandrier/index.html.twig', [
            'controller_name' => 'MonCalandrierController',
        ]);
    }
    /**
     * @Route("/main", name="main")
     */
    public function in(CalendarRepository $calendar)
    {
        $events = $calendar->findAll();

        $rdvs = [];

       foreach($events as $event){
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getStart()->format('Y-m-d H:i:s'),
                'end' => $event->getEnd()->format('Y-m-d H:i:s'),
                'title' => $event->getTitle(),
                'description' => $event->getDescription(),
                'backgroundColor' => $event->getBackgroundColor(),
                'borderColor' => $event->getBorderColor(),
                'textColor' => $event->getTextColor(),
                'allDay' => $event->getAllDay(),
            ];
        }

        $data = json_encode($rdvs);

        return $this->render('mon_calandrier/index.html.twig', compact('data'));
    }
}
