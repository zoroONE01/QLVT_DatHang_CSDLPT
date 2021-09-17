/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.service.DDHService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class DDHDelete extends DDHCommand {

    private DDH donDatHang;

    public DDHDelete() {
        super();
    }

    public DDHDelete(List<DDH> currentList, DDH donDatHang) {
        super(currentList);
        this.donDatHang = donDatHang;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(ddh -> {
            listBackup.add(ddh);
        });
        list.remove(donDatHang);
    }

    @Override
    public void unExecute() {
        list = listBackup;
    }

    @Override
    public String toString() {
        return "Delete " + donDatHang.getMaDDH();
    }

    @Override
    public void exectteToDatabase() {
        DDHService service = new DDHService();
        service.delete(donDatHang.getMaDDH());
    }

}
