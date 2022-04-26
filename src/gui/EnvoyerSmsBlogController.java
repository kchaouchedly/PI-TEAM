/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class EnvoyerSmsBlogController implements Initializable {

    @FXML
    private TextField text_sms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer_sms(MouseEvent event) throws IOException {
        
        
        String messagetoSend = text_sms.getText();
          String accountSid = "ACc7590419e3a8dc6a9773214b2835a2cf"; // Your Account SID from www.twilio.com/user/account
    String authToken = "cb9040b3aeadb2d78f0c9309f8ad4c22"; // Your Auth Token from www.twilio.com/user/account

    Twilio.init(accountSid, authToken);
    Message message = Message.creator(
    new PhoneNumber("+21694533488"),  // To number
    new PhoneNumber("+12544573677"),  // From number
    messagetoSend                   // SMS body
    ).create();

   NotificationH.NotifcationOnAction("Sms Envoyé", "Nous reviendrons vers vous le plus tot possible ♥");
   
      ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AjouterBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();
        }

   
    @FXML
    private void consulter_map(MouseEvent event) {
        
  
    }

    @FXML
    private void retour_home(MouseEvent event) throws IOException {
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("Acceuil.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();
    }
    }
    

