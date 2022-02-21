<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220212152818 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence_voiture ADD numtel INT NOT NULL, ADD nbrvoiture INT NOT NULL, DROP num_tel, DROP nbr_voiture');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence_voiture ADD num_tel INT NOT NULL, ADD nbr_voiture INT NOT NULL, DROP numtel, DROP nbrvoiture');
    }
}
