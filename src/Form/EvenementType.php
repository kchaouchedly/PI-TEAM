<?php

namespace App\Form;
//use Doctrine\DBAL\Types\DateType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateType;

use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Vich\UploaderBundle\Form\Type\VichImageType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\ColorType;

class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('Type', ChoiceType::class, [
                'label'=> 'Selectionnez le Type  : ',
                'choices' => [
                    ''=>'',
                    'Sport' =>'sport',
                    'Musique' => 'Musique',
                    'Culture' => 'Culture',
                    'Les festivals'=>'Les festivals',
                    'Les salons ou expositions'=>'Les salons ou expositions',
                    'les voyages organisés '=>'les voyages organisés ',

                ],

            ])
            ->add('NomEvenement')
            ->add('DateDebut',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
            ->add('DateFin',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])

            ->add('Lieux')
            ->add('imageFile',VichImageType::class)
            ->add('color', ColorType::class)
            ->add('Ajouter',SubmitType::class)

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
