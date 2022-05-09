/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.FavHotel;
import entities.FavVol;
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
import services.FavHotelService;
import services.FavVolService;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class FavVolController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button retourM;
    private List<FavVol> favvols = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    public void afficherListeVol() {

        FavVolService vs = new FavVolService();
        try {
            favvols = vs.afficherFavVol();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < favvols.size(); i++) {
                FXMLLoader fXMLLoader = new FXMLLoader();
                fXMLLoader.setLocation(getClass().getResource("/fxm/CadreVol.fxml"));
                VBox box = fXMLLoader.load();

                CadreVolController cadreController = fXMLLoader.getController();
                cadreController.setData(favvols.get(i));

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
        afficherListeVol();
    }

    @FXML
    private void retourMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/home.fxml"));
        Parent root = loader.load();
        home.Controller ac = loader.getController();
        retourM.getScene().setRoot(root);
    }

}
