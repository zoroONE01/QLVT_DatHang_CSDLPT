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
public class DDH {

    private String MaDDH;
    private String Ngay;
    private String NCC;
    private int maNV;
    private String trangThai;
    private String ho;
    private String ten;
    private String maKho;
    private String tenKho;
    private String maNVHoTen;
    private String maKhoTenKho;

    public DDH() {
    }

    public DDH(String MaDDH, String Ngay, String NCC, int maNV, String ho, String ten, String maKho, String tenKho, String maNVHoTen, String maKhoTenKho, String trangThai) {
        this.MaDDH = MaDDH;
        this.Ngay = Ngay;
        this.NCC = NCC;
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maNVHoTen = maNVHoTen;
        this.maKhoTenKho = maKhoTenKho;
        this.trangThai = trangThai;
    }

    public DDH(String MaDDH, String Ngay, String NCC, int maNV, String maKho) {
        this.MaDDH = MaDDH;
        this.Ngay = Ngay;
        this.NCC = NCC;
        this.maNV = maNV;
        this.maKho = maKho;
//        this.trangThai = null;
    }

    public DDH(String MaDDH, String Ngay, String NCC, int maNV, String ho, String ten, String maKho, String tenKho, String trangThai) {
        this.MaDDH = MaDDH;
        this.Ngay = Ngay;
        this.NCC = NCC;
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.maNVHoTen = "[" + maNV + "] " + ho + " " + ten;
        this.maKhoTenKho = "[" + maKho + "] " + tenKho;
        this.trangThai = trangThai;
    }

    public String getMaDDH() {
        return MaDDH;
    }

    public void setMaDDH(String MaDDH) {
        this.MaDDH = MaDDH;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String Ngay) {
        this.Ngay = Ngay;
    }

    public String getNCC() {
        return NCC;
    }

    public void setNCC(String NCC) {
        this.NCC = NCC;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
        return "DDH{" + "MaDDH=" + MaDDH + ", Ngay=" + Ngay + ", NCC=" + NCC + ", maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", maKho=" + maKho + ", tenKho=" + tenKho + ", maNVHoTen=" + maNVHoTen + ", maKhoTenKho=" + maKhoTenKho + '}';
    }

}
