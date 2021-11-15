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
public abstract class ActionListenerCommandTest<T> {
    
    private String type;
    
    public ActionListenerCommandTest(String type) {
        this.type = type;
    }

    public abstract void backup(T t);

    public abstract T undo();

    public String getType() {
        return type;
    }

    public abstract boolean execute();

}
