<?php

namespace App\Form;
use App\Entity\AgenceVoiture;
use App\Entity\Voiture;
use EasyCorp\Bundle\EasyAdminBundle\Field\ImageField;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Vich\UploaderBundle\Form\Type\VichImageType;
use Symfony\Component\Form\FormBuilder;


class VoitureType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('nbrplace')
            ->add('type')
            ->add('marque')
            ->add('datedebut')
            ->add('datefin')
            ->add('prix')
            ->add('Disponibilite')
            ->add('agencevoiture')
            ->add('imageFile',VichImageType::class)

            ->add('submit',SubmitType::class )
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Voiture::class,
        ]);
    }
}
