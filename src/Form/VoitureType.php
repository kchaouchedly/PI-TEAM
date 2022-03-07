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
use Symfony\Component\Form\Extension\Core\Type\DateType;


class VoitureType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('nbrplace')
            ->add('type')
            ->add('marque')
            ->add('datedebut',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
            ->add('datefin',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])
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
