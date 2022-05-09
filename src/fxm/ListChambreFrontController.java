/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import controller.MenuFrontController;
import entities.Chambre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.ChambreService;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ListChambreFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button retourM;
    private List<Chambre> chambres = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    
      public void afficherListeChambres() {

          ChambreService chs = new ChambreService();
        try {
            chambres = chs.afficherChambre();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        int column = 0;
        int row = 1;     
        try {
            for (int i = 0; i < chambres.size(); i++) {
                
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.setLocation(getClass().getResource("/fxm/cadreChambre.fxml"));           
                VBox box = fXMLLoader.load();
                CadreChambreController cadreController = fXMLLoader.getController();
                cadreController.setData(chambres.get(i));
                if (column == 4) {
                    column = 0;
                    row++;
                }
                grid.add(box, column++, row); //(child,column,row)
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherListeChambres();
    }    

    @FXML
    private void retourMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/HotelFront.fxml"));
        Parent root = loader.load();
        HotelFrontController ac = loader.getController();
        retourM.getScene().setRoot(root);
    }
    
}
