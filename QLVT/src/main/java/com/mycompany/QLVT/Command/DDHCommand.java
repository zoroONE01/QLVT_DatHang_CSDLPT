/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.DDH;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public abstract class DDHCommand {

    protected List<DDH> list;
    protected List<DDH> listBackup;

    public DDHCommand() {
    }

    public DDHCommand(List<DDH> list) {
        this.list = list;
//        this.listBackup = list;
    }

    public List<DDH> getList() {
        return list;
    }

    public void setList(List<DDH> list) {
        this.list = list;
    }

    public List<DDH> getListBackup() {
        return listBackup;
    }

    public void setListBackup(List<DDH> listBackup) {
        this.listBackup = listBackup;
    }

    @Override
    public abstract String toString();

    public abstract void execute();

    public abstract void exectteToDatabase();

    public abstract void unExecute();

}
