/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.NhanVien;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class NhanVienTableModel {
    private final ObservableList<NhanVien> nhanVienList=FXCollections.observableArrayList();
    
    private final ObjectProperty<NhanVien> currentNhanVien=new SimpleObjectProperty<>(null);

    public NhanVienTableModel() {
    }

    public ObservableList<NhanVien> getNhanVienList() {
        return nhanVienList;
    }

    public ObjectProperty<NhanVien> getCurrentNhanVien() {
        return currentNhanVien;
    }
    public final void setCurrentNhanVien(NhanVien nhanVien)
    {
        currentNhanVien.set(nhanVien);
    }
    public final void getCurrentNhanVien(NhanVien nhanVien)
    {
        currentNhanVien.get();
    }
    
}
