/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxm;

import entities.Billet;
import entities.ResBillet;
import entities.SendEmail;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.BilletService;
import services.ResBilletService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class AjoutResBilletController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ComboBox<String> classe;
    @FXML
    private Text alertClasse;
    @FXML
    private Label Prix;
    @FXML
    private TextField nbrPas;
    @FXML
    private Text alertNbrPassager;
    @FXML
    private ComboBox<Integer> idB;
    @FXML
    private Text alertIdbillet;
    @FXML
    private ImageView im_qrcode;
    @FXML
    private Text alertNbrChambres1;
      PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    ObservableList<ResBillet> listResB;
    private List<ResBillet> resBillets = new ArrayList<>();
    private BilletService bs = new BilletService();
    @FXML
    private Button res;
    @FXML
    private Button re;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ObservableList<String> classeVol = FXCollections.observableArrayList("First Class", "Business Class", "Economy Class ");
        this.classe.setItems(classeVol);

        ObservableList<Integer> langs;
        try {
            langs = FXCollections.observableArrayList(bs.getIdBillet());
            idB.setItems(langs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void ajouterResBillet(ActionEvent event) throws SQLException, Exception {
        alertClasse.setText("");
        alertIdbillet.setText("");
        alertNbrPassager.setText("");
        ResBillet resB = new ResBillet();
        Boolean verif = true;

        // Controle classe vol
        if (classe.getValue() == null) {
            alertClasse.setText("Remplir le champs !!");
            verif = false;
        }

        // Control nbr Passager
        if (nbrPas.getText().equals("")) {
            alertNbrPassager.setText("Remplir le champs !!");
            verif = false;
        }
        // Control dateVol         
        if (idB.getValue() == null) {
            alertIdbillet.setText("Remplir le champs !!");
            verif = false;
        }

        if (verif == true) {

            BilletService bs = new BilletService();
            int prixV = bs.getprixVol(idB.getValue());

            float pourcentF = (float) (prixV * 0.2);
            float pourcentB = (float) (prixV * 0.3);
            float total = 0;

            if (classe.getValue() == "First Class") {
                total = (Integer.parseInt(nbrPas.getText()) * (prixV + pourcentF));
            } else if (classe.getValue() == "Business Class") {
                total = (Integer.parseInt(nbrPas.getText()) * (prixV + pourcentB));
            } else {
                total = (Integer.parseInt(nbrPas.getText()) * prixV);
            }

            resB = new ResBillet((int) total, Integer.parseInt(nbrPas.getText()), idB.getValue(), classe.getValue());
          
                ResBilletService resBs = new ResBilletService();
                try {

                    resBs.ajouterResBillet(resB);
                    SendEmail cc = new SendEmail("letstravel48@gmail.com", "travel12345", "bechir.mennai@esprit.tn", "Billet Reservée","<center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>Let's Travel </a></h1></td></tr></table></td> </tr><br> <br>"
             
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE Let's Travel </h2><h4 style=\"color: #ffffff;\" +\">let's Travel , Notre agence de voyage est une entreprise qui à pour vocation d'organiser , proposer des offres de voyages , de vols , d'hôtels et de séjours aux clients.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" ></h2><h2 style=\"margin-left:150px;\" +\">Bonjour Votre Tarif est  :  " + resB.getTarif()+"DT " +" </h2><p  style=\"margin-left:150px;\" +\" > le prix de votre réservation !!! </p>"
                + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + "</a></p></div></td></tr></table> </td></tr>"
                + " </center>");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("réservation billet ajoutée avec succés");
                    alert.show();
                    classe.setValue("");
                    nbrPas.setText("");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } 

            Notifications notificationBuilder = Notifications.create()
                    .title("Réservation billet ").text("Réservation a été ajoutée avec succées").graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
            
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/VolFront.fxml"));
        Parent root = loader.load();
        VolFrontController ac = loader.getController();
        res.getScene().setRoot(root);
        }

    @FXML
    private void retour(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/VolFront.fxml"));
        Parent root = loader.load();
        VolFrontController ac = loader.getController();
        re.getScene().setRoot(root);
    }
      
   
    
}
