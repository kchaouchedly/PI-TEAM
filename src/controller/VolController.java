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
import entities.FavVol;
import entities.Hotel;
import entities.PDf;
import entities.Vol;
import fxm.FavVolController;
import fxm.VolFrontController;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import services.FavVolService;
import services.HotelService;
import services.VolService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class VolController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField NumVol;
    private Text alertNom;
    @FXML
    private TextField nbrPlace;
    private Text alertNbrEtoile;
    @FXML
    private TextField VilleDepart;
    private Text alertNbrChambres;

    private Text alertCodeH;
    @FXML
    private TextField imageVol;
    private Text alertNumHotel;
    private Text alertAdresse;
    @FXML
    private DatePicker DateDepart;
    private Text alertEmail;
    @FXML
    private DatePicker DateArrive;
    @FXML
    private TextField idSuppression;
    @FXML
    private TableView<Vol> tableVols;
    @FXML
    private TableColumn<?, ?> id;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    ObservableList<Vol> listV;
    private List<Vol> vols = new ArrayList<>();
    @FXML
    private TableColumn<?, ?> NumVoll;
    @FXML
    private TableColumn<?, ?> NbrPlacee;
    @FXML
    private TableColumn<?, ?> VilleDepartt;
    @FXML
    private TableColumn<?, ?> VilleArrivee;
    @FXML
    private TableColumn<?, ?> DateDepartt;
    @FXML
    private TableColumn<?, ?> DateArrivee;
    @FXML
    private TableColumn<?, ?> typevoll;
    @FXML
    private TableColumn<?, ?> imageVoll;
    @FXML
    private ComboBox<String> typeV;
    @FXML
    private Text alertNumVol;
    @FXML
    private Text alertNbrPlace;
    @FXML
    private Text alertVilleDepart;
    @FXML
    private Text alertVilleArrivee;
    @FXML
    private Text alertImageVol;
    @FXML
    private Text alerteTypeVol;
    @FXML
    private Text alertDateDepart;
    @FXML
    private Text alerteDateArrivee;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField filterFieldV;
    @FXML
    private ComboBox<String> VilleArrv;
    @FXML
    private Button statistiques;
    @FXML
    private Button insererImgV;

    private FileChooser filechooser;
    private javafx.scene.image.Image image;
    private File file;
    @FXML
    private RadioButton ville_depart;
    @FXML
    private RadioButton date_depart;
    @FXML
    private RadioButton ville_arrive;
    @FXML
    private RadioButton nbr_place;
    @FXML
    private Button affchFav;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ville_depart.setSelected(false);
        ville_arrive.setSelected(false);
        date_depart.setSelected(false);
        nbr_place.setSelected(false);

        ObservableList<String> langs = FXCollections.observableArrayList("Aller", "Retour", "Aller-Retour");
        typeV.setItems(langs);

        ObservableList<String> Ville = FXCollections.observableArrayList("Paris", "Dubai", "Espagne", "Turquie", "Italie", "Allemagne", "Chine", "Egypte");
        VilleArrv.setItems(Ville);

        afficherListeVol();
        RechercheVol();
        tableVols.setOnMouseClicked(e -> {
            try {
                alertNumVol.setText("");
                alertNbrPlace.setText("");
                alertVilleDepart.setText("");
                alertVilleArrivee.setText("");
                alertImageVol.setText("");
                alerteTypeVol.setText("");
                alertDateDepart.setText("");
                alerteDateArrivee.setText("");
                String query = "select * from vol where id = ?";
                TablePosition tablePosition = (TablePosition) tableVols.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableVols.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    idSuppression.setText(String.valueOf(rs.getInt("id")));
                    NumVol.setText(String.valueOf(rs.getInt("num_vol")));
                    nbrPlace.setText(String.valueOf(rs.getInt("nbr_place")));
                    VilleDepart.setText(rs.getString("ville_depart"));
                    VilleArrv.setValue(rs.getString("ville_arrive"));
                    imageVol.setText(rs.getString("image_vol"));
                    DateArrive.setValue(LocalDate.from(rs.getDate("date_arrive").toLocalDate()));
                    DateDepart.setValue(LocalDate.from(rs.getDate("date_depart").toLocalDate()));
                    typeV.setValue(rs.getString("type_v"));
                    File filImg = new File(rs.getString("image_vol"));
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

    public void afficherListeVol() {
        VolService vs = new VolService();
        try {
            List<Vol> vols = vs.afficherVol();
            listV = FXCollections.observableArrayList(vols);
            tableVols.setItems(listV);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            NumVoll.setCellValueFactory(new PropertyValueFactory<>("numVol"));
            NbrPlacee.setCellValueFactory(new PropertyValueFactory<>("nbrPlace"));
            VilleDepartt.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
            VilleArrivee.setCellValueFactory(new PropertyValueFactory<>("villeArrive"));
            DateDepartt.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
            DateArrivee.setCellValueFactory(new PropertyValueFactory<>("dateArrive"));
            imageVoll.setCellValueFactory(new PropertyValueFactory<>("imageVol"));
            typevoll.setCellValueFactory(new PropertyValueFactory<>("typeV"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void RechercheVol() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Vol> filteredData = new FilteredList<>(listV, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFieldV.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vol -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (vol.getTypeV().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (vol.getVilleArrive().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (vol.getVilleDepart().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(vol.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(vol.getNbrPlace()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(vol.getNumVol()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(vol.getDateArrive()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(vol.getDateDepart()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Vol> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableVols.comparatorProperty());
        tableVols.setItems(sortedData);
    }

    @FXML
    private void ajouterVol(ActionEvent event) {
        alertNumVol.setText("");
        alertNbrPlace.setText("");
        alertVilleDepart.setText("");
        alertVilleArrivee.setText("");
        alertImageVol.setText("");
        alerteTypeVol.setText("");
        alertDateDepart.setText("");
        alerteDateArrivee.setText("");
        Vol v = new Vol();
        Boolean verif = true;
        ControlVol control = new ControlVol();
        // Control Numéro Vol
        if (NumVol.getText().equals("")) {
            alertNumVol.setText("Remplir le champs !!");
            verif = false;
        } else if (!NumVol.getText().matches("[\\d\\.]+")) {
            alertNumVol.setText("Numéro Vol doit être un entier !!");
            verif = false;
        }
        // Control Nombre Places
        if (nbrPlace.getText().equals("")) {
            alertNbrPlace.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbrPlace.getText().matches("[\\d\\.]+")) {
            alertNbrPlace.setText("Nombres doit être un entier !!");
            verif = false;
        } else if (!control.controlNbrPlace(Integer.parseInt(nbrPlace.getText()))) {
            alertNbrPlace.setText("Nombre Place >= 100 !!");
            verif = false;
        }
        // Control Ville Départ
        if (VilleDepart.getText().equals("")) {
            alertVilleDepart.setText("Remplir le champs !!");
            verif = false;
        } else if (!(control.ControleVille(VilleDepart.getText()))) {
            alertVilleDepart.setText("Nom ville comporte que des caractéres !!");
            verif = false;
        } else if (VilleDepart.getText().length() <= 4) {
            alertVilleDepart.setText("Nom ville au moins 5 caractéres !!");
            verif = false;
        }
        // Control Ville Arrivé
        if (VilleArrv.getValue() == null) {
            alertVilleArrivee.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Image Vol        
        if (imageVol.getText().equals("")) {
            alertImageVol.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Type Vol
        if (typeV.getValue() == null) {
            alerteTypeVol.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Date Départ
        if (DateDepart.getValue() == null) {
            alertDateDepart.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Date Arrivé
        if (DateArrive.getValue() == null) {
            alerteDateArrivee.setText("Remplir le champs !!");
            verif = false;
        }
        if ((DateArrive.getValue() != null) && (DateDepart.getValue() != null)) {
            // Control Date Arrivé < Date Départ
            Date d1 = Date.valueOf(DateDepart.getValue());
            Date d2 = Date.valueOf(DateArrive.getValue());

            Date d = new Date(System.currentTimeMillis());
            System.out.println(d);
            if (d1.before(d)) {
                alertDateDepart.setText(" Date depart  >  date d aujourd hui  !!");
                verif = false;
            }

            if (d2.before(d1)) {
                alerteDateArrivee.setText(" Date Arrivé < Date Départ !!");
                verif = false;
            }

        }
        if (verif == true) {
            v = new Vol(Integer.parseInt(NumVol.getText()), Integer.parseInt(nbrPlace.getText()), Date.valueOf(DateDepart.getValue()), Date.valueOf(DateArrive.getValue()), VilleDepart.getText(), VilleArrv.getValue(), imageVol.getText(), typeV.getValue());

            VolService vs = new VolService();
            if (!listV.contains(v)) {
                try {
                    vs.ajouterVol(v);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Vol ajoutée avec succés");
                    alert.show();
                    NumVol.setText("");
                    nbrPlace.setText("");
                    VilleDepart.setText("");
                    //VilleArrive.setText("");
                    imageVol.setText("");
                    idSuppression.setText("");

                    afficherListeVol();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("vol existe deja");
                alert.show();
            }
        }
    }

    @FXML
    private void modiferVol(ActionEvent event) {
        TablePosition tablePosition = (TablePosition) tableVols.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        Object item = tableVols.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
        Boolean verif = true;
        ControlVol control = new ControlVol();
        // Control Numéro Vol
        if (NumVol.getText().equals("")) {
            alertNumVol.setText("Remplir le champs !!");
            verif = false;
        } else if (!NumVol.getText().matches("[\\d\\.]+")) {
            alertNumVol.setText("Numéro Vol doit être un entier !!");
            verif = false;
        }
        // Control Nombre Places
        if (nbrPlace.getText().equals("")) {
            alertNbrPlace.setText("Remplir le champs !!");
            verif = false;
        } else if (!nbrPlace.getText().matches("[\\d\\.]+")) {
            alertNbrPlace.setText("Nombres doit être un entier !!");
            verif = false;
        } else if (!control.controlNbrPlace(Integer.parseInt(nbrPlace.getText()))) {
            alertNbrPlace.setText("Nombre Place >= 100 !!");
            verif = false;
        }
        // Control Ville Départ
        if (VilleDepart.getText().equals("")) {
            alertVilleDepart.setText("Remplir le champs !!");
            verif = false;
        } else if (!(control.ControleVille(VilleDepart.getText()))) {
            alertVilleDepart.setText("Nom ville comporte que des caractéres !!");
            verif = false;
        } else if (VilleDepart.getText().length() <= 4) {
            alertVilleDepart.setText("Nom ville au moins 5 caractéres !!");
            verif = false;
        }
        // Control Ville Arrivé
        if (VilleArrv.getValue() == null) {
            alertVilleArrivee.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Image Vol        
        if (imageVol.getText().equals("")) {
            alertImageVol.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Type Vol
        if (typeV.getValue() == null) {
            alerteTypeVol.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Date Départ
        if (DateDepart.getValue() == null) {
            alertDateDepart.setText("Remplir le champs !!");
            verif = false;
        }
        // Control Date Arrivé
        if (DateArrive.getValue() == null) {
            alerteDateArrivee.setText("Remplir le champs !!");
            verif = false;
        }
        if ((DateArrive.getValue() != null) && (DateDepart.getValue() != null)) {
            // Control Date Arrivé < Date Départ
            Date d1 = Date.valueOf(DateDepart.getValue());
            Date d2 = Date.valueOf(DateArrive.getValue());

            Date d = new Date(System.currentTimeMillis());
            if (d1.before(d)) {
                alertDateDepart.setText(" Date depart  >  date d aujourd hui  !!");
                verif = false;
            }

            if (d2.before(d1)) {
                alerteDateArrivee.setText(" Date Arrivé < Date Départ !!");
                verif = false;
            }
        }
        if (verif == true) {
            Vol v = new Vol(data, Integer.parseInt(NumVol.getText()), Integer.parseInt(nbrPlace.getText()), Date.valueOf(DateDepart.getValue()), Date.valueOf(DateArrive.getValue()), VilleDepart.getText(), VilleArrv.getValue(), imageVol.getText(), typeV.getValue());
            VolService vs = new VolService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer ce vol ", "modifier vol", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    vs.modifierVol(v);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Vol Modifier avec succés");
                    alert.show();
                    NumVol.setText("");
                    nbrPlace.setText("");
                    VilleDepart.setText("");
                    //VilleArrive.setText("");
                    idSuppression.setText("");
                    imageVol.setText("");
                    alertNumVol.setText("");
                    alertNbrPlace.setText("");
                    alertVilleDepart.setText("");
                    alertVilleArrivee.setText("");
                    alertImageVol.setText("");
                    alerteTypeVol.setText("");
                    alertDateDepart.setText("");
                    alerteDateArrivee.setText("");
                    afficherListeVol();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    private void supprimerVol(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer ce vol ", "suppression vol", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            VolService vs = new VolService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                vs.supprimerVol(id);
                idSuppression.setText("");
                afficherListeVol();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            NumVol.setText("");
            nbrPlace.setText("");
            VilleDepart.setText("");
            imageVol.setText("");

        }
    }

    @FXML
    private void Stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/Dashboard.fxml"));
        Parent root = loader.load();
        Dashboard ac = loader.getController();
        statistiques.getScene().setRoot(root);

    }

    @FXML
    private void insererImgVol(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("Séléctionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        insererImgV.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            System.out.println(file);
            if (file != null) {
                String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                imageVol.setText(file.toString());
                image = new javafx.scene.image.Image(file.toURI().toString(), 179, 143, true, true);
                fruitImg.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter image");
            }
        });
    }

    @FXML
    private void pdfvol(ActionEvent event) {

        PDf pd = new PDf();
        try {
            pd.GeneratePdfVol("La Liste Des vols");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ville_depart(MouseEvent event) throws SQLException {
        ville_depart.setSelected(true);
        ville_arrive.setSelected(false);
        date_depart.setSelected(false);
        nbr_place.setSelected(false);

        VolService vs = new VolService();
        ObservableList<Vol> items = FXCollections.observableArrayList();
        List<Vol> listVol = vs.TrierVOLVilleDepart();
        for (Vol v : listVol) {
            items.add(v);
        }

        tableVols.setItems(items);
    }

    @FXML
    private void date_depart(MouseEvent event) throws SQLException {
        ville_depart.setSelected(false);
        ville_arrive.setSelected(false);
        date_depart.setSelected(true);
        nbr_place.setSelected(false);

        VolService vs = new VolService();
        ObservableList<Vol> items = FXCollections.observableArrayList();
        List<Vol> listVol = vs.trierVolDateDepart();
        for (Vol v : listVol) {
            items.add(v);
        }

        tableVols.setItems(items);
    }

    @FXML
    private void ville_arrive(MouseEvent event) throws SQLException {
        ville_depart.setSelected(false);
        ville_arrive.setSelected(true);
        date_depart.setSelected(false);
        nbr_place.setSelected(false);
        VolService vs = new VolService();
        ObservableList<Vol> items = FXCollections.observableArrayList();
        List<Vol> listVol = vs.trierVolVilleArrivé();
        for (Vol v : listVol) {
            items.add(v);
        }

        tableVols.setItems(items);
    }

    @FXML
    private void nbr_place(MouseEvent event) throws SQLException {
        ville_depart.setSelected(false);
        ville_arrive.setSelected(false);
        date_depart.setSelected(false);
        nbr_place.setSelected(true);

        VolService vs = new VolService();
        ObservableList<Vol> items = FXCollections.observableArrayList();
        List<Vol> listVol = vs.trierVolNbrPlace();
        for (Vol v : listVol) {
            items.add(v);
        }

        tableVols.setItems(items);
    }

    @FXML
    private void AjoutFav(ActionEvent event)  {
        alertNumVol.setText("");
        alertNbrPlace.setText("");
        alertVilleDepart.setText("");
        alertVilleArrivee.setText("");
        alertImageVol.setText("");
        alerteTypeVol.setText("");
        alertDateDepart.setText("");
        alerteDateArrivee.setText("");


        FavVol v = new FavVol();
        Boolean verif = true;
        ControlVol control = new ControlVol();

        //control Liste favoris
        if ( (VilleArrv.getValue() == null) || imageVol.getText().equals("") || (DateArrive.getValue() == null) || (typeV.getValue() == null)
                || VilleDepart.getText().equals("") || (DateDepart.getValue() == null) || nbrPlace.getText().equals("") || NumVol.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Aucun Vol a été selectionné ");
            alert.show();
            verif = false;
        }

        if (verif == true) {
            v = new FavVol(Integer.parseInt(NumVol.getText()), Integer.parseInt(nbrPlace.getText()), Date.valueOf(DateDepart.getValue()), Date.valueOf(DateArrive.getValue()), VilleDepart.getText(), VilleArrv.getValue(), imageVol.getText(), typeV.getValue());
            System.out.println(v);
            FavVolService vs = new FavVolService();
            List<FavVol> vols = null;
            try {
                vols = vs.afficherFavVol();

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            if (!vols.contains(v)) {
                try {
                    vs.ajouterFavVOL(v);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Vol ajoutée a la liste des favoris");
                    alert.show();
                    NumVol.setText("");
                    nbrPlace.setText("");
                    VilleDepart.setText("");
                    imageVol.setText("");
                    idSuppression.setText("");

                    afficherListeVol();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Vol est déja favori");
                alert.show();
            }    
    }}

    @FXML
    private void affchFav(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/FavVol.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxm/VolFront.fxml"));
        Parent root = loader.load();
        FavVolController ac = loader.getController();
        //VolFrontController ac = loader.getController();
        affchFav.getScene().setRoot(root);
    }

}
