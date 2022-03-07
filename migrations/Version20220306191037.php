<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220306191037 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE voiture DROP FOREIGN KEY FK_E9E2810FC2826E5E');
        $this->addSql('ALTER TABLE res_billet DROP FOREIGN KEY FK_5E411D4044973C78');
        $this->addSql('ALTER TABLE res_chambre DROP FOREIGN KEY FK_E74AB0119B177F54');
        $this->addSql('ALTER TABLE chambre DROP FOREIGN KEY FK_C509E4FF3243BB18');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955181A8BA');
        $this->addSql('ALTER TABLE billet DROP FOREIGN KEY FK_1F034AF69F2BFB7A');
        $this->addSql('DROP TABLE agence_voiture');
        $this->addSql('DROP TABLE billet');
        $this->addSql('DROP TABLE calendar');
        $this->addSql('DROP TABLE chambre');
        $this->addSql('DROP TABLE hotel');
        $this->addSql('DROP TABLE res_billet');
        $this->addSql('DROP TABLE res_chambre');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE reservation_e');
        $this->addSql('DROP TABLE voiture');
        $this->addSql('DROP TABLE vol');
        $this->addSql('ALTER TABLE evenement DROP color');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE agence_voiture (id INT AUTO_INCREMENT NOT NULL, nomagence VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, adresse VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, numtel INT NOT NULL, nbrvoiture INT NOT NULL, image VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, color VARCHAR(100) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE billet (id INT AUTO_INCREMENT NOT NULL, vol_id INT DEFAULT NULL, num_b INT NOT NULL, nom_com VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date_v DATETIME NOT NULL, prix INT NOT NULL, image_billet VARCHAR(400) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX IDX_1F034AF69F2BFB7A (vol_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE calendar (id INT AUTO_INCREMENT NOT NULL, title VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, start DATETIME NOT NULL, end DATETIME NOT NULL, description LONGTEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, all_day TINYINT(1) NOT NULL, background_color VARCHAR(7) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, border_color VARCHAR(7) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, text_color VARCHAR(7) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE chambre (id INT AUTO_INCREMENT NOT NULL, hotel_id INT DEFAULT NULL, num_ch INT NOT NULL, nbr_lits INT NOT NULL, vue VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, etage INT NOT NULL, prix DOUBLE PRECISION NOT NULL, bloc VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, dispo VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, image_ch VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX IDX_C509E4FF3243BB18 (hotel_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE hotel (id INT AUTO_INCREMENT NOT NULL, nom_hotel VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, adresse VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, num_tel INT NOT NULL, nbr_etoiles INT NOT NULL, nbr_chambre INT NOT NULL, code_h INT NOT NULL, email VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, image_hotel VARCHAR(350) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE res_billet (id INT AUTO_INCREMENT NOT NULL, billet_id INT DEFAULT NULL, tarif INT NOT NULL, nbr_pas INT NOT NULL, classe VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, INDEX IDX_5E411D4044973C78 (billet_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE res_chambre (id INT AUTO_INCREMENT NOT NULL, chambre_id INT DEFAULT NULL, tarif INT NOT NULL, nbr_j INT NOT NULL, date_res DATE NOT NULL, INDEX IDX_E74AB0119B177F54 (chambre_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, voiture_id INT DEFAULT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, INDEX IDX_42C84955181A8BA (voiture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation_e (id INT AUTO_INCREMENT NOT NULL, evenement_id INT DEFAULT NULL, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, INDEX IDX_89EED45EFD02F13 (evenement_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE voiture (id INT AUTO_INCREMENT NOT NULL, agencevoiture_id INT DEFAULT NULL, nbrplace INT DEFAULT NULL, type VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, marque VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, datedebut DATE DEFAULT NULL, datefin DATE DEFAULT NULL, prix INT DEFAULT NULL, disponibilite VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, image VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, rate DOUBLE PRECISION DEFAULT NULL, INDEX IDX_E9E2810FC2826E5E (agencevoiture_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE vol (id INT AUTO_INCREMENT NOT NULL, num_vol INT NOT NULL, date_depart DATETIME NOT NULL, date_arrive DATETIME NOT NULL, ville_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, ville_arrive VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, image_vol VARCHAR(350) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, type_v VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, nbr_place INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE billet ADD CONSTRAINT FK_1F034AF69F2BFB7A FOREIGN KEY (vol_id) REFERENCES vol (id)');
        $this->addSql('ALTER TABLE chambre ADD CONSTRAINT FK_C509E4FF3243BB18 FOREIGN KEY (hotel_id) REFERENCES hotel (id)');
        $this->addSql('ALTER TABLE res_billet ADD CONSTRAINT FK_5E411D4044973C78 FOREIGN KEY (billet_id) REFERENCES billet (id)');
        $this->addSql('ALTER TABLE res_chambre ADD CONSTRAINT FK_E74AB0119B177F54 FOREIGN KEY (chambre_id) REFERENCES chambre (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955181A8BA FOREIGN KEY (voiture_id) REFERENCES voiture (id)');
        $this->addSql('ALTER TABLE reservation_e ADD CONSTRAINT FK_89EED45EFD02F13 FOREIGN KEY (evenement_id) REFERENCES evenement (id)');
        $this->addSql('ALTER TABLE voiture ADD CONSTRAINT FK_E9E2810FC2826E5E FOREIGN KEY (agencevoiture_id) REFERENCES agence_voiture (id)');
        $this->addSql('ALTER TABLE evenement ADD color VARCHAR(100) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`');
    }
}
