<?php

namespace App\Form;

use App\Entity\AgenceVoiture;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ColorType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Vich\UploaderBundle\Form\Type\VichImageType;


class AgenceVoitureType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom_agence')
            ->add('adresse')
            ->add('num_tel')
            ->add('nbr_voiture')
            ->add('imageFile',VichImageType::class)
            ->add('color', ColorType::class)
            ->add('submit',SubmitType::class )

    // ...
             ;
    // ...

    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => AgenceVoiture::class,
        ]);
    }
}
