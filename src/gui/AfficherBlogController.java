/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.webkit.graphics.WCGraphicsManager;
import entities.Blog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Optional.empty;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.BlogService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class AfficherBlogController implements Initializable {

    @FXML
    private ImageView IImage;
    private ImageView img;
    @FXML
    private TextField titreColl;
    @FXML
    private TextField descriptionColl;
    @FXML
    private TextField paysColl;
    @FXML
    private ImageView precedent;
    @FXML
    private Label nbr;
Connection cnx;
public PreparedStatement st;
public ResultSet result;
    @FXML
    private ImageView image;
    @FXML
    private WebView maps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

 showLoggement();
    }

    @FXML
    private void show_suivant() {
        Blob blob;
            byte byteImg[];


        int blog = Integer.valueOf(nbr.getText());
            String sql3=" select * from blog";
            int position=0;
            try 
            { 
                st=cnx.prepareStatement(sql3);
                result=st.executeQuery();
                if(result.next())
                {
                    position=result.getInt("id");
                }}
             catch (SQLException e)
    {
           e.printStackTrace();
            }
         
              String sql2=" select * from blog";
    try {
        st=cnx.prepareStatement(sql2);
        result=st.executeQuery();
        if(result.next())
        {             titreColl.setText(result.getString("titre"));
                      descriptionColl.setText(result.getString("description"));
                      paysColl.setText(result.getString("pays"));
                      blob=result.getBlob("photo");
                      byteImg=blob.getBytes(1,(int) blob.length());
                      Image imag= new Image(new ByteArrayInputStream(byteImg),image.getFitWidth(),image.getFitHeight(),true,true);
                      image.setImage(imag);
        }
        
    }catch (SQLException e)
    {
           e.printStackTrace();
            }
         
                
            }

    

    @FXML
    private void show_precedent(MouseEvent event) {
    }
public void showLoggement() 
{   
    
    cnx = MyDB.getInstance().getConnexion();
    String sql=" select count(*) from blog ";
    int i=0;
    Blob blob;
    byte byteImg[];
    try {
        st=cnx.prepareStatement(sql);
       result=st.executeQuery();
       if(result.next())
       {
           i=result.getInt(1);
                     
       }
           nbr.setText(Integer.toString(i));
           
           
    }catch (SQLException e)
    {
           e.printStackTrace();
            }
    String sql2=" select * from blog";
    try {
        st=cnx.prepareStatement(sql2);
        result=st.executeQuery();
        if(result.next())
        {             titreColl.setText(result.getString("titre"));
                      descriptionColl.setText(result.getString("description"));
                      paysColl.setText(result.getString("pays"));
                      blob=result.getBlob("photo");
                      byteImg=blob.getBytes(1,(int) blob.length());
                      Image imag= new Image(new ByteArrayInputStream(byteImg),image.getFitWidth(),image.getFitHeight(),true,true);
                      image.setImage(imag);
        }
        
    }catch (SQLException e)
    {
           e.printStackTrace();
            }
}

    @FXML
    private void show_map(MouseEvent event) throws IOException {
       WebEngine webEngine = maps.getEngine();
          webEngine.load("https://www.google.com/maps/");
         
            
        
    }

    @FXML
    private void Afficher_liste_blogs(MouseEvent event) {
    }

    @FXML
    private void show_ajoutBlog(ActionEvent event) throws IOException {
        
         ((Node)event.getSource()).getScene().getWindow().hide();
            Stage primaryStage=new Stage();
            FXMLLoader loader=new FXMLLoader();
            Pane root =  loader.load(getClass().getResource("AjouterBlog.fxml"));    
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Blogs");
            primaryStage.show();    
    }
    }

