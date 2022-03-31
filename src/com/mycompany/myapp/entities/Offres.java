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
public class Offres {
        private int id;
    private String nom, NomGuide; 

    public Offres(int id, String nom, String NomGuide) {
        this.id = id;
        this.nom = nom;
        this.NomGuide = NomGuide;
    }

    public Offres(String nom, String NomGuide) {
        this.nom = nom;
        this.NomGuide = NomGuide;
    }

    public Offres() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Offres{" + "id=" + id + ", nom=" + nom + ", NomGuide=" + NomGuide + '}';
    }
    
}
