/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.PhieuNhap;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class PhieuNhapModel {
        private final ObservableList<PhieuNhap> nhanVienList = FXCollections.observableArrayList();

    private final ObjectProperty<PhieuNhap> currentPhieuNhap = new SimpleObjectProperty<>(null);

    public PhieuNhapModel() {
    }

    public void setPhieuNhapList(List<PhieuNhap> list) {
        nhanVienList.addAll(list);
    }

    public ObservableList<PhieuNhap> getPhieuNhapList() {
        return nhanVienList;
    }

    public ObjectProperty<PhieuNhap> getCurrentPhieuNhapProperty() {
        return currentPhieuNhap;
    }

    public PhieuNhap getCurrentPhieuNhap() {
        return currentPhieuNhap.get();
    }

    public final void setCurrentPhieuNhap(PhieuNhap nhanVien) {
        currentPhieuNhap.set(nhanVien);
    }

    public final void getCurrentPhieuNhap(PhieuNhap nhanVien) {
        currentPhieuNhap.get();
    }
}
