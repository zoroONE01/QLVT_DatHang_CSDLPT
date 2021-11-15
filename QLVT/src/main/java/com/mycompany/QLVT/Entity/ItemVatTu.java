/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author zoroONE01
 */
public class ItemVatTu {

    private StringProperty maVTProperty = new SimpleStringProperty("");
    private StringProperty tenVTProperty = new SimpleStringProperty("");
    private FloatProperty donGiaProperty = new SimpleFloatProperty();
    private IntegerProperty soLuongProperty = new SimpleIntegerProperty(1);

    public ItemVatTu() {

    }

    public StringProperty getMaVTProperty() {
        return maVTProperty;
    }

    public void setMaVTProperty(StringProperty maVTProperty) {
        this.maVTProperty = maVTProperty;
    }

    public StringProperty getTenVTProperty() {
        return tenVTProperty;
    }

    public void setTenVTProperty(StringProperty tenVTProperty) {
        this.tenVTProperty = tenVTProperty;
    }

    public FloatProperty getDonGiaProperty() {
        return donGiaProperty;
    }

    public void setDonGiaProperty(FloatProperty donGiaProperty) {
        this.donGiaProperty = donGiaProperty;
    }

    public IntegerProperty getSoLuongProperty() {
        return soLuongProperty;
    }

    public void setSoLuongProperty(IntegerProperty soLuongProperty) {
        this.soLuongProperty = soLuongProperty;
    }

    public void setMaVT(String maVT) {
        this.maVTProperty = new SimpleStringProperty(maVT);
    }

    public void setTenVT(String tenVT) {
        this.tenVTProperty = new SimpleStringProperty(tenVT);
    }

    public void setSoLuong(int soLuong) {
        this.soLuongProperty = new SimpleIntegerProperty(soLuong);
    }

    public void setDonGia(float donGia) {
        this.donGiaProperty = new SimpleFloatProperty(donGia);
    }

    public String getMaVT() {
        return maVTProperty.get();
    }

    public String getTenVT() {
        return tenVTProperty.get();
    }

    public float getDonGia() {
        return donGiaProperty.get();
    }

    public int getSoLuong() {
        return soLuongProperty.get();
    }

    @Override
    public String toString() {
        return "ItemVatTu{" + "maVTProperty=" + getMaVT() + ", tenVTProperty=" + getTenVT() + ", donGiaProperty=" + getDonGia() + ", soLuongProperty=" + getSoLuong() + '}';
    }

//    public boolean isEmpty() {
//        return getMaVT().isEmpty() && getTenVT().isEmpty();
//    }
}
