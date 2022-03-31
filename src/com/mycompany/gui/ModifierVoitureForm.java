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
import com.mycompany.myapp.entities.voiture;
import com.mycompany.myapp.services.ServiceVoiture;
/**
 *
 * @author LENOVO
 */
public class ModifierVoitureForm extends BaseForm{
   Form current ; 
    public ModifierVoitureForm(Resources res , voiture e )
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
        TextField marque= new TextField(e.getMarque(),"marque",20,TextField.ANY);
        TextField type= new TextField(e.getType(),"Type",20,TextField.ANY);
        marque.setUIID("NewsTopLine");
        type.setUIID("NewsTopLine");
        marque.setSingleLineTextArea(true);
         type.setSingleLineTextArea(true);
         Button btnupdatee = new Button("Modifier "); 
         btnupdatee.setUIID("Button");
         btnupdatee.addPointerPressedListener(l->{
             e.setMarque(marque.getText());
             e.setType(type.getText());
     
         if(ServiceVoiture.getInstance().updateeve(e)){
             new ListvoitureForm(res).show();
             
         }
             });
         Button btnAnnuler= new Button("Annuler");
         btnAnnuler.addActionListener(l->{
             new ListvoitureForm(res).show();
         });
         Label m8= new Label(""); 
         Label m88= new Label(""); 
         Label m888=new Label(""); 
      //   Label m8888=new Label(""); 
         Label m1= new Label(); 
          
          
          Container content = BoxLayout.encloseY(
          m8,m88,
                 new FloatingHint(marque),
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
