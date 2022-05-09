/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.Billet;
import entities.Vol;
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
public class CadreBilletController implements Initializable {

    @FXML
    private ImageView cadre;
    @FXML
    private Label numB;
    @FXML
    private Label prix;
    @FXML
    private Label nomcom;
    @FXML
    private Label dateV;
    @FXML
    private Button reserver;

    /**
     * Initializes the controller class.
     */
    
      public void setData(Billet b) {

        File filImg = new File(b.getImageBillet());
        Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
        cadre.setImage(img);
        numB.setText(b.getNumB()+"" );
        prix.setText(b.getPrix()+ "");
        nomcom.setText(b.getNomCom()+ "");
        dateV.setText(b.getDateV()+ "");
     
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reserverBillet(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/AjoutResBillet.fxml"));
        Parent root = loader.load();
        AjoutResBilletController ac = loader.getController();
        reserver.getScene().setRoot(root);
    }
    
}
