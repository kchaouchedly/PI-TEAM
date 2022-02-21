<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220214172928 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE voiture (id INT AUTO_INCREMENT NOT NULL, nbrplace INT DEFAULT NULL, type VARCHAR(255) DEFAULT NULL, marque VARCHAR(255) DEFAULT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, prix INT DEFAULT NULL, disponibilite VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE agence_voiture MODIFY id INT NOT NULL');
        $this->addSql('ALTER TABLE agence_voiture DROP PRIMARY KEY');
        $this->addSql('ALTER TABLE agence_voiture ADD matricule INT NOT NULL, DROP id, CHANGE nomagence nomagence VARCHAR(255) NOT NULL, CHANGE adresse adresse VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE agence_voiture ADD PRIMARY KEY (matricule)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE voiture');
        $this->addSql('ALTER TABLE agence_voiture ADD id INT AUTO_INCREMENT NOT NULL, DROP matricule, CHANGE nomagence nomagence VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE adresse adresse VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, DROP PRIMARY KEY, ADD PRIMARY KEY (id)');
    }
}
