/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Hp
 */
public interface Ievenement<T> {
    public void ajouterevenement(T p )throws SQLException; 
    public List<T> afficherevenement()throws SQLException; 
       public Boolean  deleteEvent(T l ) ; 
    public Boolean updateEvent(T k  ) ; 
    public ObservableList<T> afficherevent(); 
}
