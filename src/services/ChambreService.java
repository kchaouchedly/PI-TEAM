/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chambre;
import entities.Hotel;
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
public class ChambreService implements IChambre<Chambre> {

    Connection connexion;
    Statement stm;

    public ChambreService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void supprimerChambre(int id) throws SQLException {
        String Req = "DELETE FROM `chambre` WHERE id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterChambre(Chambre ch) throws SQLException {
        String req = "INSERT INTO `chambre`(`num_ch`, `nbr_lits`, `vue`, `etage`, `prix`, `bloc`, `dispo`, `image_ch`,`hotel_id`) "
                + "VALUES (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, ch.getNumCh());
        ps.setInt(2, ch.getNbrLits());
        ps.setString(3, ch.getVue());
        ps.setInt(4, ch.getEtage());
        ps.setFloat(5, ch.getPrix());
        ps.setString(6, ch.getBloc());
        ps.setString(7, ch.getDispo());
        ps.setString(8, ch.getImageCh());
        ps.setInt(9, ch.getHotel_id());
        ps.executeUpdate();
    }

    @Override
    public void modifierChambre(Chambre ch) throws SQLException {

        String req = "UPDATE `chambre` SET `num_ch`=" + "?" + ",`nbr_lits`=" + "?" + ",`vue`=" + "?" + ",`etage`=" + "?" + ",`prix`=" + "?" + ",`bloc`=" + "?" + ",`dispo`=" + "?" + ",`hotel_id`=" + "?" + ",`image_ch`=" + "?" + "where id =" + "?";

        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, ch.getNumCh());
        ps.setInt(2, ch.getNbrLits());
        ps.setString(3, ch.getVue());
        ps.setInt(4, ch.getEtage());
        ps.setFloat(5, ch.getPrix());
        ps.setString(6, ch.getBloc());
        ps.setString(7, ch.getDispo());
        ps.setInt(8, ch.getHotel_id());
        ps.setString(9, ch.getImageCh());

        ps.setInt(10, ch.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Chambre> afficherChambre() throws SQLException {
        List<Chambre> chambres = new ArrayList<>();
        String req = "select * from chambre";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre ch = new Chambre(rst.getInt("id"),
                    rst.getInt("nbr_lits"),
                    rst.getInt("num_ch"),
                    rst.getInt("etage"),
                    rst.getString("vue"),
                    rst.getString("bloc"),
                    rst.getString("dispo"),
                    rst.getString("image_ch"),
                    rst.getInt("prix"),
                    rst.getInt("hotel_id"));

            chambres.add(ch);
        }
        return chambres;
    }

    public List<Chambre> afficherChambreNonDispo() throws SQLException {
        List<Chambre> chambres = new ArrayList<>();

        String req = "select * from chambre WHERE dispo like '%Non Disponible%' ";

        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre ch = new Chambre(rst.getInt("id"),
                    rst.getInt("nbr_lits"),
                    rst.getInt("num_ch"),
                    rst.getInt("etage"),
                    rst.getString("vue"),
                    rst.getString("bloc"),
                    rst.getString("dispo"),
                    rst.getString("image_ch"),
                    rst.getInt("prix"),
                    rst.getInt("hotel_id"));

            chambres.add(ch);
        }
        return chambres;
    }
    
        public List<Chambre> afficherChambreDispo() throws SQLException {
        List<Chambre> chambres = new ArrayList<>();

        String req = "select * from chambre WHERE dispo like 'Disponible' ";

        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chambre ch = new Chambre(rst.getInt("id"),
                    rst.getInt("nbr_lits"),
                    rst.getInt("num_ch"),
                    rst.getInt("etage"),
                    rst.getString("vue"),
                    rst.getString("bloc"),
                    rst.getString("dispo"),
                    rst.getString("image_ch"),
                    rst.getInt("prix"),
                    rst.getInt("hotel_id"));

            chambres.add(ch);
        }
        return chambres;
    }

        
        
         public List<Integer> getIdChambre() throws SQLException {
        List<Integer> billets = new ArrayList<>();
        String req = "select * from chambre";
        stm = connexion.createStatement();

        try {

            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                billets.add(rst.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return billets;

    }
         
       public int  getprixChambre(int id) throws SQLException {
       int prix=0;
        String req = "select prix from chambre where id="+id+" ";
        stm = connexion.createStatement();

        try {

            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
             prix+= rst.getInt(1);
            

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return prix;

    }
}
