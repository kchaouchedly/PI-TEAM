/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Blog;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.BlogService;

/**
 * FXML Controller class
 *
 * @author houdagoUpdateService
 */
public class UpdateBlogFXMLController implements Initializable {
@FXML
    private TextField TNomService;
  @FXML
    private TextField temail;
    @FXML
    private TextField tdescription;
    @FXML
    private TextField tpays;
    @FXML
    private TextField ttitre;
    @FXML
    private TextField tphoto;
     @FXML
    private Button btn_charger_info;
    @FXML
    private Button btn_Modifier;
    @FXML
    private Button btn_Annuler;
    @FXML
    private Button btnMenu_AddService;
    @FXML
    private Button btnMenu_UpdateService;
    @FXML
    private Button btnMenu_DeleteService;
    @FXML
    private Button btnMenu_DisplayServicesFirst;
    @FXML
    private Button btnMenu_SearchService;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
     @FXML  
  
    public void goAddService(ActionEvent event) {
        try
        {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AjouterFXML.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter Service--Deal For All");
            primaryStage.show();    
        }catch(Exception e)
        {
            
        }
        
    
    }
    
    @FXML
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
      
   @FXML
   public void updateBlog(ActionEvent event)
    {  
        Blog blog=new Blog();
         BlogService ps=new BlogService();    
         Blog svAModifier=ps.findByTitre(TNomService.getText());
        blog.setEmail(temail.getText());
        blog.setDescription(tdescription.getText());
        blog.setPays(tpays.getText());
        blog.setTitre(ttitre.getText());
        ps.ModifierBlog(blog,svAModifier.getId());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succes");
        alert.setContentText("Blog Modifie");
        alert.show();
    
    }
     @FXML    
     public void LoadData(ActionEvent event) throws FileNotFoundException, IOException {
           
            BlogService ps=new BlogService();    
               Blog svAModifier=ps.findByTitre(TNomService.getText());
              
              temail.setText(svAModifier.getEmail());
              ttitre.setText(svAModifier.getTitre());
              tdescription.setText(svAModifier.getDescription());
              tpays.setText(svAModifier.getPays());
             
              
            
//              BufferedImage bufferedImage = ImageIO.read(selecFile);
//          imageSer   = SwingFXUtils.toFXImage(bufferedImage, null);
//                IImage.setImage(imageSer);
              
             // InputStream f = new FileInputStream(svAModifier.getImage()) ;

            
    }  
    
}
