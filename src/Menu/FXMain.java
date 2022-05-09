/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import entities.Billet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.BilletService;
import services.VolService;

/**
 *
 * @author moham
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        try {
           
            
  
       
               //  Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
                   Parent root = FXMLLoader.load(getClass().getResource("/fxm/MenuFront.fxml"));
                    Scene scene = new Scene(root, 1650, 980);
                    primaryStage.setTitle("LET'S TRAVEl");
                    primaryStage.setScene(scene);
                    primaryStage.show();
         
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
