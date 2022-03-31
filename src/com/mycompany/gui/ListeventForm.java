/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
import com.sun.java.accessibility.util.SwingEventMonitor;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.ToolBar;

/**
 *
 * @author Hp
 */
public class ListeventForm extends BaseForm{
    Form  current ; 
    public ListeventForm(Resources res ){
         super("Newsfeed", BoxLayout.y());
       // ToolBar tb = new ToolBar(true); 
        Toolbar tb = new Toolbar(true); 
        current = this ; 
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("ajouter ");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e->{
            
            
            
        });
        Tabs swipe=new Tabs();
        Label s1 = new Label(); 
       
          Label s2 = new Label(); 
  // addTab(swipe,res.getImage("back.png"),"","",res);
      addTab(swipe,s1 ,res.getImage("back-logo.jpg"), "", "", res);
     
        
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

     rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Proposer encore ", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Tri", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Proposer ", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

     liste.addActionListener(l->{
          new TriEveForm(res).show();
      });
        mesListes.addActionListener((e) -> {
              // InfiniteProgress ip = new InfiniteProgress();
       // final Dialog ipDlg = ip.showInifiniteBlocking();
        new Ajouteventform(res).show();
         
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        

        ArrayList<Evenement> result= ServiceEvenement.getInstance().affichageeve();
        for(Evenement rec : result ){
            String urlimage= "back-logo.jpg";
            Image placeHolder=Image.createImage(120,90); 
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false); 
            URLImage urlim = URLImage.createToStorage(enc, urlimage, urlimage,URLImage.RESIZE_SCALE);
            
            
          
            
            addButton(urlim,rec,res);
            ScaleImageLabel image= new ScaleImageLabel(urlim);
            Container containerimg= new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         
        }
       
        
        //
    }
    private void addTab(Tabs swipe,Label spacer ,  Image image, String string, String text, Resources res) {
       int size= Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
       if(image.getHeight()<size){
           image=image.scaledHeight(size);
       }
       if(image.getHeight()>Display.getInstance().getDisplayHeight() / 2){
           image=image.scaledHeight(Display.getInstance().getDisplayHeight() / 2); 
           
       }
        ScaleImageLabel imageScale = new ScaleImageLabel(image); 
        imageScale.setUIID("container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label("","ImageOverlay"); 
        Container page1;
        page1 = LayeredLayout.encloseIn(
                imageScale , 
                            overlay,
                BorderLayout.south(
                BoxLayout.encloseY(
                        new SpanLabel(text,"LargeWhiteText"),                                    
                                        spacer
                                        
                                )
                )
                
        );
        swipe.addTab("", res.getImage("back-logo.jpg"),page1);
    }

   
  

    private void updateArrowPosition(Button btn, Label l) {
       l.getUnselectedStyle().setMargin(LEFT,btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2 );
       l.getParent().repaint();
    }

    private void bindButtonSelection(Button btn , Label l) {
       btn.addActionListener(e->{
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
        });
    }

   private void addButton(Image img, Evenement rec, Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f); 
        int witth = Display.getInstance().convertToPixels(14.5f);
        Button image=  new Button(img.fill(witth, height));
        image.setUIID("label");
        
        
       Container cnt = BorderLayout.west(image);
      // TextArea ta = new TextArea(nomEvenement); 
        // TextArea a = new TextArea(type); 
       
      // ta.setUIID("NewsTopline");
      // ta.setEditable(false);
       setTitle("Avis de client ");
       Label nomtxt= new Label("NomEvenement:"+rec.getNomEvenement(),"NewsTopline2");
       Label typetxt =new Label ("Type:"+rec.getType(),"NewsTopline2");
      // cnt.add(BorderLayout.CENTER,BoxLayout.encloseX(nomtxt),BoxLayout.encloseX(typetxt))); 
      // cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomtxt),BoxLayout.encloseX(typetxt)));
       
       Label supp= new Label("");
       supp.setUIID("NewsTopline");
       Style suppstyle= new Style(supp.getUnselectedStyle());
       suppstyle.setFgColor(0xf21f1f);
       FontImage suppimage= FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppstyle);
       supp.setIcon(suppimage);
       supp.setTextPosition(RIGHT);
       
       supp.addPointerPressedListener(l->{
       Dialog dig= new Dialog("suppression");
       if (dig.show("suppression","vous vouler supprimer ce event?","annuler","oui")){
           dig.dispose();
       }
       else {
           dig.dispose();
           if (ServiceEvenement.getInstance().delete(rec.getId()))
           {
               new ListeventForm(res).show(
               
               );
           }
       }
       });
       
         Label update= new Label("");
       update.setUIID("NewsTopline");
       Style updateeventstyle= new Style(update.getUnselectedStyle());
       updateeventstyle.setFgColor(0xf7ad02);
       FontImage upfondimg = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT,updateeventstyle );
       update.setIcon(upfondimg);
       update.setTextPosition(LEFT);
       update.addPointerPressedListener(l-> {
            //System.out.println("hello mod");
        new ModifierEvenementForm(res, rec).show();
           
           
       });
       
       
        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
     /*   CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));*/
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
     //   addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
        
               
       
       
        //cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(supp)));
         cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomtxt,supp,update),BoxLayout.encloseX(typetxt)));
       
       add(cnt ); 
    }

    private void addStringValue(String s, Component v ) {
        add(BorderLayout.west(new Label(s,"PaddelLabel")).add(BorderLayout.CENTER,v)); 
        add(createLineSeparator(0xeeeeee)); 
    }
}
