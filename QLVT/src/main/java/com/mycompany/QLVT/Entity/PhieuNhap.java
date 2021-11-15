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
public class PhieuNhap {
    private String maPN;
    private String ngay;
    private String maDDH;
    private int maNhanVien;
    private String maKhoa;

    public PhieuNhap() {
    }

    public PhieuNhap(String maPN, String ngay, String maDDH, int maNhanVien, String maKhoa) {
        this.maPN = maPN;
        this.ngay = ngay;
        this.maDDH = maDDH;
        this.maNhanVien = maNhanVien;
        this.maKhoa = maKhoa;
    }

    public String getMaPN() {
        return maPN;
    }

    public void setMaPN(String maPN) {
        this.maPN = maPN;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getMaDDH() {
        return maDDH;
    }

    public void setMaDDH(String maDDH) {
        this.maDDH = maDDH;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
    
    
}
