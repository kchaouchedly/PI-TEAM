/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import com.sun.javafx.scene.control.SelectedCellsMap;
import entities.Hotel;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.HotelService;
import entities.Hotel;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class CadreController implements Initializable {

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

    private Hotel hotel;

    public void setData(Hotel hotel) {

        Image image = new Image(getClass().getResourceAsStream("/img/watermelon.png"));
        cadre.setImage(image);

        nomHotel.setText(hotel.getNomHotel());
        nbrEtoiles.setText(hotel.getNbrEtoiles() + "");
        nbrChambres.setText(hotel.getNbrChambre() + "");
        numTelephone.setText(hotel.getNumTel() + "");
        adresse.setText(hotel.getAdresse());
        email.setText(hotel.getEmail());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



}
