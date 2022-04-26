/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Blog;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author houda
 */
public interface IBlog<T> {
   public void ajouterBlog(T b) throws SQLException;
    public List<T> afficherBlog() throws SQLException;
    public void ModifierBlog(T b,int id);
    public Blog findByTitre(String titre);
     public void SupprimerBlog(int id);
     public List ListerBlogs();
     public Blog RechercheBlog(int id);
     public ObservableList<Blog> getAll();
    
}
