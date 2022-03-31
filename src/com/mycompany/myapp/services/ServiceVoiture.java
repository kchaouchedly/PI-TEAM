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
import com.mycompany.myapp.entities.voiture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author LENOVO
 */
public class ServiceVoiture {
   public static ServiceVoiture instance ; 
    public boolean resultok=true; 
    public ConnectionRequest req; 
    public  ServiceVoiture(){
        req = new ConnectionRequest(); 
    }
    public ArrayList<voiture> voitures;  
    public static ServiceVoiture getInstance(){
        if(instance== null)
           instance = new ServiceVoiture(); 
        return instance ; 
    }  
    
      /*****************ajout ********/
    public boolean addvoiture(voiture E ){
    
        String url= Statics.BASE_URL+"/addjvoiture/new?type="+E.getType()+"&marque=" +E.getMarque(); 
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
    
     /*****************affichage  ********/
     public ArrayList<voiture>affichagevoiture(){
        
          ArrayList<voiture> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/allvoiture";
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp; 
                jsonp= new JSONParser();
              try{
                  Map<String,Object>mapvoiture=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapvoiture.get("root");
                 for(Map<String,Object>obj:listOfMaps){
                 voiture re = new voiture();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String Type=obj.get("type").toString();
                 String marque=obj.get("marque").toString();
                 re.setId((int)id);
                  re.setMarque(marque);   
                  re.setType(Type);
                  result.add(re);
                 }} catch(Exception ex ){
                      ex.printStackTrace();

              }
                  
              }
          });
             NetworkManager.getInstance().addToQueueAndWait(req);
             return result; 

}
     
      public boolean delete(int id)
     {
          String url=Statics.BASE_URL+"/Deletejvoiture/"+id; 
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
    
     public boolean updateeve(voiture e ){
               
          String url=Statics.BASE_URL+"/updatejvoiture/"+e.getId()+"?type="+e.getType()+"&marque="+e.getMarque();
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
      public ArrayList<voiture>triparmarque(){
        
          ArrayList<voiture> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/filtrevoiture";
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp; 
                jsonp= new JSONParser();
              try{
                  Map<String,Object>mapvoiture=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapvoiture.get("root");
                 for(Map<String,Object>obj:listOfMaps){
                 voiture re = new voiture();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String Type=obj.get("type").toString();
                 String marque=obj.get("marque").toString();
                 re.setId((int)id);
                  re.setMarque(marque);   
                  re.setType(Type);
                  result.add(re);
                 }} catch(Exception ex ){
                      ex.printStackTrace();

              }
                  
              }
          });
             NetworkManager.getInstance().addToQueueAndWait(req);
             return result; 

}
}
