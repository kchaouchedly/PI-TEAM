/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Hp
 */
public interface Ioffres <T> {
     public void ajouteroffre(T p )throws SQLException; 
    public List<T> afficheroffre()throws SQLException; 
      public Boolean  deleteOffre(T l ) ; 
       public Boolean updateOffre(T k  ) ; 
        public ObservableList<T> afficheroffrelist(); 

        
}
