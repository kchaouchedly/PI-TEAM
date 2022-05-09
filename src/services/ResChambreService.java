/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chambre;
import entities.ResChambre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author 123
 */
public class ResChambreService implements IResChambre<ResChambre> {

    Connection connexion;
    Statement stm;

    public ResChambreService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterResChambre(ResChambre ch) throws SQLException {
        String req = "INSERT INTO `res_chambre`(`tarif`, `nbr_j`, `date_res`, `chambre_id`)"
                + "VALUES (?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, ch.getTarif());
        ps.setInt(2, ch.getNbr_j());
        ps.setDate(3, ch.getDate_res());
        ps.setInt(4, ch.getChambre_id());
        ps.executeUpdate();
    }

    @Override
    public void modifierResChambre(ResChambre ch) throws SQLException {
        String req = "UPDATE `res_chambre` SET `tarif`=" + "?" + ",`nbr_j`=" + "?" +",`chambre_id`=" + "?"+ ",`date_res`=" + "?" + "where id =" + "?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, ch.getTarif());
        ps.setInt(2, ch.getNbr_j());
        ps.setInt(3, ch.getChambre_id());
        ps.setDate(4, ch.getDate_res());
        ps.setInt(5, ch.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerResChambre(int id) throws SQLException {
        String Req = "DELETE FROM `res_chambre` WHERE  id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ResChambre> afficherResChambre() throws SQLException {
        List<ResChambre> reschambres = new ArrayList<>();
        String req = "select * from res_chambre";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            ResChambre ch = new ResChambre(rst.getInt("id"),
                    rst.getInt("tarif"),
                    rst.getInt("nbr_j"),
                    rst.getDate("date_res"),
                    rst.getInt("chambre_id"));

            reschambres.add(ch);
        }
        return reschambres;
    }

}
