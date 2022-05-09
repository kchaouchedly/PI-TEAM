/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.FavHotel;
import entities.FavVol;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author 123
 */
public class CadreVolController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    
        public void setData(FavVol fv) {

        File filImg = new File(fv.getImageVol());
        Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
        cadre.setImage(img);
        numVol.setText(fv.getNumVol()+"" );
        DateD.setText(fv.getDateDepart() + "");
        dateA.setText(fv.getDateArrive() + "");
        VilleA.setText(fv.getVilleArrive() + "");
        villeD.setText(fv.getVilleDepart());
        NbrPlace.setText(fv.getNbrPlace()+"");
        TypeV.setText(fv.getTypeV());
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
