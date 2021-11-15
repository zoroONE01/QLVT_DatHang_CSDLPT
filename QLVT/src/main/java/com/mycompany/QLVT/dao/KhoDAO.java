/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Mapper.KhoMapper;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class KhoDAO extends AbstractDAO<Kho> {

    public List<Kho> findAll() {
        return queryProcedure("{call spShowKho}", new KhoMapper());
    }

    public int checkExist(String value, String type) {
        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
    }

    

    public Kho findOne(String id) {
        List<Kho> listKho = queryProcedure("{call spSearchKho(?)}", new KhoMapper(), id);
        return listKho.isEmpty() ? null : listKho.get(0);
    }

    public void delete(String id) {
        update("{call spDeleteKho(?)}", id);
    }

    public void insert(Kho kho) {
        insert("{call spInsertKho(?,?,?,?)}", kho.getMaKho(), kho.getTenKho(), kho.getDiaChi(), kho.getMaCN());

    }

    public void update(Kho kho) {
        update("{call spUpdateKho(?,?,?)}", kho.getMaKho(), kho.getTenKho(), kho.getDiaChi());

    }
}
