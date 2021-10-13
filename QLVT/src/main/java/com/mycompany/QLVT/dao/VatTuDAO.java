/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.Mapper.VatTuMapper;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class VatTuDAO extends AbstractDAO<VatTu> {

    public List<VatTu> findAll() {
        return queryProcedure("exec spShowVatTu", new VatTuMapper());
    }

    public int checkExist(String value, String type) {
        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
    }

    public VatTu findOne(String id) {
        List<VatTu> listVatTu = queryProcedure("{call spSearchVatTu(?)}", new VatTuMapper(), id);
        return listVatTu.isEmpty() ? null : listVatTu.get(0);
    }

    public void delete(String id) {
        update("{call spDeleteVatTu(?)}", id);
    }

    public void insert(VatTu vatTu) {
        insert("{call spInsertVatTu(?,?,?)}", vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getDVT());

    }

    public void update(VatTu vatTu) {
        update("{call spUpdateVatTu(?,?,?)}", vatTu.getMaVT(), vatTu.getTenVT(), vatTu.getDVT());

    }
}
