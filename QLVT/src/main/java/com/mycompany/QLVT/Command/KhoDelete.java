/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class KhoDelete extends KhoCommand {

    private Kho kho;

    public KhoDelete() {
        super();
    }

    public KhoDelete(List<Kho> currentList, Kho kho) {
        super(currentList);
        this.kho = kho;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(k -> {
            listBackup.add(k);
        });
        list.remove(kho);
    }

    @Override
    public void unExecute() {
        list = listBackup;
    }

    @Override
    public String toString() {
        return "Delete " + kho.getMaKho();
    }

}
