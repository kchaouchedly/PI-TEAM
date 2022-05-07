/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Hp
 */
public class Offre {
    int id , nbr_places  ; 
            String nom ,NomGuide , image  ; 
            float prix ; 
            Date  DateDebutOff , DateFinOff ; 
            int nom_event; 
   public static String pathfile; 
            public static String filename="";
            
    public Offre() {
        
    }

    public Offre(int id, int nbr_places, String nom, String NomGuide, String image, float prix, Date DateDebutOff, Date DateFinOff ,int nom_event) {
        this.nom_event=nom_event ; 
        this.id = id;
        this.nbr_places = nbr_places;
        this.nom = nom;
        this.NomGuide = NomGuide;
        this.image = image;
        this.prix = prix;
        this.DateDebutOff = DateDebutOff;
        this.DateFinOff = DateFinOff;
       
    }

    public Offre(int nom_event,int nbr_places, String nom, String NomGuide, String image, float prix, Date DateDebutOff, Date DateFinOff) {
        this.nom_event=nom_event ; 
        this.nbr_places = nbr_places;
        this.nom = nom;
        this.NomGuide = NomGuide;
        this.image = image;
        this.prix = prix;
        this.DateDebutOff = DateDebutOff;
        this.DateFinOff = DateFinOff;
         
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomGuide() {
        return NomGuide;
    }

    public void setNomGuide(String NomGuide) {
        this.NomGuide = NomGuide;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateDebutOff() {
        return DateDebutOff;
    }

    public void setDateDebutOff(Date DateDebutOff) {
        this.DateDebutOff = DateDebutOff;
    }

    public Date getDateFinOff() {
        return DateFinOff;
    }

    public void setDateFinOff(Date DateFinOff) {
        this.DateFinOff = DateFinOff;
    }

    public int getNom_event() {
        return nom_event;
    }

    public void setNom_event(int nom_event) {
        this.nom_event = nom_event;
    }

    

    public static String getPathfile() {
        return pathfile;
    }

    public static void setPathfile(String pathfile) {
        Offre.pathfile = pathfile;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Offre.filename = filename;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", nbr_places=" + nbr_places + ", nom=" + nom + ", NomGuide=" + NomGuide + ", image=" + image + ", prix=" + prix + ", DateDebutOff=" + DateDebutOff + ", DateFinOff=" + DateFinOff + '}';
    }
    

            
}
