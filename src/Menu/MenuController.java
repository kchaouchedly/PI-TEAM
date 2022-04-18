/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/Hotel.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void gestionHotelButton(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/Hotel.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void gestionChambreButton(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/Chambre.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestionVolsButton(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/Vol.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void gestionBilletButton(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/Billet.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



}
