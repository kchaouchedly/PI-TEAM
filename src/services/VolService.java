/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vol;
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
public class VolService implements IVol<Vol> {

    Connection connexion;
    Statement stm;

    public VolService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterVol(Vol v) throws SQLException {
        String req = "INSERT INTO `vol`(`num_vol`, `date_depart`, `ville_depart`, `date_arrive`, `ville_arrive`, `image_vol`, `type_v`, `nbr_place`) "
                + "VALUES (?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, v.getNumVol());
        ps.setDate(2, v.getDateDepart());
        ps.setString(3, v.getVilleDepart());
        ps.setDate(4, v.getDateArrive());
        ps.setString(5, v.getVilleArrive());
        ps.setString(6, v.getImageVol());
        ps.setString(7, v.getTypeV());
        ps.setInt(8, v.getNbrPlace());
        ps.executeUpdate();
    }

    @Override
    public void modifierVol(Vol v) throws SQLException {
        String req = "UPDATE `vol` SET `num_vol`=" + "?" + ",`date_depart`=" + "?" + ",`ville_depart`=" + "?" + ",`date_arrive`=" + "?" + ",`ville_arrive`=" + "?" + ",`image_vol`=" + "?" + ",`type_v`=" + "?" + ",`nbr_place`=" + "?" + " where `id`= " + v.getId() + "";

        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, v.getNumVol());
        ps.setDate(2, v.getDateDepart());
        ps.setString(3, v.getVilleDepart());
        ps.setDate(4, v.getDateArrive());
        ps.setString(5, v.getVilleArrive());
        ps.setString(6, v.getImageVol());
        ps.setString(7, v.getTypeV());
        ps.setInt(8, v.getNbrPlace());
        //ps.setInt(9, v.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerVol(int id) throws SQLException {
        String Req = "DELETE FROM `vol` WHERE  id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Vol> afficherVol() throws SQLException {
        List<Vol> vols = new ArrayList<>();
        String req = "select * from vol";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Vol v = new Vol(rst.getInt("id"),
                    rst.getInt("num_vol"),
                    rst.getInt("nbr_place"),
                    rst.getDate("date_depart"),
                    rst.getDate("date_arrive"),
                    rst.getString("ville_depart"),
                    rst.getString("ville_arrive"),
                    rst.getString("image_vol"),
                    rst.getString("type_v"));

            vols.add(v);
        }
        return vols;
    }
    
     public List<Integer> getIdVol() throws SQLException {
        List<Integer> vols = new ArrayList<>();
        String req = "select * from vol";
        stm = connexion.createStatement();

        try {

            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                vols.add(rst.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return vols;

    }

}
