/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.ChiTietDDH;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MinhTo
 */
public class ChiTietDDHModel {

    private final ObservableList<CTDDH> ChiTietDDHList = FXCollections.observableArrayList();

    private final ObjectProperty<CTDDH> currentChiTietDDH = new SimpleObjectProperty<>(null);
    private static ObservableList<CTDDH> list = FXCollections.observableArrayList();

//    static {
//        ChiTietDDH ct1=new ChiTietDDH("MDTESST ", "TL1", 50, 50000);
//        ct1.setTenVT("Tủ Lạnh");
//      
//            ChiTietDDH ct2=new ChiTietDDH("MDTESST2", "TV02", 50, 50000);
//              ct2.setTenVT("Ti vi");
//        list.add(ct1);
//        list.add(ct2);
//
//    }

    public ChiTietDDHModel() {
    }

    public void setChiTietDDHList(List<CTDDH> list) {
        ChiTietDDHList.addAll(list);
    }

//    public void setChiTietDDHList2(List<ChiTietDDH> l) {
//        list.addAll(l);
//    }

    public ObservableList<CTDDH> getChiTietDDHList() {
        return ChiTietDDHList;

    }

//    public ObservableList<ChiTietDDH> getChiTietDDHList2() {
//
//        return list;
//    }

    public ObjectProperty<CTDDH> getCurrentChiTietDDHProperty() {
        return currentChiTietDDH;
    }

    public CTDDH getCurrentChiTietDDH() {
        return currentChiTietDDH.get();
    }

    public final void setCurrentChiTietDDH(CTDDH ddh) {
        currentChiTietDDH.set(ddh);
    }

    public final void getCurrentChiTietDDH(ChiTietDDH ddh) {
        currentChiTietDDH.get();
    }
}
