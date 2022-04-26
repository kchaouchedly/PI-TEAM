/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Blog;
import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author houda
 */
public class CommentaireService implements ICommentaire<Commentaire>{
Connection connexion;
    Statement stm;

    public CommentaireService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public int addCommentaire(Commentaire Com) throws SQLException {
  String req = "INSERT INTO `commentaire`( `id_publication`, `contenu`, `date`) VALUES (?,?,?) " ; 
         
        PreparedStatement preparedStatement = connexion.prepareStatement(req);
         
         preparedStatement.setInt(1, Com.getIdPub().getId());
         preparedStatement.setString(2,Com.getTexte());
         preparedStatement.setString(3,Com.getDate().toString());
         
        return preparedStatement.executeUpdate() ; 
    }

    public List<Commentaire> getCommentairePerPub(Blog Pp) throws SQLException {
     
        Statement ste = connexion.createStatement() ; 
        
        String req = "Select * from commentaire where id_publication="+Pp.getId()+" " ; 
        
       ResultSet set= ste.executeQuery(req) ; 
        
          List<Commentaire> ListCommentaire = new ArrayList<>() ; 
      while (set.next())
              {
                  Commentaire com = new Commentaire();
                 
                  
                 LocalDate Datepubcom=set.getDate("date").toLocalDate(); 
                  
                  com.setDate(Datepubcom);
                  com.setIdCom(set.getInt("id_com")); 
                  com.setIdPub(Pp);
                  com.setTexte(set.getString("contenu"));
                  
                  ListCommentaire.add(com);
              }
      
  
      return ListCommentaire ; 
    }

    @Override
    public List<Commentaire> getCommentaire(Commentaire Pp) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
