<?php

namespace App\Repository;

use App\Entity\Chambre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Chambre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Chambre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Chambre[]    findAll()
 * @method Chambre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ChambreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Chambre::class);
    }

    public function orderByBloc()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.Bloc', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function orderByPrix()
    {
        return $this->createQueryBuilder('s')
            ->orderBy('s.Prix', 'ASC')
            ->getQuery()
            ->getResult();
    }

    public function searchChambre($NumCh)
    {

        $entityManager=$this->getEntityManager();
        $query=$entityManager
            ->createQuery("select s FROM APP\Entity\Chambre s WHERE s.NumCh = :NumCh")
            ->setParameter('NumCh',$NumCh)
        ;
        return $query->getResult();
    }
<<<<<<< Updated upstream
    // /**
    //  * @return Chambre[] Returns an array of Chambre objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Chambre
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
=======

    public function listChambreByHotel($id)
    {
        return $this->createQueryBuilder('s')
            ->join('s.hotel','c')
            ->addSelect('c')
            ->where('c.id=:id')
            ->setParameter('id',$id)
            ->getQuery()
            ->getResult();
    }

>>>>>>> Stashed changes
}
