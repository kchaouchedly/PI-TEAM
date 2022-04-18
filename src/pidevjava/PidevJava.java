/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Billet;
import entities.Chambre;
import entities.Hotel;
import entities.ResBillet;
import entities.ResChambre;
import entities.Vol;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.BilletService;
import services.ChambreService;
import services.HotelService;
import services.ResBilletService;
import services.ResChambreService;
import services.VolService;

/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//
        Hotel h = new Hotel(9, 9, 9, 9, "hh", "hh", "hh", "hh");
        Hotel h2 = new Hotel(53,7, 7, 7, 7, "o", "o", "n", "n");
//        
        HotelService sh = new HotelService();

//        try {
//            sh.modifierHotel(h2);
//            System.out.println("Hotel Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sh.ajouterHotel(h2);
//            System.out.println("Hotel Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
        try {
            sh.supprimerHotel(59);
            System.out.println("Hotel supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//
//        try {
//            System.out.println(sh.afficherHotel());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
///////////////////// CHAMBRE //////////////////////////////////        
//        Chambre ch1 = new Chambre(4, 17, 1, "Vue Jardin", "A", "Disponible", "image", 150);
        Chambre ch2 = new Chambre(64, 2, 7, 3, "Vue mere", "D", "NON Disponible", "image", 150,59);
        ChambreService sc = new ChambreService();
//        try {
//            sc.ajouterChambre(ch2);
//            System.out.println("Chambre Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sc.modifierChambre(ch2);
//            System.out.println("Chambre Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sc.supprimerChambre(17);
//            System.out.println("Chambre supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            System.out.println(sc.afficherChambre());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
///////////////////// RESERVATION CHAMBRE ////////////////////////////////// 
//        ResChambre res1 = new ResChambre(2000,15,new java.sql.Date(269998777));
//        ResChambre res2 = new ResChambre(66,4500,10,new java.sql.Date(269998777));
//
//        ResChambreService rcs = new ResChambreService();
//
//        try {
//            rcs.ajouterResChambre(res1);
//            System.out.println("Rservation Chambre Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            rcs.modifierResChambre(res2);
//            System.out.println("Reservation Chambre Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
////        }
//        try {
//            rcs.supprimerResChambre(67);
//            System.out.println("Reservation Chambre supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            System.out.println(rcs.afficherResChambre());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
///////////////////// Vol //////////////////////////////////        
//        Vol v1 = new Vol(17, 500, new java.sql.Date(369998777), new java.sql.Date(569998777), "TUNIS", "PARIS", "Image", "ALLEZ");
//        Vol v2 = new Vol(28, 70, 2500, new java.sql.Date(369998777), new java.sql.Date(569998777), "TUNIS", "DUBAI", "Image", "ALLEZ");
//
//        VolService sv = new VolService();
//        try {
//            sv.modifierVol(v2);
//            System.out.println("Vol Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sv.ajouterVol(v1);
//            System.out.println("Vol Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }                   
//        try {
//            sv.supprimerVol(29);
//            System.out.println("Vol supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            System.out.println(sv.afficherVol());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

///////////////////// Billet //////////////////////////////////
//
//        Billet b1 = new Billet(7,200,"QATAR_AIRWAYS","Image",new java.sql.Date(269998777));
//        Billet b2 = new Billet(34,334,2500,"QATAR_AIRWAYS","Image",new java.sql.Date(269998777));
//        
//        BilletService sb = new BilletService();

//        try {
//            sb.modifierBillet(b2);
//            System.out.println("Billet Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sb.ajouterBillet(b1);
//            System.out.println("Billet Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            sb.supprimerBillet(35);
//            System.out.println("Billet supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        try {
//            System.out.println(sb.afficherBillet());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

///////////////////// Réservation Billet //////////////////////////////////
        
//        ResBillet resb1 = new ResBillet(800, 5, "First Classe");
//        ResBillet resb2 = new ResBillet(32,6000,60, "First Classe");
//        ResBilletService rsb = new ResBilletService();

//        try {
//            rsb.modifierResBillet(resb2);
//            System.out.println("Rservation Billet Modifié");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            rsb.ajouterResBillet(resb1);
//            System.out.println("Réservation Billet Ajouté");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            rsb.supprimerResBillet(30);
//            System.out.println("Réservation Billet supprimé");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

//        try {
//            System.out.println(rsb.afficherResBillet());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
   }

}
