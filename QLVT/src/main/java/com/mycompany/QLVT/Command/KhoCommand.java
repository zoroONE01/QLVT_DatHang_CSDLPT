/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import java.util.AbstractList;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public abstract class KhoCommand {

    protected List<Kho> list;
    protected List<Kho> listBackup;

    public KhoCommand() {
    }

    public KhoCommand(List<Kho> list) {
        this.list = list;
//        this.listBackup = list;
    }

    public List<Kho> getList() {
        return list;
    }

    public void setList(List<Kho> list) {
        this.list = list;
    }

    public List<Kho> getListBackup() {
        return listBackup;
    }

    public void setListBackup(List<Kho> listBackup) {
        this.listBackup = listBackup;
    }

    @Override
    public abstract String toString();

    public abstract void execute();

    public abstract void executoToDataBase();

    public abstract void unExecute();

}
