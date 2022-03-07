<?php

namespace App\Entity;

use App\Repository\OffresRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;


/**
 * @ORM\Entity(repositoryClass=OffresRepository::class)
 * @Vich\Uploadable
 */
class Offres
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le champ Nom Offres est vide ! ")
     */
    private $nom;

    /**
     * @ORM\Column(type="date")
     * @Assert\GreaterThan("today", message ="La dade de début ne devrait pas être inférieure à la date du jour ! ")
     */
    private $DateDebutOffres;

    /**
     * @ORM\Column(type="date")
     * @Assert\Expression(
     *     "this.getDateDebutOffres() < this.getDateFinOffre()",
     *     message="La date fin ne doit pas  etre inférieure a la date de début "
     * )
     */
    private $DateFinOffre;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le champ Nom Guide est vide ! ")
     */
    private $NomGuide;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="Le champ Prix  est vide ! ")

     */
    private $Prix;

    /**
     * @ORM\ManyToOne(targetEntity=Evenement::class, inversedBy="Offres")
     */
    private $evenement;
    /**
     * @ORM\Column(type="string", length=255)
     * @var string
     */
    private $image;

    /**
     * @Vich\UploadableField(mapping="product_images", fileNameProperty="image")
     * @var File
     */
    private $imageFile;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="veuillez saisir le nombre d'étoiles")
     */
    private $NbrPlaces;



    public function setImageFile($image = null)
    {
        $this->imageFile = $image;

        // VERY IMPORTANT:
        // It is required that at least one field changes if you are using Doctrine,
        // otherwise the event listeners won't be called and the file is lost
        if ($image) {
            // if 'updatedAt' is not defined in your entity, use another property
            $this->updatedAt = new \DateTime('now');
        }
    }

    public function getImageFile()
    {
        return $this->imageFile;
    }

    public function setImage($image)
    {
        $this->image = $image;
    }

    public function getImage()
    {
        return $this->image;
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDateDebutOffres():?\DateTimeInterface
    {

        return $this->DateDebutOffres;
    }

    public function setDateDebutOffres(\DateTimeInterface $DateDebutOffres): self
    {
        $this->DateDebutOffres = $DateDebutOffres;

        return $this;
    }

    public function getDateFinOffre(): ?\DateTimeInterface
    {
        return $this->DateFinOffre;
    }

    public function setDateFinOffre(\DateTimeInterface $DateFinOffre): self
    {
        $this->DateFinOffre = $DateFinOffre;

        return $this;
    }

    public function getNomGuide(): ?string
    {
        return $this->NomGuide;
    }

    public function setNomGuide(string $NomGuide): self
    {
        $this->NomGuide = $NomGuide;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->Prix;
    }

    public function setPrix(int $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }
    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }
    public function getEvenement(): ?Evenement
    {
        return $this->evenement;
    }

    public function setEvenement (?Evenement $evenement): self
    {
        $this->evenement = $evenement;

        return $this;
    }
    public function getNbrPlaces(): ?int
    {
        return $this->NbrPlaces;
    }

    public function setNbrPlaces(int $NbrPlaces): self
    {
        $this->NbrPlaces =$NbrPlaces;

        return $this;
    }



}
