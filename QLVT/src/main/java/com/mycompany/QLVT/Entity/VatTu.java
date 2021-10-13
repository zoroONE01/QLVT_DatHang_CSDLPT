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
public class VatTu {

    private String maVT;
    private String tenVT;
    private String DVT;
    private int soLuongTon;

    public VatTu() {
    }

    public VatTu(String maVT, String tenVT, String DVT) {
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.DVT = DVT;
        this.soLuongTon = 0;
    }

    public VatTu(String maVT, String tenVT, String DVT, int soLuongTon) {
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.DVT = DVT;
        this.soLuongTon = soLuongTon;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    @Override
    public String toString() {
        return "VatTu{" + "maVT=" + maVT + ", tenVT=" + tenVT + ", DVT=" + DVT + ", soLuongTon=" + soLuongTon + '}';
    }

}
