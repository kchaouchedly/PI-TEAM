<?php

namespace App\Controller;

use App\Entity\Blog;
use App\Entity\Commentaire;
use App\Entity\Rating;

use App\Form\BlogType;
use App\Repository\BlogRepository;
use App\Repository\RatingRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\User\UserInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
class BlogController extends AbstractController
{
    /**
     * @Route("/blog", name="blog")
     */
    public function index(): Response
    {
        return $this->render('blog/index.html.twig', [
            'controller_name' => 'BlogController',
        ]);
    }

    /**
     * @Route("/showBlog", name="showBlog")
     */
    public function showBlog()
    {
        $blog= $this->getDoctrine()->getRepository(Blog::class)->findAll();
        return $this->render('blog/showBlog.html.twig', array("showBlogs" =>$blog));
    }

    /**
     * @Route ("/blog/addBlog", name="addBlog")
     */
    public function addAction(Request $request,FlashyNotifier $flashyNotifier)
    {

        $blog = new Blog();
        $form= $this->createForm(BlogType::class,$blog);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();

            $file = $blog->getPhoto();
            $filename= md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $filename);
            $blog->setPhoto($filename);//$blog->setCreator($this->getUser());
            $blog->setEtat('0');

            $em->persist($blog);
            $em->flush();
            $flashyNotifier->primaryDark('Blog ajouté','#');
            return $this->redirectToRoute('list_blog');

        }
        return $this->render('/blog/addBlog.html.twig', array(
            "formBlog"=> $form->createView()
        ));
    }

    /**
     * @Route("/blog/updateBlog/{id}", name="updateBlog")
     */
    public function updateBlog(Request $request, $id)
    {
        $blog = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        $form = $this->createForm(BlogType::class, $blog);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("showBlog");
        }

        return $this->render('blog/updateBlog.html.twig', array("formBlog" => $form->createView()));

    }
#permet a l'admin d'ajouter le blog dans front

    /**
     * @Route ("/blog/admin/addBlog", name="addBlogFront")
     */
    public function addListB(Request $request)
    {
        $blogs=$this->getDoctrine()->getRepository(Blog::class)
            ->findBy([],['id'=>'DESC']);
        return $this->render('blog/blogAdmin.html.twig',['blogs'=> $blogs]
        );
    }

    /**
     * @Route ("/blog/list_blog", name="list_blog")
     */
    public function listpostAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $posts=$em->getRepository(Blog::class)->findBy(array('etat'=>1));
        return $this->render('/blog/listBlog.html.twig', array(
            "posts" =>$posts
        ));

    }
    /**
     * @Route ("/blog/showdetailed/{id}", name="showdetailed")
     */
    public function showdetailedAction($id)
    {    $em = $this->getDoctrine()->getManager();
        $p = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        return $this->render('blog/detailedpost.html.twig', array(
            'title'=>$p->getTitre(),
            'pays'=>$p->getPays(),
            'photo'=>$p->getPhoto(),
            'posts'=>$p,
            'description'=>$p->getDescription(),
            'id'=>$p->getId()
        ));
    }


    /**
     * @Route ("/blog/Admin/showdetailed/{id}", name="showdetailedAdmin")
     */
    public function showdetailedAdmin($id)
    {    $em = $this->getDoctrine()->getManager();
        $p = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        return $this->render('blog/detailedpostAdmin.html.twig', array(
            'title'=>$p->getTitre(),
            'pays'=>$p->getPays(),
            'photo'=>$p->getPhoto(),
            'posts'=>$p,
            'description'=>$p->getDescription(),
            'id'=>$p->getId()
        ));
    }

    /**
     * @Route ("/blog/add_comment/{id}", name="add_comment")
     */
    public function addCommentAction(Request $request,$id)
    {
        $ref = $id;
        $blog = $this->getDoctrine()
            ->getRepository(Blog::class)
            ->findPostByid($id);
        $comment = new Commentaire();
        $comment->setBlog($blog);
        $comment->setContent($request->request->get('comment'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($comment);
        $em->flush();

        $this->addFlash(
            'info', 'Comment published !.'
        );

         $p = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        return $this->render('blog/detailedpost.html.twig', array(
            'title'=>$p->getTitre(),
            'pays'=>$p->getPays(),
            'photo'=>$p->getPhoto(),
            'posts'=>$p,
            'description'=>$p->getDescription(),
            'id'=>$p->getId()
        ));
    }
    /**
     * @Route("/blog/deleteBlog/{id}", name="deleteBlog")
     */
    public function deleteBlog($id,FlashyNotifier $flashy){
        $blog=$this->getDoctrine()->getRepository(Blog::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($blog);
        $em->flush();
        $this->addFlash(
            'info', 'Blog Supprimer !.'
        );
        return $this->redirectToRoute("addBlogFront");
    }

    /**
     * @Route("/blog/PartagerBlog/{id}", name="partagerBlog")
     */
    public function partagerBlog( Request $request, $id, BlogRepository  $blog)
    {
        $blog = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        $blog->setEtat('1');
        $em = $this->getDoctrine()->getManager();
        $em->persist($blog);
        $em->flush();
        return $this->redirectToRoute("addBlogFront");
    }
    /**
     * @Route("/blog/deleteCommentaire/{id}", name="deleteCommentaire")
     */
    public function deleteCommentaire($id,Request $request,FlashyNotifier $flashy)
    {
        $com =$this->getDoctrine()->getRepository(Commentaire::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($com);
        $em->flush();
        $p = $this->getDoctrine()->getRepository(Blog::class)->find($id);
        $request->getSession()
            ->getFlashBag()
            ->add('notice', 'success');
        $referer = $request->headers->get('referer');
        return $this->redirect($referer);

    }
    /**
     * @Route("/blog/addReclamation", name="addReclamation")
     */
    public function sendMail(Request $request, \Swift_Mailer $mailer,FlashyNotifier $flashy)
    {
        $email=  $request->get('email');
        $description=  $request->get('description');
            $username = 'houdakoubaa94@gmail.com';
            // On crée le message
            $message = (new \Swift_Message('Site web: Lets travel'))
                // On attribue l'expéditeur
                ->setFrom($email)
                ->setTo($username)
                ->setBody($description);
               $mailer->send($message);


            $this->addFlash('message', 'Votre message a été transmis, nous vous répondrons dans les meilleurs délais.'); // Permet un message flash de renvoi
        return $this->redirectToRoute('list_blog');
        }

    /**
     * @Route("/addrating ", name="addrating")
     */
    public function searchStudentx(Request $request)
    {
        $ref=$request->get('ref');
        $rat=$request->get('rating');
        $rating =new Rating($ref,$rat);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($rating);
        $entityManager->flush();

    }


}