/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MinhTo
 */
public class NhanVienTest {
    private final IntegerProperty maNhanVien;
    private final StringProperty ho;
    private final StringProperty diaChi;
    private final StringProperty ngaySinh;
    private final IntegerProperty luong;
    private final StringProperty maCN;
    private final StringProperty trangThai;

    public NhanVienTest(int maNhanVien, String ho, String diaChi, String ngaySinh, int luong, String maCN, String trangThai) {
        this.maNhanVien = new SimpleIntegerProperty(maNhanVien);
        this.ho =new SimpleStringProperty (ho);
        this.diaChi =new SimpleStringProperty (diaChi);
        this.ngaySinh =new SimpleStringProperty (ngaySinh);
        this.luong =new SimpleIntegerProperty (luong);
        this.maCN =new SimpleStringProperty (maCN);
        this.trangThai =new SimpleStringProperty (trangThai);
    }

    public IntegerProperty getMaNhanVien() {
        return maNhanVien;
    }

    public StringProperty getHo() {
        return ho;
    }

    public StringProperty getDiaChi() {
        return diaChi;
    }

    public StringProperty getNgaySinh() {
        return ngaySinh;
    }

    public IntegerProperty getLuong() {
        return luong;
    }

    public StringProperty getMaCN() {
        return maCN;
    }

    public StringProperty getTrangThai() {
        return trangThai;
    }
    
    
    
}
