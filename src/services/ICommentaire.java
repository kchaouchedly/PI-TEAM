/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author houda
 */

   
public interface ICommentaire<T> {
    
    
    public int addCommentaire(T Com)throws SQLException;
    public  List<Commentaire> getCommentaire(T Pp)throws SQLException;
}
 