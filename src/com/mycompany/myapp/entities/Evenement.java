/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Hp
 */
public class Evenement {
      private int id;
    private String Type, NomEvenement; 

    public Evenement(int id, String Type, String NomEvenement) {
        this.id = id;
        this.Type = Type;
        this.NomEvenement = NomEvenement;
    }

    public Evenement(String Type, String NomEvenement) {
        this.Type = Type;
        this.NomEvenement = NomEvenement;
    }

    public Evenement() {
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

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", Type=" + Type + ", NomEvenement=" + NomEvenement + '}';
    }
    
}
