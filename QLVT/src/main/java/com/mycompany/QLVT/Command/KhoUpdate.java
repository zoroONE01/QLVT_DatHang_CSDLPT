/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.service.KhoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class KhoUpdate extends KhoCommand {

    private Kho oldKho;
    private Kho newKho;

    public KhoUpdate() {
        super();
    }

    public KhoUpdate(List<Kho> currentList, Kho oldKho, Kho newKho) {
        super(currentList);
        this.oldKho = oldKho;
        this.newKho = newKho;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(k -> {
            listBackup.add(k);
        });
//        System.out.println(listBackup);
        list.set(list.indexOf(oldKho), newKho);
//        System.out.println(listBackup);
//        System.out.println(list);
    }

    @Override
    public void unExecute() {
        System.out.println(list);
        this.list = listBackup;
        System.out.println(list);
    }

    @Override
    public String toString() {
        return "Update " + oldKho.getMaKho();
    }

    @Override
    public void executoToDataBase() {
        KhoService service = new KhoService();
        service.update(newKho);
    }
}
