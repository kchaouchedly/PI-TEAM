/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.iio.ImageStorage.ImageType;

import entities.Hotel;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pidevjava.HotelFXMain;
import services.HotelService;
import utils.MyDB;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import entities.FavHotel;
import entities.SendEmail;
import entities.PDf;
import fxm.FavHotelController;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.exception.QRGenerationException;
import org.controlsfx.control.Notifications;
import services.FavHotelService;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class HotelController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ComboBox<String> nomh;

    @FXML
    private TextField nbrChambre;
    @FXML
    private TextField codeH;
    @FXML
    private TextField telHotel;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email;
    @FXML
    private TextField imageH;
    @FXML
    private Text alertNom;
    @FXML
    private Text alertNumHotel;
    @FXML
    private Text alertNbrChambres;
    @FXML
    private Text alertCodeH;
    @FXML
    private Text alertAdresse;
    @FXML
    private Text alertEmail;
    @FXML
    private TableView<Hotel> tableHotels;

    ObservableList<Hotel> listH;

    private List<Hotel> hotels = new ArrayList<>();
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nomHotel;
    @FXML
    private TableColumn<?, ?> numTel;
    @FXML
    private TableColumn<?, ?> adresseH;
    @FXML
    private TableColumn<?, ?> nbrE;
    @FXML
    private TableColumn<?, ?> nbrCh;
    @FXML
    private TableColumn<?, ?> emailH;
    @FXML
    private TableColumn<?, ?> codeHotel;
    @FXML
    private TextField idSuppression;

    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    @FXML
    private TextField nbeEtoiles;
    @FXML
    private Text alertNbrEtoile;
    @FXML
    private Text alertNbrChambres1;
    @FXML
    private TableColumn<?, ?> img;
    @FXML
    private Text alerteImageHotel;
    @FXML
    private TextField filterField;
    @FXML
    private ImageView im_qrcode;
    private String deriterio;
    private static final String DIR = "QRDir";
    @FXML
    private Button btnouvrir;
    private FileChooser filechooser;
    private javafx.scene.image.Image image;
    private File file;
    private ImageView img1;
    @FXML
    private Button insérer;
    @FXML
    private Button affichFav;
    @FXML
    private RadioButton triId;
    @FXML
    private RadioButton TriNom;
    @FXML
    private RadioButton triNbrCh;
    @FXML
    private RadioButton triCode;
    @FXML
    private RadioButton triNbrE;
    @FXML
    private RadioButton triEmail;
    @FXML
    private Button statistiques;

    /**
     * Initializes the controller class.
     */
    public void afficherListeHotel() {

        HotelService hs = new HotelService();
        try {
            List<Hotel> hotels = hs.afficherHotel();
            listH = FXCollections.observableArrayList(hotels);
            tableHotels.setItems(listH);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomHotel.setCellValueFactory(new PropertyValueFactory<>("nomHotel"));
            numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
            codeHotel.setCellValueFactory(new PropertyValueFactory<>("codeH"));
            adresseH.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            nbrE.setCellValueFactory(new PropertyValueFactory<>("nbrEtoiles"));
            nbrCh.setCellValueFactory(new PropertyValueFactory<>("nbrChambre"));
            emailH.setCellValueFactory(new PropertyValueFactory<>("email"));
            img.setCellValueFactory(new PropertyValueFactory<>("imageHotel"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        triId.setSelected(false);
        TriNom.setSelected(false);
        triCode.setSelected(false);
        triEmail.setSelected(false);
        triNbrE.setSelected(false);
        triNbrCh.setSelected(false);
        
        ObservableList<String> nomHotel = FXCollections.observableArrayList("iberostar", "Goldanyasmin", "TabarcaThalasou ", "BravoDjerba ", "LaBadira","RamadaPlaza","RoyalThalassa","forSeasonTes");
        nomh.setItems(nomHotel);

        //QRCODE
        deriterio = new File("").getAbsolutePath();
        deriterio += File.separator + DIR;
        File file = new File(deriterio);
        if (!file.isDirectory()) {
            file.mkdir();
        }

        // TODO
        afficherListeHotel();
        RechercheHotel();
        tableHotels.setOnMouseClicked(e -> {
            alertNumHotel.setText("");
            alertNom.setText("");
            alertEmail.setText("");
            alertAdresse.setText("");
            alertCodeH.setText("");
            alertNbrEtoile.setText("");
            alertNbrChambres.setText("");
            alerteImageHotel.setText("");
            try {
                String query = "select * from hotel where id = ?";
                TablePosition tablePosition = (TablePosition) tableHotels.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableHotels.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    codeH.setText(String.valueOf(rs.getInt("code_h")));
                    nbrChambre.setText(String.valueOf(rs.getInt("nbr_chambre")));
                    nbeEtoiles.setText(String.valueOf(rs.getInt("nbr_etoiles")));
                    telHotel.setText(String.valueOf(rs.getInt("num_tel")));
                    adresse.setText(rs.getString("adresse"));
                    nomh.setValue(rs.getString("nom_hotel"));
                    email.setText(rs.getString("email"));
                    imageH.setText(rs.getString("image_hotel"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));

                    File filImg = new File(rs.getString("image_hotel"));
                    Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
                    fruitImg.setImage(img);
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

    }

    private void RechercheHotel() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Hotel> filteredData = new FilteredList<>(listH, b -> true);

        //System.out.println(listH);
        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(hotel -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (hotel.getNomHotel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (hotel.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(hotel.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(hotel.getCodeH()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(hotel.getNbrEtoiles()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(hotel.getNumTel()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Hotel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableHotels.comparatorProperty());
        tableHotels.setItems(sortedData);
    }

    @FXML
    private void ajouterHotel(ActionEvent event) {

        alertNumHotel.setText("");
        alertNom.setText("");
        alertEmail.setText("");
        alertAdresse.setText("");
        alertCodeH.setText("");
        alertNbrEtoile.setText("");
        alertNbrChambres.setText("");
        alerteImageHotel.setText("");
        Hotel h = new Hotel();
        Boolean verif = true;
        ControlHotel control = new ControlHotel();
        //Controle Code hotel
        if (codeH.getText().equals("")) {
            alertCodeH.setText("Remplir le champs !!");
            verif = false;
        } else if (!codeH.getText().matches("[\\d\\.]+")) {
            alertCodeH.setText("Code hotel Vol doit être un entier !!");
            verif = false;
        }
        // Controle Nombre Chambre
        if (nbrChambre.getText().equals("")) {
            alertNbrChambres.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbrChambre.getText().matches("[\\d\\.]+")) {
            alertNbrChambres.setText("Nombres Chambres Vol doit être un entier !!");
            verif = false;
        } else if (Integer.parseInt(nbrChambre.getText()) <= 20) {
            alertNbrChambres.setText("Nombre Chambres doit être > 20 !!");
            verif = false;
        }
        // Controle Nom Hotel
        if (nomh.getValue().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        } 
        // Controle Numéro Téléphone
        if (telHotel.getText().equals("")) {
            alertNumHotel.setText("Remplir le champs !!");
            verif = false;
        } else if (!telHotel.getText().matches("[\\d\\.]+")) {
            alertNumHotel.setText("Numéro Téléphone doit être un entier !!");
            verif = false;
        } else if ((!control.ControleTel(Integer.parseInt(telHotel.getText())))) {
            alertNumHotel.setText("Numéro de telephone doit etre composé de 8 chiffres !!");
            verif = false;
        }
        // Controle Adresse Hotel
        if (adresse.getText().equals("")) {
            alertAdresse.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleAdresse(adresse.getText()))) {
            alertAdresse.setText("Adresse comporte que des caractéres !!");
            verif = false;
        } else if (adresse.getText().length() <= 4) {
            alertAdresse.setText("Adresse au moins 5 caractéres !!");
            verif = false;
        }
        // Controle Email       
        if (email.getText().equals("")) {
            alertEmail.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleEmail(email.getText()))) {
            alertEmail.setText("Email non valide !!");
            verif = false;
        }
        // Controle Nombres des étoiles 
        if (nbeEtoiles.getText().equals("")) {
            alertNbrEtoile.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbeEtoiles.getText().matches("[\\d\\.]+")) {
            alertNbrEtoile.setText("Nombres étoiles Hotel doit être un entier !!");
            verif = false;
        } else if ((!control.controlNbrEtoiles(Integer.parseInt(nbeEtoiles.getText())))) {
            alertNbrEtoile.setText("Le nombre des étoiles entre 2 et 5 !!");
            verif = false;
        }
        //control image Hotel
        if (imageH.getText().equals("")) {
            alerteImageHotel.setText(" Insérer une Image !!");
            verif = false;
        }
        if (verif == true) {
            h = new Hotel(Integer.parseInt(telHotel.getText()), Integer.parseInt(nbeEtoiles.getText()), Integer.parseInt(nbrChambre.getText()), Integer.parseInt(codeH.getText()), nomh.getValue(), adresse.getText(), email.getText(), imageH.getText());
            HotelService hs = new HotelService();
            if (!listH.contains(h)) {
                try {
                    hs.ajouterHotel(h);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Hotel ajoutée avec succés");
                    alert.show();
                    telHotel.setText("");
                    nomh.setValue("");
                    email.setText("");
                    adresse.setText("");
                    imageH.setText("");
                    nbrChambre.setText("");
                    codeH.setText("");
                    nbeEtoiles.setText("");
                    idSuppression.setText("");
                    afficherListeHotel();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Hotel existe deja");
                alert.show();
            }
        }
    }

    @FXML
    private void supprimerHotel(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cet hotel ", "Suppression Hotel", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            HotelService hs = new HotelService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                hs.supprimerHotel(id);
                idSuppression.setText("");
                telHotel.setText("");
                nomh.setValue("");
                email.setText("");
                adresse.setText("");
                imageH.setText("");
                nbrChambre.setText("");
                codeH.setText("");
                nbeEtoiles.setText("");
                afficherListeHotel();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void modifierHotel(ActionEvent event) {
        TablePosition tablePosition = (TablePosition) tableHotels.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        Object item = tableHotels.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
        Boolean verif = true;
        ControlHotel control = new ControlHotel();
        //Controle Code hotel
        if (codeH.getText().equals("")) {
            alertCodeH.setText("Remplir le champs !!");
            verif = false;
        } else if (!codeH.getText().matches("[\\d\\.]+")) {
            alertCodeH.setText("Code hotel Vol doit être un entier !!");
            verif = false;
        }
        // Controle Nombre Chambre
        if (nbrChambre.getText().equals("")) {
            alertNbrChambres.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbrChambre.getText().matches("[\\d\\.]+")) {
            alertNbrChambres.setText("Nombres Chambres Vol doit être un entier !!");
            verif = false;
        } else if (Integer.parseInt(nbrChambre.getText()) <= 20) {
            alertNbrChambres.setText("Nombre Chambres doit être > 20 !!");
            verif = false;
        }
        // Controle Nom Hotel
        if (nomh.getValue().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        }
        // Controle Numéro Téléphone
        if (telHotel.getText().equals("")) {
            alertNumHotel.setText("Remplir le champs !!");
            verif = false;
        } else if (!telHotel.getText().matches("[\\d\\.]+")) {
            alertNumHotel.setText("Numéro Téléphone doit être un entier !!");
            verif = false;
        } else if ((!control.ControleTel(Integer.parseInt(telHotel.getText())))) {
            alertNumHotel.setText("Numéro de telephone doit etre composé de 8 chiffres !!");
            verif = false;
        }
        // Controle Adresse Hotel
        if (adresse.getText().equals("")) {
            alertAdresse.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleAdresse(adresse.getText()))) {
            alertAdresse.setText("Adresse comporte que des caractéres !!");
            verif = false;
        } else if (adresse.getText().length() <= 4) {
            alertAdresse.setText("Adresse au moins 5 caractéres !!");
            verif = false;
        }
        // Controle Email       
        if (email.getText().equals("")) {
            alertEmail.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleEmail(email.getText()))) {
            alertEmail.setText("Email non valide !!");
            verif = false;
        }
        // Controle Nombres des étoiles 
        if (nbeEtoiles.getText().equals("")) {
            alertNbrEtoile.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbeEtoiles.getText().matches("[\\d\\.]+")) {
            alertNbrEtoile.setText("Nombres étoiles Hotel doit être un entier !!");
            verif = false;
        } else if ((!control.controlNbrEtoiles(Integer.parseInt(nbeEtoiles.getText())))) {
            alertNbrEtoile.setText("Le nombre des étoiles entre 2 et 5 !!");
            verif = false;
        }
        //control image Hotel
        if (imageH.getText().equals("")) {
            alerteImageHotel.setText("Insérer une Image !!");
            verif = false;
        }

        if (verif == true) {
            Hotel h = new Hotel(data, Integer.parseInt(telHotel.getText()), Integer.parseInt(nbeEtoiles.getText()), Integer.parseInt(nbrChambre.getText()), Integer.parseInt(codeH.getText()), nomh.getValue(), adresse.getText(), email.getText(), imageH.getText());
            HotelService hs = new HotelService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cet hotel ", "modifier hotel", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    hs.modifierHotel(h);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Hotel Modifier avec succés");
                    alert.show();
                    telHotel.setText("");
                    nomh.setValue("");
                    email.setText("");
                    adresse.setText("");
                    imageH.setText("");
                    nbrChambre.setText("");
                    codeH.setText("");
                    nbeEtoiles.setText("");
                    idSuppression.setText("");
                    alertNumHotel.setText("");
                    alertNom.setText("");
                    alertEmail.setText("");
                    alertAdresse.setText("");
                    alertCodeH.setText("");
                    alertNbrEtoile.setText("");
                    alertNbrChambres.setText("");
                    alerteImageHotel.setText("");

                    afficherListeHotel();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public String readQRCode(String filePath, String charset, Map hintMap) throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        return qrCodeResult.getText();
    }

    @FXML
    private void GérerQR(ActionEvent event) {

        String nome = nomh.getValue();
        if (!nome.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Votre Code QR Gérer Avec Succès");
            alert.show();
            try {
                String cnt_qrcode = "";
                cnt_qrcode += "Nom Hotel:" + nomh.getValue()+ "\n";
                cnt_qrcode += "Nombre étoiles:" + nbeEtoiles.getText() + "\n";
                cnt_qrcode += "Nombre des chamres:" + nbrChambre.getText() + "\n";
                cnt_qrcode += "Code Hotel:" + codeH.getText() + "\n";
                cnt_qrcode += "Numéro Téléphone:" + telHotel.getText() + "\n";
                cnt_qrcode += "Adresse:" + adresse.getText() + "\n";
                cnt_qrcode += "Image:" + imageH.getText() + "\n";
                cnt_qrcode += "Email:" + email.getText() + "\n";

                FileOutputStream fout = new FileOutputStream(deriterio + File.separator + nome + ".png");
                ByteArrayOutputStream bos = QRCode.from(cnt_qrcode).withSize(125, 125).to(net.glxn.qrgen.image.ImageType.JPG).stream();

                fout.write(bos.toByteArray());
                bos.close();
                fout.close();
                fout.flush();
                Image image = new Image(new FileInputStream(deriterio + File.separator + nome + ".png"));
                im_qrcode.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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

                nomh.setValue(readQRCode.substring(readQRCode.indexOf("Nom Hotel:") + 10, readQRCode.indexOf("Nombre étoiles:")));
                nbeEtoiles.setText(readQRCode.substring(readQRCode.indexOf("Nombre étoiles:") + 15, readQRCode.indexOf("Nombre des chamres:")));
                nbrChambre.setText(readQRCode.substring(readQRCode.indexOf("Nombre des chamres:") + 19, readQRCode.indexOf("Code Hotel:")));
                codeH.setText(readQRCode.substring(readQRCode.indexOf("Code Hotel:") + 11, readQRCode.indexOf("Numéro Téléphone:")));
                telHotel.setText(readQRCode.substring(readQRCode.indexOf("Numéro Téléphone:") + 17, readQRCode.indexOf("Adresse:")));
                adresse.setText(readQRCode.substring(readQRCode.indexOf("Adresse:") + 8, readQRCode.indexOf("Image:")));
                imageH.setText(readQRCode.substring(readQRCode.indexOf("Image:") + 6, readQRCode.indexOf("Email:")));
                email.setText(readQRCode.substring(readQRCode.indexOf("Email:") + 6, readQRCode.length()));

                File filImg = new File(imageH.getText());
                //Image img = new Image(filImg.toURI().toString());
                Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
                fruitImg.setImage(img);

            } catch (IOException | NotFoundException ex) {
                System.out.println(ex);
            }

        }
    }

    @FXML
    private void insérerImg(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("Séléctionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        insérer.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            System.out.println(file);
            if (file != null) {
                String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                imageH.setText(file.toString());
                image = new javafx.scene.image.Image(file.toURI().toString(), 179, 143, true, true);
                fruitImg.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter image");
            }
        });

    }

    @FXML
    private void pdf(ActionEvent event) {

        PDf pd = new PDf();
        try {
            pd.GeneratePdfHotel("La Liste Des Hotels");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void favoris(ActionEvent event) {

        alertNumHotel.setText("");
        alertNom.setText("");
        alertEmail.setText("");
        alertAdresse.setText("");
        alertCodeH.setText("");
        alertNbrEtoile.setText("");
        alertNbrChambres.setText("");
        alerteImageHotel.setText("");
        FavHotel h = new FavHotel();
        Boolean verif = true;
        ControlHotel control = new ControlHotel();

        //control Liste favoris
        if (imageH.getText().equals("") || nbeEtoiles.getText().equals("") || email.getText().equals("") || telHotel.getText().equals("")
                || (!nbeEtoiles.getText().matches("[\\d\\.]+")) || codeH.getText().equals("") || (!codeH.getText().matches("[\\d\\.]+"))
                || adresse.getText().equals("") || nbrChambre.getText().equals("") || (!nbrChambre.getText().matches("[\\d\\.]+"))
                || ((!control.controlNbrEtoiles(Integer.parseInt(nbeEtoiles.getText())))) || ((!control.ControleEmail(email.getText())))
                || ((!control.ControleAdresse(adresse.getText()))) || (nomh.getValue().equals("")) || (!(control.ControleNomHotel(nomh.getValue())))
                || (!telHotel.getText().matches("[\\d\\.]+")) || ((!control.ControleTel(Integer.parseInt(telHotel.getText()))))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Aucun Hotel a été selectionné ");
            alert.show();
            verif = false;
        }
        if (verif == true) {
            h = new FavHotel(Integer.parseInt(telHotel.getText()), Integer.parseInt(nbeEtoiles.getText()), Integer.parseInt(nbrChambre.getText()), Integer.parseInt(codeH.getText()), nomh.getValue(), adresse.getText(), email.getText(), imageH.getText());
            FavHotelService hs = new FavHotelService();
            List<FavHotel> hotels = null;
            try {
                hotels = hs.afficherFavHotel();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            if (!hotels.contains(h)) {
                try {
                    hs.ajouterFavHotel(h);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Hotel ajoutée a la liste des favoris");
                    alert.show();
                    telHotel.setText("");
                    nomh.setValue("");
                    email.setText("");
                    adresse.setText("");
                    imageH.setText("");
                    nbrChambre.setText("");
                    codeH.setText("");
                    nbeEtoiles.setText("");
                    idSuppression.setText("");
                    afficherListeHotel();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Hotel est déja favori");
                alert.show();
            }
        }
    }

    @FXML
    private void affichFav(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/FavHotel.fxml"));
        Parent root = loader.load();
        FavHotelController ac = loader.getController();
        affichFav.getScene().setRoot(root);
    }

    @FXML
    private void TriNom(MouseEvent event) throws SQLException {

        triId.setSelected(false);
        TriNom.setSelected(true);
        triCode.setSelected(false);
        triEmail.setSelected(false);
        triNbrE.setSelected(false);
        triNbrCh.setSelected(false);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.TrierHotelParNom();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void triId(MouseEvent event) throws SQLException {
        triId.setSelected(true);
        TriNom.setSelected(false);
        triCode.setSelected(false);
        triEmail.setSelected(false);
        triNbrE.setSelected(false);
        triNbrCh.setSelected(false);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.afficherHotel();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void triCode(MouseEvent event) throws SQLException {
        triId.setSelected(false);
        triNbrCh.setSelected(false);
        TriNom.setSelected(false);
        triCode.setSelected(true);
        triEmail.setSelected(false);
        triNbrE.setSelected(false);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.TrierHotelParCode();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void triNbrE(MouseEvent event) throws SQLException {
        triId.setSelected(false);
        TriNom.setSelected(false);
        triCode.setSelected(false);
        triEmail.setSelected(false);
        triNbrE.setSelected(true);
        triNbrCh.setSelected(false);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.TrierHotelParNbrE();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void triNbrCh(MouseEvent event) throws SQLException {
        triId.setSelected(false);
        TriNom.setSelected(false);
        triCode.setSelected(false);
        triEmail.setSelected(false);
        triNbrE.setSelected(false);
        triNbrCh.setSelected(true);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.TrierHotelParNbrChambre();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void triEmail(MouseEvent event) throws SQLException {
        triId.setSelected(false);
        TriNom.setSelected(false);
        triCode.setSelected(false);
        triEmail.setSelected(true);
        triNbrE.setSelected(false);
        triNbrCh.setSelected(false);
        HotelService cs = new HotelService();
        ObservableList<Hotel> items = FXCollections.observableArrayList();
        List<Hotel> listHotel = cs.TrierHotelParEmail();
        for (Hotel h : listHotel) {
            items.add(h);
        }

        tableHotels.setItems(items);
    }

    @FXML
    private void Stat(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/DashboardH.fxml"));
        Parent root = loader.load();
        HotelDashboard ac = loader.getController();
        statistiques.getScene().setRoot(root);
    }

}
