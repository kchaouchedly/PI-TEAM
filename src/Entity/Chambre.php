<?php

namespace App\Entity;

use App\Repository\ChambreRepository;
<<<<<<< Updated upstream
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
=======
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Annotation\Groups;
>>>>>>> Stashed changes

/**
 * @ORM\Entity(repositoryClass=ChambreRepository::class)
 */
class Chambre
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="le numéro de la chambre est obligatoire")
<<<<<<< Updated upstream
     *      @Assert\Length(
     *      max=20,
     *      maxMessage  ="Numéro chambre ne doit pas depasser 20 chiffres ",
     * )
=======

>>>>>>> Stashed changes
     */
    private $NumCh;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="le nombre de lits de la chambre est obligatoire")
     */
    private $NbrLits;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champ est obligatoire")
     */
    private $vue;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="Ce champ est obligatoire")
     */
    private $Etage;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotBlank(message="le prix de la chambre est obligatoire")
     * @Assert\GreaterThanOrEqual(
<<<<<<< Updated upstream
     *     value = 150,message="Le prix minimale de la chambre est  150Dt"
=======
     *     value = 50,message="Le prix minimale de la chambre est  150Dt"
>>>>>>> Stashed changes
     * )
     * @Assert\LessThanOrEqual(
     *     value = 2000,message="Le prix de la chambre ne doit pas depasser 2000Dt"
     * )
     */
    private $Prix;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Ce champ est obligatoire")
     */
    private $Bloc;

<<<<<<< Updated upstream
=======
    /**
     * @ORM\Column(type="string", length=255)
     */
    private $dispo;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="ajouter une image")
     */
    private $ImageCh;

    /**
     * @ORM\ManyToOne(targetEntity=Hotel::class, inversedBy="chambre")
     */
    private $hotel;

    /**
     * @ORM\OneToMany(targetEntity=ResChambre::class, mappedBy="chambre",cascade={"remove"})
     */
    private $resChambre;

    public function __construct()
    {
        $this->resChambres = new ArrayCollection();
        $this->resChambre = new ArrayCollection();
    }

>>>>>>> Stashed changes
    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumCh(): ?int
    {
        return $this->NumCh;
    }

    public function setNumCh(int $NumCh): self
    {
        $this->NumCh = $NumCh;

        return $this;
    }

    public function getNbrLits(): ?int
    {
        return $this->NbrLits;
    }

    public function setNbrLits(int $NbrLits): self
    {
        $this->NbrLits = $NbrLits;

        return $this;
    }

    public function getVue(): ?string
    {
        return $this->vue;
    }

    public function setVue(string $vue): self
    {
        $this->vue = $vue;

        return $this;
    }

    public function getEtage(): ?int
    {
        return $this->Etage;
    }

    public function setEtage(int $Etage): self
    {
        $this->Etage = $Etage;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->Prix;
    }

    public function setPrix(float $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }

    public function getBloc(): ?string
    {
        return $this->Bloc;
    }

    public function setBloc(string $Bloc): self
    {
        $this->Bloc = $Bloc;

        return $this;
    }
<<<<<<< Updated upstream
=======

    public function getDispo(): ?string
    {
        return $this->dispo;
    }

    public function setDispo(string $dispo): self
    {
        $this->dispo = $dispo;

        return $this;
    }

    public function getImageCh()
    {
        return $this->ImageCh;
    }

    public function setImageCh( $ImageCh)
    {
        $this->ImageCh = $ImageCh;

        return $this;
    }

    public function getHotel(): ?Hotel
    {
        return $this->hotel;
    }

    public function setHotel(?Hotel $hotel): self
    {
        $this->hotel = $hotel;

        return $this;
    }

    /**
     * @return Collection<int, ResChambre>
     */
    public function getResChambres(): Collection
    {
        return $this->resChambres;
    }

    public function addResChambre(ResChambre $resChambre): self
    {
        if (!$this->resChambres->contains($resChambre)) {
            $this->resChambres[] = $resChambre;
            $resChambre->setChambre($this);
        }

        return $this;
    }

    public function removeResChambre(ResChambre $resChambre): self
    {
        if ($this->resChambres->removeElement($resChambre)) {
            // set the owning side to null (unless already changed)
            if ($resChambre->getChambre() === $this) {
                $resChambre->setChambre(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return(string)$this->getNumCh();
    }

    /**
     * @return Collection<int, ResChambre>
     */
    public function getResChambre(): Collection
    {
        return $this->resChambre;
    }

>>>>>>> Stashed changes
}
