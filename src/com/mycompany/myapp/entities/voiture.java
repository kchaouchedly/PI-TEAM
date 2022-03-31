/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author LENOVO
 */
public class voiture {
     private int id ;
    
    String type , marque ;

    public voiture() {
    }

    public voiture(int id, String type, String marque) {
        this.id = id;
        this.type = type;
        this.marque = marque;
    }

    public voiture(String type, String marque) {
        this.type = type;
        this.marque = marque;
    }

   

    public float getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "voiture{" + "id=" + id + ", type=" + type + ", marque=" + marque + '}';
    }
    
}
