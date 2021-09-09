/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MinhTo
 */
public class FomaterDate {

    public static Date convertStringToDate(String dateString) {

        //date String có dạng yyyy-MM-dd
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //  SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd "); 
            String strDate = format.format(date);
            return strDate;
        } catch (Exception e) {
            System.out.println("Ngay thang khong dung dinh danh");
        }
        return null;
}   
}
//    public static void main(String[] args) throws ParseException {
//        String ns="2000-02-30 5:30:30";
//        java.sql.Date d=new java.sql.Date())
//        Date date=null;
//        date=FomaterDate.convertStringToDate(ns);
//        JDateChooser dateChooser=new JDateChooser(date);
//        System.out.println(dateChooser.getDate());
//        
//        }

