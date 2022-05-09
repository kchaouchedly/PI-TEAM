/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.Hotel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class CadreHFrontController implements Initializable {

    @FXML
    private ImageView cadre;
    @FXML
    private Label nomHotel;
    @FXML
    private Label nbrEtoiles;
    @FXML
    private Label nbrChambres;
    @FXML
    private Label numTelephone;
    @FXML
    private Label adresse;
    @FXML
    private Label email;
    @FXML
    private Button chambres;

    /**
     * Initializes the controller class.
     */
        public void setData(Hotel hotel) {

        File filImg = new File(hotel.getImageHotel());
        Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
        cadre.setImage(img);
        nomHotel.setText(hotel.getNomHotel());
        nbrEtoiles.setText(hotel.getNbrEtoiles() + "");
        nbrChambres.setText(hotel.getNbrChambre() + "");
        numTelephone.setText(hotel.getNumTel() + "");
        adresse.setText(hotel.getAdresse());
        email.setText(hotel.getEmail());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consulterChambres(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/ListChambreFront.fxml"));
        Parent root = loader.load();
        ListChambreFrontController ac = loader.getController();
        //VolFrontController ac = loader.getController();
        chambres.getScene().setRoot(root);
    }
    
}
