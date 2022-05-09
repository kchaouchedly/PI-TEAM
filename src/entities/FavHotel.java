/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author 123
 */
public class FavHotel {
    private int id, NumTel, NbrEtoiles, NbrChambre, CodeH;
    private String NomHotel, Adresse, email, imageHotel;

    public FavHotel() {
    }

    public FavHotel(int NumTel, int NbrEtoiles, int NbrChambre, int CodeH, String NomHotel, String Adresse, String email, String imageHotel) {
        this.NumTel = NumTel;
        this.NbrEtoiles = NbrEtoiles;
        this.NbrChambre = NbrChambre;
        this.CodeH = CodeH;
        this.NomHotel = NomHotel;
        this.Adresse = Adresse;
        this.email = email;
        this.imageHotel = imageHotel;
    }

    public FavHotel(int id, int NumTel, int NbrEtoiles, int NbrChambre, int CodeH, String NomHotel, String Adresse, String email, String imageHotel) {

        this.id = id;
        this.NumTel = NumTel;
        this.NbrEtoiles = NbrEtoiles;
        this.NbrChambre = NbrChambre;
        this.CodeH = CodeH;
        this.NomHotel = NomHotel;
        this.Adresse = Adresse;
        this.email = email;
        this.imageHotel = imageHotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int NumTel) {
        this.NumTel = NumTel;
    }

    public int getNbrEtoiles() {
        return NbrEtoiles;
    }

    public void setNbrEtoiles(int NbrEtoiles) {
        this.NbrEtoiles = NbrEtoiles;
    }

    public int getNbrChambre() {
        return NbrChambre;
    }

    public void setNbrChambre(int NbrChambre) {
        this.NbrChambre = NbrChambre;
    }

    public int getCodeH() {
        return CodeH;
    }

    public void setCodeH(int CodeH) {
        this.CodeH = CodeH;
    }

    public String getNomHotel() {
        return NomHotel;
    }

    public void setNomHotel(String NomHotel) {
        this.NomHotel = NomHotel;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String Adresse) {
        this.Adresse = Adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(String imageHotel) {
        this.imageHotel = imageHotel;
    }

    @Override
    public String toString() {
        return "\nHotel{" + "id=" + id + ", NumTel=" + NumTel + ", NbrEtoiles=" + NbrEtoiles + ", NbrChambre=" + NbrChambre + ", CodeH=" + CodeH + ", NomHotel=" + NomHotel + ", Adresse=" + Adresse + ", email=" + email + ", imageHotel=" + imageHotel + '}';
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
        final FavHotel other = (FavHotel) obj;

        if (this.NumTel != other.NumTel) {
            return false;
        }
        if (this.NbrEtoiles != other.NbrEtoiles) {
            return false;
        }
        if (this.NbrChambre != other.NbrChambre) {
            return false;
        }
        if (this.CodeH != other.CodeH) {
            return false;
        }
        if (!Objects.equals(this.NomHotel, other.NomHotel)) {
            return false;
        }
        if (!Objects.equals(this.Adresse, other.Adresse)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.imageHotel, other.imageHotel)) {
            return false;
        }
        return true;
    }




}
