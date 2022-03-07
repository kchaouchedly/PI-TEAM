<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220307141420 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE res_chambre ADD user_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE res_chambre ADD CONSTRAINT FK_E74AB011A76ED395 FOREIGN KEY (user_id) REFERENCES `user` (id)');
        $this->addSql('CREATE INDEX IDX_E74AB011A76ED395 ON res_chambre (user_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE res_chambre DROP FOREIGN KEY FK_E74AB011A76ED395');
        $this->addSql('DROP INDEX IDX_E74AB011A76ED395 ON res_chambre');
        $this->addSql('ALTER TABLE res_chambre DROP user_id');
    }
}
