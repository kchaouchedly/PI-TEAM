/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Offre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Hp
 */
public class ServiceOffres implements Ioffres<Offre>{
    Connection connexion ; 
    Statement stm ;  

    public ServiceOffres() {
                connexion= MyDB.getInstance().getConnexion(); 

    }

    @Override
    public void ajouteroffre(Offre p) throws SQLException {
                   String req = "INSERT INTO `offres`(`evenement_id`,`nom`, `date_debut_offres`, `date_fin_offre`, `nom_guide`, `prix`, `image`, `nbr_places`)  VALUES ( '"
               + p.getNom_event()+  "' , '"  + p.getNom()+ "', '" + p.getDateDebutOff()+  "', '" +p.getDateFinOff()+ "', '" +p.getNomGuide()+ "', '" +p.getPrix()+ "', '" +
                   p.getImage()+ "', '" +p.getNbr_places()
                   + "') ";
        stm = connexion.createStatement();
       try{ stm.executeUpdate(req);}
       catch(SQLException ex)
           {System.out.println(ex.getMessage());}
        
    }

    @Override
    public List<Offre> afficheroffre() throws SQLException {
                 List<Offre> Offre= new ArrayList<>();
        String req = "select * from offres";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Offre p = new Offre(
                   
                     rst.getInt("id"),//or rst.getInt(1)
                  
                    rst.getInt("nbr_places"),
                    rst.getString("nom"), 
                    rst.getString("nom_guide"),
                   rst.getString("image"),
                   rst.getFloat("prix"),
                    rst.getDate("date_debut_offres"),
                    rst.getDate("date_fin_offre"),
            rst.getInt("evenement_id"));
               
                   
                    
                    
                   
            Offre.add(p);
        }
        return Offre;
    }

   /* @Override
    public void deleteOffre(int id) throws SQLException {
        
         String req = " DELETE FROM `offres` WHERE `offres`.`id`="+id+"" ; 
        stm = connexion.createStatement();
        stm.executeUpdate(req);
           
        
        
    }
*/
    @Override
    public Boolean updateOffre(Offre k) {
          Connection cnx =null;
        Statement st = null;
        
String req = "UPDATE `offres` SET `nom` = '"+k.getNom()+"' , `date_debut_offres` = '"+k.getDateDebutOff()+"',`date_fin_offre`='"+k.getDateFinOff()+"' ,`nom_guide`='"+k.getNomGuide()+"',`prix`='"+k.getPrix()+"' ,`image`= '"+k.getImage()+"',`nbr_places`='"+k.getNbr_places()+"'  WHERE id = "+k.getId();
        try {
            cnx = MyDB.getInstance().getConnexion();
            st = cnx.createStatement();
            st.executeUpdate(req);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored  */}
    }
    }
    }

    @Override
    public ObservableList<Offre> afficheroffrelist() {
           Connection cnx =null;
        Statement st = null;
        ResultSet rst = null;
        ObservableList<Offre> liste = FXCollections.observableArrayList();
         String req = "select * from offres";
        try {
            cnx = MyDB.getInstance().getConnexion();
            st = cnx.createStatement();
            rst = st.executeQuery(req);
           Offre testajout;
            while (rst.next()) {
           Offre p = new Offre(
                    rst.getInt("id"),//or rst.getInt(1)
                  
                    rst.getInt("nbr_places"),
                    rst.getString("nom"), 
                    rst.getString("nom_guide"),
                   rst.getString("image"),
                   rst.getFloat("prix"),
                    rst.getDate("date_debut_offres"),
                    rst.getDate("date_fin_offre"),
            rst.getInt("evenement_id"));
           
                   
            liste.add(p);
        }
        
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
    if (rst != null) {
        try {
            rst.close();
        } catch (SQLException e) { /* Ignored */}
    }
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
        return liste;
        
}

    @Override
    public Boolean deleteOffre(Offre l) {
          Connection cnx =null;
        Statement st = null;
      // String req = "DELETE FROM `evenement` WHERE `evenement`.`id`="+l.getId()+"" ; 
          String req = " DELETE FROM `offres` WHERE `offres`.`id`="+l.getId()+"" ; 
        try {
            cnx = MyDB.getInstance().getConnexion();
            st = cnx.createStatement();
            st.executeUpdate(req);
            return true;
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }finally {
    
    if (st != null) {
        try {
            st.close();
        } catch (SQLException e) { /* Ignored */}
    }
    }
    }
    }
    
    
    
       

