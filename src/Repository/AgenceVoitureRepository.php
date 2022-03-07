<?php

namespace App\Repository;

use App\Entity\AgenceVoiture;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method AgenceVoiture|null find($id, $lockMode = null, $lockVersion = null)
 * @method AgenceVoiture|null findOneBy(array $criteria, array $orderBy = null)
 * @method AgenceVoiture[]    findAll()
 * @method AgenceVoiture[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class AgenceVoitureRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, AgenceVoiture::class );
    }

    // /**
    //  * @return Voiture[] Returns an array of Voiture objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('v')
            ->andWhere('v.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('v.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Voiture
    {
        return $this->createQueryBuilder('v')
            ->andWhere('v.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
