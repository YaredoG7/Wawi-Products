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
public class ReadProduct {
 
       private String barcode_id ;  
       private String item_name ;
       private int    item_qty ;
       private String cust_name ;
       private String paid ;
       private String city ;
       private String _month ;
       private String _date ;
       private int    unit_price;
       private int    total_price;
       private String sent_killo;
       private int    rec_killo;
       
       
       public ReadProduct() {}
       
       
            
     
         // insert items fom quick sell 
     
         public ReadProduct(String barcode_id,  String item_name, int item_qty, String cust_name, String _date,  int unit_price, int total_price){
     
         this.barcode_id = barcode_id;
         this.item_name = item_name;
         this.item_qty = item_qty;
         this.cust_name = cust_name;
         this._date = _date;
         this.unit_price = unit_price;
         this.total_price = total_price;
     }
       
       public ReadProduct(String barcode_id, String item_name, int item_qty, String cust_name, String paid, String city, String _month, String _date, int unit_price, int total_price, String sent_killo, int rec_killo) {
        
         this.barcode_id = barcode_id;
         this.item_name = item_name ;
         this.item_qty = item_qty;
         this.cust_name = cust_name;
         this.paid = paid;
         this.city = city;
         this._month = _month;
         this._date = _date;
         this.unit_price = unit_price;
         this.total_price = total_price;
         this.sent_killo = sent_killo;
         this.rec_killo = rec_killo;
         
        }

    public String getBarcode_id() {
        return barcode_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getCity() {
        return city;
    }

    public void setBarcode_id(String barcode_id) {
        this.barcode_id = barcode_id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomer() {
        return cust_name;
    }

    public int getQty() {
        return item_qty;
    }

    public String getDate() {
        return _date;
    }

    public void setCustomer(String customer) {
        this.cust_name = customer;
    }
       
       
    
}
