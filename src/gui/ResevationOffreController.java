/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.reservationoff;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.servicereservationoffre;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ResevationOffreController implements Initializable {

    @FXML
    private DatePicker datedebutreser;
    @FXML
    private DatePicker datefinreser;
    public static float prix = 0 ;
    @FXML
    private Button valider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void valider(ActionEvent event) throws IOException {
          reservationoff r = new reservationoff(Date.valueOf(datedebutreser.getValue()), Date.valueOf(datefinreser.getValue()),AjouteroffreFXMLController.v.getId());
          
         servicereservationoffre ps = new servicereservationoffre();
        try {
            ps.ajoutreso(r);
          //  System.out.println(Integer.parseInt((String) Date.valueOf(datedebutreser.getValue()).toString().subSequence(8,10)));
            //System.out.println( Integer.parseInt((String) Date.valueOf(datefinreser.getValue()).toString().subSequence(8,10)));
            //prix =(Integer.parseInt((String) Date.valueOf(datefinreser.getValue()).toString().subSequence(8,10))-
               //     Integer.parseInt((String) Date.valueOf(datedebutreser.getValue()).toString().subSequence(8,10)))*AjouteroffreFXMLController.v.getPrix();
             prix =   AjouteroffreFXMLController.v.getPrix(); 
             System.out.println(prix);
             System.out.println(AjouteroffreFXMLController.v.getPrix());
             
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/Paiement.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
