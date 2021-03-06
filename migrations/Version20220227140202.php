<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220227140202 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE offres CHANGE nom nom VARCHAR(255) NOT NULL, CHANGE date_debut_offres date_debut_offres DATE NOT NULL, CHANGE date_fin_offre date_fin_offre DATE NOT NULL, CHANGE nom_guide nom_guide VARCHAR(255) NOT NULL, CHANGE prix prix INT NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE offres CHANGE nom nom VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE date_debut_offres date_debut_offres DATE DEFAULT NULL, CHANGE date_fin_offre date_fin_offre DATE DEFAULT NULL, CHANGE nom_guide nom_guide VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, CHANGE prix prix INT DEFAULT NULL');
    }
}
