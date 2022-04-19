/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Billet;
import entities.Chambre;
import entities.Vol;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import services.BilletService;
import services.ChambreService;
import services.HotelService;
import services.VolService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author msi
 */
public class BilletController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField numB;
    @FXML
    private Text alertNumBillet;
    @FXML
    private Label Prix;
    @FXML
    private TextField prix;
    @FXML
    private Text alertPrix;
    @FXML
    private ComboBox<String> nomCom;
    @FXML
    private Text alertNomC;
    @FXML
    private Label imageBillet;
    @FXML
    private TextField imageB;
    @FXML
    private Text alertiMAGEbILET;
    @FXML
    private DatePicker dateB;
    private Text alertImageVol;
    @FXML
    private TextField idSuppression;
    @FXML
    private TableView<Billet> tableBillets;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> num;
    @FXML
    private TableColumn<?, ?> prixBillet;
    @FXML
    private TableColumn<?, ?> compagnie;
    @FXML
    private TableColumn<?, ?> img;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private Text alertNbrChambres1;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection connexion = MyDB.getInstance().getConnexion();
    ObservableList<Billet> listB;
    private List<Billet> billets = new ArrayList<>();
    @FXML
    private ComboBox<Integer> idVols;
    @FXML
    private Text alertIdVols;
    @FXML
    private TableColumn<?, ?> idVoll;
    private VolService vs = new VolService();
    @FXML
    private Text alertDate;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField filterFieldB;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ObservableList<String> nomCom = FXCollections.observableArrayList("Qatar Airways", "Emirates", "Turkish ", "British ", "Air France");
        this.nomCom.setItems(nomCom);

        ObservableList<Integer> langs;
        try {
            langs = FXCollections.observableArrayList(vs.getIdVol());
            idVols.setItems(langs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tableBillets.setOnMouseClicked(e -> {
            try {
                String query = "select * from billet where id = ?";
                TablePosition tablePosition = (TablePosition) tableBillets.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableBillets.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                System.out.println(data);
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    numB.setText(String.valueOf(rs.getInt("num_b")));
                    prix.setText(String.valueOf(rs.getInt("prix")));
                    this.nomCom.setValue(rs.getString("nom_com"));
                    imageB.setText(rs.getString("image_billet"));
                    dateB.setValue(LocalDate.from(rs.getDate("date_v").toLocalDate()));
                    idVols.setValue(rs.getInt("vol_id"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));

                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        afficherListeBillet();
        RechercheBillet();
    }

        private void RechercheBillet() {
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Billet> filteredData = new FilteredList<>(listB, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterFieldB.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(billet -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (billet.getNomCom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (String.valueOf(billet.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(billet.getNumB()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(billet.getVol_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(billet.getPrix()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(billet.getDateV()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        SortedList<Billet> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableBillets.comparatorProperty());
        tableBillets.setItems(sortedData);
    }
        
    public void afficherListeBillet() {

        BilletService bs = new BilletService();
        try {
            List<Billet> billets = bs.afficherBillet();
            listB = FXCollections.observableArrayList(billets);
            tableBillets.setItems(listB);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            num.setCellValueFactory(new PropertyValueFactory<>("numB"));
            prixBillet.setCellValueFactory(new PropertyValueFactory<>("prix"));
            compagnie.setCellValueFactory(new PropertyValueFactory<>("nomCom"));
            img.setCellValueFactory(new PropertyValueFactory<>("imageBillet"));
            date.setCellValueFactory(new PropertyValueFactory<>("dateV"));
            idVoll.setCellValueFactory(new PropertyValueFactory<>("vol_id"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterBillet(ActionEvent event) {

        alertIdVols.setText("");
        alertDate.setText("");
        alertNomC.setText("");
        alertNumBillet.setText("");
        alertiMAGEbILET.setText("");
        alertPrix.setText("");
        Billet b = new Billet();
        Boolean verif = true;

        // Control Numéro billet
        if (numB.getText().equals("")) {
            alertNumBillet.setText("Remplir le champs !!");
            verif = false;
        } else if (!numB.getText().matches("[\\d\\.]+")) {
            alertNumBillet.setText("Numéro billet doit être un entier !!");
            verif = false;
        }
        // Control  prix
        if (prix.getText().equals("")) {
            alertPrix.setText("Remplir le champs !!");
            verif = false;
        } else if (!prix.getText().matches("[\\d\\.]+")) {
            alertPrix.setText("le prix doit être un entier !!");
            verif = false;
        }
        // Control nom Compagnie
        if (nomCom.getValue() == null) {
            alertNomC.setText("Remplir le champs !!");
            verif = false;
        }

        // Control image
        if (imageB.getText().equals("")) {
            alertiMAGEbILET.setText("Remplir le champs !!");
            verif = false;
        }
        // Control dateVol         
        if (dateB.getValue() == null) {
            alertDate.setText("Remplir le champs !!");
            verif = false;
        }
        if ((dateB.getValue() != null)) {
            // Control dateB  >  sysdate
            Date d1 = Date.valueOf(dateB.getValue());
            Date d = new Date(System.currentTimeMillis());
            System.out.println(d);
            if (d1.before(d)) {
                alertDate.setText(" Date  >  date d aujourd hui  !!");
                verif = false;
            }
        }
        // Control idVol         
        if (idVols.getValue() == null) {
            alertIdVols.setText("Remplir le champs !!");
            verif = false;
        }
        if (verif == true) {
            b = new Billet(Integer.parseInt(numB.getText()), Integer.parseInt(prix.getText()), nomCom.getValue(), imageB.getText(), Date.valueOf(dateB.getValue()), idVols.getValue());
            if (!listB.contains(b)) {

                System.out.println("erreur");
                BilletService bs = new BilletService();
                try {

                    bs.ajouterBillet(b);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Billet ajoutée avec succés");
                    alert.show();
                    numB.setText("");
                    prix.setText("");
                    imageB.setText("");
                    idSuppression.setText("");

                    afficherListeBillet();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Billet existe deja");
                alert.show();
            }
        }
    }

    @FXML
    private void modifierBillet(ActionEvent event) {
        alertIdVols.setText("");
        alertDate.setText("");
        alertNomC.setText("");
        alertNumBillet.setText("");
        alertiMAGEbILET.setText("");
        alertPrix.setText("");

        Boolean verif = true;

        // Control Numéro billet
        if (numB.getText().equals("")) {
            alertNumBillet.setText("Remplir le champs !!");
            verif = false;
        } else if (!numB.getText().matches("[\\d\\.]+")) {
            alertNumBillet.setText("Numéro billet doit être un entier !!");
            verif = false;
        }
        // Control  prix
        if (prix.getText().equals("")) {
            alertPrix.setText("Remplir le champs !!");
            verif = false;
        } else if (!prix.getText().matches("[\\d\\.]+")) {
            alertPrix.setText("le prix doit être un entier !!");
            verif = false;
        }
        // Control nom Compagnie
        if (nomCom.getValue() == null) {
            alertNomC.setText("Remplir le champs !!");
            verif = false;
        }

        // Control image
        if (imageB.getText().equals("")) {
            alertiMAGEbILET.setText("Remplir le champs !!");
            verif = false;
        }
        // Control dateVol         
        if (dateB.getValue() == null) {
            alertDate.setText("Remplir le champs !!");
            verif = false;

        }
        if ((dateB.getValue() != null)) {
            // Control dateB  >  sysdate
            Date d1 = Date.valueOf(dateB.getValue());
            Date d = new Date(System.currentTimeMillis());
            System.out.println(d);
            if (d1.before(d)) {
                alertDate.setText(" Date  >  date d aujourd hui  !!");
                verif = false;
            }
        }
        // Control idVol         
        if (idVols.getValue() == null) {
            alertIdVols.setText("Remplir le champs !!");
            verif = false;
        }
        TablePosition tablePosition = (TablePosition) tableBillets.getSelectionModel().getSelectedCells().get(0);

        int row = tablePosition.getRow();
        Object item = tableBillets.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();

        if (verif == true) {
            Billet b = new Billet(data, Integer.parseInt(numB.getText()), Integer.parseInt(prix.getText()), nomCom.getValue(), imageB.getText(), Date.valueOf(dateB.getValue()), idVols.getValue());
            BilletService bs = new BilletService();
            int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de modifer cette billet ", "modifier billet", JOptionPane.YES_NO_OPTION);
            if (opt == 0) {
                try {
                    bs.modifierBillet(b);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("billet Modifier avec succés");
                    alert.show();
                    numB.setText("");
                    prix.setText("");
                    imageB.setText("");
                    idSuppression.setText("");
                    afficherListeBillet();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @FXML
    private void supprimerBillet(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cette billet ", "Suppression billet", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            BilletService bs = new BilletService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                bs.supprimerBillet(id);
                numB.setText("");
                prix.setText("");
                imageB.setText("");
                idSuppression.setText("");
                afficherListeBillet();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
