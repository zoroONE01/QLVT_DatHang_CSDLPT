/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class ChiTietPhieuNhapModel {
        private final ObservableList<ChiTietPhieuNhap> nhanVienList = FXCollections.observableArrayList();

    private final ObjectProperty<ChiTietPhieuNhap> currentChiTietPhieuNhap = new SimpleObjectProperty<>(null);

    public ChiTietPhieuNhapModel() {
    }

    public void setChiTietPhieuNhapList(List<ChiTietPhieuNhap> list) {
        nhanVienList.addAll(list);
    }

    public ObservableList<ChiTietPhieuNhap> getChiTietPhieuNhapList() {
        return nhanVienList;
    }

    public ObjectProperty<ChiTietPhieuNhap> getCurrentChiTietPhieuNhapProperty() {
        return currentChiTietPhieuNhap;
    }

    public ChiTietPhieuNhap getCurrentChiTietPhieuNhap() {
        return currentChiTietPhieuNhap.get();
    }

    public final void setCurrentChiTietPhieuNhap(ChiTietPhieuNhap nhanVien) {
        currentChiTietPhieuNhap.set(nhanVien);
    }

    public final void getCurrentChiTietPhieuNhap(ChiTietPhieuNhap nhanVien) {
        currentChiTietPhieuNhap.get();
    }
}
