<?php

namespace App\Entity;


use App\Repository\BilletRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=BilletRepository::class)
 */
class Billet
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank(message="le numéro du billet est obligatoire")
     *      @Assert\Length(
     *      min=1,
     *      max=20,
     *      minMessage = "Numéro billet doit etre composer de 1 chiffres au minimum  ",
     *      maxMessage  ="Numéro billet ne doit pas depasser 20 chiffres ",
     * )
     */
    private $numB;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *     min=6,
     *     max=10,
     *     minMessage = "le nom de la compagnie doit etre supérieur a 6 caracteres",
     *     maxMessage  ="le nom de la compagnie ne doit pas depasser 10 caracteres",
     *     allowEmptyString = false
     *     )
     * @Assert\Type(
     * type={"alpha"},
     * message="Nom de la compagnie  {{ value }} doit contenir seulement des lettres alphabétiques")
     * @Assert\NotBlank(message="veuillez saisir le nom de la compagnie")
     */
    private $nomCom;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\NotBlank(message="la date et l heure du billet sont obligatoires")
     * @Assert\GreaterThan("Today",message="Saisir une date à partir de la date d'aujourd'hui")
     */
    private $dateV;

    /**
     * @ORM\Column(type="integer")
     *
     * @Assert\NotBlank(message="le prix du billet est obligatoire")
     * @Assert\GreaterThanOrEqual(
     *     value = 400,message="Le prix minimale du billet est  400Dt"
     * )
     * @Assert\LessThanOrEqual(
     *     value = 3000,message="Le prix du billet ne doit pas depasser 3000Dt"
     * )
     */
    private $Prix;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNumB(): ?int
    {
        return $this->numB;
    }

    public function setNumB(int $numB): self
    {
        $this->numB = $numB;

        return $this;
    }

    public function getNomCom(): ?string
    {
        return $this->nomCom;
    }

    public function setNomCom(string $nomCom): self
    {
        $this->nomCom = $nomCom;

        return $this;
    }

    public function getDateV(): ?\DateTimeInterface
    {
        return $this->dateV;
    }

    public function setDateV(\DateTimeInterface $dateV): self
    {
        $this->dateV = $dateV;

        return $this;
    }

    public function getPrix(): ?int
    {
        return $this->Prix;
    }

    public function setPrix(int $Prix): self
    {
        $this->Prix = $Prix;

        return $this;
    }




}
