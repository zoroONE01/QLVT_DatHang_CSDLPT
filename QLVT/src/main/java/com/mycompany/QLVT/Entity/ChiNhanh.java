/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

/**
 *
 * @author MinhTo
 */
public class ChiNhanh {
   private String maCN;
   private String chiNhanh;
   private String diaChi;
   private String sdt;

    public ChiNhanh() {
    }

    public ChiNhanh(String maCN, String chiNhanh, String diaChi, String sdt) {
        this.maCN = maCN;
        this.chiNhanh = chiNhanh;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
   
    
   
}
