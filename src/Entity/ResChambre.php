<?php

namespace App\Entity;

use App\Repository\ResChambreRepository;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Validator\Constraints as Assert;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;

/**
 * @ORM\Entity(repositoryClass=ResChambreRepository::class)
 */
class ResChambre
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="integer")
     */
    private $tarif;

    /**
     * @ORM\Column(type="integer")
     * @Assert\NotBlank (message="le nombre du jour pour la réservation est obligatoire")
     */
    private $nbrJ;

    /**
     * @ORM\Column(type="date")
     * @Assert\NotBlank (message="la date  de la réservation est obligatoire")
     */
    private $dateRes;

    /**
     * @ORM\ManyToOne(targetEntity=Chambre::class, inversedBy="resChambre")
     */
    private $chambre;

    /**
     * @CaptchaAssert\ValidCaptcha(
     *      message = "CAPTCHA validation failed, try again."
     * )
     */
    protected $captchaCode;

    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTarif(): ?int
    {
        return $this->tarif;
    }

    public function setTarif(int $tarif): self
    {
        $this->tarif = $tarif;

        return $this;
    }

    public function getNbrJ(): ?int
    {
        return $this->nbrJ;
    }

    public function setNbrJ(int $nbrJ): self
    {
        $this->nbrJ = $nbrJ;

        return $this;
    }

    public function getDateRes(): ?\DateTimeInterface
    {
        return $this->dateRes;
    }

    public function setDateRes(\DateTimeInterface $dateRes): self
    {
        $this->dateRes = $dateRes;

        return $this;
    }

    public function getChambre(): ?Chambre
    {
        return $this->chambre;
    }

    public function setChambre(?Chambre $chambre): self
    {
        $this->chambre = $chambre;

        return $this;
    }






}
