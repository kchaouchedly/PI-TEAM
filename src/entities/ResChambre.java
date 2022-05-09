/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author 123
 */
public class ResChambre {
    private int id,tarif,nbr_j,chambre_id;
    private Date date_res;

  


    public ResChambre() {
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
        return "ResChambre{" + "id=" + id + ", tarif=" + tarif + ", nbr_j=" + nbr_j + ", date_res=" + date_res + ", chambre_id=" + chambre_id + '}';
    }

    public ResChambre(int tarif, int nbr_j, Date date_res, int chambre_id) {
        this.tarif = tarif;
        this.nbr_j = nbr_j;
        this.date_res = date_res;
        this.chambre_id = chambre_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResChambre other = (ResChambre) obj;
        if (this.tarif != other.tarif) {
            return false;
        }
        if (this.nbr_j != other.nbr_j) {
            return false;
        }
        if (this.chambre_id != other.chambre_id) {
            return false;
        }
        if (!Objects.equals(this.date_res, other.date_res)) {
            return false;
        }
        return true;
    }

 
    
    
}
