/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Hotel;
import fxm.CadreController;
import java.io.File;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pidevjava.HotelFXMain;
import services.HotelService;
import utils.MyDB;

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
    private TextField nomh;

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
        // TODO
        afficherListeHotel();
        RechercheHotel();
        tableHotels.setOnMouseClicked(e -> {
            try {
                String query = "select * from hotel where id = ?";
                TablePosition tablePosition = (TablePosition) tableHotels.getSelectionModel().getSelectedCells().get(0);

                int row = tablePosition.getRow();
                Object item = tableHotels.getItems().get(row);
                TableColumn tableColumn = tablePosition.getTableColumn();
                Integer data = (Integer) tableColumn.getCellObservableValue(item).getValue();
                System.out.println(data);
                pst = connexion.prepareStatement(query);
                pst.setInt(1, data);
                rs = pst.executeQuery();
                while (rs.next()) {
                    codeH.setText(String.valueOf(rs.getInt("code_h")));
                    nbrChambre.setText(String.valueOf(rs.getInt("nbr_chambre")));
                    nbeEtoiles.setText(String.valueOf(rs.getInt("nbr_etoiles")));
                    telHotel.setText(String.valueOf(rs.getInt("num_tel")));
                    adresse.setText(rs.getString("adresse"));
                    nomh.setText(rs.getString("nom_hotel"));
                    email.setText(rs.getString("email"));
                    imageH.setText(rs.getString("image_hotel"));
                    idSuppression.setText(String.valueOf(rs.getInt("id")));
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        

    }

    private void RechercheHotel(){
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Hotel> filteredData = new FilteredList<>(listH, b -> true);

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
                }else {
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
        if (nomh.getText().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        } else if (!(control.ControleNomHotel(nomh.getText()))) {
            alertNom.setText("Nom Hotel comporte que des caractéres !!");
            verif = false;
        } else if (nomh.getText().length() <= 4) {
            alertNom.setText("Nom Hotel au moins 5 caractéres !!");
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
            alerteImageHotel.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleAdresse(imageH.getText()))) {
            alerteImageHotel.setText("Adresse comporte que des caractéres !!");
            verif = false;
        }
        if (verif == true) {
            h = new Hotel(Integer.parseInt(telHotel.getText()), Integer.parseInt(nbeEtoiles.getText()), Integer.parseInt(nbrChambre.getText()), Integer.parseInt(codeH.getText()), nomh.getText(), adresse.getText(), email.getText(), imageH.getText());
            HotelService hs = new HotelService();
            if (!listH.contains(h)) {
                try {
                    hs.ajouterHotel(h);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Succes");
                    alert.setContentText("Hotel ajoutée avec succés");
                    alert.show();
                    telHotel.setText("");
                    nomh.setText("");
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
                alert.setContentText("vol existe deja");
                alert.show();
            }
        }
    }

    @FXML
    private void supprimerHotel(ActionEvent event) {
        int opt = JOptionPane.showConfirmDialog(null, "Vous etes  sur de supprimer cet hotel ", "suppression Hotel", JOptionPane.YES_NO_OPTION);
        if (opt == 0) {
            HotelService hs = new HotelService();
            int id = (Integer.parseInt(idSuppression.getText()));
            try {
                hs.supprimerHotel(id);
                idSuppression.setText("");
                telHotel.setText("");
                nomh.setText("");
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
        if (nomh.getText().equals("")) {
            alertNom.setText("Remplir le champs !!");
            verif = false;
        } else if (!(control.ControleNomHotel(nomh.getText()))) {
            alertNom.setText("Nom Hotel comporte que des caractéres !!");
            verif = false;
        } else if (nomh.getText().length() <= 4) {
            alertNom.setText("Nom Hotel au moins 5 caractéres !!");
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
            alerteImageHotel.setText("Remplir le champs !!");
            verif = false;
        } else if ((!control.ControleAdresse(imageH.getText()))) {
            alerteImageHotel.setText("Adresse comporte que des caractéres !!");
            verif = false;
        }
        if (verif == true) {
            Hotel h = new Hotel(data, Integer.parseInt(telHotel.getText()), Integer.parseInt(nbeEtoiles.getText()), Integer.parseInt(nbrChambre.getText()), Integer.parseInt(codeH.getText()), nomh.getText(), adresse.getText(), email.getText(), imageH.getText());
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
                    nomh.setText("");
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

}
