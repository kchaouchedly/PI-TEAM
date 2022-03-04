<?php

namespace App\Form;

use App\Entity\Offres;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Vich\UploaderBundle\Form\Type\VichImageType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

use App\Entity\Evenement;


class OffresType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom')
            ->add('DateDebutOffres',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
            ->add('DateFinOffre',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])

            ->add('NomGuide')
            ->add('Prix')
            ->add('evenement', EntityType::class,[
                'class'=>Evenement::class,
                'choice_label'=>'Type',
                'multiple'=>false
            ])

            ->add('imageFile',VichImageType::class)
            ->add('NbrPlaces')

            ->add('Ajouter',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Offres::class,
        ]);
    }
}
