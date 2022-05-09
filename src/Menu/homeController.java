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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class homeController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox sideBarPane;
    @FXML
    private Button btnSignout;
    @FXML
    private Button HotelButton;
    @FXML
    private Button ChambreButton;
    @FXML
    private Button VolButton;
    @FXML
    private Button BilletButton;
    @FXML
    private Button resBilletButton;
    @FXML
    private Button resChambreButton;

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
        HotelButton.setTextFill(Color.WHITE);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.BLACK);
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
        HotelButton.setTextFill(Color.WHITE);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.BLACK);
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
        HotelButton.setTextFill(Color.BLACK);
        ChambreButton.setTextFill(Color.WHITE);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.BLACK);
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
        HotelButton.setTextFill(Color.BLACK);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.WHITE);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.BLACK);
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
        HotelButton.setTextFill(Color.BLACK);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.WHITE);
         resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.BLACK);
    }

    @FXML
    private void gestionresBilletButton(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/ResBillet.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        HotelButton.setTextFill(Color.BLACK);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.WHITE);
        resChambreButton.setTextFill(Color.BLACK);
    }

    @FXML
    private void gestionResChambre(ActionEvent event) {
        
           AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/fxm/ResChambre.fxml"));
            mainPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        HotelButton.setTextFill(Color.BLACK);
        ChambreButton.setTextFill(Color.BLACK);
        VolButton.setTextFill(Color.BLACK);
        BilletButton.setTextFill(Color.BLACK);
        resBilletButton.setTextFill(Color.BLACK);
        resChambreButton.setTextFill(Color.WHITE);
    }
    
}
