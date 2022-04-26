/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.mysql.jdbc.Blob;
import javafx.scene.image.Image;

/**
 *
 * @author houda
 */
public class Blog {
      private int id;
     private String email,description,pays,titre;
     private String photo;

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    

  

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }



    public Blog(String email, String description, String pays, String titre,  String photo) {
        this.email = email;
        this.description = description;
        this.pays = pays;
        this.titre = titre;
        this.photo=photo;
    }
        public Blog(int id,String email, String description, String pays, String titre) {
        this.id=id;
        this.email = email;
        this.description = description;
        this.pays = pays;
        this.titre = titre;
       
    }

        public Blog(String email, String description, String pays, String titre) {
        this.email = email;
        this.description = description;
        this.pays = pays;
        this.titre = titre;
       
    }
    public Blog(int id, String email, String description, String pays, String titre, String photo) {
        this.id = id;
        this.email = email;
        this.description = description;
        this.pays = pays;
        this.titre = titre;
        this.photo = photo;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", email=" + email + ", description=" + description + ", pays=" + pays + ", titre=" + titre + ", photo=" + photo + '}';
    }

    

    
    
     
    
}
