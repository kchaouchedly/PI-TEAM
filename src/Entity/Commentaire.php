<?php

namespace App\Entity;

use App\Repository\CommentaireRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass="App\Repository\CommentaireRepository")
 * @ORM\Table(name="commentaire")
 * @ORM\HasLifecycleCallbacks()
 */
class Commentaire
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="content", type="text", unique=false)
     */
    private $content;


    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return string
     */
    public function getContent()
    {
        return $this->content;
    }

    /**
     * @param string $content
     */
    public function setContent($content)
    {
        $this->content = $content;
    }

    /**
     * @Assert\DateTime
     */
    private $posted_at;

    /**
     * @ORM\ManyToOne(targetEntity=Blog::class, inversedBy="commentaires")
     * @ORM\JoinColumn(name="blog_id", referencedColumnName="id")
     */
    private $blog;



    /**
     * @ORM\PrePersist
     */

    public function setPostedAt()
    {
        $this->posted_at = new \DateTime('now');
    }

    /**
     * @return mixed
     */
    public function getBlog()
    {
        return $this->blog;
    }
    /**
     * @param mixed $blog
     */
    public function setBlog($blog)
    {
        $this->blog = $blog;
    }



}
