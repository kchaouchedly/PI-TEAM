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
public class ResBillet {
    
    private int id,tarif,nbrPas,billet_id;
    private String classe;

    public ResBillet() {
    }

  public ResBillet(int id, int tarif, int nbrPas, int billet_id, String classe) {
        this.id = id;
        this.tarif = tarif;
        this.nbrPas = nbrPas;
        this.billet_id = billet_id;
        this.classe = classe;
    }

 
    public ResBillet(int tarif, int nbrPas, int billet_id, String classe) {
    
        this.tarif = tarif;
        this.nbrPas = nbrPas;
        this.billet_id = billet_id;
        this.classe = classe;
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

    public int getNbrPas() {
        return nbrPas;
    }

    public void setNbrPas(int nbrPas) {
        this.nbrPas = nbrPas;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "ResBillet{" + "tarif=" + tarif + ", nbrPas=" + nbrPas + ", billet_id=" + billet_id + ", classe=" + classe + '}';
    }

    public int getBillet_id() {
        return billet_id;
    }

    public void setBillet_id(int billet_id) {
        this.billet_id = billet_id;
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
        final ResBillet other = (ResBillet) obj;
        if (this.tarif != other.tarif) {
            return false;
        }
        if (this.nbrPas != other.nbrPas) {
            return false;
        }
        if (this.billet_id != other.billet_id) {
            return false;
        }
        if (!Objects.equals(this.classe, other.classe)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
