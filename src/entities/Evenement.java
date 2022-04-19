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
public class Evenement {
    
             int id ; 
            String Type ,NomEvenement   , image ,color , lieux  ; 
            Date DateDebut ,DateFin ; 
            public static String pathfile; 
            public static String filename="";

    public Evenement() {
    }

    public Evenement(int id, String Type, String NomEvenement, String image, String color, String lieux, Date DateDebut, Date DateFin) {
        this.id = id;
        this.Type = Type;
        this.NomEvenement = NomEvenement;
        this.image = image;
        this.color = color;
        this.lieux = lieux;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

    public Evenement(String Type, String NomEvenement, String image, String color, String lieux, Date DateDebut, Date DateFin) {
        this.Type = Type;
        this.NomEvenement = NomEvenement;
        this.image = image;
        this.color = color;
        this.lieux = lieux;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getNomEvenement() {
        return NomEvenement;
    }

    public void setNomEvenement(String NomEvenement) {
        this.NomEvenement = NomEvenement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public static String getPathfile() {
        return pathfile;
    }

    public static void setPathfile(String pathfile) {
        Evenement.pathfile = pathfile;
    }

    public static String getFilename() {
        return filename;
    }

    public static void setFilename(String filename) {
        Evenement.filename = filename;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Type=" + Type + ", NomEvenement=" + NomEvenement + ", image=" + image + ", color=" + color + ", lieux=" + lieux + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + '}';
    }

  
}
