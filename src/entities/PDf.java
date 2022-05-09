/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Annotation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import services.HotelService;
import services.VolService;


/**
 *
 * @author 123
 */
public class PDf {
    
        public void GeneratePdfHotel(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        HotelService su = new HotelService() ;        
        List<Hotel> list=su.afficherHotel();
        document.add(new Paragraph(" La liste des Hotels :"));
        document.add(new Paragraph("     "));
         for(Hotel h:list)
        {
            
        document.add(new Paragraph("Nom Hotel :"+h.getNomHotel()));
        document.add(new Paragraph("Nombre étoiles :"+h.getNbrEtoiles()));
        document.add(new Paragraph("Nombre des chamres :"+h.getNbrChambre()));
        document.add(new Paragraph("Code Hotel :"+h.getCodeH()));
        document.add(new Paragraph("Numéro Téléphone :"+h.getNumTel()));
        document.add(new Paragraph("Adresse :"+h.getAdresse()));
        document.add(new Paragraph("Email :"+h.getEmail()));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
        
        
   
        
     public void GeneratePdfVol(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
        VolService vs = new VolService();        
        List<Vol> list=vs.afficherVol();
        document.add(new Paragraph(" La liste des vols :"));
        document.add(new Paragraph("     "));
         for(Vol v:list)
        {
            
        document.add(new Paragraph("Numéro de vol :"+v.getNumVol()));
        document.add(new Paragraph("Nombre des places  :"+v.getNbrPlace()));
        document.add(new Paragraph("ville départ  :"+v.getVilleDepart()));
        document.add(new Paragraph("ville arrivée :"+v.getVilleArrive()));
        document.add(new Paragraph("date départ  :"+v.getDateDepart()));
        document.add(new Paragraph("date arrivée :"+v.getDateArrive()));
        document.add(new Paragraph("type de vol :"+v.getTypeV()));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
        
        
    
}
