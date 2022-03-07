<?php

namespace App\Form;

use App\Entity\ResChambre;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;

class ResChambreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nbrJ',NumberType::class)
            ->add('dateRes', DateType::class,[
                'attr' => ['class' => 'form-control '],

                'widget' =>'single_text',

            ])

            ->add('Submit',SubmitType::class,['label' => 'Enregistrer'])
            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptcha'
            ));
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ResChambre::class,
        ]);
    }
}
