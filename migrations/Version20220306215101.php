<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220306215101 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agence_voiture (id INT AUTO_INCREMENT NOT NULL, nomagence VARCHAR(255) NOT NULL, adresse VARCHAR(255) NOT NULL, numtel INT NOT NULL, nbrvoiture INT NOT NULL, image VARCHAR(255) NOT NULL, color VARCHAR(100) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, voiture_id INT DEFAULT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, INDEX IDX_42C84955181A8BA (voiture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE voiture (id INT AUTO_INCREMENT NOT NULL, agencevoiture_id INT DEFAULT NULL, nbrplace INT DEFAULT NULL, type VARCHAR(255) DEFAULT NULL, marque VARCHAR(255) DEFAULT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, prix INT DEFAULT NULL, disponibilite VARCHAR(255) DEFAULT NULL, image VARCHAR(255) NOT NULL, rate DOUBLE PRECISION DEFAULT NULL, INDEX IDX_E9E2810FC2826E5E (agencevoiture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955181A8BA FOREIGN KEY (voiture_id) REFERENCES voiture (id)');
        $this->addSql('ALTER TABLE voiture ADD CONSTRAINT FK_E9E2810FC2826E5E FOREIGN KEY (agencevoiture_id) REFERENCES agence_voiture (id)');
        $this->addSql('DROP TABLE blog');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE voiture DROP FOREIGN KEY FK_E9E2810FC2826E5E');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955181A8BA');
        $this->addSql('CREATE TABLE blog (id INT AUTO_INCREMENT NOT NULL, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, pays VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, titre VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, photo VARCHAR(500) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, etat TINYINT(1) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('DROP TABLE agence_voiture');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE voiture');
    }
}
