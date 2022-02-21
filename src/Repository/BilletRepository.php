<?php

namespace App\Repository;

use App\Entity\Billet;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Billet|null find($id, $lockMode = null, $lockVersion = null)
 * @method Billet|null findOneBy(array $criteria, array $orderBy = null)
 * @method Billet[]    findAll()
 * @method Billet[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class BilletRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Billet::class);
    }

    public function orderByPrixV()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.Prix', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function orderBydateV()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.dateV', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function searchBillet($numB)
    {

        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("select s FROM APP\Entity\Billet s WHERE s.numB = :numB")
            ->setParameter('numB',$numB)
        ;
        return $query->getResult();
    }

    public function listBilletByVol($id)
    {
        return $this->createQueryBuilder('s')
            ->join('s.vol','c')
            ->addSelect('c')
            ->where('c.id=:id')
            ->setParameter('id',$id)
            ->getQuery()
            ->getResult();
    }

    // /**
    //  * @return Billet[] Returns an array of Billet objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('b')
            ->andWhere('b.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('b.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Billet
    {
        return $this->createQueryBuilder('b')
            ->andWhere('b.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
