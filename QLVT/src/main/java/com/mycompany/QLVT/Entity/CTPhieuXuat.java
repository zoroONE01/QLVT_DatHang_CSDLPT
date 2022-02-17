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
public class CTPhieuXuat {

    private String maPhieuXuat;
    private String maVT;
    private int soLuong;
    private float donGia;

    public CTPhieuXuat(String maPhieuXuat, String maVT, int soLuong, float donGia) {
        this.maPhieuXuat = maPhieuXuat;
        this.maVT = maVT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public CTPhieuXuat() {
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
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
        return "CTPhieuXuat{" + "maPhieuXuat=" + maPhieuXuat + ", maVT=" + maVT + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

    

}
