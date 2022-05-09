/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Chambre;
import entities.Hotel;
import entities.Vol;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javax.swing.JOptionPane;
import services.ChambreService;
import services.HotelService;
import services.VolService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class ChambreController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField NbrLits;
    @FXML
    private Text alertNbrLits;
    @FXML
    private TextField NumCh;
    @FXML
    private Text alertNbrChambre;
    @FXML
    private Label etagee;
    @FXML
    private ComboBox<Integer> Etage;
    @FXML
    private Text alertEtage;
    @FXML
    private Label vuee;
    @FXML
    private ComboBox<String> vue;
    @FXML
    private Text alertVue;
    @FXML
    private ComboBox<String> bloc;
    @FXML
    private Text alertBlocl;
    @FXML
    private Label dispo;
    @FXML
    private Text alertDispo;
    @FXML
    private Label ImageCh;
    @FXML
    private Text alertImageCh;
    @FXML
    private Label Prix;

    @FXML
    private Text alertPrix;
    @FXML
    private Label Prix1;
    @FXML
    private ComboBox<Integer> hotelId;
    @FXML
    private Text alertHotelId;
    @FXML
    private TextField idSuppression;
    @FXML
    private TableView<Chambre> tableChambre;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nbrLits;
    @FXML
    private TableColumn<?, ?> numCh;
    @FXML
    private TableColumn<?, ?> blocc;
    @FXML
    private TableColumn<?, ?> disponi;
    private TableColumn<?, ?> image;
    @FXML
    private TableColumn<?, ?> prixx;
    ObservableList<Chambre> listCh;
    private List<Chambre> chambres = new ArrayList<>();
    @FXML
    private Text alertNbrChambres1;
    @FXML
    private ComboBox<String> dispoo;
    @FXML
    private TextField prixxx;
    @FXML
    private TableColumn<?, ?> etageeee;
    @FXML
    private TableColumn<?, ?> vu;
    @FXML
    private TableColumn<?, ?> hotelIddd;
    private HotelService hs = new HotelService();
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    @FXML
    private TextField imageCh;
    @FXML
    private TableColumn<?, ?> imgCh;

    private FileChooser filechooser;
    private javafx.scene.image.Image imag;
    private File file;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField filterFieldCh;
    @FXML
    private Button insererImg;
    @FXML
    private RadioButton toutes;
    @FXML
    private RadioButton ChambreDispo;
    @FXML
    private RadioButton ChambreNonDispo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toutes.setSelected(true);
        ChambreDispo.setSelected(false);
        ChambreNonDispo.setSelected(false);
        // TODO
        ObservableList<Integer> etage = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        Etage.setItems(etage);

        ObservableList<String> VUE = FXCollections.observableArrayList("Standard vue Mer", "Standard vue Jardin", "Deluxe vue Mer", "Deluxe vue Jardin");
        vue.setItems(VUE);

        ObservableList<String> bloc = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F");
        this.bloc.setItems(bloc);

        ObservableList<String> dispo = FXCollections.observableArrayList("Disponible", "Non Disponible");
        dispoo.setItems(dispo);

        ObservableList<Integer> langs;
        try {
            langs = FXCollections.observableArrayList(hs.getIdHotels());
            hotelId.setItems(langs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tableChambre.setOnMouseClicked(e -> {
            try {
                alertNbrLits.setText("");
                alertNbrChambre.setText("");
                alertImageCh.setText("");
                alertPrix.setText("");
                alertEtage.setText("");
                alertVue.setText("");
                alertBlocl.setText("");
                alertDispo.setText("");
                alertHotelId.setText("");
                String query = "select * from chambre where id = ?";
                TablePosition tablePosition = (TablePosition) tableChambre.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableChambre.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                System.out.println(data);
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    NumCh.setText(String.valueOf(rs.getInt("num_ch")));
                    NbrLits.setText(String.valueOf(rs.getInt("nbr_lits")));
                    vue.setValue(rs.getString("vue"));
                    Etage.setValue(rs.getInt("etage"));
                    prixxx.setText(rs.getString("prix"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));
                    this.bloc.setValue(rs.getString("bloc"));
                    dispoo.setValue(rs.getString("dispo"));
                    imageCh.setText(rs.getString("image_ch"));
                    hotelId.setValue(rs.getInt("hotel_id"));
                    File filImg = new File(rs.getString("image_ch"));
                    Image img = new javafx.scene.image.Image(filImg.toURI().toString(), 179, 143, true, true);
                    fruitImg.setImage(img);

                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        afficherListChambre();
        RechercheChambre();

    }

    public void afficherListChambre() {

        ChambreService chS = new ChambreService();
        try {
            List<Chambre> chambres = chS.afficherChambre();
            listCh = FXCollections.observableArrayList(chambres);
            tableChambre.setItems(listCh);

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nbrLits.setCellValueFactory(new PropertyValueFactory<>("nbrLits"));
            numCh.setCellValueFactory(new PropertyValueFactory<>("numCh"));
            etageeee.setCellValueFactory(new PropertyValueFactory<>("etage"));
            vu.setCellValueFactory(new PropertyValueFactory<>("vue"));
            blocc.setCellValueFactory(new PropertyValueFactory<>("bloc"));
            prixx.setCellValueFactory(new PropertyValueFactory<>("prix"));
            disponi.setCellValueFactory(new PropertyValueFactory<>("dispo"));
            imgCh.setCellValueFactory(new PropertyValueFactory<>("imageCh"));
            hotelIddd.setCellValueFactory(new PropertyValueFactory<>("hotel_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerHotel(ActionEvent event) {

        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette chambre ", "Suppression chambre", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            ChambreService chS = new ChambreService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                chS.supprimerChambre(id);
                idSuppression.setText("");
                NbrLits.setText("");
                NumCh.setText("");
                prixxx.setText("");
                imageCh.setText("");
                afficherListChambre();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void RechercheChambre() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Chambre> filteredData = new FilteredList<>(listCh, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFieldCh.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(chambre -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (chambre.getBloc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (chambre.getDispo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (chambre.getVue().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (String.valueOf(chambre.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(chambre.getEtage()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(chambre.getHotel_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(chambre.getNbrLits()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(chambre.getNumCh()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(chambre.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Chambre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableChambre.comparatorProperty());
        tableChambre.setItems(sortedData);
    }

    @FXML
    private void ajouterChambre(ActionEvent event) {
        alertNbrLits.setText("");
        alertNbrChambre.setText("");
        alertImageCh.setText("");
        alertPrix.setText("");
        alertEtage.setText("");
        alertVue.setText("");
        alertBlocl.setText("");
        alertDispo.setText("");
        alertHotelId.setText("");
        Chambre ch = new Chambre();
        Boolean verif = true;
        controlChambre control = new controlChambre();
        //Controle Nombres Lits
        if (NbrLits.getText().equals("")) {
            alertNbrLits.setText("Remplir le champs !!");
            verif = false;
        } else if (!NbrLits.getText().matches("[\\d\\.]+")) {
            alertNbrLits.setText("Nombre de lits  doit être un entier !!");
            verif = false;
        } else if (!(control.controlNbrLits(Integer.parseInt(NbrLits.getText())))) {
            alertNbrLits.setText("Nombre de lit  doit etre un entier comprise entre 1 et 4 !!");
            verif = false;
        }
        //Controle Numéro des chambres
        if (NumCh.getText().equals("")) {
            alertNbrChambre.setText("Remplir le champs !!");
            verif = false;
        } else if (!NumCh.getText().matches("[\\d\\.]+")) {
            alertNbrChambre.setText("Nombre des chambres  doit être un entier !!");
            verif = false;
        }
        //Controle Image Chambre
        if (imageCh.getText().equals("")) {
            alertImageCh.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Prix
        if (prixxx.getText().equals("")) {
            alertPrix.setText("Remplir le champs !!");
            verif = false;
        } else if (!prixxx.getText().matches("[\\d\\.]+")) {
            alertPrix.setText("Prix  doit être un entier !!");
            verif = false;
        }
        //Controle Etage
        if (Etage.getValue() == null) {
            alertEtage.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Bloc
        if (bloc.getValue() == null) {
            alertBlocl.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Dispo
        if (dispoo.getValue() == null) {
            alertDispo.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Vue
        if (vue.getValue() == null) {
            alertVue.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Hotel id
        if (hotelId.getValue() == null) {
            alertHotelId.setText("Remplir le champs !!");
            verif = false;
        }
        if (verif == true) {
            ch = new Chambre(Integer.parseInt(NbrLits.getText()), Integer.parseInt(NumCh.getText()), Etage.getValue(), vue.getValue(), bloc.getValue(), dispoo.getValue(), imageCh.getText(), Integer.parseInt(prixxx.getText()), hotelId.getValue());

            ChambreService chS = new ChambreService();
            if (!listCh.contains(ch)) {
                try {

                    chS.ajouterChambre(ch);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("chambre ajoutée avec succés");
                    alert.show();
                    NbrLits.setText("");
                    NumCh.setText("");
                    prixxx.setText("");
                    imageCh.setText("");

                    afficherListChambre();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("chambre existe deja");
                alert.show();
            }
        }
    }

    @FXML
    private void ModifierChambre(ActionEvent event) {
        controlChambre control = new controlChambre();
        Boolean verif = true;
        //Controle Nombres Lits
        if (NbrLits.getText().equals("")) {
            alertNbrLits.setText("Remplir le champs !!");
            verif = false;
        } else if (!NbrLits.getText().matches("[\\d\\.]+")) {
            alertNbrLits.setText("Nombre de lits  doit être un entier !!");
            verif = false;
        } else if (!(control.controlNbrLits(Integer.parseInt(NbrLits.getText())))) {
            alertNbrLits.setText("Nombre de lit  doit etre un entier comprise entre 1 et 4 !!");
            verif = false;
        }
        //Controle Numéro des chambres
        if (NumCh.getText().equals("")) {
            alertNbrChambre.setText("Remplir le champs !!");
            verif = false;
        } else if (!NumCh.getText().matches("[\\d\\.]+")) {
            alertNbrChambre.setText("Nombre des chambres  doit être un entier !!");
            verif = false;
        }
        //Controle Image Chambre
        if (imageCh.getText().equals("")) {
            alertImageCh.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Prix
        if (prixxx.getText().equals("")) {
            alertPrix.setText("Remplir le champs !!");
            verif = false;
        } else if (!prixxx.getText().matches("[\\d\\.]+")) {
            alertPrix.setText("Prix  doit être un entier !!");
            verif = false;
        }
        //Controle Etage
        if (Etage.getValue() == null) {
            alertEtage.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Bloc
        if (bloc.getValue() == null) {
            alertBlocl.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Dispo
        if (dispoo.getValue() == null) {
            alertDispo.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Vue
        if (vue.getValue() == null) {
            alertVue.setText("Remplir le champs !!");
            verif = false;
        }
        //Controle Hotel id
        if (hotelId.getValue() == null) {
            alertHotelId.setText("Remplir le champs !!");
            verif = false;
        }
        TablePosition tablePosition = (TablePosition) tableChambre.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        Object item = tableChambre.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
        if (verif == true) {
            Chambre ch = new Chambre(data, Integer.parseInt(NbrLits.getText()), Integer.parseInt(NumCh.getText()), Etage.getValue(), vue.getValue(), bloc.getValue(), dispoo.getValue(), imageCh.getText(), Integer.parseInt(prixxx.getText()), hotelId.getValue());
            ChambreService chS = new ChambreService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cet chambre ", "modifier chambre", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    chS.modifierChambre(ch);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("chambre Modifier avec succés");
                    alert.show();
                    NbrLits.setText("");
                    NumCh.setText("");
                    prixxx.setText("");
                    imageCh.setText("");
                    idSuppression.setText("");
                    alertNbrLits.setText("");
                    alertNbrChambre.setText("");
                    alertImageCh.setText("");
                    alertPrix.setText("");
                    alertEtage.setText("");
                    alertVue.setText("");
                    alertBlocl.setText("");
                    alertDispo.setText("");
                    alertHotelId.setText("");
                    afficherListChambre();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @FXML
    private void InsererImgChambre(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("Séléctionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        insererImg.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            System.out.println(file);
            if (file != null) {
                String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                imageCh.setText(file.toString());
                imag = new javafx.scene.image.Image(file.toURI().toString(), 179, 143, true, true);
                fruitImg.setImage(imag);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter image");
            }
        });
    }

    @FXML
    private void TouteChambre(MouseEvent event) throws SQLException {
        toutes.setSelected(true);
        ChambreDispo.setSelected(false);
        ChambreNonDispo.setSelected(false);

        ChambreService cs = new ChambreService();
        ObservableList<Chambre> items = FXCollections.observableArrayList();
        List<Chambre> listChambre = cs.afficherChambre();
        for (Chambre h : listChambre) {
            items.add(h);
        }

        tableChambre.setItems(items);
    }

    @FXML
    private void ChambreDispo(MouseEvent event) throws SQLException {
        toutes.setSelected(false);
        ChambreDispo.setSelected(true);
        ChambreNonDispo.setSelected(false);
        ChambreService cs = new ChambreService();
        ObservableList<Chambre> items = FXCollections.observableArrayList();
        List<Chambre> listChambre = cs.afficherChambreDispo();
        for (Chambre h : listChambre) {
            items.add(h);
        }

        tableChambre.setItems(items);
    }

    @FXML
    private void ChambreNonDispo(MouseEvent event) throws SQLException {
        toutes.setSelected(false);
        ChambreDispo.setSelected(false);
        ChambreNonDispo.setSelected(true);

        ChambreService cs = new ChambreService();
        ObservableList<Chambre> items = FXCollections.observableArrayList();
        List<Chambre> listChambre = cs.afficherChambreNonDispo();
        for (Chambre h : listChambre) {
            items.add(h);
        }

        tableChambre.setItems(items);
    }

}
