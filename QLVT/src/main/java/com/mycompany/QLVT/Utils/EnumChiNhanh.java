/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Utils;

/**
 *
 * @author MinhTo
 */
public enum EnumChiNhanh {
    CN1("Chi Nhánh 1 TP HCM"),
    CN2("Chi Nhánh 2 TP Cần Thơ");
    public  String tp;
    private EnumChiNhanh(String tp) {
        this.tp = tp;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }
    
    
    
    
}
