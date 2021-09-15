/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.Kho;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zoroONE01
 */
public class KhoTableModel {
    
    private final ObservableList<Kho> khoList = FXCollections.observableArrayList();
    
    private final ObjectProperty<Kho> currentKho = new SimpleObjectProperty<>(null);
    
    
    public KhoTableModel() {
    }
    
    public void setKhoList(List<Kho> list) {
        khoList.addAll(list);
    }
    
    
    public ObservableList<Kho> getKhoList() {
        return khoList;
    }
    
    public ObjectProperty<Kho> getCurrentKhoProperty() {
        return currentKho;
    }
    
    public Kho getCurrentKho() {
        return currentKho.get();
    }
    
    public final void setCurrentKho(Kho kho) {
        currentKho.set(kho);
    }
    
    public final void getCurrentKho(Kho kho) {
        currentKho.get();
    }
}
