/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ResBillet;
import entities.ResChambre;
import entities.SendEmail;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.BilletService;
import services.ChambreService;
import services.ResBilletService;
import services.ResChambreService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ResChambreController implements Initializable {

    @FXML
    private TextField filterFieldB;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField tarif;
    @FXML
    private Text alertTarif;
    @FXML
    private Label Prix;
    @FXML
    private TextField nbtJ;
    @FXML
    private Text alertNbrJours;
    @FXML
    private ComboBox<Integer> idCh;
    @FXML
    private Text alertIdchambre;
    @FXML
    private DatePicker dateRes;
    @FXML
    private Text alertDate;
    @FXML
    private TextField idSuppression;
    @FXML
    private TableView<ResChambre> tableResChambre;
    @FXML
    private TableColumn<?, ?> idResChambre;
    @FXML
    private TableColumn<?, ?> nbrJourREsCh;
    @FXML
    private TableColumn<?, ?> idChambre;
    @FXML
    private TableColumn<?, ?> tarifResChambre;
    @FXML
    private TableColumn<?, ?> dateResChambre;
    @FXML
    private Text alertNbrChambres1;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    ObservableList<ResChambre> listResCh;
    private List<ResChambre> resChambres = new ArrayList<>();
    private ChambreService chS = new ChambreService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<Integer> langs;
        try {
            langs = FXCollections.observableArrayList(chS.getIdChambre());
            idCh.setItems(langs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableResChambre.setOnMouseClicked(e -> {
            alertDate.setText("");
            alertIdchambre.setText("");
            alertNbrJours.setText("");
            alertTarif.setText("");

            try {
                String query = "select * from res_chambre where id = ?";
                TablePosition tablePosition = (TablePosition) tableResChambre.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableResChambre.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                System.out.println(data);
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    nbtJ.setText(String.valueOf(rs.getInt("nbr_j")));
                    dateRes.setValue(LocalDate.from(rs.getDate("date_res").toLocalDate()));
                    idCh.setValue(rs.getInt("chambre_id"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));

                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        afficherListeResChambre();
    }

    public void afficherListeResChambre() {

        ResChambreService resChs = new ResChambreService();
        try {
            List<ResChambre> resChambres = resChs.afficherResChambre();
            listResCh = FXCollections.observableArrayList(resChambres);
            tableResChambre.setItems(listResCh);
            idResChambre.setCellValueFactory(new PropertyValueFactory<>("id"));
            tarifResChambre.setCellValueFactory(new PropertyValueFactory<>("tarif"));
            nbrJourREsCh.setCellValueFactory(new PropertyValueFactory<>("nbr_j"));
            dateResChambre.setCellValueFactory(new PropertyValueFactory<>("date_res"));
            idChambre.setCellValueFactory(new PropertyValueFactory<>("chambre_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterResChambre(ActionEvent event) throws SQLException, Exception {
        alertDate.setText("");
        alertIdchambre.setText("");
        alertNbrJours.setText("");
        alertTarif.setText("");
        ResChambre resCh = new ResChambre();
        Boolean verif = true;

        // Control nbr jours
        if (nbtJ.getText().equals("")) {
            alertNbrJours.setText("Remplir le champs !!");
            verif = false;
        }
        // Control id Chambre         
        if (idCh.getValue() == null) {
            alertIdchambre.setText("Remplir le champs !!");
            verif = false;
        }

        // Control Date Arrivé
        if (dateRes.getValue() == null) {
            alertDate.setText("Remplir le champs !!");
            verif = false;
        }
        Date d1 = Date.valueOf(dateRes.getValue());
        Date d = new Date(System.currentTimeMillis());
        System.out.println(d);
        if (d1.before(d)) {
            alertDate.setText(" Date réservation  >  date d aujourd hui  !!");
            verif = false;
        }

        if (verif == true) {
            ChambreService chs = new ChambreService();

            int prix = chs.getprixChambre(idCh.getValue());
            int tariff = Integer.parseInt(nbtJ.getText()) * prix;
            resCh = new ResChambre(tariff, Integer.parseInt(nbtJ.getText()), Date.valueOf(dateRes.getValue()), idCh.getValue());

            if (!listResCh.contains(resCh)) {

                System.out.println("erreur");
                ResChambreService resChs = new ResChambreService();

                try {

                    resChs.ajouterResChambre(resCh);
                    SendEmail cc = new SendEmail("letstravel48@gmail.com", "travel12345", "hejer.ferjani@esprit.tn", "Chambre Reservée","<center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>Let's Travel </a></h1></td></tr></table></td> </tr><br> <br>"
             
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE Let's Travel </h2><h4 style=\"color: #ffffff;\" +\">let's Travel , Notre agence de voyage est une entreprise qui à pour vocation d'organiser , proposer des offres de voyages , de vols , d'hôtels et de séjours aux clients.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" ></h2><h2 style=\"margin-left:150px;\" +\">Bonjour Votre Tarif est  :  " + resCh.getTarif()+"DT " +" </h2><p  style=\"margin-left:150px;\" +\" > le prix de votre réservation !!! </p>"
                + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + "</a></p></div></td></tr></table> </td></tr>"
                + " </center>");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("réservation chambre ajoutée avec succés");
                    alert.show();
                    nbtJ.setText("");
                    idSuppression.setText("");

                    afficherListeResChambre();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("réservation chambre existe deja");
                alert.show();
            }

            Notifications notificationBuilder = Notifications.create()
                    .title("Réservation chambre").text("Réservation a été ajoutée avec succées").graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }

    }

    @FXML
    private void modifierResChambre(ActionEvent event) {

        alertDate.setText("");
        alertIdchambre.setText("");
        alertNbrJours.setText("");
        Boolean verif = true;
        // Control nbr jours
        if (nbtJ.getText().equals("")) {
            alertNbrJours.setText("Remplir le champs !!");
            verif = false;
        }
        // Control id Chambre         
        if (idCh.getValue() == null) {
            alertIdchambre.setText("Remplir le champs !!");
            verif = false;
        }

        // Control Date Arrivé
        if (dateRes.getValue() == null) {
            alertDate.setText("Remplir le champs !!");
            verif = false;
        }
        TablePosition tablePosition = (TablePosition) tableResChambre.getSelectionModel().getSelectedCells().get(0);

        int row = tablePosition.getRow();
        Object item = tableResChambre.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();

        if (verif == true) {
            ResChambre resCh = new ResChambre(data, 200, Integer.parseInt(nbtJ.getText()), Date.valueOf(dateRes.getValue()), idCh.getValue());
            ResChambreService resChs = new ResChambreService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cette réservation ", "modifier réservation chambre", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    resChs.modifierResChambre(resCh);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Réservation chambre Modifier avec succés");
                    alert.show();
                    nbtJ.setText("");
                    idSuppression.setText("");
                    afficherListeResChambre();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @FXML
    private void supprimerReschambre(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette réservation ", "Suppression réservation chambre", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            ResChambreService resChs = new ResChambreService();
            int id = (Integer.parseInt(idSuppression.getText()));
            System.out.println(id);
            try {
                resChs.supprimerResChambre(id);

                nbtJ.setText("");
                idSuppression.setText("");
                afficherListeResChambre();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

}
