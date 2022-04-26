/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class ReclamationBlogController implements Initializable {

    private String username = "houdakoubaa94@gmail.com";
    private String password = "lavieestbelle9471";
    @FXML
    private AnchorPane email;
    @FXML
    private TextField emailFrom;
    @FXML
    private TextField reclamation;
    @FXML
    private ImageView ic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void envoyer_reclamation(ActionEvent event) throws IOException {
  String to=emailFrom.getText();
  String desc=reclamation.getText();
  

// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
// Etape 2 : Création de l'objet Message
Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress("houdakoubaa94@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject("Reclamation  Blogs");
            message.setText(desc);
            
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
            NotificationH.NotifcationOnAction("Envoie de Reclamation ", "Votre email est bien envoyé");
            
           ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AjouterBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}

