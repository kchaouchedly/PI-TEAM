/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Blog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class AjouterFXMLController implements Initializable {

    private Label lusername;
    @FXML
    private TextField temail;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tpays;
    @FXML
    private TextField ttitre;
    private TextField tphoto;
    private Label lListeP;

    private Image imageSer;
    
     private File selecFile;
    @FXML
    private ImageView IImage;
    private String s;
    String path ; 
    
    /**
     * Initializes the controller class.
     */
    public void SetUsername(String username) {
        lusername.setText(username);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 

       @FXML
    private void BtnAjouterP(ActionEvent event) {
       
        Blog p = new Blog(temail.getText(), tdescription.getText(),tpays.getText(),ttitre.getText(),path);
        
        BlogService ps = new BlogService();
        try {
            if (  temail.getText().equals(""))
                    
            { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("Email Obligatoire");
            alert.show();
            }
              if (  tdescription.getText().equals(""))
                    
            { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setContentText("Description Obligatoire");
            alert.show();
            }
            else {
            ps.ajouterBlog(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Blog ajoutée");
            alert.show();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void goAddService(ActionEvent event) {
        try
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AjouterFXML.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter Blog ");
            primaryStage.show();    
        }catch(Exception e)
        {
            
        }
        
    
    }
    
    
      public void goUpdateService(ActionEvent event) {
        try
        {  
           
              
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("UpdateBlogFXML.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Modifier Blog");
            primaryStage.show();    
        }catch(Exception e)
        {
            
        }
        
    
    }
    private void AfficherListePersonnes(ActionEvent event) {
        BlogService ps=new BlogService();
        try {
            lListeP.setText(ps.afficherBlog().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public void goDisplayServicesFirst(ActionEvent event) {
       
        try
        {
           ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AfficherBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("La Liste des Blogs");
            primaryStage.show();    
        }catch(Exception e)
        {
            
        }
        
    
    }

    @FXML
     public void ChoiceImage(ActionEvent event) throws IOException
    { 
        ImageTools It= new ImageTools();
        path="" ; 
        
            try {
                path= It.LoadImagePath(); 
            
                File f = new File(path); 
               
                
                Process p = Runtime.getRuntime().exec("cmd /c copy "+f.getPath()+"  C:\\Users\\houda\\Desktop\\JAVAFX\\Validation1.0\\");
                
                path="C:\\Users\\houda\\Desktop\\JAVAFX\\Validation1.0\\photo"+f.getName() ;
                                System.out.println(path);

                                Alert AlertAjout = new Alert(Alert.AlertType.INFORMATION);
        AlertAjout.setTitle("Photo Ajouté");
        AlertAjout.setHeaderText(null);
        AlertAjout.setContentText("Vous avez ajouté une Photo");
        AlertAjout.showAndWait();


        
                        } catch (IOException ex) {
                Logger.getLogger(AjouterFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    
        };

    @FXML
    private void Afficher_liste_blogs(MouseEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AfficherBlogs.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
    
    }

    @FXML
    private void show_reclamation(ActionEvent event) throws IOException {
        
          ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("ReclamationBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
    
        
        
    }

    @FXML
    private void show_sms(ActionEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("EnvoyerSmsBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
    }
        
      
     }

    
 

