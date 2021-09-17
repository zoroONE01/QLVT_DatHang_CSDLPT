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
public class VatTuUpdate extends VatTuCommand {

    private VatTu oldVatTu;
    private VatTu newVatTu;

    public VatTuUpdate() {
        super();
    }

    public VatTuUpdate(List<VatTu> currentList, VatTu oldVatTu, VatTu newVatTu) {
        super(currentList);
        this.oldVatTu = oldVatTu;
        this.newVatTu = newVatTu;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(vt -> {
            listBackup.add(vt);
        });

        list.set(list.indexOf(oldVatTu), newVatTu);

    }

    @Override
    public void unExecute() {

        this.list = listBackup;

    }

    @Override
    public String toString() {
        return "Update " + oldVatTu.getMaVT();
    }

    @Override
    public void exectteToDatabase() {
        VatTuService service = new VatTuService();
        service.update(newVatTu);
    }
}
