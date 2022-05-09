/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Billet;
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
public class BilletService implements IBillet<Billet> {

    Connection connexion;
    Statement stm;

    public BilletService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterBillet(Billet b) throws SQLException {
        String req = "INSERT INTO `billet`(`num_b`, `date_v`, `nom_com`, `prix`, `image_billet`,`vol_id`) "
                + "VALUES (?, ?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, b.getNumB());
        ps.setDate(2, b.getDateV());
        ps.setString(3, b.getNomCom());
        ps.setInt(4, b.getPrix());
        ps.setString(5, b.getImageBillet());
        ps.setInt(6, b.getVol_id());
        ps.executeUpdate();
    }

    @Override
    public void modifierBillet(Billet b) throws SQLException {
        String req = "UPDATE `billet` SET `num_b`=" + "?" + ",`date_v`=" + "?" + ",`nom_com`=" + "?" + ",`prix`=" + "?" + ",`vol_id`=" + "?"+ ",`image_billet`=" + "?" +"where id ="+"?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, b.getNumB());
        ps.setDate(2, b.getDateV());
        ps.setString(3, b.getNomCom());
        ps.setInt(4, b.getPrix());
           ps.setInt(5, b.getVol_id());
        ps.setString(6, b.getImageBillet());
     
        ps.setInt(7, b.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerBillet(int id) throws SQLException {
        String Req = "DELETE FROM `billet` WHERE  id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Billet> afficherBillet() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );

            billets.add(b);
        }
        return billets;
    }

    
      public List<Billet> afficherBilletbyVol(int id) throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet where vol_id="+id+" ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );

            billets.add(b);
        }
        return billets;
    }
    
     public List<Integer> getIdBillet() throws SQLException {
        List<Integer> billets = new ArrayList<>();
        String req = "select * from billet";
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
     
      public int  getprixVol(int id) throws SQLException {
       int prix=0;
        String req = "select prix from billet where id="+id+" ";
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
      
    
       public List<Billet> afficherCompagnieQatar() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet WHERE nom_com like '%Qatar Airways%' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );
            billets.add(b);
        }
        return billets ;
    }
    
       public List<Billet> afficherCompagnieEmirates() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet WHERE nom_com like '%Emirates%' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );
            billets.add(b);
        }
        return billets ;
    }
       
       public List<Billet> afficherCompagnieTurkish() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet WHERE nom_com like '%Turkish%' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );
            billets.add(b);
        }
        return billets ;
    }
       public List<Billet> afficherCompagnieBritish() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet WHERE nom_com like '%British%' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );
            billets.add(b);
        }
        return billets ;
    }
       public List<Billet> afficherCompagnieFrance() throws SQLException {
        List<Billet> billets = new ArrayList<>();
        String req = "select * from billet WHERE nom_com like '%Air France%' ";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Billet b = new Billet(rst.getInt("id"),
                    rst.getInt("num_b"),
                    rst.getInt("prix"),
                    rst.getString("nom_com"),
                    rst.getString("image_billet"),
                    rst.getDate("date_v"), 
                    rst.getInt("vol_id") );
            billets.add(b);
        }
        return billets ;
    }
       
       
}
