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
public interface IVol <T>{

    public void ajouterVol(T v) throws SQLException;

    public void modifierVol(T v) throws SQLException;

    public void supprimerVol(int id) throws SQLException;

    public List<T> afficherVol() throws SQLException;
}
