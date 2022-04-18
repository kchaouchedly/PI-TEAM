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
public interface IChambre<T> {

    public void ajouterChambre(T p) throws SQLException;

    public void modifierChambre(T p) throws SQLException;

    public void supprimerChambre(int id) throws SQLException;

    public List<T> afficherChambre() throws SQLException;
}
