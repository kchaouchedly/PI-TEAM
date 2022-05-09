/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Hotel;
import entities.ResBillet;
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
public class ResBilletService implements IResBillet<ResBillet> {

    Connection connexion;
    Statement stm;

    public ResBilletService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterResBillet(ResBillet rb) throws SQLException {
        String req = "INSERT INTO `res_billet`(`tarif`, `nbr_pas`, `classe`,`billet_id`) "
                + "VALUES (?, ?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, rb.getTarif());
        ps.setInt(2, rb.getNbrPas());
        ps.setString(3, rb.getClasse());
        ps.setInt(4, rb.getBillet_id());
        ps.executeUpdate();
    }

    @Override
    public void modifierResBillet(ResBillet rb) throws SQLException {
        String req = "UPDATE `res_billet` SET `tarif`=" + "?" + ",`nbr_pas`=" + "?" + ",`billet_id`=" + "?" + ",`classe`=" + "?" + "where id =" + "?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, rb.getTarif());
        ps.setInt(2, rb.getNbrPas());
        ps.setInt(3, rb.getBillet_id());
        ps.setString(4, rb.getClasse());
        ps.setInt(5, rb.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerResBillet(int id) throws SQLException {
        String Req = "DELETE FROM `res_billet` WHERE  id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ResBillet> afficherResBillet() throws SQLException {
        List<ResBillet> resbillets = new ArrayList<>();
        String req = "select * from res_billet";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            ResBillet rb = new ResBillet(rst.getInt("id"),
                    rst.getInt("tarif"),
                    rst.getInt("nbr_pas"),
                    rst.getInt("billet_id"),
                    rst.getString("classe"));

            resbillets.add(rb);
        }
        return resbillets;
    }

}
