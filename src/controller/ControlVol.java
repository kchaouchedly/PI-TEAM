/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Hotel;
import entities.Vol;

/**
 *
 * @author msi
 */
public class ControlVol {
   public static boolean ControleVille(String st) {
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

    public boolean controlNbrPlace(int nb) {
        if (nb >= 100) {
            return true;
        }
        return false;
    }
}
