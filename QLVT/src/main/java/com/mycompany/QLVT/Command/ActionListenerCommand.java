/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.NhanVien;

/**
 *
 * @author MinhTo
 */
public abstract class ActionListenerCommand<T> {

    private String type;
    private T ob;
    
    public ActionListenerCommand(String type) {
        this.type = type;
    }

    public void backup(T t){
        this.ob=t;
    }

    public T undo(){
    return this.ob;
    }

    public String getType() {
        return type;
    }

    public abstract boolean execute();
//    NhanVien backupNhanVien;
//    private String type;
//
//    public ActionListenerCommand(String type) {
//        this.type = type;
//    }
//
//    public void backup(NhanVien nv) {
//        this.backupNhanVien = nv;
//    }
//
//    public NhanVien undo() {
//        return this.backupNhanVien;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//
//    public abstract boolean execute();

}
