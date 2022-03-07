<?php

namespace App\Form;

use App\Entity\Hotel;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
<<<<<<< Updated upstream
=======
use Symfony\Component\Form\Extension\Core\Type\FileType;
>>>>>>> Stashed changes
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class HotelType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('CodeH',NumberType::class)
<<<<<<< Updated upstream
            ->add('NomHotel')
=======
            ->add('NomHotel',ChoiceType::class,[
                'choices'=> array(
                    'iberostar'=>'iberostar',
                    'Goldanyasmin'=>'Goldanyasmin',
                    'TabarcaThalasou'=>'TabarcaThalasou	',
                    'BravoDjerba'=>'BravoDjerba',
                    'LaBadira'=>'LaBadira',
                    'RamadaPlaza'=>'RamadaPlaza',
                    'RoyalThalassa'=>'RoyalThalassa',
                    'forSeasonTes'=>'forSeasonTes',
                ),
            ])
>>>>>>> Stashed changes
            ->add('email')
            ->add('Adresse')
            ->add('NumTel',NumberType::class)
            ->add('NbrEtoiles',ChoiceType::class,[
                'choices'=> array(
                    '2'=>'2',
                    '3'=>'3',
                    '4'=>'4',
                    '5'=>'5',
                ),
            ])
<<<<<<< Updated upstream
            ->add('NbrChambre',NumberType::class)

=======

            ->add('imageHotel',FileType::class,['label' => 'charger une image ','required' => false,
                'data_class' => null])
>>>>>>> Stashed changes
            ->add('Submit',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Hotel::class,
        ]);
    }
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
}
