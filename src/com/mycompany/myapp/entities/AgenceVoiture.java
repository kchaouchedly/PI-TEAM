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
public class AgenceVoiture {
    private int id;
    private String nomagence,adresse;

    public AgenceVoiture() {
    }

    public AgenceVoiture(int id, String nomagence, String adresse) {
        this.id = id;
        this.nomagence = nomagence;
        this.adresse = adresse;
    }

    public AgenceVoiture(String nomagence, String adresse) {
        this.nomagence = nomagence;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomagence() {
        return nomagence;
    }

    public void setNomagence(String nomagence) {
        this.nomagence = nomagence;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "AgenceVoiture{" + "id=" + id + ", nomagence=" + nomagence + ", adresse=" + adresse + '}';
    }
    
}
