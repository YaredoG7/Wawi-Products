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

/**
 *
 * @author yared.yaregal
 */
public class GetStockProducts {
    
    
     
    // daily sell quantity will come from the text filed -- TODO --  what if the entred value is by mistake? 

    // method gets the values that are in stock right now 
    
   
    
     public int itemsInStock (String setValue, int val_from_sell) throws SQLException, IOException{
        int items_in_stock = 0;
       
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
         SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
       
         System.out.println("Going to read... ");
         RegisterProduct sd = new RegisterProduct();
         sd.setBarcode_id(setValue);
         
         List <RegisterProduct> prods = (List <RegisterProduct>)
         sqlMapper.queryForList("RegisterProduct.selectQty", sd);
         
         for (RegisterProduct q: prods){
        int qty_registerd_item = q.getItem_qty();
         items_in_stock = qty_registerd_item - val_from_sell;
        
       }
       System.out.println(items_in_stock);
     return items_in_stock; 
    }    
    

    // updates stock value when product is registered
    
  
  public int addItemsToStock(String barcode_id, int registered) throws SQLException, IOException{
    
        int items_in_stock = 0;
       
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
         SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
       
         System.out.println("Going to read... ");
         RegisterProduct sd = new RegisterProduct();
         sd.setBarcode_id(barcode_id);
         
         List <RegisterProduct> prods = (List <RegisterProduct>)
         sqlMapper.queryForList("RegisterProduct.selectQty", sd);
         
         for (RegisterProduct q: prods){
        int qty_registerd_item = q.getItem_qty();
         items_in_stock = qty_registerd_item + registered;
        
       }
       System.out.println(items_in_stock);
     return items_in_stock; 
    
    }
     

}
