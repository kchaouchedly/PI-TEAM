/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author 123
 */
public class ResChambre {
    private int id,tarif,nbr_j;
    private Date date_res;
    private int chambre_id;

    public ResChambre() {
    }

    public ResChambre(int id, int tarif, int nbr_j, Date date_res) {
        this.id = id;
        this.tarif = tarif;
        this.nbr_j = nbr_j;
        this.date_res = date_res;
    }

    public ResChambre(int tarif, int nbr_j, Date date_res) {
        this.tarif = tarif;
        this.nbr_j = nbr_j;
        this.date_res = date_res;
    }

    public ResChambre(int id, int tarif, int nbr_j, Date date_res, int chambre_id) {
        this.id = id;
        this.tarif = tarif;
        this.nbr_j = nbr_j;
        this.date_res = date_res;
        this.chambre_id = chambre_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public int getNbr_j() {
        return nbr_j;
    }

    public void setNbr_j(int nbr_j) {
        this.nbr_j = nbr_j;
    }

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public int getChambre_id() {
        return chambre_id;
    }

    public void setChambre_id(int chambre_id) {
        this.chambre_id = chambre_id;
    }

    @Override
    public String toString() {
        return "\n Reservation Chambre{" + "id=" + id + ", tarif=" + tarif + ", nbr_j=" + nbr_j + ", date_res=" + date_res + '}';
    }
    
    
}
