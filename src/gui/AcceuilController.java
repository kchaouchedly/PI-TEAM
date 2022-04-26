/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class AcceuilController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
    }

    @FXML
    private void Affiche_front(MouseEvent event) throws IOException {
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
    private void Affiche_Back(MouseEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("BackBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
    }
    
}
