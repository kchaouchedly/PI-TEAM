<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\EdituserType;
use App\Repository\UserRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

class AdminController extends AbstractController
{

  

  /**
     * @Route("/admin", name="admin")
     *@IsGranted("ROLE_ADMIN")
     */
    public function index(): Response
    {
        return $this->render('admin/index.html.twig', [
            'controller_name' => 'AdminController',
        ]);
    }
    /**
     * @return Reponse
     * @Route("/admin/listU", name="userlist")
     * @IsGranted("ROLE_ADMIN")
     */
    public function afficher(UserRepository $rep){
        $users=$rep->findAll();
        return $this->render('/admin/listusers.html.twig', [
            'users' => $users,
        ]);
    }
     /**
     * @return Reponse
     * @Route("/admin/listU/delete/{id}", name="userdelete")
     * @IsGranted("ROLE_ADMIN")
     */

    public function Delete($id,UserRepository $rep){
        $user=$rep->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();

        return $this->redirectToRoute('userlist');
    }

        /**
     * @Route("/admin/update/{id}",name="userupdate")
     * @IsGranted("ROLE_ADMIN")
     */
    public function Update($id,UserRepository $rep,Request $request, User $user){
        
        $user=$rep->find($id);

        $form=$this->createform(EdituserType::class,$user);
        

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
        $em=$this->getDoctrine()->getManager();
        $em->flush();
            return $this->redirectToRoute('userlist');

        }return $this->render("admin/update.html.twig", [
            'userForm'=>$form->createView(),
            
        ]);

     }

      /**
     * @Route("/getuser",name="getuser")
     */
     public function afficherjson(NormalizerInterface $Normalizer){

        
        $rep = $this ->getDoctrine()->getRepository(User::class);
        $users=$rep->findAll();
        $jsonContent = $Normalizer->normalize($users, 'json',['groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
     }

}
