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
public class Chambre {
    private int id,NbrLits,NumCh,Etage ;
    private String vue,Bloc,dispo,ImageCh;
    private float Prix;
    private int hotel_id;

    public Chambre() {
    }

    public Chambre(int NbrLits, int NumCh, int Etage, String vue, String Bloc, String dispo, String ImageCh, float Prix, int hotel_id) {
        this.NbrLits = NbrLits;
        this.NumCh = NumCh;
        this.Etage = Etage;
        this.vue = vue;
        this.Bloc = Bloc;
        this.dispo = dispo;
        this.ImageCh = ImageCh;
        this.Prix = Prix;
        this.hotel_id = hotel_id;
    }

    public Chambre(int id, int NbrLits, int NumCh, int Etage, String vue, String Bloc, String dispo, String ImageCh, float Prix) {
        this.id = id;
        this.NbrLits = NbrLits;
        this.NumCh = NumCh;
        this.Etage = Etage;
        this.vue = vue;
        this.Bloc = Bloc;
        this.dispo = dispo;
        this.ImageCh = ImageCh;
        this.Prix = Prix;
    }

    public Chambre(int NbrLits, int NumCh, int Etage, String vue, String Bloc, String dispo, String ImageCh, float Prix) {
        this.NbrLits = NbrLits;
        this.NumCh = NumCh;
        this.Etage = Etage;
        this.vue = vue;
        this.Bloc = Bloc;
        this.dispo = dispo;
        this.ImageCh = ImageCh;
        this.Prix = Prix;
    }

    public Chambre(int id, int NbrLits, int NumCh, int Etage, String vue, String Bloc, String dispo, String ImageCh, float Prix, int hotel_id) {
        this.id = id;
        this.NbrLits = NbrLits;
        this.NumCh = NumCh;
        this.Etage = Etage;
        this.vue = vue;
        this.Bloc = Bloc;
        this.dispo = dispo;
        this.ImageCh = ImageCh;
        this.Prix = Prix;
        this.hotel_id = hotel_id;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrLits() {
        return NbrLits;
    }

    public void setNbrLits(int NbrLits) {
        this.NbrLits = NbrLits;
    }

    public int getNumCh() {
        return NumCh;
    }

    public void setNumCh(int NumCh) {
        this.NumCh = NumCh;
    }

    public int getEtage() {
        return Etage;
    }

    public void setEtage(int Etage) {
        this.Etage = Etage;
    }

    public String getVue() {
        return vue;
    }

    public void setVue(String vue) {
        this.vue = vue;
    }

    public String getBloc() {
        return Bloc;
    }

    public void setBloc(String Bloc) {
        this.Bloc = Bloc;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getImageCh() {
        return ImageCh;
    }

    public void setImageCh(String ImageCh) {
        this.ImageCh = ImageCh;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Chambre{" + "id=" + id + ", NbrLits=" + NbrLits + ", NumCh=" + NumCh + ", Etage=" + Etage + ", vue=" + vue + ", Bloc=" + Bloc + ", dispo=" + dispo + ", ImageCh=" + ImageCh + ", Prix=" + Prix + ", hotel_id=" + hotel_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Chambre other = (Chambre) obj;
        if (this.NbrLits != other.NbrLits) {
            return false;
        }
        if (this.NumCh != other.NumCh) {
            return false;
        }
        if (this.Etage != other.Etage) {
            return false;
        }
        if (Float.floatToIntBits(this.Prix) != Float.floatToIntBits(other.Prix)) {
            return false;
        }
        if (this.hotel_id != other.hotel_id) {
            return false;
        }
        if (!Objects.equals(this.vue, other.vue)) {
            return false;
        }
        if (!Objects.equals(this.Bloc, other.Bloc)) {
            return false;
        }
        if (!Objects.equals(this.dispo, other.dispo)) {
            return false;
        }
        if (!Objects.equals(this.ImageCh, other.ImageCh)) {
            return false;
        }
        return true;
    }


    
    
}
