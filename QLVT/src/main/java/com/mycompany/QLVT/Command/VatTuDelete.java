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
public class VatTuDelete extends VatTuCommand {

    private VatTu vatTu;

    public VatTuDelete() {
        super();
    }

    public VatTuDelete(List<VatTu> currentList, VatTu vatTu) {
        super(currentList);
        this.vatTu = vatTu;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(vt -> {
            listBackup.add(vt);
        });
        list.remove(vatTu);
    }

    @Override
    public void unExecute() {
        list = listBackup;
    }

    @Override
    public String toString() {
        return "Delete " + vatTu.getMaVT();
    }

    @Override
    public void exectteToDatabase() {
        VatTuService service = new VatTuService();
        service.delete(vatTu.getMaVT());
    }

}
