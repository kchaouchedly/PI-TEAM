/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.Billet;
import entities.ResBillet;
import entities.SendEmail;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import org.controlsfx.control.Notifications;
import services.BilletService;
import services.ResBilletService;
import services.VolService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ResBilletController implements Initializable {

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
    private ComboBox<String> classe;
    private Text alertNumBillet;
    @FXML
    private Label Prix;
    @FXML
    private TextField nbrPas;
    private Text alertPrix;
    @FXML
    private ComboBox<Integer> idB;
    private Text alertDate;
    @FXML
    private TextField idSuppression;

    @FXML
    private TableColumn<?, ?> IdResBillet;
    @FXML
    private TableColumn<?, ?> classeVoll;
    @FXML
    private TableColumn<?, ?> nbrP;
    @FXML
    private TableColumn<?, ?> tarif;
    @FXML
    private TableColumn<?, ?> idBillet;
    @FXML
    private Text alertNbrChambres1;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    ObservableList<ResBillet> listResB;
    private List<ResBillet> resBillets = new ArrayList<>();
    private BilletService bs = new BilletService();
    @FXML
    private TableView<ResBillet> tableResBillets;
    @FXML
    private Text alertClasse;
    @FXML
    private Text alertNbrPassager;
    @FXML
    private Text alertIdbillet;
    @FXML
    private Button btnouvrir;
    private String deriterio;
    private static final String DIR = "QRDir";
    @FXML
    private ImageView im_qrcode;

    private FileChooser filechooser;
    private javafx.scene.image.Image image;
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //QRCODE
        deriterio = new File("").getAbsolutePath();
        deriterio += File.separator + DIR;
        File file = new File(deriterio);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        ObservableList<String> classeVol = FXCollections.observableArrayList("First Class", "Business Class", "Economy Class ");
        this.classe.setItems(classeVol);

        ObservableList<Integer> langs;
        try {
            langs = FXCollections.observableArrayList(bs.getIdBillet());
            idB.setItems(langs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableResBillets.setOnMouseClicked(e -> {

            try {
                String query = "select * from res_billet where id = ?";
                TablePosition tablePosition = (TablePosition) tableResBillets.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableResBillets.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                System.out.println(data);
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    classe.setValue(rs.getString("classe"));
                    nbrPas.setText(String.valueOf(rs.getInt("nbr_pas")));
                    this.idB.setValue(rs.getInt("billet_id"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));

                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

        afficherListeResBillet();
    }

    public void afficherListeResBillet() {

        ResBilletService resb = new ResBilletService();
        try {
            List<ResBillet> resbillets = resb.afficherResBillet();
            listResB = FXCollections.observableArrayList(resbillets);
            tableResBillets.setItems(listResB);
            IdResBillet.setCellValueFactory(new PropertyValueFactory<>("id"));
            classeVoll.setCellValueFactory(new PropertyValueFactory<>("classe"));
            nbrP.setCellValueFactory(new PropertyValueFactory<>("nbrPas"));
            tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
            idBillet.setCellValueFactory(new PropertyValueFactory<>("billet_id"));

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
            if (!listResB.contains(resB)) {

                System.out.println("erreur");
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
                    idSuppression.setText("");

                    afficherListeResBillet();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("réservation Billet existe deja");
                alert.show();
            }

            Notifications notificationBuilder = Notifications.create()
                    .title("Réservation billet ").text("Réservation a été ajoutée avec succées").graphic(null)
                    .hideAfter(Duration.seconds(10))
                    .position(Pos.BOTTOM_CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }

    }

    @FXML
    private void modifierResBillet(ActionEvent event) {

        alertClasse.setText("");
        alertIdbillet.setText("");
        alertNbrPassager.setText("");

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
        TablePosition tablePosition = (TablePosition) tableResBillets.getSelectionModel().getSelectedCells().get(0);

        int row = tablePosition.getRow();
        Object item = tableResBillets.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();

        if (verif == true) {
            ResBillet resB = new ResBillet(data, 400, Integer.parseInt(nbrPas.getText()), idB.getValue(), classe.getValue());
            ResBilletService resBs = new ResBilletService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cette réservation ", "modifier réservation billet", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    resBs.modifierResBillet(resB);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Réservation billet Modifier avec succés");
                    alert.show();
                    classe.setValue("");
                    nbrPas.setText("");
                    idSuppression.setText("");
                    afficherListeResBillet();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @FXML
    private void supprimerResBillet(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette réservation ", "Suppression réservation billet", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            ResBilletService resBS = new ResBilletService();
            int id = (Integer.parseInt(idSuppression.getText()));
            System.out.println(id);
            try {
                resBS.supprimerResBillet(id);
                classe.setValue("");
                nbrPas.setText("");
                idSuppression.setText("");
                afficherListeResBillet();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    public String readQRCode(String filePath, String charset, Map hintMap) throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        return qrCodeResult.getText();
    }

    @FXML
    private void ouvrir(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(deriterio));
        FileChooser.ExtensionFilter extfilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extfilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extfilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extfilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extfilterpng, extfilterJPG, extfilterjpg, extfilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file.getAbsolutePath()));
                im_qrcode.setImage(image);
                String charset = "UTF-8";
                Map hintMap = new HashMap();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                String readQRCode = readQRCode(file.getAbsolutePath(), charset, hintMap);

                classe.setValue(readQRCode.substring(readQRCode.indexOf("classe vol:") + 11, readQRCode.indexOf("Nombre de passagers:")));
                nbrPas.setText(readQRCode.substring(readQRCode.indexOf("Nombre de passagers:") + 20, readQRCode.indexOf("id billet:")));
//               String s="";
//               s = readQRCode.substring(readQRCode.indexOf("id billet:") + 10, readQRCode.length());

//                System.out.println(Integer.parseInt(s));
                //idB.setValue(Integer.valueOf(s));
                //  System.out.println(readQRCode.substring(readQRCode.indexOf("id billet:") + 10, readQRCode.length()));
                // Image img = new Image(filImg.toURI().toString());
            } catch (IOException | NotFoundException ex) {
                System.out.println(ex);
            }

        }
    }

    @FXML
    private void GérerQR(ActionEvent event) {

        String id = idSuppression.getText();
        if (!id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Votre Code QR Gérer Avec Succès");
            alert.show();
            try {
                String cnt_qrcode = "";
                cnt_qrcode += "classe vol:" + classe.getValue() + "\n";
                cnt_qrcode += "Nombre de passagers:" + nbrPas.getText() + "\n";
                cnt_qrcode += "id billet:" + idB.getValue() + "\n";

                FileOutputStream fout = new FileOutputStream(deriterio + File.separator + id + ".png");
                ByteArrayOutputStream bos = QRCode.from(cnt_qrcode).withSize(125, 125).to(net.glxn.qrgen.image.ImageType.JPG).stream();

                fout.write(bos.toByteArray());
                bos.close();
                fout.close();
                fout.flush();
                Image image = new Image(new FileInputStream(deriterio + File.separator + id + ".png"));
                im_qrcode.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
