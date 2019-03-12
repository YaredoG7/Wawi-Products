package com.hahucomputers.wawiproducts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GenerateReport {

 private BaseFont bfBold;
 private BaseFont bf;
 private int pageNumber = 2;
 private String from_date;
 private String to_date;
 
private static final String USER_NAME = "root";
private static final String USER_PASS =  "hahucomputers123";
 

 // create a method to be able to display suggesion 
   
    HandleDB hdb = new HandleDB();

  // method to create connection and queries 
    public ResultSet connect(String query) throws SQLException {
    hdb.setHostURL("jdbc:mysql://localhost:3306/wawiproducts?allowMultiQueries=true&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", USER_NAME, USER_PASS); 
    ResultSet rs = hdb.SetQuery (query);
    return rs;
   }
  
 // create details of the report 
 
        private void generateDetail(Document doc, PdfContentByte cb) throws SQLException  {
        DecimalFormat df = new DecimalFormat("0");

  
           String searchProd = "select barcode_id, item_name, item_qty, unit_price from dailysell where _date between "+"'"+from_date+"'"+" and "+"'"+to_date+"'"+"";
           
//           System.out.println(from_date);
//           System.out.println(to_date);
//           
//          System.out.println("I am doing my thing... ");
           
           ResultSet res = connect(searchProd);
           String valueGotten = "Nothing matchs..";
           String valueGotten2 = "Nothing matchs..";
           String valueGotten3 = "Nothing matchs..";
           String valueGotten4 = "Nothing matchs..";
        
              int y = 615;
              int x = 1;
             boolean beginPage = true;
             while (res.next()){
               
 
                valueGotten = res.getString("barcode_id"); 
                valueGotten2 = res.getString("item_name");
                valueGotten3 = res.getString("item_qty");
                valueGotten4 = res.getString("unit_price");
               
               // names.add(valueGotten2);
               System.out.println("I am reading " + valueGotten2);
                  if(beginPage){
                    beginPage = false;
                    generateLayout(doc, cb); 
                    generateHeader(doc, cb);
                    y = 615; 
                   }
                    y = y - 15;
                    if(y < 50){
                     printPageNumber(cb);
                     doc.newPage();
                     beginPage = true;
    }
                 try {

                    createContent(cb, 48, y, String.valueOf(x++), PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 100, y, valueGotten, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 200, y, valueGotten2, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 455, y, valueGotten3, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 530, y, valueGotten4, PdfContentByte.ALIGN_RIGHT);
                    
                   
                  
                    }  catch (Exception ex){
                     ex.printStackTrace();
                    }
                 
               
           }
             
              printPageNumber(cb);
            
 }
        
        
  // create details of the report 
 
        private void generateDetailForEmp(Document doc, PdfContentByte cb) throws SQLException  {
        DecimalFormat df = new DecimalFormat("0");

        
        // trying to get the dates from the product table 
         String searchProd = "select emp_id, emp_name, emp_salary, emp_net, paid_date from paidemployees where paid_date between "+"'"+from_date+"'"+" and "+"'"+to_date+"'"+"";
           
//           System.out.println(from_date);
//           System.out.println(to_date);
           
        //  System.out.println("I am doing my thing... ");
           
           ResultSet res = connect(searchProd);
           String valueGotten = "Nothing matchs..";
           String valueGotten2 = "Nothing matchs..";
           String valueGotten3 = "Nothing matchs..";
           String valueGotten4 = "Nothing matchs..";
        
              int y = 615;
              int x = 1;
             boolean beginPage = true;
             if(beginPage){
            beginPage = false;
            generateLayout(doc, cb); 
            generateHeader(doc, cb);
            y = 615; 
           }
            y = y - 15;
            if(y < 50){
             printPageNumber(cb);
             doc.newPage();
             beginPage = true;
                                }
             while (res.next()){
               
 
                valueGotten = res.getString("emp_id"); 
                valueGotten2 = res.getString("emp_name");
                valueGotten3 = res.getString("emp_salary");
                valueGotten4 = res.getString("emp_net");
                String valueGotten5 = res.getString("paid_date");
               
               // names.add(valueGotten2);
            //   System.out.println("I am reading " + valueGotten2);

                 try {

                    createContent(cb, 48, y, String.valueOf(x++), PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 100, y, valueGotten5, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 230, y, "EMP ID - "+valueGotten, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 350, y, " EMP NAME -" + valueGotten2, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 455, y, valueGotten3, PdfContentByte.ALIGN_RIGHT);
                    createContent(cb, 530, y, valueGotten4, PdfContentByte.ALIGN_RIGHT);
                    
                   
                  
                    }  catch (Exception ex){
                     ex.printStackTrace();
                  
                    }
                 
               
           }
              printPageNumber(cb);
             
            
            
 }
 
 boolean getOption;
 
 void createPDF (String dir, String fromDate, String toDate, boolean isSelected, Component cmp){

     
  this.from_date = fromDate;
  this.to_date = toDate;
  this.getOption = isSelected;
  Document doc = new Document();
  PdfWriter docWriter = null;
  initializeFonts();

  try {
    String fileName1 = "\\hahu_salary_report.pdf";
    String fileName2 = "\\hahu_sell_report.pdf";
    String path = null;
    if (getOption){
     path = dir + fileName2;
    } else {
    
     path = dir + fileName1;
    }
    
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
   doc.addAuthor("hahucomputers");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("www.hahucomputers.com");
   doc.addTitle("Invoice");
   doc.setPageSize(PageSize.LETTER);

   doc.open();
   PdfContentByte cb = docWriter.getDirectContent();
   
   boolean beginPage = true;
   int y = 0;
   
   // condition the desc generation with boolean 
   if (getOption){
   generateDetail(doc, cb);
   } else {
    generateDetailForEmp(doc, cb);
   }
   for(int i=0; i < 30; i++ ){
    if(beginPage){
     beginPage = false;
     generateLayout(doc, cb); 
     generateHeader(doc, cb);
     y = 615; 
    }
    
    y = y - 15;
    if(y < 50){
     printPageNumber(cb);
     doc.newPage();
     beginPage = true;
    }
   }
   printPageNumber(cb);

    JOptionPane.showMessageDialog (cmp, "success! report generated", "success", JOptionPane.INFORMATION_MESSAGE);
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
   JOptionPane.showMessageDialog (cmp, "error while generating pdf \n" + "check if the file is already open", "error", JOptionPane.ERROR_MESSAGE);
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
   JOptionPane.showMessageDialog (cmp, "error while generating pdf \n" +"check if the file is already open", "error", JOptionPane.ERROR_MESSAGE);
  }
  finally
  {
   if (doc != null)
   {
    doc.close();
   }
   if (docWriter != null)
   {
    docWriter.close();
   }
  }
 }

 private void generateLayout(Document doc, PdfContentByte cb)  {

  try {

   cb.setLineWidth(1f);

   // Invoice Header box layout
   cb.rectangle(420,700,150,60);
   cb.moveTo(420,720);
   cb.lineTo(570,720);
   cb.moveTo(420,740);
   cb.lineTo(570,740);
   cb.moveTo(480,700);
   cb.lineTo(480,760);
   cb.stroke();

   // Invoice Header box Text Headings 
   createHeadings(cb,422,743,"Account No.");
   createHeadings(cb,422,723,"Report No.");
   createHeadings(cb,422,703,"Report Date");

   // Invoice Detail box layout 
   cb.rectangle(20,50,550,600);
   cb.moveTo(20,630);
   cb.lineTo(570,630);
   cb.moveTo(50,50);
   cb.lineTo(50,650);
   cb.moveTo(150,50);
   cb.lineTo(150,650);
   cb.moveTo(430,50);
   cb.lineTo(430,650);
   cb.moveTo(500,50);
   cb.lineTo(500,650);
   cb.stroke();

   if (getOption){
   // Invoice Detail box Text Headings 
   createHeadings(cb,22,633,"No.");
   createHeadings(cb,52,633,"ITEM NUMBER");
   createHeadings(cb,152,633,"ITEM DESCRIPTION");
   createHeadings(cb,432,633,"QUANTITY");
   createHeadings(cb,502,633,"UNIT PRICE");
   } else {
       
   // Heading for salary report 
   
   createHeadings(cb,22,633,"No.");
   createHeadings(cb,52,633,"PAID DATE");
   createHeadings(cb,152,633,"EMPLOYEE ID AND NAME");
   createHeadings(cb,432,633,"BASE SALARY");
   createHeadings(cb,502,633,"NET SALARY");
   }


  }

  catch (Exception ex){
   ex.printStackTrace();
  }

 }
 
 private void generateHeader(Document doc, PdfContentByte cb)  {

  try {

   createHeadings(cb,50,750,"Company Name:   Emebet & Her Children Milk Process P.L.C");
   createHeadings(cb,50,735,"Address 1:      Kebele 16  ");
   createHeadings(cb,50,720,"Address 2:      _____________");
   createHeadings(cb,50,705,"City:           Bahir Dar");
   createHeadings(cb,50,690,"Country:        Ethiopia");
 
   
   createHeadings(cb,482,743,"");
   createHeadings(cb,482,723,"");
   createHeadings(cb,482,703,"");

  }

  catch (Exception ex){
   ex.printStackTrace();
  }

 }
 
 private void createHeadings(PdfContentByte cb, float x, float y, String text){


  cb.beginText();
  cb.setFontAndSize(bfBold, 8);
  cb.setTextMatrix(x,y);
  cb.showText(text.trim());
  cb.endText(); 

 }
 
 private void printPageNumber(PdfContentByte cb){


  cb.beginText();
  cb.setFontAndSize(bfBold, 8);
  cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
  cb.endText(); 
  
  pageNumber++;
  
 }
 
 private void createContent(PdfContentByte cb, float x, float y, String text, int align){


  cb.beginText();
  cb.setFontAndSize(bf, 8);
  cb.showTextAligned(align, text.trim(), x , y, 0);
  cb.endText(); 

 }

 private void initializeFonts(){


  try {
   bfBold = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
   bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

  } catch (DocumentException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }


 }
 
}