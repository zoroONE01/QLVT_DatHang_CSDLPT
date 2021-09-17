/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.VatTu;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public abstract class VatTuCommand {

    protected List<VatTu> list;
    protected List<VatTu> listBackup;

    public VatTuCommand() {
    }

    public VatTuCommand(List<VatTu> list) {
        this.list = list;
//        this.listBackup = list;
    }

    public List<VatTu> getList() {
        return list;
    }

    public void setList(List<VatTu> list) {
        this.list = list;
    }

    public List<VatTu> getListBackup() {
        return listBackup;
    }

    public void setListBackup(List<VatTu> listBackup) {
        this.listBackup = listBackup;
    }

    @Override
    public abstract String toString();

    public abstract void execute();

    public abstract void exectteToDatabase();

    public abstract void unExecute();

}
