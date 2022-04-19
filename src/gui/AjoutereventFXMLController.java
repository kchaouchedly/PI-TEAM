/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import static entities.Evenement.filename;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import services.ServiceEvenement;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AjoutereventFXMLController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField TypeEvent;
    @FXML
    private TextField NomEvent;
    @FXML
    private DatePicker debutevent;
    @FXML
    private DatePicker finevent;
    @FXML
    private TextField lieuxevent;
    @FXML
    private Button btnimageevent;
    @FXML
    private Button btnajouterevent;
    @FXML
    private ImageView imageevent;
    public String imagecomp;
    @FXML
    private TableView<Evenement> EVENEMENTAFFICHAGE;
    @FXML
    private TableColumn<Evenement, Integer> idaffichage;
    @FXML
    private TableColumn<Evenement, String> typeeventaff;
    @FXML
    private TableColumn<Evenement, String> nomeventaff;
    @FXML
    private TableColumn<Evenement, Date> debuteventaff;
    @FXML
    private TableColumn<Evenement, Date> fineventaff;
    @FXML
    private TableColumn<Evenement, String> eventlieuxaff;
    @FXML
    private Button modifierevent;
    @FXML
    private Button supprimerevent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficherevenemntcontroller();
        
     lieuxevent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
      TypeEvent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
     NomEvent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
        System.out.println("lllll");
      
    }    

    @FXML
    private void imageupload(ActionEvent event) {
        FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.jpg"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img) {};
            
            imageevent.setImage(i);
            imagecomp = fc.toString();
            //System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            Evenement.filename = "C:\\Users\\Hp\\Documents\\NetBeansProjects\\Pidev\\src\\Image\\" + filename;
           
        }
        imageevent.setFitHeight(215);
        imageevent.setFitWidth(265);
        
        Evenement.pathfile = fc.getAbsolutePath();
        
    }

    @FXML
    private void btnajouterevent(ActionEvent event) {
        String image = Evenement.filename;
        System.out.println(image);
        if(TypeEvent.getText().equals("")||NomEvent.getText().equals("")||lieuxevent.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERREUR");
            alert.setContentText("remplir tous les champs");
    alert.show();
            
        }
        
        Evenement a = new Evenement(TypeEvent.getText(), NomEvent.getText(), image,"color", lieuxevent.getText(), Date.valueOf(debutevent.getValue()) , Date.valueOf(finevent.getValue()));
        ServiceEvenement ps = new ServiceEvenement();
        try {
            ps.ajouterevenement(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Personne ajoutée");
            alert.show();
            //tnom.setText("");
           // tprenom.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        afficherevenemntcontroller();
    }
    private void afficherevenemntcontroller() {
        ServiceEvenement mS = new ServiceEvenement();
        
        ObservableList<Evenement> liste = mS.afficherevent();
        idaffichage.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));
        typeeventaff.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Type"));
        nomeventaff.setCellValueFactory(new PropertyValueFactory<Evenement, String>("NomEvenement"));
        
           eventlieuxaff.setCellValueFactory(new PropertyValueFactory<Evenement, String>("Lieux"));
        debuteventaff.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("DateDebut"));
        fineventaff.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("DateFin"));
     
        System.out.println(mS.afficherevent());
        
     /*   rst.getString("type"),
                    rst.getString("nom_evenement"), 
                   rst.getString("image"),
                     rst.getString("color"),
                    rst.getString("lieux"),
                   rst.getDate("date_debut"),
                   rst.getDate("date_fin"));*/
       
        
        EVENEMENTAFFICHAGE.setItems(liste);
    }

    @FXML
    private void btnsuppevent(ActionEvent event) {
        if (EVENEMENTAFFICHAGE.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, "Aucune Evenement n'est selectionné ,veuillez choisir un evenement");
        } else {
            int responce = JOptionPane.showConfirmDialog(null, "Vous étes sur de supprimer cet evenement ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if (responce == JOptionPane.YES_OPTION) {
               ServiceEvenement sa = new ServiceEvenement();
              Evenement a= EVENEMENTAFFICHAGE.getSelectionModel().getSelectedItem();
                sa.deleteEvent(a);

                //refresh(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("Votre evenement a été bien supprime");
                JOptionPane.showMessageDialog(null, "event supprimé");
               afficherevenemntcontroller();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Annulation");
                JOptionPane.showMessageDialog(null, "Suppression annulé");
            }

        }
       afficherevenemntcontroller();

    

        
    }

    @FXML
    private void btnmodevent(ActionEvent event) {
     
          Evenement M=    EVENEMENTAFFICHAGE.getSelectionModel().getSelectedItem();
          if (EVENEMENTAFFICHAGE.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, "Aucun Evenement n'est selectionné ,veuillez choisir un Evenement");
          }else {
            int responce = JOptionPane.showConfirmDialog(null, "Vous étes sur de Modifier cet Evenement ?", "Modification", JOptionPane.YES_NO_OPTION);
              if (responce == JOptionPane.YES_OPTION) {
                  
      M.setNomEvenement(NomEvent.getText());

        M.setLieux(lieuxevent.getText());
        
        M.setType(TypeEvent.getText());
     
              M.setDateDebut( Date.valueOf(debutevent.getValue())) ;
              M.setDateFin(Date.valueOf(finevent.getValue()));
        M.setImage(imagecomp);
        
        
  
     ServiceEvenement ms = new ServiceEvenement();
      if(TypeEvent.getText().equals("")||NomEvent.getText().equals("")||lieuxevent.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERREUR");
            alert.setContentText("remplir tous les champs");
    alert.show();
            
        }
      else {
      try {
          
    
                       
        ms.updateEvent(M);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.show();
                        alert.setTitle("Modifié!");
                        alert.setContentText("Modification effectué");
                        EVENEMENTAFFICHAGE.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed");

                    }
       
      afficherevenemntcontroller();
              }
        
        
    }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Annulation");
                JOptionPane.showMessageDialog(null, "Modification annulé");
            }
          }
    }

    @FXML
    private void selectevent(MouseEvent event) {
        Evenement M= EVENEMENTAFFICHAGE.getSelectionModel().getSelectedItem();
     
    TypeEvent.setText(M.getType());
    NomEvent.setText(M.getNomEvenement());
    lieuxevent.setText(M.getLieux());
    //debutevent.set;
   // finevent.setText(String.valueOf(M.getDateFin()));
    
    }
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[0-9.]")){ 
if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
e.consume();
}else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
e.consume(); 
}
}else{
e.consume();
}
}
};
}    

   /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
return new EventHandler<KeyEvent>() {
@Override
public void handle(KeyEvent e) {
TextField txt_TextField = (TextField) e.getSource();                
if (txt_TextField.getText().length() >= max_Lengh) {                    
e.consume();
}
if(e.getCharacter().matches("[A-Za-z]")){ 
}else{
e.consume();
}
}
};
}    

  
}
