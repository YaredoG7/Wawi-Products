/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

/**
 *
 * @author yared.yaregal
 */
public class RegisterProduct {
    
    private String barcode_id ;  
    private String item_name ;
    private String item_vol;
    private int   item_qty ;
    private boolean in_store;
    private int left_in_stock;
    private int   unit_price;
    private String _date ;
    private String registered_by ;
    private String prod_date;
    private String exp_date ;
    private String _comment;
    
    
    public RegisterProduct(){}
    
    
     public RegisterProduct(String barcode_id, String item_name, String item_vol,   
                           int item_qty, boolean in_store, int left_in_stock, int unit_price,String _date,
                           String registered_by, String prod_date,String exp_date, String _comment ){
    
            this.barcode_id = barcode_id;
            this.item_name = item_name;
            this.item_vol = item_vol;
            this.item_qty = item_qty;
            this.in_store = in_store;
            this.left_in_stock = left_in_stock;
            this.unit_price = unit_price;
            this._date = _date;
            this.registered_by = registered_by;
            this.prod_date = prod_date;
            this.exp_date = exp_date;
            this._comment = _comment;
            
    
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

     
     
     // method overloading , inserting the difference between sold items  and registered prod
     
     public RegisterProduct (String barcode_id, String item_name, int item_qty, String comment){
         
         this.barcode_id = barcode_id;
         this.item_name = item_name;
         this.item_qty = item_qty;
         //use this comment filed to display the remianing items, do this in out going regsiter 
         this._comment =  comment;
         
     }

    public String getBarcode_id() {
        return barcode_id;
    }

    public void setBarcode_id(String barcode_id) {
        this.barcode_id = barcode_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(int item_qty) {
        this.item_qty = item_qty;
    }

    public void setComment(String comment) {
        this._comment = comment;
    }

    public String getComment() {
        return _comment;
    }



    public String getDate() {
        return _date;
    }

    public void setDate(String _date) {
        this._date = _date;
    }
    
   
    
    
    
    
}
