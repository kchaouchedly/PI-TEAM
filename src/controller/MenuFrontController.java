/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import fxm.FavVolController;
import fxm.VolFrontController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class MenuFrontController implements Initializable {

    @FXML
    private Button hotels;
    @FXML
    private Button vols;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void listVols(ActionEvent event) throws IOException {
      
        AnchorPane pane;
        try {
          pane = FXMLLoader.load(getClass().getResource("/fxm/VolFront.fxml"));
           // pane = FXMLLoader.load(getClass().getResource("/fxm/BilletFront.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void hotelButton(ActionEvent event) {
         AnchorPane pane;
        try {
          pane = FXMLLoader.load(getClass().getResource("/fxm/HotelFront.fxml"));
           // pane = FXMLLoader.load(getClass().getResource("/fxm/BilletFront.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
