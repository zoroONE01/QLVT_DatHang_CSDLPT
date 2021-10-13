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
public class DDHUpdate extends DDHCommand {

    private DDH oldDDH;
    private DDH newDDH;

    public DDHUpdate() {
        super();
    }

    public DDHUpdate(List<DDH> currentList, DDH oldDDH, DDH newDDH) {
        super(currentList);
        this.oldDDH = oldDDH;
        this.newDDH = newDDH;
    }

    @Override
    public void execute() {
        listBackup = new ArrayList<>();
        list.forEach(ddh -> {
            listBackup.add(ddh);
        });

        list.set(list.indexOf(oldDDH), newDDH);

    }

    @Override
    public void unExecute() {

        this.list = listBackup;

    }

    @Override
    public String toString() {
        return "Update " + oldDDH.getMaDDH();
    }

    @Override
    public void exectteToDatabase() {
//        DDHService service = new DDHService();
//        service.update(newDDH);
    }
}
