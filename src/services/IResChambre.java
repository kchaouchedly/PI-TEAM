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
public interface IResChambre<T> {

    public void ajouterResChambre(T p) throws SQLException;

    public void modifierResChambre(T p) throws SQLException;

    public void supprimerResChambre(int id) throws SQLException;

    public List<T> afficherResChambre() throws SQLException;
}
