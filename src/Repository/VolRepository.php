<?php

namespace App\Repository;

use App\Entity\Vol;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Vol|null find($id, $lockMode = null, $lockVersion = null)
 * @method Vol|null findOneBy(array $criteria, array $orderBy = null)
 * @method Vol[]    findAll()
 * @method Vol[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class VolRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Vol::class);
    }
    public function orderByNum()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.NumVol', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function orderByDateDep()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.DateDepart', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function searchVol($NumVol)
    {

        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("select s FROM APP\Entity\Vol s WHERE s.NumVol = :NumVol")
            ->setParameter('NumVol',$NumVol)
        ;
        return $query->getResult();
    }
    // /**
    //  * @return Vol[] Returns an array of Vol objects
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
    public function findOneBySomeField($value): ?Vol
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
