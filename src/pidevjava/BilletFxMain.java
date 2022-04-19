/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

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
 * @author msi
 */
public class BilletFxMain extends Application {
     Stage stage;
    Parent parent;
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxm/Billet.fxml"));
            Scene scene = new Scene(parent);
            stage.setTitle("Gestion des Billet");
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
