/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yared.yaregal
 */


public class SearchProducts {
    
    
    ErrorLogger err = new ErrorLogger();

       
    public int getDailySellQty(String setValue) throws IOException, SQLException{
    
       int qty_daily_sell = 0;
        
       Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
       SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
       
         System.out.println("Going to read... ");
         ReadProduct sd = new ReadProduct();
         sd.setBarcode_id(setValue);
       
       List <ReadProduct> prods = (List <ReadProduct>)
       sqlMapper.queryForList("Soldproduct.selectQty", sd);
       
       //Soldproduct qs = null;
       for (ReadProduct q: prods){
        qty_daily_sell = q.getQty();
        System.out.println(qty_daily_sell);
       }
     return qty_daily_sell;
    }
    
    public int getRegsitredQty (String setValue) throws SQLException, IOException{
    
        int qty_registerd_item = 0;
        
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
         SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
       
         System.out.println("Going to read... ");
         RegisterProduct sd = new RegisterProduct();
         sd.setBarcode_id(setValue);
         
         List <RegisterProduct> prods = (List <RegisterProduct>)
         sqlMapper.queryForList("RegisterProduct.selectQty", sd);
         
         for (RegisterProduct q: prods){
        qty_registerd_item = q.getItem_qty();
        System.out.println(qty_registerd_item);
       }
        
         return qty_registerd_item;
    }

public boolean isBarcode(String bacode_id)  {

        String barcode_frm_db = "";
        boolean isMatch = false;
        Reader rd = null;
        try {
            rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
        } catch (IOException ex) {
            Logger.getLogger(SearchProducts.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile("error occured in search product class is barcode method "+ex.toString());
        }
         SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
       
         System.out.println("Going to read... ");
         RegisterProduct sd = new RegisterProduct();
         sd.setBarcode_id(bacode_id);
         
         List <RegisterProduct> prods;
        try {
            prods = (List <RegisterProduct>)
                    sqlMapper.queryForList("RegisterProduct.selectBarcode", sd);
            
                     for (RegisterProduct q: prods){
                    barcode_frm_db = q.getBarcode_id();

                    }

              if (barcode_frm_db.matches(bacode_id)){

                  isMatch = true;
 }
        } catch (SQLException ex) {
            Logger.getLogger(SearchProducts.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile("error occured in search product class is barcode method "+ex.toString());
        }
         
 
 return isMatch;  
}



}
