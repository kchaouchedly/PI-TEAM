/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Hp
 */
public class reservationoff {
    private int id ; 

private Date datedebut ; 
    private Date datefin ; 
    private int ido ; 

    public reservationoff() {
    }

    public reservationoff(Date datedebut, Date datefin, int ido) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.ido = ido;
    }

    public reservationoff(int id, Date datedebut, Date datefin, int ido) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.ido = ido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    @Override
    public String toString() {
        return "reservationoff{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", ido=" + ido + '}';
    }
    
    
}
