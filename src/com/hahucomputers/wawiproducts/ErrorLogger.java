/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author tom
 */
public class ErrorLogger {
   
        
     public static void writeFile (String str) {

         String userHome = System.getProperty("user.home");
         new File (userHome+"\\hahucomp").mkdir();
       // System.out.println(userHome);
        
       File file = new File(userHome +"\\hahucomp\\hahuerrorLog.txt");
        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
        
         if(!file.exists()){
             
             out.println(str + "error happened at  " +  Time.valueOf(LocalTime.now()));
  
           } else{
            bw.newLine();
            out.println(str + "error happened at  " +  Time.valueOf(LocalTime.now()));
           }
         }
             catch(Exception e){
             ErrorLogger.writeFile("error happend at ErrorLogger class  " + e.toString());
          //  System.out.println("File has not been written");
           }


}     
     public static void main(String[] args) {      
      writeFile("New error appended ");
      }
}
