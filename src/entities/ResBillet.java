/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author 123
 */
public class ResBillet {
    
    private int id,tarif,nbrPas;
    private String classe;

    public ResBillet() {
    }

    public ResBillet(int id, int tarif, int nbrPas, String classe) {
        this.id = id;
        this.tarif = tarif;
        this.nbrPas = nbrPas;
        this.classe = classe;
    }

    public ResBillet(int tarif, int nbrPas, String classe) {
        this.tarif = tarif;
        this.nbrPas = nbrPas;
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
        return "\nResBillet{" + "id=" + id + ", tarif=" + tarif + ", nbrPas=" + nbrPas + ", classe=" + classe + '}';
    }
    
    
}
