/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Mailing.Mailing;

import entities.Evenement;
import static entities.Evenement.filename;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.mail.Message;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
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
    ResultSet rs = null;
   
    @FXML
    private TextField filterField;
    
    
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnevent;
    @FXML
    private Button offrebtn;
    
     
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficherevenemntcontroller();
        
     lieuxevent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
      TypeEvent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(20));
     NomEvent.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(20));
       
        
       LocalDate today = LocalDate.now(); 
      
                
      
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
    private void btnajouterevent(ActionEvent event) throws Exception {
        String image = Evenement.filename;
        System.out.println(image);
        Date debut = Date.valueOf(debutevent.getValue());
            Date dfin = Date.valueOf(finevent.getValue());
            Date d = new Date(System.currentTimeMillis());
            
        
            if (debut.before(d)|| dfin.before(debut)) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("Erreur !");
                        alert.setContentText("verifier la date");
               
            }
        
         else {

      if  (TypeEvent.getText().equals("")||NomEvent.getText().equals("")||lieuxevent.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERREUR");
            alert.setContentText("remplir tous les champs");
    alert.show();
            
        }
         
      
        Evenement a = new Evenement(TypeEvent.getText(), NomEvent.getText(), image,"color", lieuxevent.getText(), Date.valueOf(debutevent.getValue()) , Date.valueOf(finevent.getValue()));
        ServiceEvenement ps = new ServiceEvenement();
        try {
            ps.ajouterevenement(a);
            String path = ps.QR(NomEvent.getText());
             Mailing.mailing(path);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
           
            alert.setContentText("Evenement ajoutée :: Merci de vérifier votre boite mail ");
            alert.show();
            //tnom.setText("");
           // tprenom.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        afficherevenemntcontroller();
        
       new mynotif().start();
        // sms s = new sms(); 
        
     
         
         
            }
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
        FilteredList<Evenement> filteredData = new FilteredList<>( liste, e -> true);
            filterField.setOnKeyReleased(e -> {
                filterField.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Evenement>) Evenement -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Evenement.getLieux().toLowerCase().contains(lower)) {
                            return true;
                        }
                        if(Evenement.getNomEvenement().toLowerCase().contains(lower)){
                            return true ; 
                        }
                        if(Evenement.getDateDebut().toLocaleString().contains(lower)){
                            return true ; 
                        }
                        if(Evenement.getDateFin().toLocaleString().contains(lower)){
                            return true ; 
                        }
                      
                        

                        return false;
                    });
                });
                SortedList<Evenement> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(EVENEMENTAFFICHAGE.comparatorProperty());
                EVENEMENTAFFICHAGE.setItems(sortedData);
            });
       
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
         new mynotif().start();

    

        
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
     
           //   M.setDateDebut( Date.valueOf(debutevent.getValue())) ;
             // M.setDateFin(Date.valueOf(finevent.getValue()));
        M.setImage(imagecomp);
         M.setDateDebut(Date.valueOf(debutevent.getValue().toString()));
           M.setDateFin(Date.valueOf(finevent.getValue().toString()));
         
        
        
  
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
        new mynotif().start();
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
    //  DateArrive.setValue(LocalDate.from(rs.getDate("date_arrive").toLocalDate()));
   
    
    
    // debutevent.setValue(LocalDate.from(M.getDate("DateDebut").toLocalDate()));*
   
    // M.setDateDebut(Date.valueOf(debutevent.getValue().toString()));
  /* txtda.Date(as.liste2()
                        .get(affichageActivites.getSelectionModel().getSelectedIndex())
                        .getDate()
                ); */
 
     
    ;
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
if(e.getCharacter().matches("[A-Z a-z]")){ 
}else{
e.consume();
}
}
};
}    

    @FXML
    private void statis(MouseEvent event) {
        try {
                   
            Parent parent =  FXMLLoader.load(getClass().getResource("/gui/statevenement.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
           ex.getMessage();
        }

        
    }

    @FXML
    private void calandrier(MouseEvent event) {
          try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/TrackEmployeeFX.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
           ex.getMessage();
        }
    }

   
    @FXML
    private void videoplay(MouseEvent event) {
        
        try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/FXMLDocument.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
           ex.getMessage();
        } 
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        
    }

    @FXML
    private void btnevent(MouseEvent event) {
    }

    @FXML
    private void btnoffre(MouseEvent event) {
           try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/AjouteroffreFXML.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
           ex.getMessage();
        } 
      
    }

 

   

   
    public class mynotif extends Thread{
        public void run(){
            try{
            Thread.sleep(5000);
            }catch (InterruptedException ex ){
                Logger.getLogger(AjoutereventFXMLController.class.getName()).log(Level.SEVERE,null,ex);
            }
         Image img = new Image("/Image/small_tick4.png");
            Notifications notifcationBuilder = Notifications.create()
                    
            .title("Opération effectué")
                    .text("Gestion Des Evenements").
                    graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_LEFT)
                    .onAction(new EventHandler<ActionEvent>(){
                       

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Notification");
                            
                   
                }
                    });
            notifcationBuilder.darkStyle(); 
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    notifcationBuilder.show();
                }
            });
           
        }
        
    }
     
    
      
		
    
    
    }

          
    
  

