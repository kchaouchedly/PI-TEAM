/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.reservationoff;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.servicereservationoffre;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class ListeoffreReservéController implements Initializable {

    @FXML
    private TableColumn<reservationoff, Integer> idaff;
    @FXML
    private TableColumn<reservationoff, Date> iddadeb;
    @FXML
    private TableColumn<reservationoff, Date> iddatef;
    @FXML
    private TableColumn<reservationoff, Integer> offreid;
    @FXML
    private TableView<reservationoff> tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichereservation();
        // TODO
    }    
      private void affichereservation() {
       servicereservationoffre mS = new servicereservationoffre();
        System.out.println(mS.afficherreser());
        ObservableList<reservationoff> liste = mS.afficherreser(); 
        idaff.setCellValueFactory(new PropertyValueFactory<reservationoff, Integer>("id"));
       
        iddadeb.setCellValueFactory(new PropertyValueFactory<reservationoff, Date>("datedebut"));
        iddatef.setCellValueFactory(new PropertyValueFactory<reservationoff, Date>("datefin"));
         offreid.setCellValueFactory(new PropertyValueFactory<reservationoff, Integer>("ido"));
     
        
         tab.setItems(liste);
    
}
    
}
