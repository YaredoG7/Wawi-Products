/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tom
 */


public class TableHandler extends AbstractTableModel{
    
    
    private static final String USER_NAME = "root";
    private static final String USER_PASS =  "hahucomputers123";

    
    //ErrorLogger errorlog = new ErrorLogger();
     
    Vector cache;   // using it to store string [] objects
    int colCount;
    String [] headers;
    Connection db;
    Statement statement;
    String currentURL;
//    String username;
//    String password;
//    
    public TableHandler(){
    
        cache = new Vector();
       // ?? new gsl.sql.driv.Driver();
    }
    
    public String getColumnName(int i){
    return headers[i];
    }
    
    public int getColumnCount(){
    return colCount;
    }

    public int getRowCount(){
    return cache.size();
    }
    
    public Object getValueAt(int row, int col) {
      return ((String []) cache.elementAt(row))[col];
    }
    
    public void setHostURL(String url, String username, String password){
    url  = "jdbc:mysql://localhost:3306/wawiproducts?allowMultiQueries=true&autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    username = USER_NAME; 
    password = USER_PASS;
    if (url.equals(currentURL)){  // if its the same database we can leave the connection open 
    return;
    }
    // new connection required? 
    closeDB();
    initDB(url, username, password);
    currentURL = url;
    
    
    }

// All the real work happens here; in a real application,
  // we'd probably perform the query in a separate thread.
    
 public void SetQuery(String q){
     cache = new Vector();
     try {
     
         // execute the query and save the result set and its metadata 
         ResultSet rs = statement.executeQuery(q);
         ResultSetMetaData meta = rs.getMetaData();
         colCount = meta.getColumnCount();
         
         // rebulid the headers array with new col names
         headers = new String[colCount];
         for (int h = 1; h <= colCount; h++){
           headers[h-1] = meta.getColumnName(h);
         }
         
         // fill the cache with retrived data 
         while(rs.next()){
         String [] record = new String [colCount];
         for (int i = 0; i < colCount; i++){
         record[i]= rs.getString(i +1);
         }
         cache.addElement(record);
         }
         fireTableChanged(null); // notify everyone that we have a new table.
     }   catch (Exception e) {
      cache = new Vector(); // blank it out and keep going.
      e.printStackTrace();
     //errorlog.writeFile("Set Query error "+e.toString());
    }
 }
 
   public void initDB(String url, String username, String password) {
    try {
      db = DriverManager.getConnection(url, username, password);
      statement = db.createStatement();
      System.out.println("Connected..");
    } catch (Exception e) {
      System.out.println("Could not initialize the database.");
      e.printStackTrace();
       // errorlog.writeFile("Init DB error "+e.toString());
    }
  }
   
   
  public void closeDB() {
    try {
      if (statement != null) {
        statement.close();
      }
      if (db != null) {
        db.close();
      }
    } catch (Exception e) {
      System.out.println("Could not close the current connection.");
      e.printStackTrace();
      //errorlog.writeFile("Close DB error "+e.toString());
    }
  }
    
}
