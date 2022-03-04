<?php

namespace App\Controller;

use App\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ContactevenementController extends AbstractController
{
    /**
     * @Route("/contactevenement", name="contactevenement")
     */
    public function index(): Response
    {
        return $this->render('contactevenement/index.html.twig', [
            'controller_name' => 'ContactevenementController',
        ]);
    }
    /**
     * @Route("/contactevenementmail", name="contactevenementmail")
     */
    public function mail(Request $req, \Swift_Mailer $mailer){
   $form=$this->createForm(ContactType::class );
   $form->handleRequest($req);
        if ($form->isSubmitted()&& $form->isValid()) {
            $contact=$form->getData();
            //dd($contact);
          $message = (new \Swift_Message( 'new contact'))
              ->setFrom($contact['email'])
              ->setTo('kechaou.chedly@gmail.com')->setBody(
                  $this->renderView('emails/contact.html.twig', compact('contact')),
              'text/html'
              );
                $mailer->send($message);
                $this->addFlash('message','message envoye');




        }

        return $this->render('contactevenement/index.html.twig', [
            'contactform' => $form->createView()
        ]);
    }

}
