/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.service.KhoService;

/**
 *
 * @author zoroONE01
 */
public class KhoDatabaseUpdate extends KhoCommand {

    private Kho kho;

    public KhoDatabaseUpdate() {
        super();
    }

    public KhoDatabaseUpdate(Kho kho) {
        this.kho = kho;
    }

    @Override
    public void execute() {
        KhoService service = new KhoService();
        service.update(kho);
    }

    @Override
    public void unExecute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
