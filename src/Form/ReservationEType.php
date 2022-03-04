<?php

namespace App\Form;

use App\Entity\ReservationE;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ReservationEType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('datedebut',DateType::class,[
            'widget'=>'single_text',
            'html5'=>true,
        ])
            ->add('datefin',DateType::class,[
                'widget'=>'single_text',
                'html5'=>true,
            ])

            ->add('submit',SubmitType::class ,array(
                'attr' => array(
                    'class' => 'main_bt')))
        ;
    }




    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ReservationE::class,
        ]);
    }
}
