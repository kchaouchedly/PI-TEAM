/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javax.activation.DataSource;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class StatevenementController implements Initializable {

    @FXML
    private PieChart piechart;
    private Statement st;
    private ResultSet rs;
    private Connection    connexion;

    /**
     * Initializes the controller class.
     */
     
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           // cnx=DataSource.getInstance().getCnx();
                   connexion= MyDB.getInstance().getConnexion(); 

        stat();
    } 
     private void stat()
    {
          
           
      try {
           
          String query = "SELECT COUNT(*),type FROM evenement GROUP BY type" ;
       
             PreparedStatement PreparedStatement =    connexion.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("type"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(AjoutereventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("Statistiques selon les types");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);
    
    }
    
}
