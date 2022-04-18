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
public class Billet {
    private int id,numB,Prix;
    private String nomCom,imageBillet;
    private Date dateV;
      private int vol_id;

    public Billet(int id, int numB, int Prix, String nomCom, String imageBillet, Date dateV, int vol_id) {
        this.id = id;
        this.numB = numB;
        this.Prix = Prix;
        this.nomCom = nomCom;
        this.imageBillet = imageBillet;
        this.dateV = dateV;
        this.vol_id = vol_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Billet other = (Billet) obj;
        
        if (this.numB != other.numB) {
            return false;
        }
        if (this.Prix != other.Prix) {
            return false;
        }
        if (this.vol_id != other.vol_id) {
            return false;
        }
        if (!Objects.equals(this.nomCom, other.nomCom)) {
            return false;
        }
        if (!Objects.equals(this.imageBillet, other.imageBillet)) {
            return false;
        }
        if (!Objects.equals(this.dateV, other.dateV)) {
            return false;
        }
        return true;
    }

    public Billet(int numB, int Prix, String nomCom, String imageBillet, Date dateV, int vol_id) {
        this.numB = numB;
        this.Prix = Prix;
        this.nomCom = nomCom;
        this.imageBillet = imageBillet;
        this.dateV = dateV;
        this.vol_id = vol_id;
    }

    public int getVol_id() {
        return vol_id;
    }

    public void setVol_id(int vol_id) {
        this.vol_id = vol_id;
    }
      

    public Billet() {
    }

    public Billet(int id, int numB, int Prix, String nomCom, String imageBillet, Date dateV) {
        this.id = id;
        this.numB = numB;
        this.Prix = Prix;
        this.nomCom = nomCom;
        this.imageBillet = imageBillet;
        this.dateV = dateV;
    }

    public Billet(int numB, int Prix, String nomCom, String imageBillet, Date dateV) {
        this.numB = numB;
        this.Prix = Prix;
        this.nomCom = nomCom;
        this.imageBillet = imageBillet;
        this.dateV = dateV;
    }

    @Override
    public String toString() {
        return "Billet{" + "id=" + id + ", numB=" + numB + ", Prix=" + Prix + ", nomCom=" + nomCom + ", imageBillet=" + imageBillet + ", dateV=" + dateV + ", vol_id=" + vol_id + '}';
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumB() {
        return numB;
    }

    public void setNumB(int numB) {
        this.numB = numB;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public String getNomCom() {
        return nomCom;
    }

    public void setNomCom(String nomCom) {
        this.nomCom = nomCom;
    }

    public String getImageBillet() {
        return imageBillet;
    }

    public void setImageBillet(String imageBillet) {
        this.imageBillet = imageBillet;
    }

    public Date getDateV() {
        return dateV;
    }

    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }
    
    
}
