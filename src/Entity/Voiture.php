<?php

namespace App\Entity;

use App\Repository\VoitureRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=VoitureRepository::class)
 * @Vich\Uploadable
 */
class Voiture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $nbrplace;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $type;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="Champ NOM AGENCE EST OBLIGATOIRE ")
     */
    private $marque;

    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\GreaterThan("today")
     * @Assert\NotBlank(message="Champ  EST OBLIGATOIRE ")
     */
    private $datedebut;

    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\Expression(
     *     "this.getDateDebut() < this.getDateFin()",
     *     message="La date fin ne doit pas être antérieure à la date début"
     * )
     */
    private $datefin;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $prix;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="Champ NOM AGENCE EST OBLIGATOIRE ")
     */
    private $Disponibilite;
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
     * @ORM\ManyToOne(targetEntity=AgenceVoiture::class, inversedBy="voiture")
     */
    private $agencevoiture;
    /**
     * @ORM\Column(type="float", nullable=true)
     */
    private $rate;

    public function setImageFile( $image = null)
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

    public function getNbrplace(): ?int
    {
        return $this->nbrplace;
    }

    public function setNbrplace(?int $nbrplace): self
    {
        $this->nbrplace = $nbrplace;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(?string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(?string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getDatedebut(): ?\DateTimeInterface
    {
        return $this->datedebut;
    }

    public function setDatedebut(?\DateTimeInterface $datedebut): self
    {
        $this->datedebut = $datedebut;

        return $this;
    }

    public function getDatefin(): ?\DateTimeInterface
    {
        return $this->datefin;
    }

    public function setDatefin(?\DateTimeInterface $datefin): self
    {
        $this->datefin = $datefin;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(?int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getDisponibilite(): ?string
    {
        return $this->Disponibilite;
    }

    public function setDisponibilite(?string $Disponibilite): self
    {
        $this->Disponibilite = $Disponibilite;

        return $this;
    }

    public function getAgencevoiture(): ?AgenceVoiture
    {
        return $this->agencevoiture;
    }

    public function setAgencevoiture(?AgenceVoiture $agencevoiture): self
    {
        $this->agencevoiture = $agencevoiture;

        return $this;
    }

    public function getRate(): ?float
    {
        return $this->rate;
    }

    public function setRate(?float $rate): self
    {
        $this->rate = $rate;

        return $this;
    }

}
