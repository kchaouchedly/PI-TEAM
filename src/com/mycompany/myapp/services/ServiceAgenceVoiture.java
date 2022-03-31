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
import com.mycompany.myapp.entities.AgenceVoiture;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author LENOVO
 */
public class ServiceAgenceVoiture {
    
    
 public static ServiceAgenceVoiture instance ; 
    public boolean resultok=true; 
    public ConnectionRequest req; 
    public  ServiceAgenceVoiture(){
        req = new ConnectionRequest(); 
    }
    public ArrayList<AgenceVoiture> voitures;  
    public static ServiceAgenceVoiture getInstance(){
        if(instance== null)
           instance = new ServiceAgenceVoiture(); 
        return instance ; 
    }  
    
      /*****************ajout ********/
    public boolean addagence(AgenceVoiture E ){
    
        String url= Statics.BASE_URL+"/addjagence/new?nomagence="+E.getNomagence()+"&adresse=" +E.getAdresse(); 
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
     public ArrayList<AgenceVoiture>affichageagence(){
        
          ArrayList<AgenceVoiture> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/allagence";
            req.setUrl(url);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp; 
                jsonp= new JSONParser();
              try{
                  Map<String,Object>mapagence=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapagence.get("root");
                 for(Map<String,Object>obj:listOfMaps){
                AgenceVoiture re = new AgenceVoiture();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String nomagence=obj.get("nomAgence").toString();
                 String adresse=obj.get("adresse").toString();
                 re.setId((int)id);
                  re.setNomagence(nomagence);   
                  re.setAdresse(adresse);
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
          String url=Statics.BASE_URL+"/Deletejagence/"+id; 
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
    
     public boolean updateagence(AgenceVoiture e ){
               
          String url=Statics.BASE_URL+"/updatejagence/"+e.getId()+"?nomagence="+e.getNomagence()+"&adresse="+e.getAdresse();
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

