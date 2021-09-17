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
public class DonDatHang {
    
    private String maDHH;
    private String ngay;
    private String nhaCC;
    private NhanVien nhanVien;
    private Kho kho;

    public DonDatHang() {
    }

    public DonDatHang(String maDHH, String ngay, String nhaCC, NhanVien nhanVien, Kho kho) {
        this.maDHH = maDHH;
        this.ngay = ngay;
        this.nhaCC = nhaCC;
        this.nhanVien = nhanVien;
        this.kho = kho;
    }

    public String getMaDHH() {
        return maDHH;
    }

    public void setMaDHH(String maDHH) {
        this.maDHH = maDHH;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNhaCC() {
        return nhaCC;
    }

    public void setNhaCC(String nhaCC) {
        this.nhaCC = nhaCC;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }
    
    
}
