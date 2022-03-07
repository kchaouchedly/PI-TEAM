<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220306212355 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE voiture DROP FOREIGN KEY voiture_ibfk_1');
        $this->addSql('DROP TABLE agence_voiture');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE voiture');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agence_voiture (id INT AUTO_INCREMENT NOT NULL, nomagence VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, adresse VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, numtel INT NOT NULL, nbrvoiture INT NOT NULL, image VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, color VARCHAR(100) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, voiture_id INT DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE voiture (id INT AUTO_INCREMENT NOT NULL, agencevoiture_id INT DEFAULT NULL, nbrplace INT DEFAULT NULL, type VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, marque VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, prix INT DEFAULT NULL, disponibilite VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, image VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, INDEX agencevoiture_id (agencevoiture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE voiture ADD CONSTRAINT voiture_ibfk_1 FOREIGN KEY (agencevoiture_id) REFERENCES agence_voiture (id)');
    }
}
