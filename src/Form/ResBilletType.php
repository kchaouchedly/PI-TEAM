<?php

namespace App\Form;

use App\Entity\ResBillet;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


class ResBilletType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nbrPas')
            ->add('classe',ChoiceType::class,[
                'choices'=> array(
                    'First Class'=>'First Class',
                    'Business Class'=>' Business Class',
                    'Economy Class'=>'Economy Class',

                ),
            ])

        ->add('submit',SubmitType::class,['label' => 'Enregistrer'])
            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptcha'
            ));
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ResBillet::class,
        ]);
    }
}
