/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.awt.Component;
import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yared.yaregal
 */
public class UpdateProduct {
    
    private String barcode_id ;  
    private String item_name ;
    private int item_vol;
    private int   item_qty ;
    private boolean in_store;
    private int left_in_stock;
    private int    unit_price;
    private String _date ;
    private String registered_by ;
    private String prod_date;
    private String exp_date ;
    private String comment;
    
    public UpdateProduct(){}
    
       // insert class import for updating unregistered items
    
    static InsertProducts register_new = new InsertProducts();
    static ErrorLogger err = new ErrorLogger();
    
    
    // update item quantity up on register 
    
     public void updateItemQty(String barcode_id, int item_qty, Component cmp) throws IOException {
   
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
        SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
        try {
            rd.close();
        } catch (IOException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile(" Erro on update qunatity class throws: " + ex.toString());
        }
        
        try {
            // check if barcode exists in db
            
            // updating records
            if (new SearchProducts().isBarcode(barcode_id)){
                System.out.println("Going to update a record...");
                RegisterProduct rec = new RegisterProduct ();
                rec.setBarcode_id(barcode_id);
                rec.setItem_qty(item_qty);
                
                if (cmp == null){
                System.out.println("output nothing..");
                } else {
                JOptionPane.showMessageDialog (cmp, "Success, stock items updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                sqlMapper.update("RegisterProduct.updateQty", rec);
                System.out.println("record has been updated");
                
            } else
            {
                
                
                int chk = JOptionPane.showConfirmDialog(cmp, "product not found in stock! insert item to stock?", "confirm", JOptionPane.YES_NO_OPTION);
                
                //  JOptionPane.showInputDialog(cmp, "product not found in stock! insert item to stock?");
                //JOptionPane.showMessageDialog (cmp, "product not found in stock! insert item to stock?", "Question", JOptionPane.QUESTION_MESSAGE);
                
                if (chk == 0){
                    Date today = Date.valueOf(LocalDate.now());
                    String unreg_date = today.toString();
                    register_new.stockReg(barcode_id, unreg_date, item_qty, "unregistered");
                    
                    JOptionPane.showMessageDialog (cmp, "Success!", "WARNING", JOptionPane.INFORMATION_MESSAGE);
                    
                } else if (chk == 1) {
                    
                    JOptionPane.showMessageDialog (cmp, "transaction saved. report will NOT show this sell", "info", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch (SQLException ex) {
            err.writeFile(" error on update qunatity class throws: " + ex.toString());
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile(" error on update qunatity class throws: " + ex.toString());
        }
         
    }
     
     
     /*
      // update item name up on register 
    
     public void updateItemName(String barcode_id, String name, Component cmp) throws IOException, SQLException{
    
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
        SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
        rd.close();
        
        // check if barcode exists in db 

        // updating records
       if (new SearchProducts().isBarcode(barcode_id)){ 
            System.out.println("Going to update a record...");
            RegisterProduct rec = new RegisterProduct ();
            rec.setBarcode_id(barcode_id);
            rec.setItem_name(name);
            JOptionPane.showMessageDialog (cmp, "Product has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            sqlMapper.update("RegisterProduct.updateName", rec);
            System.out.println("record has been updated");

        } else {
           
            System.out.println("It seems product has never been registered");
            JOptionPane.showMessageDialog (cmp, "It seems product has never been registered", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
         
    }
     
     
     
      // update item price up on register 
    
     public void updateItemprice(String barcode_id, int unit_price, Component cmp) throws IOException, SQLException{
    
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
        SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
        rd.close();
        
        // check if barcode exists in db 

        // updating records
       if (new SearchProducts().isBarcode(barcode_id)){ 
            System.out.println("Going to update a record...");
            RegisterProduct rec = new RegisterProduct ();
            rec.setBarcode_id(barcode_id);
            rec.setUnit_price(unit_price);
            JOptionPane.showMessageDialog (cmp, "Product has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            sqlMapper.update("RegisterProduct.updatePrice", rec);
            System.out.println("record has been updated");

        } else {
           
            System.out.println("It seems product has never been registered");
            JOptionPane.showMessageDialog (cmp, "It seems product has never been registered", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
         
    }
     
     */
    // update item quantity up on sell  
    
    // this can be also used as update qunatity from an edit function provided as a table in case any mistake has been made 
     
      
     public void updateQty(String barcode_id, String qtyFromJtext) throws IOException {
         
         GetStockProducts itemsInStock = new GetStockProducts();
         this.barcode_id = barcode_id;
         
         // convert  the value from Jtext to int 
         
         int qty_daily_sell = Integer.parseInt(qtyFromJtext);
         
         int _item_qty_inside = 0;
        try {
            _item_qty_inside = itemsInStock.itemsInStock(barcode_id, qty_daily_sell);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile(" error on update qunatity class throws: " + ex.toString());
        }
               
        Reader rd = Resources.getResourceAsReader("config/SqlMapConfig.xml");
        SqlMapClient sqlMapper = SqlMapClientBuilder.buildSqlMapClient(rd);
        rd.close();
      
        // updating records
        
        System.out.println("Going to update a record...");
        RegisterProduct rec = new RegisterProduct ();
        rec.setBarcode_id(barcode_id);
       // rec.setItem_name(item_name);
        rec.setItem_qty(_item_qty_inside);
      //  rec.setComment(comment);
        try {
            sqlMapper.update("RegisterProduct.updateQty", rec);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProduct.class.getName()).log(Level.SEVERE, null, ex);
            err.writeFile(" error on update qunatity class throws: " + ex.toString());
        }
        System.out.println("record has been updated");
         
    }
     
}
