/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.NhanVien;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class NhanVienTableModel {

    private final ObservableList<NhanVien> nhanVienList = FXCollections.observableArrayList();

    private final ObjectProperty<NhanVien> currentNhanVien = new SimpleObjectProperty<>(null);

    public NhanVienTableModel() {
    }

    public void setNhanVienList(List<NhanVien> list) {
        nhanVienList.addAll(list);
    }

    public ObservableList<NhanVien> getNhanVienList() {
        return nhanVienList;
    }

    public ObjectProperty<NhanVien> getCurrentNhanVienProperty() {
        return currentNhanVien;
    }

    public NhanVien getCurrentNhanVien() {
        return currentNhanVien.get();
    }

    public final void setCurrentNhanVien(NhanVien nhanVien) {
        currentNhanVien.set(nhanVien);
    }

    public final void getCurrentNhanVien(NhanVien nhanVien) {
        currentNhanVien.get();
    }
}
