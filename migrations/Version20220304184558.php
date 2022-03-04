<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220304184558 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reservation_e ADD evenement_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation_e ADD CONSTRAINT FK_89EED45EFD02F13 FOREIGN KEY (evenement_id) REFERENCES evenement (id)');
        $this->addSql('CREATE INDEX IDX_89EED45EFD02F13 ON reservation_e (evenement_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reservation_e DROP FOREIGN KEY FK_89EED45EFD02F13');
        $this->addSql('DROP INDEX IDX_89EED45EFD02F13 ON reservation_e');
        $this->addSql('ALTER TABLE reservation_e DROP evenement_id');
    }
}
