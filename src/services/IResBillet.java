/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 123
 */
public interface IResBillet<T> {

    public void ajouterResBillet(T rb) throws SQLException;

    public void modifierResBillet(T rb) throws SQLException;

    public void supprimerResBillet(int id) throws SQLException;

    public List<T> afficherResBillet() throws SQLException;
}