<?php

namespace App\Entity;

use App\Repository\ChambreRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

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
     *      @Assert\Length(
     *      max=20,
     *      maxMessage  ="Numéro chambre ne doit pas depasser 20 chiffres ",
     * )
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
     *     value = 150,message="Le prix minimale de la chambre est  150Dt"
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

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $dispo;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $ImageCh;

    /**
     * @ORM\ManyToOne(targetEntity=Hotel::class, inversedBy="chambre")
     */
    private $hotel;

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

}
