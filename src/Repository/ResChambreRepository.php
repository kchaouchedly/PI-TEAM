<?php

namespace App\Repository;

use App\Entity\ResChambre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ResChambre|null find($id, $lockMode = null, $lockVersion = null)
 * @method ResChambre|null findOneBy(array $criteria, array $orderBy = null)
 * @method ResChambre[]    findAll()
 * @method ResChambre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ResChambreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ResChambre::class);
    }

    // /**
    //  * @return ResChambre[] Returns an array of ResChambre objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?ResChambre
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
