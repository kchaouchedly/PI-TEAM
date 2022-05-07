/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.Evenement;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Hp
 */
public class ServiceEvenement implements Ievenement<Evenement>{
    Connection connexion ; 
    Statement stm ; 

    public ServiceEvenement() {
        connexion= MyDB.getInstance().getConnexion(); 
    }
    

    @Override
    public void ajouterevenement(Evenement p) throws SQLException {
           String req = "INSERT INTO `evenement`( `type`, `nom_evenement`, `date_debut`, `date_fin`, `lieux`, `image`, `color`)  VALUES ( '"
                + p.getType() + "', '" + p.getNomEvenement()+  "', '" +p.getDateDebut()+ "', '" +p.getDateFin()+ "', '" +p.getLieux()+ "', '" +
                   p.getImage()+ "', '" +p.getColor()
                   + "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
        
    }

    @Override
    public List<Evenement> afficherevenement() throws SQLException {
            
              List<Evenement> Evenement= new ArrayList<>();
        String req = "select * from evenement";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Evenement p = new Evenement(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("type"),
                    rst.getString("nom_evenement"), 
                   rst.getString("image"),
                     rst.getString("color"),
                    rst.getString("lieux"),
                   
                   rst.getDate("date_debut"),
                   rst.getDate("date_fin")
                   
           );
                    
                  
                   
            Evenement.add(p);
        }
        return Evenement;
    }

    @Override
    public Boolean deleteEvent(Evenement l) {
        Connection cnx =null;
        Statement st = null;
       String req = "DELETE FROM `evenement` WHERE `evenement`.`id`="+l.getId()+"" ; 
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

    @Override
    public Boolean updateEvent(Evenement ev) {
       Connection cnx =null;
        Statement st = null;
        
String req = "UPDATE `evenement` SET `type` = '"+ev.getType()+"' , `nom_evenement` = '"+ev.getNomEvenement()+"',`date_debut`='"+ev.getDateDebut()+"' ,`date_fin`='"+ev.getDateFin()+"',`lieux`='"+ev.getLieux()+"' ,`image`= '"+ev.getImage()+"',`color`='"+ev.getColor()+"'  WHERE id = "+ev.getId();
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
    public ObservableList<Evenement> afficherevent() {
        
        Connection cnx =null;
        Statement st = null;
        ResultSet rst = null;
        ObservableList<Evenement> liste = FXCollections.observableArrayList();
         String req = "select * from evenement";
        try {
            cnx = MyDB.getInstance().getConnexion();
            st = cnx.createStatement();
            rst = st.executeQuery(req);
            Evenement testajout;
            while (rst.next()) {
           Evenement p = new Evenement(rst.getInt("id"),//or rst.getInt(1)
                    rst.getString("type"),
                    rst.getString("nom_evenement"), 
                   rst.getString("image"),
                     rst.getString("color"),
                    rst.getString("lieux"),
                   rst.getDate("date_debut"),
                   rst.getDate("date_fin"));
                   
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
    public String QR (String A){
        
       
              
             try {
            String qrCodeData = "VOITURE "+A+"";
            String filePath = "C:\\Users\\HP\\Documents\\NetBeansProjects\\Pidev\\src\\Image\\"+A+".png";
            
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            return filePath;
        } catch (Exception e) {
            System.err.println(e);
           return "";
        }
               
       }
    public List<String> Eventnom() throws SQLException {
        List<String> agences = new ArrayList<>();
        String req = "select * from evenement";
        stm = connexion.createStatement();

        try {

            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                agences.add(rst.getString(3));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return agences;

    }
    
    
      public int getidevent(String nom) throws SQLException
    {
      String req = "SELECT * FROM evenement WHERE nom_evenement='"+nom+"'";
        stm = connexion.createStatement();
        int id = 0 ;
        try {
        ResultSet rst = stm.executeQuery(req);
        if(rst.next()){
   id = rst.getInt(1);
}
        
        }
        catch(SQLException ex)
        {System.out.println(ex.getMessage());
        }
       
    return id; 
}
}
 

