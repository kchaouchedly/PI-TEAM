<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\RegistrationType;
use Doctrine\ORM\EntityManager;
use App\Controller\Swift_Mailer;
use App\Repository\UserRepository;
use Doctrine\Persistence\ObjectManager;
use Monolog\Handler\SwiftMailerHandler;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Security\Core\Validator\Constraints\UserPassword;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use App\Form\ResetPassType;


class SecurityController extends AbstractController
{  
    /**
     * @Route("/inscription", name="security_registration")
     */
   public function registration(Request $request, EntityManagerInterface $manager, UserPasswordEncoderInterface $encoder,\Swift_Mailer $mailer):Response
    {
       $user= new User();

       $form = $this->createForm(RegistrationType::class, $user);

       $form->handleRequest($request);

       if ($form->isSubmitted() && $form->isValid()) { 
           $hash = $encoder ->encodePassword($user, $user->getPassword());
           $user->setActivationToken(md5(uniqid()));
           $user->setPassword($hash);
           $manager->persist($user);
           $manager->flush();
           $fromName = "ADMINISTRATEUR";
           $fromEmail = "mathyassine28@gmail.com";


           $message = (new \Swift_Message('Nouveau compte'))
    // On attribue l'expéditeur
    ->setFrom([$fromEmail => $fromName])
    // On attribue le destinataire
    ->setTo($user->getEmail())
    // On crée le texte avec la vue
    ->setBody(
        $this->renderView(
            'emails/activation.html.twig', ['token' => $user->getActivationToken()]
        ),
        'text/html'
    )
;
$mailer->send($message);
        
           return $this->redirectToRoute('security_login');
       }
       return $this->render('security/registration.html.twig', [
           'form' => $form ->createView()
       ]);

   }
   /**
    * @Route("/connexion", name="security_login")
    */

   public function login(AuthenticationUtils  $authenticationUtils){
    $error = $authenticationUtils->getLastAuthenticationError();

    return $this->render('security/login.html.twig',['error' => $error]);
    
   }

    /**
    * @Route("/deconnexion", name="security_logout")
    */
    public function logout() {}
    
    /**
     *@Route("/activation/{token}", name="activation")
     */

    public function activation($token, UserRepository $usersRepo) {

        $user = $usersRepo->findOneBy(['activation_token' => $token]);

        if(!$user){

            throw $this->createNotFoundException('Cet utilisateur n\'existe pas');
        }

        $user->setActivationToken(null);
        $manager = $this->getDoctrine()->getManager();
        $manager->persist($user);
        $manager->flush();

        $this->addFlash('message','Vous avez bien activé votre compte');

        return $this->redirecttoRoute('home');

    }
    /**
    *@Route("/oubli-pass", name="app_forgotten_password")
    */

     public function forgottenPass(Request $request,UserRepository $userRepository, \Swift_Mailer $mailer,TokenGeneratorInterface $tokenGenerator):Response{

        $form = $this->createForm(ResetPassType::class);

        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

            $donnees = $form->getData();
            $user = $userRepository->findOneByEmail($donnees['email']);
 
            if($user === null){
                $this->addFlash('danger', 'Cette adresse n\'existe pas');

               return  $this->redirectToRoute('security_login');
            }

            $token = $tokenGenerator->generateToken();

            try{
                $user->setResetToken($token);
                $manager = $this->getDoctrine()->getManager();
                $manager->persist($user);
                $manager->flush();
            }catch(\Exception $e){
                $this->addFlash('warning','Une erreur est survenue:', $e->getMessage());
                $this->redirectToRoute('security_login');

            }

            $url = $this->generateUrl('app_reset_password', array('token' => $token), UrlGeneratorInterface::ABSOLUTE_URL);

            // On génère l'e-mail
            $message = (new \Swift_Message('Mot de passe oublié'))
                ->setFrom('Mathlouthiyassine28@gmail.com')
                ->setTo($user->getEmail())
                ->setBody(
                    "Bonjour,<br><br>Une demande de réinitialisation de mot de passe a été effectuée pour le site Let's Travel. Veuillez cliquer sur le lien suivant : " . $url,
                    'text/html'
                )
            ;
    
            // On envoie l'e-mail
            $mailer->send($message);
            $this->addFlash('message', 'E-mail de réinitialisation du mot de passe envoyé !');

        // On redirige vers la page de login
        return $this->redirectToRoute('security_login');
    }

    // On envoie le formulaire à la vue
    return $this->render('security/forgotten_password.html.twig',['emailForm' => $form->createView()]); }

    /**
 * @Route("/reset_pass/{token}", name="app_reset_password")
 */
public function resetPassword(Request $request, string $token, UserPasswordEncoderInterface $passwordEncoder)
{
    // On cherche un utilisateur avec le token donné
    $user = $this->getDoctrine()->getRepository(User::class)->findOneBy(['reset_token' => $token]);

    // Si l'utilisateur n'existe pas
    if (!$user) {
        // On affiche une erreur
        $this->addFlash('danger', 'Token Inconnu');
        return $this->redirectToRoute('security_login');
    }

    // Si le formulaire est envoyé en méthode post
    if ($request->isMethod('POST')) {
        // On supprime le token
        $user->setResetToken(null);

        // On chiffre le mot de passe
        $user->setPassword($passwordEncoder->encodePassword($user, $request->request->get('password')));

        // On stocke
        $manager = $this->getDoctrine()->getManager();
        $manager->persist($user);
        $manager->flush();

        // On crée le message flash
        $this->addFlash('message', 'Mot de passe mis à jour');

        // On redirige vers la page de connexion
        return $this->redirectToRoute('security_login');
    }else {
        // Si on n'a pas reçu les données, on affiche le formulaire
        return $this->render('security/reset_password.html.twig', ['token' => $token]);
    }
        }
    }




     



