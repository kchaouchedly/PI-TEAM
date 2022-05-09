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
public interface IFavVol<T> {

    public void ajouterFavVOL(T p) throws SQLException;

    public void supprimerFavVol(int id) throws SQLException;

    public List<T> afficherFavVol() throws SQLException;
}
