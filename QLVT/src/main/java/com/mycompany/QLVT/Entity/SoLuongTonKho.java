/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

/**
 *
 * @author zoroONE01
 */
public class SoLuongTonKho {
    private String kho;
    private int soLuongTon;

    public SoLuongTonKho(String kho, int soLuongTon) {
        this.kho = kho;
        this.soLuongTon = soLuongTon;
    }

    public SoLuongTonKho() {
    }
    
    

    public String getKho() {
        return kho;
    }

    public void setKho(String kho) {
        this.kho = kho;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    
}
