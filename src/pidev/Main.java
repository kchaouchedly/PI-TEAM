/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class Main extends Application {
    Stage stage;
    Parent parent;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage = primaryStage;
        try {
            //parent = FXMLLoader.load(getClass().getResource("/gui/ajoutereventFXML.fxml"));
            parent = FXMLLoader.load(getClass().getResource("/gui/AjouteroffreFXML.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Acceuil");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}