<?php

namespace App\Entity;

use App\Repository\AgenceVoitureRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;

/**
 * @ORM\Entity(repositoryClass=AgenceVoitureRepository::class)
 * @Vich\Uploadable
 */
class AgenceVoiture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Champ NOM AGENCE EST OBLIGATOIRE ")
     * @Assert\Type("string")
     */

    private $nomagence;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Champ ADRESSE EST OBLIGATOIRE ")
     */
    private $adresse;

    /**
     * @ORM\Column(type="integer")
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage = "non valide ",
     *      maxMessage = "non "
     * )
     */
    private $numtel;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="Champ NOM AGENCE EST OBLIGATOIRE ")
     * @Assert\Type(
     *     type="integer",
     *     message="The value {{ value }} is not a valid {{ type }}."
     * )
     */
    private $nbrvoiture;

    /**
     * @ORM\OneToMany(targetEntity=Voiture::class, mappedBy="agencevoiture" ,cascade={"remove"})
     */
    private $voiture;

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
     * @ORM\Column(type="string", length=100, nullable=true)
     */
    private $color;

    public function getColor(): ?string
    {
        return $this->color;
    }

    public function setColor(?string $color): self
    {
        $this->color = $color;

        return $this;
    }


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

    public function getNomAgence(): ?string
    {
        return $this->nomagence;
    }

    public function setNomAgence(string $nomagence): self
    {
        $this->nomagence = $nomagence;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNumTel(): ?int
    {
        return $this->numtel;
    }

    public function setNumTel(int $numtel): self
    {
        $this->numtel = $numtel;

        return $this;
    }

    public function getNbrVoiture(): ?int
    {
        return $this->nbrvoiture;
    }

    public function setNbrVoiture(int $nbrvoiture): self
    {
        $this->nbrvoiture = $nbrvoiture;

        return $this;
    }
    public function __toString()
    {
        return(string)$this->getNomAgence();
    }
}
