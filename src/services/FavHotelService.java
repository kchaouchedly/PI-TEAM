/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.FavHotel;
import entities.Hotel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author 123
 */
public class FavHotelService implements IFavHotel<FavHotel> {

    Connection connexion;
    Statement stm;

    public FavHotelService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterFavHotel(FavHotel h) throws SQLException {
        String req = "INSERT INTO `favhotel`(`nom_hotel`, `adresse`, `num_tel`, `nbr_etoiles`, `nbr_chambre`, `code_h`, `email`, `image_hotel`) "
                + "VALUES ( ?, ?,?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, h.getNomHotel());
        ps.setString(2, h.getAdresse());
        ps.setInt(3, h.getNumTel());
        ps.setInt(4, h.getNbrEtoiles());
        ps.setInt(5, h.getNbrChambre());
        ps.setInt(6, h.getCodeH());
        ps.setString(7, h.getEmail());
        ps.setString(8, h.getImageHotel());
        ps.executeUpdate();

    }

    @Override
    public void supprimerFavHotel(int id) throws SQLException {
        String Req = "DELETE FROM `favhotel` WHERE  id=" + id + " ";
        try {
            stm = connexion.createStatement();
            stm.execute(Req);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<FavHotel> afficherFavHotel() throws SQLException {
        List<FavHotel> hotels = new ArrayList<>();
        String req = "select * from favhotel";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            FavHotel h = new FavHotel(rst.getInt("id"),
                    rst.getInt("num_tel"),
                    rst.getInt("nbr_etoiles"),
                    rst.getInt("nbr_chambre"),
                    rst.getInt("code_h"),
                    rst.getString("nom_hotel"),
                    rst.getString("adresse"),
                    rst.getString("email"),
                    rst.getString("image_hotel"));

            hotels.add(h);
        }
        return hotels;

    }

}
