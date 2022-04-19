/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import entities.Evenement;
import entities.Offre;
import java.sql.SQLException;
import services.ServiceEvenement;
import services.ServiceOffres;

/**
 *
 * @author Hp
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

       
        
       // Evenement p = new Evenement("sport", "coupe du monde pm","2022-04-06" , "2022-04-09", "ll", "p8p", "1Qatar");
    //  Offre o = new Offre(7, "coupe", "2022-04-06", "2022-04-06", "fathi", "", 8);
        
        ServiceEvenement  sp = new ServiceEvenement();
        ServiceOffres l = new ServiceOffres(); 
        
       /* try {
           sp.ajouterevenement(p);
           sp.deleteEvent(119);
         sp.updateEvent(126, p);
           l.ajouteroffre(o);
        //   l.deleteOffre(6);
        
          //    System.out.println("Deleted");
           
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            System.out.println(sp.afficherevenement());
            System.out.println(l.afficheroffre());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    }*/
      // l.updateOffre(1,"coupeee"); 
        
        
    }
}
    

