/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

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
import services.BilletService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class CadreVFrontController implements Initializable {

    @FXML
    private ImageView cadre;
    @FXML
    private Label numVol;
    @FXML
    private Label DateD;
    @FXML
    private Label dateA;
    @FXML
    private Label villeD;
    @FXML
    private Label VilleA;
    @FXML
    private Label TypeV;
    @FXML
    private Label NbrPlace;
    @FXML
    private Button billets;

    /**
     * Initializes the controller class.
     */
    
     public void setData(Vol v) {

        File filImg = new File(v.getImageVol());
        Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
        cadre.setImage(img);
        numVol.setText(v.getNumVol()+"" );
        DateD.setText(v.getDateDepart() + "");
        dateA.setText(v.getDateArrive() + "");
        VilleA.setText(v.getVilleArrive() + "");
        villeD.setText(v.getVilleDepart());
        NbrPlace.setText(v.getNbrPlace()+"");
        TypeV.setText(v.getTypeV());
    }
      public int getData(Vol v) {

     return v.getId();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consulterBillet(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/ListbilletFront.fxml"));
        Parent root = loader.load();
        ListbilletFrontController ac = loader.getController();
        
        billets.getScene().setRoot(root);
        
    }
    
}
