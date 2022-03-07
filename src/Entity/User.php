<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Repository\UserRepository;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Validation;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints\EqualTo;
use Symfony\Component\Serializer\Annotation\Groups;




/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\Table(name="`user`")
 * @UniqueEntity(
 * fields= {"email"},
 * message= "L'email que vous avez indiqué est déjà utilisé !"
 * )
 */
class User implements UserInterface
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("post:read")
     */
    private $id;
    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Email(
     *     message="l adresse  n est pas valide")
     *  @Groups("post:read")
     */
    private $email;

  /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     * min = 2,
     * max = 20,
     * minMessage = "Votre nom doit contenir au moins {{limit }} caract`eres",
     * maxMessage = "Votre nom doit contenir au plus {{limit }} caract`eres",
     * allowEmptyString = false
     * )
     * @Assert\Type(
     * type={"alpha", "digit"},
     * message="Votre nom {{ value }} doit contenir seulement des lettres alphabétiques"
     * )
     * @Groups("post:read")

     */
    private $username;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Groups("post:read")
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Groups("post:read")
     */
    private $prenom;

    /**
     * @var array
     * @ORM\Column(type="json")
     */
    private $roles = [];

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\Length(
     * min = 8,
     * minMessage = "Votre mot de passe doit comporter au minimum {{ limit }} caractères")
     *  @Groups("post:read")
     */
    private $password;

    /**
     * @Assert\EqualTo(propertyPath = "password",
     * message="Vous n'avez pas saisi le même mot de passe !" )
     *  @Groups("post:read")
     */

    public $confirm_password;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     */
    private $activation_token;

    /**
     * @ORM\Column(type="string", length=50, nullable=true)
     */
    private $reset_token;

    

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function eraseCredentials()
    {
        
    }
    public function getSalt()
    {
        
    }

    public function getRoles()
    {

         $roles = $this->roles;
         $roles[] = 'ROLE_USER';
        return array_unique($roles);
    }

    public function getActivationToken(): ?string
    {
        return $this->activation_token;
    }

    public function setActivationToken(?string $activation_token): self
    {
        $this->activation_token = $activation_token;

        return $this;
    }

    public function getResetToken(): ?string
    {
        return $this->reset_token;
    }

    public function setResetToken(?string $reset_token): self
    {
        $this->reset_token = $reset_token;

        return $this;
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }
}
