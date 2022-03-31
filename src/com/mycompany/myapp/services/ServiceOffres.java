/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Offres;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class ServiceOffres {
     public static ServiceOffres instance ; 
    public boolean resultok=true; 
    public ConnectionRequest req; 
    public  ServiceOffres(){
        req = new ConnectionRequest(); 
    }
    public ArrayList<Offres> Offres;  
    public static ServiceOffres getInstance(){
        if(instance== null)
           instance = new ServiceOffres(); 
        return instance ; 
    } 
       /*****************ajout ********/
    public boolean addoff(Offres E ){
    
        String url= Statics.BASE_URL+"/addjOffres/new?nom="+E.getNom()+"&NomGuide=" +E.getNomGuide();
        ConnectionRequest req = new ConnectionRequest(url); 
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultok=req.getResponseCode()==200; 
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
         return resultok; 
    }
    
    
    
      public ArrayList<Offres>affichageoff(){
        
          ArrayList<Offres> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/displayOffres";
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp; 
                jsonp= new JSONParser();
              try{
                  Map<String,Object>mapEvenement=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapEvenement.get("root");
                 for(Map<String,Object>obj:listOfMaps){
                 Offres re = new Offres();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String nom=obj.get("nom").toString();
                 
                 String Nomguide=obj.get("NomGuide").toString();
                 re.setId((int)id);
                  re.setNom(nom);
re.setNomGuide(Nomguide);
                  result.add(re);
                 }} catch(Exception ex ){
                      ex.printStackTrace();

              }
                  
              }
          });
             NetworkManager.getInstance().addToQueueAndWait(req);
             return result; 
      }
            public boolean deleteoff(int id)
     {
          String url=Statics.BASE_URL+"/DeletejOffres/"+id; 
          req.setUrl(url);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  req.removeResponseCodeListener(this);
              }
          });
           NetworkManager.getInstance().addToQueueAndWait(req);
           return resultok;
     }
               public boolean updateoff(Offres e ){
               
          String url=Statics.BASE_URL+"/updatejOffres/"+e.getId()+"?nom="+e.getNom()+"&NomGuide="+e.getNomGuide();
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  resultok=req.getResposeCode()==200;
                  req.removeResponseListener(this);
                          
              }
          } );
            
     NetworkManager.getInstance().addToQueueAndWait(req);
     return resultok;
     }
}
