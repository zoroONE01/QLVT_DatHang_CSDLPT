/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.SoLuongTonKho;
import com.mycompany.QLVT.Entity.VatTu;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zoroONE01
 */
public class VatTuTonKhoTableModel {

    private ObservableList<SoLuongTonKho> vatTuList = FXCollections.observableArrayList();

//    private final ObjectProperty<SoLuongTonKho> currentVatTu = new SimpleObjectProperty<>(null);
    public VatTuTonKhoTableModel(List<SoLuongTonKho> list) {
        this.vatTuList.addAll(list);
    }

    public ObservableList<SoLuongTonKho> getVatTuList() {
        return vatTuList;
    }
}
