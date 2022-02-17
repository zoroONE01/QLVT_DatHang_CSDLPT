/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.Kho;
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
public class KhoCbbModel {
    
    private final ObservableList<Kho> nhanVienList = FXCollections.observableArrayList();

    private final ObjectProperty<Kho> currentPhieuNhap = new SimpleObjectProperty<>(null);

    public KhoCbbModel() {
    }



    public void setKhoList(List<Kho> list) {
        nhanVienList.addAll(list);
    }

    public ObservableList<Kho> getKhoList() {
        return nhanVienList;
    }

    public ObjectProperty<Kho> getCurrentKhoProperty() {
        return currentPhieuNhap;
    }

    public Kho getCurrentKho() {
        return currentPhieuNhap.get();
    }

    public final void setCurrentKho(Kho nhanVien) {
        currentPhieuNhap.set(nhanVien);
    }

    public final void getCurrentKho(Kho nhanVien) {
        currentPhieuNhap.get();
    }
}
