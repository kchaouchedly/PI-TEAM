<?php

namespace App\Entity;

use App\Repository\EvenementRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=EvenementRepository::class)
 * @Vich\Uploadable
 */
class Evenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("ev")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Selectionnez le type  ! ")
     * @Groups("ev")
     */
    public $Type;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Le champ Nom evenement est vide ! ")
     * @Groups("ev")
     */
    private $NomEvenement;

    /**
     * @ORM\Column(type="date")
     * @Assert\GreaterThan("today", message ="La dade de début ne devrait pas être inférieure à la date du jour ! ")
     * @Groups("ev")
     */
    public $DateDebut;

    /**
     * @ORM\Column(type="date")
     * @Assert\Expression(
     *     "this.getDateDebut() < this.getDateFin()",
     *     message="La date fin ne doit pas  etre inférieure a la date de début "
     * )
     * @Groups("ev")
     */
    public $DateFin;


    /**
     * @ORM\Column(type="string", length=255)
     * *@Assert\NotBlank(message="Le champ LIEUX est vide ! ")
     * @Groups("ev")
     */
    private $Lieux;
    /**
     * @ORM\Column(type="string", length=255)
     * @var string
     * @Groups("ev")
     */
    private $image;

    /**
     * @Vich\UploadableField(mapping="product_images", fileNameProperty="image")
     * @var File
     * @Groups("ev")
     */
    private $imageFile;
    /**
     * @ORM\OneToMany(targetEntity=Offres::class, mappedBy="evenement",cascade={"remove"})
     */
    private $Offres;
    /**
     * @ORM\OneToMany(targetEntity=ReservationE::class, mappedBy="evenement")
     */
    private $reservationE;

    /**
     * @ORM\Column(type="string", length=100, nullable=true)
     */
    private $color;







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

    public function getType(): ?string
    {
        return $this->Type;
    }

    public function setType(string $Type): self
    {
        $this->Type = $Type;

        return $this;
    }

    public function getNomEvenement(): ?string
    {
        return $this->NomEvenement;
    }

    public function setNomEvenement(string $NomEvenement): self
    {
        $this->NomEvenement = $NomEvenement;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->DateDebut;
    }

    public function setDateDebut(\DateTimeInterface $DateDebut): self
    {
        $this->DateDebut = $DateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->DateFin;
    }

    public function setDateFin(\DateTimeInterface $DateFin): self
    {
        $this->DateFin = $DateFin;

        return $this;
    }

    public function getLieux(): ?string
    {
        return $this->Lieux;
    }

    public function setLieux(string $Lieux): self
    {
        $this->Lieux = $Lieux;

        return $this;
    }

    public function __toString()
    {
        return(string)$this->getType();
    }

    public function getColor(): ?string
    {
        return $this->color;
    }

    public function setColor(?string $color): self
    {
        $this->color = $color;

        return $this;
    }


}
