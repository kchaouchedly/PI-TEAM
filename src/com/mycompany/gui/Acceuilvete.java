/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Hp
 */
public class Acceuilvete  extends BaseForm {
      public Acceuilvete(Resources res) {
       super(new BorderLayout());
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("Evenements");
         setUIID("Offre");
          setUIID("Agence Voiture");
         setUIID("Voiture");
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
       // TextField username = new TextField("", "Username", 20, TextField.ANY);
        //TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        //username.setSingleLineTextArea(false);
        //password.setSingleLineTextArea(false);
        Button Evenements = new Button("Evenements");
        Button Offre = new Button("Offre");
         Button Voiture = new Button("Voiture");
        Button AgenceVoiture = new Button("Agence Voiture");
       Offre.addActionListener(e -> new Ajoutoffreform(res).show());
        AgenceVoiture.addActionListener(e -> new AjoutagenceForm(res).show());
          Voiture .addActionListener(e -> new AjoutvoitureForm(res).show());
       
        //signUp.setUIID("");
      //  Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
              //  new FloatingHint(username),
                createLineSeparator(),
                //new FloatingHint(password),
                createLineSeparator(),
                Evenements, Offre,Voiture,AgenceVoiture
                //FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
       Evenements.requestFocus();
       Evenements.addActionListener(e -> new Ajouteventform(res).show());
        Offre.requestFocus();
             AgenceVoiture.requestFocus();
               Voiture.requestFocus();
         
    }
    
}
