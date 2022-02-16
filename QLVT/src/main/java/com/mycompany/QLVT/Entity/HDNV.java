/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

import java.time.LocalDate;

/**
 *
 * @author MinhTo
 */
public class HDNV {
    String dateFrom;
    String dateTo;
    String type;
    int maNV;

    public HDNV() {
    }

    public HDNV(String dateFrom, String dateTo, int maNV) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.maNV = maNV;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

 

   
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }
    
    
}
