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
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Hp
 */
public class ServiceEvenement {
   
   public static ServiceEvenement instance ; 
    public boolean resultok=true; 
    public ConnectionRequest req; 
    public  ServiceEvenement(){
        req = new ConnectionRequest(); 
    }
    public ArrayList<Evenement> evenements;  
    public static ServiceEvenement getInstance(){
        if(instance== null)
           instance = new ServiceEvenement(); 
        return instance ; 
    } 
    /*****************ajout ***************************************/
    public boolean addeve(Evenement E ){
    
        String url= Statics.BASE_URL+"/addjEvenement/new?Type="+E.getType()+"&NomEvenement=" +E.getNomEvenement(); 
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
     /*******************************************************************affichage  ************************************************************/
     public ArrayList<Evenement>affichageeve(){
        
          ArrayList<Evenement> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/displayEvenement";
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
                 Evenement re = new Evenement();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String Type=obj.get("Type").toString();
                 String NomEvenement=obj.get("NomEvenement").toString();
                 re.setId((int)id);
                  re.setNomEvenement(NomEvenement);   
                  re.setType(Type);
                  result.add(re);
                 }} catch(Exception ex ){
                      ex.printStackTrace();

              }
                  
              }
          });
             NetworkManager.getInstance().addToQueueAndWait(req);
             return result; 
    /* ArrayList<Evenement> result= new ArrayList<>();
     String url=Statics.BASE_URL+"/displayEvenement";
     req.setUrl(url);
     req.addResponseListener((NetworkEvent evt) -> {
         JSONParser jsonp ;
         jsonp= new JSONParser();
         try{
             Map<String,Object>mapEvenement=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
             java.util.List<Map<String,Object>> listOfMaps=(java.util.List<Map<String,Object>>) mapEvenement.get("root");
             for(Map<String,Object>obj:listOfMaps){
                 Evenement re = new Evenement();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String Type=obj.get("Type").toString();
                 String NomEvenement=obj.get("NomEvenement").toString();
                 System.out.println(NomEvenement);
                   System.out.println(Type);
                    
                 
//                          String Lieux=obj.get("Lieux").toString();
re.setId((int)id);
// re.setLieux(Lieux);
re.setNomEvenement(NomEvenement);
// re.setLieux(Lieux);



////////////
result.add(re);
*


//date
//   String DateConverter=obj.get("DateDebut").toString().substring(obj.get("DateDebut").toString().indexOf( "timestamp"))
             } }catch(Exception ex){
                 ex.printStackTrace();
             }
     });
     NetworkManager.getInstance().addToQueue(req);
     return result;  
     */
        
             }
      /*****************detail  ********/
     public Evenement detailE(int id  , Evenement evenement ){
         
         
           String url=Statics.BASE_URL+"/displayEvenement?"+id; 
            req.setUrl(url);
            String str = new String(req.getResponseData());
            req.addResponseListener(((evt->{
                JSONParser jsonp = new JSONParser(); 
                try {
                      Map<String,Object>obj=jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                      evenement.setNomEvenement(obj.get("NomEvenement").toString());
                       evenement.setType(obj.get("Type").toString());
                }
                        catch(Exception ex){
                            System.out.println("erreur relié a sql "+ex.getMessage());
             }
                System.out.println("data ===" +str);
                
                
            }
                    
                    
                    
                    )));
         NetworkManager.getInstance().addToQueueAndWait(req);
         return evenement; 
         
     }
     public boolean delete(int id)
     {
          String url=Statics.BASE_URL+"/DeletejEvenement/"+id; 
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
    
     public boolean updateeve(Evenement e ){
               
          String url=Statics.BASE_URL+"/updatejEvenement/"+e.getId()+"?NomEvenement="+e.getNomEvenement()+"&Type="+e.getType();
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

  
      public ArrayList<Evenement>affichagetrieve(){
        
          ArrayList<Evenement> result= new ArrayList<>();
         String url=Statics.BASE_URL+"/filtreEvej";
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
                 Evenement re = new Evenement();
                 float id=Float.parseFloat(obj.get("id").toString());
                 String Type=obj.get("Type").toString();
                 String NomEvenement=obj.get("NomEvenement").toString();
                 re.setId((int)id);
                  re.setNomEvenement(NomEvenement);   
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
