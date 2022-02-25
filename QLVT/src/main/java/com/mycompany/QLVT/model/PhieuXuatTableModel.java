/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.PhieuXuat;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zoroONE01
 */
public class PhieuXuatTableModel {

    private final ObservableList<PhieuXuat> phieuXuatList = FXCollections.observableArrayList();

    private final ObjectProperty<PhieuXuat> currentPhieuXuat = new SimpleObjectProperty<>(null);
    private static ObservableList<PhieuXuat> list = FXCollections.observableArrayList();

    static {
        list.add(new PhieuXuat("MD123   ", "2021-10-14", "DIEN MAY XANH", 4, "TD"));
        list.add(new PhieuXuat("MD13    ", "2021-10-14", "DIEN MAY XANH", 6, "TD"));
        list.add(new PhieuXuat("MDTESST    ", "2021-10-14", "DIEN MAY XANH", 6, "TD"));
    }

    public PhieuXuatTableModel() {
    }

    public void setPhieuXuatList(List<PhieuXuat> list) {
        if (list == null) {
            phieuXuatList.addAll(new ArrayList<>());
        } else {
            phieuXuatList.addAll(list);
        }
    }

    public void setPhieuXuatList2(List<PhieuXuat> l) {
        list.addAll(l);
    }

    public ObservableList<PhieuXuat> getPhieuXuatList() {
        return phieuXuatList;

    }

    public ObservableList<PhieuXuat> getPhieuXuatList2() {

        return list;
    }

    public ObjectProperty<PhieuXuat> getCurrentPhieuXuatProperty() {
        return currentPhieuXuat;
    }

    public PhieuXuat getCurrentPhieuXuat() {
        return currentPhieuXuat.get();
    }

    public final void setCurrentPhieuXuat(PhieuXuat PhieuXuat) {
        currentPhieuXuat.set(PhieuXuat);
    }

    public final void getCurrentPhieuXuat(PhieuXuat PhieuXuat) {
        currentPhieuXuat.get();
    }
}
