/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class Controller implements Initializable {

    @FXML
    private Button btnCustomers;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Label fruitNameLable;
    @FXML
    private TextField filterField;
    @FXML
    private Button btnevent;
    @FXML
    private Button offrebtn;
    @FXML
    private TextField filte;
    @FXML
    private TextField nomoffreajout;
    @FXML
    private DatePicker datedebutoff;
    @FXML
    private ComboBox<?> join;
    @FXML
    private DatePicker datefinoff;
    @FXML
    private TextField nomguideoff;
    @FXML
    private TextField prixoff;
    @FXML
    private TextField nbrplacesoff;
    @FXML
    private Label mylabel;
    @FXML
    private Button btnimageoff;
    @FXML
    private ImageView imageoffre;
    @FXML
    private Button btnaddoff;
    @FXML
    private Button modifieroffrebtn;
    @FXML
    private Button btnsupprimeroffre;
    @FXML
    private TableView<?> OFFREAFFICHER;
    @FXML
    private TableColumn<?, ?> affichageid;
    @FXML
    private TableColumn<?, ?> idnbrplaceoff;
    @FXML
    private TableColumn<?, ?> nomoff;
    @FXML
    private TableColumn<?, ?> nomguide;
    @FXML
    private TableColumn<?, ?> prixoffre;
    @FXML
    private TableColumn<?, ?> datedebutoffre;
    @FXML
    private TableColumn<?, ?> datefinnoff;
    @FXML
    private Button payerbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void btnevent(MouseEvent event) {
    }

    @FXML
    private void btnoffre(MouseEvent event) {
    }

    @FXML
    private void imageoffre(ActionEvent event) {
    }

    @FXML
    private void ajouteroffre(ActionEvent event) {
    }

    @FXML
    private void modifieroffrebtn(ActionEvent event) {
    }

    @FXML
    private void btnsupprimeroffre(ActionEvent event) {
    }

    @FXML
    private void selectOFFRE(MouseEvent event) {
    }

    @FXML
    private void payerbtn(MouseEvent event) {
    }
    
}
