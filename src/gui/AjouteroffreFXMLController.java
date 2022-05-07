/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Mailing.Mailing;
import static entities.Evenement.filename;
import entities.Offre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import services.ServiceEvenement;
import services.ServiceOffres;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class AjouteroffreFXMLController implements Initializable {

    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextField nomoffreajout;
    @FXML
    private DatePicker datedebutoff;
    @FXML
    private DatePicker datefinoff;
    @FXML
    private TextField prixoff;
    @FXML
    private TextField nbrplacesoff;
    @FXML
    private Button btnimageoff;
    @FXML
    private Button btnaddoff;
    
   
    @FXML
    private TextField nomguideoff;
      @FXML
     private ImageView imageoffre;
    public String imagecomp;
    @FXML
    private TableView<Offre> OFFREAFFICHER;
    @FXML
    private TableColumn<Offre, Integer> idnbrplaceoff;
    @FXML
    private TableColumn<Offre, String> nomoff;
    @FXML
    private TableColumn<Offre, String> nomguide;
    @FXML
    private TableColumn<Offre, Date> datefinnoff;
    @FXML
    private TableColumn<Offre, Date> datedebutoffre;
    @FXML
    private TableColumn<Offre, Float> prixoffre;
    @FXML
    private Button btnsupprimeroffre;
    @FXML
    private Button modifieroffrebtn;
    @FXML
    private Label mylabel;
    @FXML
    private TextField filtrer;
    private volatile boolean stop=false; 
    private Label datevent;
    @FXML
    private Label dateevent;
    @FXML
    private Button payerbtn;
    @FXML
    private ComboBox<String> join;
    private ServiceEvenement ser =new ServiceEvenement();
    @FXML
    private TableColumn<Offre, Integer> affichageid;
    @FXML
    private Button btnevent;
    @FXML
    private Button offrebtn;
    @FXML
    private Button btnCustomers;
    @FXML
    private TextField rehcerche;
    public static Offre v ; 
    @FXML
    private Button reserver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficheroffrecontroller();
        ServiceOffres service = new ServiceOffres(); 
        System.out.println(service.afficheroffrelist());
         nbrplacesoff.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(2));
      nomoffreajout.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    nomoffreajout.caretPositionProperty().addListener(observable -> System.out.println("Maximum 10 caractéres"));
         nomguideoff.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
       timenow();
       ObservableList<String> langs;
        try {
            langs = FXCollections.observableArrayList(ser.Eventnom());
            join.setItems(langs);
        } catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
            LocalDate today = LocalDate.now(); 
        
        
    }    

    

    @FXML
    private void ajouteroffre(ActionEvent event) throws Exception {
         String image = Offre.filename;
        System.out.println(image);
         Date debut = Date.valueOf(datedebutoff.getValue());
            Date dfin = Date.valueOf(datefinoff.getValue());
            Date d = new Date(System.currentTimeMillis());
            
        
            if (debut.before(d)|| dfin.before(debut)) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("Erreur !");
                        alert.setContentText("verifier la date");
               
            }
            
         if(nbrplacesoff.getText().equals("")||prixoff.getText().equals("")||nomoffreajout.getText().equals("")||prixoffre.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERREUR");
            alert.setContentText("remplir tous les champs");
    alert.show();
            
        }
         System.out.println(join.getValue());
        System.out.println(ser.getidevent(join.getValue()));
    //   Offre a = newOffre(TypeEvent.getText(), NomEvent.getText(), image,"color", lieuxevent.getText(), Date.valueOf(debutevent.getValue()) , Date.valueOf(finevent.getValue()));
   Offre a =new Offre(ser.getidevent(join.getValue()),Integer.parseInt(nbrplacesoff.getText()),nomoffreajout.getText(), nomguideoff.getText(), image,Float.parseFloat(prixoff.getText()) ,Date.valueOf(datedebutoff.getValue()), Date.valueOf(datefinoff.getValue()));
   //Offre a = new Offre(0, 0, 0, image, image, image, 0, DateDebutOff, DateFinOff)
        System.out.println(a);
        ServiceOffres ps = new ServiceOffres();
       
        
        try {
            ps.ajouteroffre(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Personne ajoutée");
            alert.show();
            //tnom.setText("");
           // tprenom.setText("");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      afficheroffrecontroller();
    // Mailing.mailing();
      
       new mynotifo().start();
    }
   
    

    

    @FXML
    private void imageoffre(ActionEvent event) {
         FileChooser f = new FileChooser();
        String img;

        f.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", "*.jpg"));
        File fc = f.showOpenDialog(null);
        if (f != null) {
            System.out.println(fc.getName());
            img = fc.getAbsoluteFile().toURI().toString();
            Image i = new Image(img) {};
            
            imageoffre.setImage(i);
            imagecomp = fc.toString();
            //System.out.println(imagecomp);
            int index = imagecomp.lastIndexOf('\\');
            if (index > 0) {
                filename = imagecomp.substring(index + 1);
            }

            Offre.filename = "C:\\Users\\Hp\\Documents\\NetBeansProjects\\Pidev\\src\\Image\\" + filename;
           
        }
        imageoffre.setFitHeight(215);
        imageoffre.setFitWidth(265);
        
        Offre.pathfile = fc.getAbsolutePath();
    }

    @FXML
    private void selectOFFRE(MouseEvent event) {
          Offre M= OFFREAFFICHER.getSelectionModel().getSelectedItem();
     
   nomguideoff.setText(M.getNomGuide());
  nomoffreajout.setText(M.getNom());
    nbrplacesoff.setText(String.valueOf(M.getNbr_places()));
       prixoff.setText(String.valueOf(M.getPrix()));
            
        
    }
      private void afficheroffrecontroller() {
        ServiceOffres mS = new ServiceOffres();
        
        ObservableList<Offre> liste = mS.afficheroffrelist();
        //idaffoffre.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id"));
        affichageid.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("id"));
       
        idnbrplaceoff.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("nbr_places"));
        nomoff.setCellValueFactory(new PropertyValueFactory<Offre, String>("nom"));
        
           nomguide.setCellValueFactory(new PropertyValueFactory<Offre, String>("NomGuide"));
             prixoffre.setCellValueFactory(new PropertyValueFactory<Offre, Float>("prix"));
           
        datedebutoffre.setCellValueFactory(new PropertyValueFactory<Offre, Date>("DateDebutOff"));
       datefinnoff.setCellValueFactory(new PropertyValueFactory<Offre, Date>("DateFinOff"));
     
        System.out.println(mS.afficheroffrelist());
        
  
        
        OFFREAFFICHER.setItems(liste);
        FilteredList<Offre> filteredData = new FilteredList<>( liste, e -> true);
        rehcerche.setOnKeyReleased(e -> {
                rehcerche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Offre>) Offre -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        
                        if(Offre.getNomGuide().toLowerCase().contains(lower)){
                            return true ; 
                        }
                         if(Offre.getNom().toLowerCase().contains(lower)){
                            return true ; 
                        }
                        if(Offre.getDateDebutOff().toLocaleString().contains(lower)){
                            return true ; 
                        }
                        if(Offre.getDateFinOff().toLocaleString().contains(lower)){
                            return true ; 
                        }
                      
                        

                        return false;
                    });
                });
                SortedList<Offre> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(  OFFREAFFICHER.comparatorProperty());
                OFFREAFFICHER.setItems(sortedData);
            });

        
     
    }

    @FXML
    private void btnsupprimeroffre(ActionEvent event) {
        
        if (OFFREAFFICHER.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, "Aucune offre n'est selectionné ,veuillez choisir un offre");
        } else {
            int responce = JOptionPane.showConfirmDialog(null, "Vous étes sur de supprimer cet offre ?", "Suppression", JOptionPane.YES_NO_OPTION);
            if (responce == JOptionPane.YES_OPTION) {
                ServiceOffres sa = new ServiceOffres();
              Offre a= OFFREAFFICHER.getSelectionModel().getSelectedItem();
                sa.deleteOffre(a);

                //refresh(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setContentText("Votre offre a été bien supprime");
                JOptionPane.showMessageDialog(null, "offre supprimé");
               afficheroffrecontroller();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Annulation");
                JOptionPane.showMessageDialog(null, "Suppression annulé");
            }

        }
       afficheroffrecontroller();
        new mynotifo().start();
    }
        
        
        
    

    @FXML
    private void modifieroffrebtn(ActionEvent event) {
      
        
          Offre M=    OFFREAFFICHER.getSelectionModel().getSelectedItem();
          if (OFFREAFFICHER.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, "Aucune offre n'est selectionné ,veuillez choisir un offre");
          }else {
            int responce = JOptionPane.showConfirmDialog(null, "Vous étes sur de Modifier cet offre ?", "Modification", JOptionPane.YES_NO_OPTION);
              if (responce == JOptionPane.YES_OPTION) {
      M.setNom(nomoffreajout.getText());
      M.setNomGuide(nomguideoff.getText());
      String n = nbrplacesoff.getText(); 
     int ne=Integer.parseInt(n);
     M.setNbr_places(ne);
     String l = prixoff.getText(); 
    Float le=Float.parseFloat(l);
    M.setPrix(le);
      M.setImage(imagecomp);
  
      ServiceOffres ms = new ServiceOffres();
    if(nbrplacesoff.getText().equals("")||prixoff.getText().equals("")||nomoffreajout.getText().equals("")||prixoffre.getText().equals("")||nomguideoff.getText().equals("")){
             Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERREUR");
            alert.setContentText("remplir tous les champs");
    alert.show();
            
        }
      else {
      try {
    
                       
        ms.updateOffre(M);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.show();
                        alert.setTitle("Modifié!");
                        alert.setContentText("Modification effectué");
                         OFFREAFFICHER.refresh();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.show();
                        alert.setTitle("fail !");
                        alert.setContentText("failed");

                    }
       
       //listagence1.setItems(ms.getMaisonlistnew());
       afficheroffrecontroller();
        new mynotifo().start();
    }
        
        
    }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Annulation");
                JOptionPane.showMessageDialog(null, "Modification annulé");
            }
          }
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


   @FXML
    private void payerbtn(MouseEvent event) {
        
         try {
                   
            Parent parent =  FXMLLoader.load(getClass().getResource("/gui/Paiement.fxml"));
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
    private void filtrer(ActionEvent event) {
    }

    @FXML
    private void btnevent(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/listeoffreReservé.fxml"));
             //  parent = FXMLLoader.load(getClass().getResource("/gui/listeoffreReservé.fxml"));
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
    private void btnoffre(MouseEvent event) {
    }

    @FXML
    private void eventbtn(ActionEvent event) {
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/ajoutereventFXML.fxml"));
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
    private void reserver(MouseEvent event) {
          v = OFFREAFFICHER.getSelectionModel().getSelectedItem(); 
    if (OFFREAFFICHER.getSelectionModel().isEmpty()) 
    {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            JOptionPane.showMessageDialog(null, " Pour reservez  ,veuillez selectionnez un offre");
    }
            else {
            int responce = JOptionPane.showConfirmDialog(null, "Vous étes sur de Reserver cet offre ?", "Reservation", JOptionPane.YES_NO_OPTION);
              if (responce == JOptionPane.YES_OPTION) {
        
         try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("/gui/ResevationOffre.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
          
            stage.setScene(scene);
          
            stage.initStyle(StageStyle.UTILITY);
          
            stage.show();
        } catch (IOException ex) {
           ex.getMessage();
        } 
        
        
        
       
     
    }
    }

    }
        
    
 public class mynotifo extends Thread{
        public void run(){
            try{
            Thread.sleep(3000);
            }catch (InterruptedException ex ){
                Logger.getLogger(AjoutereventFXMLController.class.getName()).log(Level.SEVERE,null,ex);
            }
         Image img = new Image("/Image/small_tick4.png");
            Notifications notifcationBuilder = Notifications.create()
                    
            .title("Opération effectué")
                    .text("Gestion des offres").
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
 private void timenow()
 {
Date z = Date.valueOf(LocalDate.now());


     
SimpleDateFormat dat = new SimpleDateFormat("EEEE-DD-MMM-YYYY hh:mm");
//SimpleDateFormat heu = new SimpleDateFormat("HH:mm");
 //timeevent.setText(heu.format(z));
 dateevent.setText(dat.format(z));
     
    
 }
     
  
}

