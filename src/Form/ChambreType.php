<?php

namespace App\Form;

use App\Entity\Chambre;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ChambreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('NumCh',NumberType::class)
            ->add('NbrLits',ChoiceType::class,[
                'choices'=> array(
                    '1'=>'1',
                    '2'=>'2',
                    '3'=>'3',
                    '4'=>'4',
                ),
            ])
            ->add('vue',ChoiceType::class,[
                'choices'=> array(
                    'Standard vue Mer'=>'Standard vue Mer',
                    'Standard vue Jardin'=>'Standard vue Jardin',
                    'Deluxe vue Mer'=>' Deluxe vue Mer',
                    'Deluxe vue Jardin'=>'Deluxe vue Jardin',

                ),
            ])
            ->add('Etage',ChoiceType::class,[
                'choices'=> array(
                    '0'=>'0',
                    '1'=>'1',
                    '2'=>'2',
                    '3'=>'3',
                    '4'=>'4',
                    '5'=>'5',
                ),
            ])
            ->add('Prix',NumberType::class)

            ->add('Bloc',ChoiceType::class,[
                'choices'=> array(
                    'A'=>'A',
                    'B'=>'B',
                    'C'=>'C',
                    'D'=>'D',
                    'E'=>'E',
                ),
            ])
            ->add('Submit',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Chambre::class,
        ]);
    }
}
