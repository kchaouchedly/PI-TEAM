/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Hotel;

/**
 *
 * @author 123
 */
public class ControlHotel {

    public static boolean ControleNomHotel(String ss) {
        String str = ss.toLowerCase();
      
        char[] charArray = str.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    public boolean ControleTel(int num) {

        // regex expression to verify if the cin inserted contains only 8 numbers
        if ((num + "").matches("[0-9]{8}")) {
            return true;
        }
        return false;
    }

    public static boolean ControleAdresse(String s) {
        String str = s.toLowerCase();       
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!((ch >= 'a' && ch <= 'z') || (String.valueOf(ch)).equals(" "))) {
                return false;
            }
        }
        return true;
    }

    public boolean ControleEmail(String s) {
        // regex expression to verify if the mail inserted follow how emails should be
        // written
        
        if (s.matches(
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            return true;
        }
        return false;
    }

  public boolean controlNbrEtoiles(int nb)
  {
      if((nb==2  ) ||( nb==3 )||(nb==4 )|| (nb==5))
          return true;
      return false;
  }
}
