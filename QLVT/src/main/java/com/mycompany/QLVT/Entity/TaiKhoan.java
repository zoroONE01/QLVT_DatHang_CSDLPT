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
public class TaiKhoan {
    
   private  String tenDangNhap;
   private String matKhau;
   private int  maNhanVien;
   private String nhom;
   private String chiNhanh;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, int maNhanVien, String nhom, String chiNhanh) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNhanVien = maNhanVien;
        this.nhom = nhom;
        this.chiNhanh = chiNhanh;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }



    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(String chiNhanh) {
        this.chiNhanh = chiNhanh;
    }
   
   
}
