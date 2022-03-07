<?php

namespace App\Controller;


use App\Form\EmailleType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;

class EmailController extends AbstractController
{
    /**
     * @Route("/email", name="email")
     */
    public function index(): Response
    {
        return $this->render('email/index.html.twig', [
            'controller_name' => 'EmailController',
        ]);
    }

    /**
     * @Route("/contactmail", name="contactmail")
     */
    public function mail(Request $req, \Swift_Mailer $mailer){
        $form=$this->createForm(EmailleType::class );
        $form->handleRequest($req);
        if ($form->isSubmitted()&& $form->isValid()) {
            $contact=$form->getData();
            //dd($contact);
            $message = (new \Swift_Message( 'new contact'))
                ->setFrom($contact['email'])
                ->setTo('omarcharfii12@gmail.com')->setBody(
                    $this->renderView('contact/email.html.twig', compact('contact')),
                    'text/html'
                );
            $mailer->send($message);
            $this->addFlash('message','message envoye');




        }

        return $this->render('email/index.html.twig', [
            'contactform' => $form->createView()
        ]);
    }
}
