<?php

namespace App\Entity;

use App\Repository\RatingRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=RatingRepository::class)
 */
class Rating
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="decimal", precision=5, scale=2)
     */
    private $rating;

    /**
     * @ORM\Column(type="integer")
     */
    private $entity_code;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getRating(): ?string
    {
        return $this->rating;
    }

    public function setRating(string $rating): self
    {
        $this->rating = $rating;

        return $this;
    }

    public function getEntityCode(): ?int
    {
        return $this->entity_code;
    }

    public function setEntityCode(int $entity_code): self
    {
        $this->entity_code = $entity_code;

        return $this;
    }


    public function __construct($ref,$rating)
    {

        $this->rating=$rating;
        $this->entity_code=$ref;
    }





}
