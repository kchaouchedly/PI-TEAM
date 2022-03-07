<?php

namespace App\Entity;

use App\Repository\ReservationERepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ReservationERepository::class)
 */
class ReservationE
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
     * @ORM\ManyToOne(targetEntity=Evenement::class, inversedBy="Offres")
     */
    private $evenement;

    /**
     * @ORM\Column (type="integer", nullable=true)
     */
    private $evenement_id;

    public function getEvenement(): ?Evenement
    {
        return $this->evenement;
    }

    public function setEvenement (?Evenement $evenement): self
    {
        $this->evenement = $evenement;

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


    public function getevenement_id()
    {
        return $this->evenement_id;
    }


    public function setEvenementId($evenement_id): void
    {
        $this->evenement_id = $evenement_id;
    }
}
