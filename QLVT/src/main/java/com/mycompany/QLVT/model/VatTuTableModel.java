/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

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
public class VatTuTableModel {

    private final ObservableList<VatTu> vatTuList = FXCollections.observableArrayList();

    private final ObjectProperty<VatTu> currentVatTu = new SimpleObjectProperty<>(null);

    public VatTuTableModel() {
    }

    public void setVatTuList(List<VatTu> list) {
        vatTuList.addAll(list);
    }

    public ObservableList<VatTu> getVatTuList() {
        return vatTuList;
    }

    public ObjectProperty<VatTu> getCurrentVatTuProperty() {
        return currentVatTu;
    }

    public VatTu getCurrentVatTu() {
        return currentVatTu.get();
    }

    public final void setCurrentVatTu(VatTu vatTu) {
        currentVatTu.set(vatTu);
    }

    public final void getCurrentVatTu(VatTu vatTu) {
        currentVatTu.get();
    }
}
