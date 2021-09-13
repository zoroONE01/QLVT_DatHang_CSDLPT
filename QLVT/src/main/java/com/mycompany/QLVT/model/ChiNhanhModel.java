/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.ChiNhanh;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhanManh;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class ChiNhanhModel {
     private final ObservableList<PhanManh> nhanVienList = FXCollections.observableArrayList();

    private final ObjectProperty<PhanManh> currentNhanVien = new SimpleObjectProperty<>(null);

    public ChiNhanhModel() {
    }

    public void setList(List<PhanManh> list) {
        nhanVienList.addAll(list);
    }

    public ObservableList<PhanManh> getList() {
        return nhanVienList;
    }

    public ObjectProperty<PhanManh> getCurrentProperty() {
        return currentNhanVien;
    }

    public PhanManh getCurrent() {
        return currentNhanVien.get();
    }

    public final void setCurrent(PhanManh nhanVien) {
        currentNhanVien.set(nhanVien);
    }

    public final void getCurrent(PhanManh nhanVien) {
        currentNhanVien.get();
    }
}
