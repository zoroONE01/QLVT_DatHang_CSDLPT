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
public class CTDDH {

    private String maSoDDH;
    private String maVT;
    private int soLuong;
    private float donGia;
    private String tenVT;

    public CTDDH(String maSoDDH, String maVT, int soLuong, float donGia) {
        this.maSoDDH = maSoDDH;
        this.maVT = maVT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public CTDDH() {
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public String getMaSoDDH() {
        return maSoDDH;
    }

    public void setMaSoDDH(String maSoDDH) {
        this.maSoDDH = maSoDDH;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietDDH{" + "maSoDDH=" + maSoDDH + ", maVT=" + maVT + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

}
