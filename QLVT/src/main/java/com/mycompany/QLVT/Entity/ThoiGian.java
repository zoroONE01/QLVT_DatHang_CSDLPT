/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

/**
 *
 * @author MinhTo
 */
public class ThoiGian {

    int thangFrom;
    int thangTo;
    int namFrom;
    int namTo;
    String type;
    public ThoiGian(int thangFrom, int thangTo, int namFrom, int namTo) {
        this.thangFrom = thangFrom;
        this.thangTo = thangTo;
        this.namFrom = namFrom;
        this.namTo = namTo;
    }

    public int getThangFrom() {
        return thangFrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public void setThangFrom(int thangFrom) {
        this.thangFrom = thangFrom;
    }

    public int getThangTo() {
        return thangTo;
    }

    public void setThangTo(int thangTo) {
        this.thangTo = thangTo;
    }

    public int getNamFrom() {
        return namFrom;
    }

    public void setNamFrom(int namFrom) {
        this.namFrom = namFrom;
    }

    public int getNamTo() {
        return namTo;
    }

    public void setNamTo(int namTo) {
        this.namTo = namTo;
    }
    
}
