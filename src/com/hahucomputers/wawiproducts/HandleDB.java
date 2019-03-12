/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom
 */
public class HandleDB {
    
    private static final String USER_NAME = "root";
    private static final String USER_PASS =  "hahucomputers123";

    
    
    ErrorLogger errorlog = new ErrorLogger();
    
    
   Vector cache;
   Connection db;
   Statement statement;
   String currentURL;
   
   //hdb.setHostURL("jdbc:mysql://localhost:3306/aurixtool?autoReconnect=true&useSSL=false", "root", "yaredyareGal24"); 
   public void setHostURL(String url, String username, String password){
       url = "jdbc:mysql://localhost:3306/wawiproducts?allowMultiQueries=true&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       username = USER_NAME;
       password= USER_PASS;
       if (url.equals(currentURL)){
       return;
       }
       // new connection required 
              closeDB();
       initDB(url, username, password);
       currentURL = url;

   }
   
   public ResultSet SetQuery(String q) throws SQLException{ // if I will bring the table here I will not need the colName as it will return a table 
   cache = new Vector();
   ResultSet rs = null;
  
        rs = statement.executeQuery(q);
   
   return rs;
   }
 
   public void initDB(String url, String username, String password){
       try {
       db = DriverManager.getConnection(url, username, password);
       statement = db.createStatement();
       } catch (Exception e) {
           System.out.println("Could not initialize database.");
           e.printStackTrace();
           errorlog.writeFile("Init DB error "+e.toString());
       }
   }
   
   public void closeDB(){
   try{
   if (statement != null){
   statement.close();
   }
   if (db != null){
       db.close();
   }
   
   } catch (Exception e){
   
    System.out.println("Could not close the current connection.");
     e.printStackTrace();
     errorlog.writeFile("Close DB error "+e.toString());
   }
   }
    
}
