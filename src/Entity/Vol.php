<?php

namespace App\Entity;

use App\Repository\VolRepository;
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
}
