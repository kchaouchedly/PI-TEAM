/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;


/**
 *
 * @author Hp
 */
public class ModifierEvenementForm extends BaseForm{
    Form current ; 
    public ModifierEvenementForm(Resources res , Evenement e )
    {
         super("Newsfeed", BoxLayout.y());
       // ToolBar tb = new ToolBar(true); 
        Toolbar tb = new Toolbar(true); 
        current = this ; 
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Modifier ");
        getContentPane().setScrollVisible(false);
        super.addSideMenu(res);
        TextField nom= new TextField(e.getNomEvenement(),"NomEvenement",20,TextField.ANY);
        TextField type= new TextField(e.getType(),"Type",20,TextField.ANY);
        nom.setUIID("NewsTopLine");
        type.setUIID("NewsTopLine");
        nom.setSingleLineTextArea(true);
         type.setSingleLineTextArea(true);
         Button btnupdatee = new Button("Modifier "); 
         btnupdatee.setUIID("Button");
         btnupdatee.addPointerPressedListener(l->{
             e.setNomEvenement(nom.getText());
             e.setType(type.getText());
     
         if(ServiceEvenement.getInstance().updateeve(e)){
             new ListeventForm(res).show();
             
         }
             });
         Button btnAnnuler= new Button("Annuler");
         btnAnnuler.addActionListener(l->{
             new ListeventForm(res).show();
         });
         Label m8= new Label(""); 
         Label m88= new Label(""); 
         Label m888=new Label(""); 
      //   Label m8888=new Label(""); 
         Label m1= new Label(); 
          
          
          Container content = BoxLayout.encloseY(
          m8,m88,
                 new FloatingHint(nom),
                 createLineSeparator(),
                  new FloatingHint(type),
                    createLineSeparator(),
                    btnupdatee,
                    btnAnnuler
                  
          
                  
          
          
          
          
          );
          add(content);
          show();
          
          
          
          
    }
}
