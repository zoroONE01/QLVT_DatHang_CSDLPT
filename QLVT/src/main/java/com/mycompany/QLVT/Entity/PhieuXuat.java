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
public class PhieuXuat {

    private String maPhieuXuat;
    private String Ngay;
    private String khachHang;
    private int maNV;
    private String ho;
    private String ten;
    private String maKho;
    private String tenKho;
    private String maNVHoTen;
    private String maKhoTenKho;

    public PhieuXuat() {
    }

    public PhieuXuat(String maPhieuXuat, String Ngay, String khachHang, int maNV, String ho, String ten, String maKho, String tenKho, String maNVHoTen, String maKhoTenKho) {
        this.maPhieuXuat = maPhieuXuat;
        this.Ngay = Ngay;
        this.khachHang = khachHang;
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maNVHoTen = maNVHoTen;
        this.maKhoTenKho = maKhoTenKho;
    }

    public PhieuXuat(String maPhieuXuat, String Ngay, String khachHang, int maNV, String maKho) {
        this.maPhieuXuat = maPhieuXuat;
        this.Ngay = Ngay;
        this.khachHang = khachHang;
        this.maNV = maNV;
        this.maKho = maKho;
//        this.trangThai = null;
    }

    public PhieuXuat(String maPhieuXuat, String Ngay, String khachHang, int maNV, String ho, String ten, String maKho, String tenKho) {
        this.maPhieuXuat = maPhieuXuat;
        this.Ngay = Ngay;
        this.khachHang = khachHang;
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maNVHoTen = "[" + maNV + "] " + ho + " " + ten;
        this.maKhoTenKho = "[" + maKho + "] " + tenKho;
    }

    public String getMaPhieuXuat() {
        return maPhieuXuat;
    }

    public void setMaPhieuXuat(String maPhieuXuat) {
        this.maPhieuXuat = maPhieuXuat;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public String getMaNVHoTen() {
        return maNVHoTen;
    }

    public void setMaNVHoTen(String maNVHoTen) {
        this.maNVHoTen = maNVHoTen;
    }

    public void setMaNVHoTen() {
        this.maNVHoTen = "[" + maNV + "] " + ho + " " + ten;
    }

    public String getMaKhoTenKho() {
        return maKhoTenKho;
    }

    public void setMaKhoTenKho() {
        this.maKhoTenKho = "[" + maKho + "] " + tenKho;
    }

    @Override
    public String toString() {
        return "PhieuXuat{" + "maPhieuXuat=" + maPhieuXuat + ", Ngay=" + Ngay + ", khachHang=" + khachHang + ", maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", maKho=" + maKho + ", tenKho=" + tenKho + ", maNVHoTen=" + maNVHoTen + ", maKhoTenKho=" + maKhoTenKho + '}';
    }
}
