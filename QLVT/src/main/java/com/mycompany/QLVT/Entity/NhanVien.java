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
public class NhanVien {
    private int maNhanVien;
    private String ho;
    private String diaChi;
    private String ngaySinh;
    private int luong;
    private String maCN;
    private String trangThai;

    public NhanVien() {
    }

    public NhanVien(int maNhanVien, String ho, String diaChi, String ngaySinh, int luong, String maCN, String trangThai) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.luong = luong;
        this.maCN = maCN;
        this.trangThai = trangThai;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getMaCN() {
        return maCN;
    }

    public void setMaCN(String maCN) {
        this.maCN = maCN;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
    
}
