/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

import com.mycompany.QLVT.Utils.DBConnectUtil;

/**
 *
 * @author zoroONE01
 */
public class DDH {
    private String MaDDH;
    private String Ngay;
    private String NCC;
    private int maNV;
    private String hoTenNV;
    private String maKho;

    public DDH(String MaDDH, String Ngay, String NCC, int maNV, String hoTenNV, String maKho) {
        this.MaDDH = MaDDH;
        this.Ngay = Ngay;
        this.NCC = NCC;
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.maKho = maKho;
    }

    

    public DDH(String MaDDH, String Ngay, String NCC, String maKho) {
        this.MaDDH = MaDDH;
        this.Ngay = Ngay;
        this.NCC = NCC;
        this.maKho = maKho;
        this.maNV = Integer.parseInt(DBConnectUtil.myUserDB);
        this.hoTenNV = DBConnectUtil.myName;
    }

    public DDH() {
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

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    @Override
    public String toString() {
        return "DDH{" + "MaDDH=" + MaDDH + ", Ngay=" + Ngay + ", NCC=" + NCC + ", maNV=" + maNV + ", hoTenNV=" + hoTenNV + ", maKho=" + maKho + '}';
    }

    
    
}
