package gui;

import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.swing.MapView;

import com.teamdev.jxmaps.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Mapa extends MapView{
	
/**
 * The map object
 */
    private WebView maps;
    @FXML
    private Button map;


    private void cons_map(javafx.scene.input.MouseEvent event) throws IOException {
          WebEngine webEngine = maps.getEngine();
          webEngine.load("https://www.google.com/maps/");
    }

    @FXML
    private void retour(javafx.scene.input.MouseEvent event) throws IOException {
        ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AfficherBlogs.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Map");
            primaryStage.show();    
    }

    @FXML
    private void cons_map(ActionEvent event) {
         WebEngine webEngine = maps.getEngine();
          webEngine.load("https://www.google.com/maps/");
    }
}
	