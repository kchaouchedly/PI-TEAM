/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.reservationoff;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Hp
 */
public class servicereservationoffre {
    Connection connect ; 
    Statement stat ; 
    public servicereservationoffre(){
    connect=MyDB.getInstance().getConnexion(); 
}
    public  void ajoutreso(reservationoff p )throws SQLException {
       String req = "INSERT INTO `reservation_o`(`datedebut`, `datefin`, `offre_id`) VALUES ( '"
         + p.getDatedebut()+ "', '" + p.getDatefin()+ "','" +p.getIdo()+
         "') ";
        stat= connect.createStatement();
        stat.executeUpdate(req);
    }

    public ObservableList<reservationoff> afficherreser() {
        
        Connection cnx =null;
        Statement st = null;
        ResultSet rst = null;
        ObservableList<reservationoff> liste = FXCollections.observableArrayList();
         String req = "select * from reservation_o";
        try {
            cnx = MyDB.getInstance().getConnexion();
            st = cnx.createStatement();
            rst = st.executeQuery(req);
            reservationoff testajout;
            while (rst.next()) {
          reservationoff p = new reservationoff(rst.getInt("id"),
                  rst.getDate("datedebut"),
                  rst.getDate("datefin"),
                  rst.getInt("offre_id") ); 
                  
                  
           
        
    
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
    
}
