/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation1;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author houda
 */

public class Validation10 extends Application {
    
    Stage stage;
    Parent parent;
    
    @Override
    public void start(Stage primaryStage) {
       this.stage = primaryStage;
        try {
            //parent = FXMLLoader.load(getClass().getResource("/gui/AjouterBlog.fxml"));
             //parent = FXMLLoader.load(getClass().getResource("/gui/AfficherBlogs.fxml"));
             // parent = FXMLLoader.load(getClass().getResource("/gui/ReclamationBlog.fxml"));
            //parent = FXMLLoader.load(getClass().getResource("/gui/EnvoyerSmsBlog.fxml"));
                        //parent = FXMLLoader.load(getClass().getResource("/gui/Mapa.fxml"));
                          //parent = FXMLLoader.load(getClass().getResource("/gui/BackBlog.fxml"));
                       parent = FXMLLoader.load(getClass().getResource("/gui/Acceuil.fxml"));






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
