/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hahucomputers.wawiproducts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author yared.yaregal
 */
public class IsExpired {
    
   public static Date oldDate(String oldDate ) throws ParseException{
    String dateFromat = "yyyy-mm-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
    sdf.setLenient(false);
    
    
    Date date = sdf.parse(oldDate);
   
    return date;
    }
    
    public static Date newDate (String newDate) throws ParseException{
    
    String dateFromat = "yyyy-mm-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
    sdf.setLenient(false);
    
    
    Date date = sdf.parse(newDate);
   
    return date;
    
    }
    public static boolean isExpired (String oldDate,String newDate ) throws ParseException{
      
        boolean isValid = false;
        
        if (oldDate(oldDate).after(newDate(newDate))){
        
        
        System.out.println("re-validation  is needed");
        
        isValid = true;
        
        }  else {
        
        
        System.out.println(" product is valid");
        
        isValid = false;
        }
        
        return isValid;
    
}
    
}
