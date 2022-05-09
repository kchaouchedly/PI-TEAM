/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.Chambre;
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
public class CadreChambreController implements Initializable {

    @FXML
    private ImageView cadre;
    @FXML
    private Label numB;
    @FXML
    private Label nbrLits;
    @FXML
    private Label prix;
    @FXML
    private Label num;
    @FXML
    private Label dispo;
    @FXML
    private Button res;

    /**
     * Initializes the controller class.
     */
       public void setData(Chambre ch) {

        File filImg = new File(ch.getImageCh());
        Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
        cadre.setImage(img);
        num.setText(ch.getNumCh()+"" );
        nbrLits.setText(ch.getNbrLits()+ "");
        prix.setText(ch.getPrix()+ "");
        dispo.setText(ch.getDispo()+ "");
     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserverChambre(ActionEvent event) throws IOException {
           
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/AjoutResChambre.fxml"));
        Parent root = loader.load();
        AjoutResChambreController ac = loader.getController();
        res.getScene().setRoot(root);
    }
    
}
