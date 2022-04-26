/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author houda
 */
public class Commentaire {
    
     private Integer idCom;
  
    private String texte;
    
    private LocalDate date;
 
    private Blog idPub;

    public Integer getIdCom() {
        return idCom;
    }

    public void setIdCom(Integer idCom) {
        this.idCom = idCom;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Blog getIdPub() {
        return idPub;
    }

    public void setIdPub(Blog idPub) {
        this.idPub = idPub;
    }

    public Commentaire() {
    }

    public Commentaire(Integer idCom, String texte, LocalDate date, Blog idPub) {
        this.idCom = idCom;
        this.texte = texte;
        this.date = date;
        this.idPub = idPub;
    }

    public Commentaire(String texte, LocalDate date, Blog idPub) {
        this.texte = texte;
        this.date = date;
        this.idPub = idPub;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCom=" + idCom + ", texte=" + texte + ", date=" + date + ", idPub=" + idPub + '}';
    }

    

    
}
