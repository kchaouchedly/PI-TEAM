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
public interface IBillet<T> {

    public void ajouterBillet(T p) throws SQLException;

    public void modifierBillet(T p) throws SQLException;

    public void supprimerBillet(int id) throws SQLException;

    public List<T> afficherBillet() throws SQLException;
}
