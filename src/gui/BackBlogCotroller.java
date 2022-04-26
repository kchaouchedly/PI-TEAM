/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Blog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import services.BlogService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author houda
 */
public class BackBlogCotroller implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> email;
 @FXML
    private TableColumn<Blog, String> editCol;
    
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Blog blog = null ;
    @FXML
private FontAwesomeIconView star;
    ObservableList<Blog>  BlogList = FXCollections.observableArrayList();
    @FXML
    private TableView<Blog> tableauBlog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    loadDate();

    }
          

    private void refreshTable() {
        BlogList.clear();
        ObservableList<Blog> blog = new BlogService().getAll();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableauBlog.setItems(blog);
    }
 private void loadDate() {
                         ObservableList<Blog> blogs = new BlogService().getAll();


        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        //add cell of button edit 
         Callback<TableColumn<Blog, String>, TableCell<Blog, String>> cellFoctory = (TableColumn<Blog, String> param) -> {
            // make cell containing buttons
            final TableCell<Blog, String> cell = new TableCell<Blog, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
              FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:30px;"
                                + "-fx-fill:#ff1744;"
                        );
                     
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                blog = tableauBlog.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `blog` WHERE id  ="+blog.getId();
                                        connection= MyDB.getInstance().getConnexion();

                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                NotificationH.NotifcationOnAction("Suppression","Votre Blog est bien Supprimer");
                                refreshTable();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(BackBlogCotroller.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                     

                        HBox managebtn = new HBox( deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
        tableauBlog.setItems(blogs);
         
         
    }
    
}
