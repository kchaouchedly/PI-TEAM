<?php

namespace App\Form;

use App\Entity\Billet;
use Symfony\Component\Form\AbstractType;
<<<<<<< Updated upstream
=======
use Symfony\Component\Form\Extension\Core\Type\FileType;
>>>>>>> Stashed changes
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
<<<<<<< Updated upstream
=======
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
>>>>>>> Stashed changes

class BilletType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('numB',NumberType::class)
            ->add('Prix',NumberType::class)
            ->add('nomCom')
            ->add('dateV')
<<<<<<< Updated upstream
=======
            ->add('imageBillet',FileType::class,['label' => 'charger une image ','required' => false,
                'data_class' => null])

            ->add('vol')

>>>>>>> Stashed changes

            ->add('Submit',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Billet::class,
        ]);
    }
}
