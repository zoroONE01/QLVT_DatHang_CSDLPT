/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.service.VatTuService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class VatTuInsert extends VatTuCommand {

    private VatTu vatTu;

    public VatTuInsert() {
        super();
    }

    public VatTuInsert(List<VatTu> currentList, VatTu vatTu) {
        super(currentList);
        this.vatTu = vatTu;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(vt -> {
            listBackup.add(vt);
        });
        list.add(0, vatTu);
    }

    @Override
    public void unExecute() {
        list = listBackup;
    }

    @Override
    public String toString() {
        return "Insert " + vatTu.getMaVT();
    }

    @Override
    public void exectteToDatabase() {
        VatTuService service = new VatTuService();
        service.insert(vatTu);
    }
}
