<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220214175941 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence_voiture ADD id INT AUTO_INCREMENT NOT NULL, DROP matricule, ADD PRIMARY KEY (id)');
        $this->addSql('ALTER TABLE voiture CHANGE id id INT AUTO_INCREMENT NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE agence_voiture MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE agence_voiture DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE agence_voiture ADD matricule INT NOT NULL, DROP id');
        $this->addSql('ALTER TABLE voiture CHANGE id id INT NOT NULL');
    }
}
