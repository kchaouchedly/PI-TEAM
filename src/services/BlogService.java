/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.javafx.iio.ImageStorage;
import entities.Blog;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.scene.input.KeyCode.T;
import javax.sql.rowset.serial.SerialBlob;
import utils.MyDB;

/**
 *
 * @author houda
 */

public class BlogService implements IBlog<Blog>{
Connection connexion;
    Statement stm;

    public BlogService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    /**
     *
     * @param p
     * @throws SQLException
     */
    @Override
 
    
      public void ajouterBlog(Blog p) throws SQLException {
        String req = "INSERT INTO `blog` (`email`, `description`,`pays`,`titre`,`photo` ) "
                + "VALUES ( ?, ?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getEmail());
        ps.setString(2, p.getDescription());
        ps.setString(3, p.getPays());
        ps.setString(4, p.getTitre());
        ps.setString(5,p.getPhoto());



       
        ps.executeUpdate();
    }
    


    @Override
    public List<Blog> afficherBlog() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String req = "select * from blog";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

       
        return blogs;
    }
    

    @Override
    public void ModifierBlog(Blog b, int id) {
try{
            
        String req2 = "UPDATE `blog` SET `email`=?,`description`=?,`pays`=?,`titre`=? WHERE id=? ";
                PreparedStatement st = connexion.prepareStatement(req2);
		
            st.setString(1, b.getEmail());
            st.setString(2, b.getDescription());
            st.setString(3, b.getPays());
            st.setString(4, b.getTitre());
            st.setInt(5,id);
                st.executeUpdate();
                
                 System.out.println("Blog  mis à jour avec succès !");
                 System.out.println(b.toString());
        }catch (SQLException ex) {
            System.out.println(b.toString());
            System.out.println("erreur lors de la modification " + ex.getMessage());
        }
    }

    @Override
    public void SupprimerBlog(int id) {
		 String req3 = "DELETE FROM `blog` WHERE id=? ";
        try {
             PreparedStatement st = connexion.prepareStatement(req3);

            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Blog supprimé avec succès !");

        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
	}
    
    
    

    @Override
    public List ListerBlogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog RechercheBlog(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog findByTitre(String titre) {
        
 String requete = "SELECT * FROM `blog` WHERE `titre`='"+titre+"'";

        try {
            PreparedStatement ps = connexion.prepareStatement(requete);
            ResultSet resultat = ps.executeQuery();
             
            
            Blog blog = new Blog();
            while (resultat.next()) {

                blog.setId(resultat.getInt(1));
                blog.setEmail(resultat.getString(2));
                blog.setDescription(resultat.getString(3));
                blog.setPays(resultat.getString(4));
                blog.setTitre(resultat.getString(5));
               
        
            }

            return blog;
        } catch (SQLException ex) {
            System.out.println(requete);
            System.out.println("erreur lors du chargement" + ex.getMessage());
           
            
      }
        return null;
    }

    @Override
    public ObservableList<Blog> getAll() {
        
        ObservableList <Blog> list = FXCollections.observableArrayList();
        String req = "select * from `blog` ";

        try {
            PreparedStatement pst = connexion.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                    list.add(new Blog(rs.getInt("Id"),rs.getString("Email"),rs.getString("Description"),rs.getString("Pays"),rs.getString("Titre")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
        }

   

 
}
