/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author MinhTo
 */
public class FomaterDate {

    public static Date convertStringToDate(String dateString) {

        //date String có dạng yyyy-MM-dd
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //  SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd "); 
        try {
            date = format.parse(dateString);

        } catch (ParseException ex) {
            System.out.println("ex");
        }
        return date;

    }

    public static String convertDateToString(java.sql.Date date) {

        //date String có dạng yyyy-MM-dd
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //  SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd "); 
            String strDate = format.format(date);
            return strDate;
        } catch (Exception e) {
            System.out.println("Ngay thang khong dung dinh danh");
        }
        return null;
}   
     public static String convertUtilDateToString(java.util.Date date) {

        //date String có dạng yyyy-MM-dd
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //  SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd "); 
           String strDate = format.format(date);
           
            return strDate;
        } catch (Exception e) {
            System.out.println("Ngay thang khong dung dinh danh");
        }
        return null;
}   
    
    public static String convertLocalDateToString(LocalDate datetime)
            {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fomatterDateTime=datetime.format(dateTimeFormatter);
                return fomatterDateTime;
            }
      public static LocalDate convertStringToLocalDate(String datetime)
            {
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDateTime=LocalDate.parse(datetime,dateTimeFormatter);
                return localDateTime;
            }

    public static void main(String[] args) throws ParseException {
        String ns="2000-02-26 05:30";
//        LocalDateTime localDateTime=convertStringToLocalDate(ns);
//        System.out.println(localDateTime);
//        System.out.println(convertLocalDateToString(localDateTime));



//        java.sql.Date d=new java.sql.Date())
//        Date date=null;
//        date=FomaterDate.convertStringToDate(ns);
//        JDateCser dateChooser=new JDateChooser(date);
//        Systehoom.out.println(dateChooser.getDate());
       // Date date=Calendar.getInstance().getTime();
        System.out.println(convertUtilDateToString(new Date()));
        }
    }

