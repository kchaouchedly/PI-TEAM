<?php

namespace App\Repository;

use App\Entity\Blog;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Blog|null find($id, $lockMode = null, $lockVersion = null)
 * @method Blog|null findOneBy(array $criteria, array $orderBy = null)
 * @method Blog[]    findAll()
 * @method Blog[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class BlogRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Blog::class);
    }

    /**
     * get one by id
     *
     * @param integer $id
     *
     * @return object or null
     */
    public function findPostByid($id)
    {
        try {
            return $this->getEntityManager()
                ->createQuery(
                    "SELECT p
                FROM  APP\Entity\Blog
                p WHERE p.id =:id"
                )
                ->setParameter('id', $id)
                ->getOneOrNullResult();
        } catch (NonUniqueResultException $e) {
        }
    }



    /**
     * get all posts
     *
     * @return array
     */
    public function findAllPosts()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT a
         FROM blog a
      
         ORDER BY a.posted_at DESC'
            )
            ->getArrayResult();
    }




    public function findEntitiesByString($str){
        return $this->getEntityManager()
            ->createQuery('select p FROM  blog p WHERE p.titre LIKE :str')
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }
}

    // /**
    //  * @return Blog[] Returns an array of Blog objects
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
    public function findOneBySomeField($value): ?Blog
    {
        return $this->createQueryBuilder('b')
            ->andWhere('b.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
