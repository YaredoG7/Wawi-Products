/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.IOException;
import java.io.Reader;
import com.ibatis.common.resources.Resources;
import java.sql.SQLException;

/**
 *
 * @author yared.yaregal
 */
public class InsertProducts {
    
    // Inserts daily sold products 
    
     public void insert (String barcode_id, String item_name , int item_qty , String cust_name , 
                         String paid , String city , String _month , String _date, int unit_price, 
                         int total_price, String sent_killo, int rec_killo) throws IOException, SQLException {

         SqlMapClient sqlMapper;
         try (Reader rd = Resources.getResourceAsReader("config/dailysellMap.xml")) {
             sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
         }
    
        System.out.println("Going to insert a record from daily sell...");
        
        ReadProduct product = new ReadProduct (barcode_id, item_name, item_qty, cust_name, paid, city, _month, _date, unit_price, total_price, sent_killo, rec_killo);
        
        sqlMapper.insert("ReadProduct.insertSoldproduct", product);
        
        System.out.println("daily sell record has been inserted");
     }
     
     // Inserts  registered products 
     
     public void register(String barcode_id, String item_name, String item_vol,   
                           int item_qty, boolean in_store, int left_in_stock, int unit_price, String _date,
                           String registered_by, String prod_date,String exp_date, String _comment) throws IOException, SQLException{
         
         SqlMapClient sqlMapper;
         try (Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml")) {
             sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
         }
    
        System.out.println("Going to insert a record from register...");
        
        RegisterProduct product = new RegisterProduct (barcode_id, item_name, item_vol, item_qty, in_store, left_in_stock, unit_price, _date, registered_by, prod_date,  exp_date,  _comment);
        
        sqlMapper.insert("RegisterProduct.RegisterProduct", product);
        
        System.out.println("register record has been inserted");
     }
     
     // Inserts calculated values to in stock products 
     
     public void stockReg(String barcode_id, String item_name, int item_qty, String _comment) throws IOException, SQLException{
         
          
         SqlMapClient sqlMapper;
         try (Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml")) {
             sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
         }
        
        
        
        System.out.println("Going to insert a record from register to stock...");
        
        RegisterProduct stockItem = new RegisterProduct (barcode_id, item_name, item_qty, _comment);
        
        sqlMapper.insert("RegisterProduct.StockProduct", stockItem);
        
        System.out.println("products in stock has also been updated");
     
     }
     
     // Inserts value to dialy sell from quick sell menu
     
        public void getQuickSell(String barcode_id,  String item_name, int item_qty, String cust_name, String _date,  int unit_price, int total_price) throws IOException, SQLException{
    
            SqlMapClient sqlMapper;
         try (Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml")) {
             sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
             // date
             // date
         }
    
         System.out.println("Going to insert a record from quick sell...");
        
        ReadProduct stockItem = new ReadProduct (barcode_id, item_name, item_qty, cust_name, _date,  unit_price,  total_price);
        
        sqlMapper.insert("ReadProduct.insertQuickSell", stockItem);
        
        System.out.println("quick sell record has been inserted");
    
    
    }
 
}
