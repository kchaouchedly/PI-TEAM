<?php

namespace App\Entity;

use App\Repository\VolRepository;
<<<<<<< Updated upstream
=======
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
>>>>>>> Stashed changes
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=VolRepository::class)
 */
class Vol
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="le numéro du vol est obligatoire")
     *      @Assert\Length(
     *      min=1,
     *      max=20,
     *      minMessage = "Numéro vol doit etre composer de 1 chiffres au minimum  ",
     *      maxMessage  ="Numéro vol ne doit pas depasser 20 chiffres ",
     * )
     */
    private $NumVol;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\NotBlank(message="la date et l heure du vol sont obligatoires")
     * @Assert\GreaterThan("Today",message="Saisir une date à partir de la date d'aujourd'hui")
     */
    private $DateDepart;

    /**
     * @ORM\Column(type="datetime")
     *  @Assert\NotBlank(message="la date et l heure du vol sont obligatoires")
     * @Assert\GreaterThan("Today",message="Saisir une date à partir de la date d'aujourd'hui")
     * @Assert\Expression("this.getDateDepart() < this.getDateArrive()", message="Veuillez vérifier la date arrive")
     */
    private $DateArrive;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *     min=5,
     *     max=20,
     *     minMessage = "le nom de la ville doit etre supérieur a 5 caracteres",
     *     maxMessage  ="le nom de la ville ne doit pas depasser 20 caracteres",
     *     allowEmptyString = false
     *     )
     * @Assert\Type(
     * type={"alpha"},
     * message="Ville Départ {{ value }} doit contenir seulement des lettres alphabétiques")
     * @Assert\NotBlank(message="veuillez saisir le nom de la ville")
     */
    private $VilleDepart;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *     min=5,
     *     max=20,
     *     minMessage = "le nom de la ville doit etre supérieur a 5 caracteres",
     *     maxMessage  ="le nom de la ville ne doit pas depasser 20 caracteres",
     *     allowEmptyString = false
     *     )
     * @Assert\Type(
     * type={"alpha"},
     * message="Ville Arrivé {{ value }} doit contenir seulement des lettres alphabétiques")
     * @Assert\NotBlank(message="veuillez saisir le nom de la ville")
     */
    private $VilleArrive;

<<<<<<< Updated upstream
=======
    /**
     * @ORM\Column(type="string", length=350)
     * @Assert\NotBlank
     */
    //@Assert\File(mimeTypes={"image/jpeg"})
    private $imageVol;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $typeV;

    /**
     * @ORM\Column(type="integer")
     *@Assert\NotBlank
     */
    private $nbrPlace;

    /**
     * @ORM\OneToMany(targetEntity=Billet::class, mappedBy="vol",cascade={"remove"})
     */
    private $billet;

    public function __construct()
    {
        $this->billet = new ArrayCollection();
    }

>>>>>>> Stashed changes
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumVol(): ?int
    {
        return $this->NumVol;
    }

    public function setNumVol(int $NumVol): self
    {
        $this->NumVol = $NumVol;

        return $this;
    }

    public function getDateDepart(): ?\DateTimeInterface
    {
        return $this->DateDepart;
    }

    public function setDateDepart(\DateTimeInterface $DateDepart): self
    {
        $this->DateDepart = $DateDepart;

        return $this;
    }

    public function getDateArrive(): ?\DateTimeInterface
    {
        return $this->DateArrive;
    }

    public function setDateArrive(\DateTimeInterface $DateArrive): self
    {
        $this->DateArrive = $DateArrive;

        return $this;
    }

    public function getVilleDepart(): ?string
    {
        return $this->VilleDepart;
    }

    public function setVilleDepart(string $VilleDepart): self
    {
        $this->VilleDepart = $VilleDepart;

        return $this;
    }

    public function getVilleArrive(): ?string
    {
        return $this->VilleArrive;
    }

    public function setVilleArrive(string $VilleArrive): self
    {
        $this->VilleArrive = $VilleArrive;

        return $this;
    }
<<<<<<< Updated upstream
=======

    public function getImageVol()
    {
        return $this->imageVol;
    }

    public function setImageVol($imageVol)
    {
        $this->imageVol = $imageVol;

        return $this;
    }

    public function getTypeV(): ?string
    {
        return $this->typeV;
    }

    public function setTypeV(string $typeV): self
    {
        $this->typeV = $typeV;

        return $this;
    }

    public function getNbrPlace(): ?int
    {
        return $this->nbrPlace;
    }

    public function setNbrPlace(int $nbrPlace): self
    {
        $this->nbrPlace = $nbrPlace;

        return $this;
    }

    /**
     * @return Collection|Billet[]
     */
    public function getBillet(): Collection
    {
        return $this->billet;
    }

    public function addBillet(Billet $billet): self
    {
        if (!$this->billet->contains($billet)) {
            $this->billet[] = $billet;
            $billet->setVol($this);
        }

        return $this;
    }

    public function removeBillet(Billet $billet): self
    {
        if ($this->billet->removeElement($billet)) {
            // set the owning side to null (unless already changed)
            if ($billet->getVol() === $this) {
                $billet->setVol(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return(string)$this->getVilleArrive();
    }
>>>>>>> Stashed changes
}
