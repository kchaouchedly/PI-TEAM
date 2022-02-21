<?php

namespace App\Entity;

use App\Repository\AgenceVoitureRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=AgenceVoitureRepository::class)
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
     * @ORM\OneToMany(targetEntity=Voiture::class, mappedBy="AgenceVoiture" ,cascade={"remove"})
     */
    private $voiture;

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
