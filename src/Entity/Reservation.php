<?php

namespace App\Entity;

use App\Repository\ReservationRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=ReservationRepository::class)
 */
class Reservation
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\GreaterThan("Today",message="Saisir une date à partir de la date d'aujourd'hui")
     */
    private $datedebut;

    /**
     * @ORM\Column(type="date", nullable=true)
     * @Assert\Expression("this.getDatedebut() < this.getDatefin()", message="Veuillez vérifier la date de fin)
     */
    private $datefin;
    /**
     * @ORM\ManyToOne(targetEntity=Voiture::class, inversedBy="reservation")
     */
    private $voiture;
    /**
     * @ORM\Column(type="integer", nullable=true)
     */

    private $voiture_id;

    public function getVoiture(): ?Voiture
    {
        return $this->voiture;
    }

    public function setVoiture(?Voiture $voiture): self
    {
        $this->voiture = $voiture;

        return $this;
    }

    public function getId(): ?int
    {
        return $this->id;
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
    public function getVoiture_id(): ?int
    {
        return $this->voiture_id;
    }

    public function setVoiture_id(int $voiture_id): self
    {
        $this->voiture_id = $voiture_id;

        return $this;
    }
}
