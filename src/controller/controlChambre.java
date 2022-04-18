/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Chambre;
import entities.Vol;

/**
 *
 * @author msi
 */
public class controlChambre {
    
   
    public boolean controlNbrLits(int nb) {
        if ((nb == 1) || (nb == 2) || (nb == 3) || (nb == 4)) {
            return true;
        }
        return false;
    }

    public static boolean ControleString(String st) {
        String str = st.toLowerCase();

        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }
    
    
}
