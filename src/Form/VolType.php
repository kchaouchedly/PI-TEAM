<?php

namespace App\Form;

use App\Entity\Vol;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class VolType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('NumVol',NumberType::class)
            ->add('DateDepart')
            ->add('DateArrive')
            ->add('VilleDepart')
            ->add('VilleArrive',ChoiceType::class,[
                'choices'=> array(
                    'Paris'=>'Paris',
                    'Dubai'=>'Dubai',
                    'Espagne'=>'Espagne',
                    'Turquie'=>'Turquie',
                     'Italie'=>'Italie',
                    'Allemagne'=>'Allemagne',
                    'Chine'=>'Chine',
                    'Egypte'=>'Egypte'
                )
            ])
            ->add('imageVol',FileType::class,['label' => 'charger une image ','required' => false,
                'data_class' => null])
            ->add('typeV',ChoiceType::class,[
                'choices'=> array(
                    'Aller/Retour'=>'Aller/Retour',
                    'Aller'=>'Aller'
                )
            ])
            ->add('nbrPlace',NumberType::class)
            ->add('Submit',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Vol::class,
        ]);
    }
}
