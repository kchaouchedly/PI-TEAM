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
public class Vol {
    private int id,NumVol,nbrPlace;
    private Date DateDepart,DateArrive;
    private String VilleDepart,VilleArrive,imageVol,typeV;

    public Vol() {
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
        final Vol other = (Vol) obj;
        if (this.NumVol != other.NumVol) {
            return false;
        }
        if (this.nbrPlace != other.nbrPlace) {
            return false;
        }
        if (!Objects.equals(this.VilleDepart, other.VilleDepart)) {
            return false;
        }
        if (!Objects.equals(this.VilleArrive, other.VilleArrive)) {
            return false;
        }
        if (!Objects.equals(this.imageVol, other.imageVol)) {
            return false;
        }
        if (!Objects.equals(this.typeV, other.typeV)) {
            return false;
        }
        if (!Objects.equals(this.DateDepart, other.DateDepart)) {
            return false;
        }
        if (!Objects.equals(this.DateArrive, other.DateArrive)) {
            return false;
        }
        return true;
    }

    public Vol(int id, int NumVol, int nbrPlace, Date DateDepart, Date DateArrive, String VilleDepart, String VilleArrive, String imageVol, String typeV) {
        this.id = id;
        this.NumVol = NumVol;
        this.nbrPlace = nbrPlace;
        this.DateDepart = DateDepart;
        this.DateArrive = DateArrive;
        this.VilleDepart = VilleDepart;
        this.VilleArrive = VilleArrive;
        this.imageVol = imageVol;
        this.typeV = typeV;
    }

    public Vol(int NumVol, int nbrPlace, Date DateDepart, Date DateArrive, String VilleDepart, String VilleArrive, String imageVol, String typeV) {
        this.NumVol = NumVol;
        this.nbrPlace = nbrPlace;
        this.DateDepart = DateDepart;
        this.DateArrive = DateArrive;
        this.VilleDepart = VilleDepart;
        this.VilleArrive = VilleArrive;
        this.imageVol = imageVol;
        this.typeV = typeV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumVol() {
        return NumVol;
    }

    public void setNumVol(int NumVol) {
        this.NumVol = NumVol;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public Date getDateDepart() {
        return DateDepart;
    }

    public void setDateDepart(Date DateDepart) {
        this.DateDepart = DateDepart;
    }

    public Date getDateArrive() {
        return DateArrive;
    }

    public void setDateArrive(Date DateArrive) {
        this.DateArrive = DateArrive;
    }

    public String getVilleDepart() {
        return VilleDepart;
    }

    public void setVilleDepart(String VilleDepart) {
        this.VilleDepart = VilleDepart;
    }

    public String getVilleArrive() {
        return VilleArrive;
    }

    public void setVilleArrive(String VilleArrive) {
        this.VilleArrive = VilleArrive;
    }

    public String getImageVol() {
        return imageVol;
    }

    public void setImageVol(String imageVol) {
        this.imageVol = imageVol;
    }

    public String getTypeV() {
        return typeV;
    }

    public void setTypeV(String typeV) {
        this.typeV = typeV;
    }

    @Override
    public String toString() {
        return "\nVol{" + "id=" + id + ", NumVol=" + NumVol + ", nbrPlace=" + nbrPlace + ", DateDepart=" + DateDepart + ", DateArrive=" + DateArrive + ", VilleDepart=" + VilleDepart + ", VilleArrive=" + VilleArrive + ", imageVol=" + imageVol + ", typeV=" + typeV + '}';
    }
    
    
    
}
