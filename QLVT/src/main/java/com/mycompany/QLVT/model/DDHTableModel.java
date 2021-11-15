/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.model;

import com.mycompany.QLVT.Entity.DDH;
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
public class DDHTableModel {

    private final ObservableList<DDH> DDHList = FXCollections.observableArrayList();

    private final ObjectProperty<DDH> currentDDH = new SimpleObjectProperty<>(null);
    private static ObservableList<DDH> list=FXCollections.observableArrayList();
    
    static {
        list.add(new DDH("MD123   ", "2021-10-14","DIEN MAY XANH" , 4,"TD"));
         list.add(new DDH("MD13    ", "2021-10-14","DIEN MAY XANH" , 6,"TD"));
           list.add(new DDH("MDTESST    ", "2021-10-14","DIEN MAY XANH" , 6,"TD"));
    }
    public DDHTableModel() {
    }

    public void setDDHList(List<DDH> list) {
        DDHList.addAll(list);
    }
    public void setDDHList2(List<DDH> l) {
        list.addAll(l);
    }

    public ObservableList<DDH> getDDHList() {
        return DDHList;
      
    }
    public ObservableList<DDH> getDDHList2() {
     
      return list;
    }

    public ObjectProperty<DDH> getCurrentDDHProperty() {
        return currentDDH;
    }

    public DDH getCurrentDDH() {
        return currentDDH.get();
    }

    public final void setCurrentDDH(DDH ddh) {
        currentDDH.set(ddh);
    }

    public final void getCurrentDDH(DDH ddh) {
        currentDDH.get();
    }
}
