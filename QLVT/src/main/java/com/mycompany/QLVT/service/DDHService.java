/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.dao.DDHDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class DDHService {

    DDHDAO ddhDAO = new DDHDAO();

    public List<DDH> findAll() {
        return ddhDAO.findAll();
    }

    public DDH findOne(String id) {
        return ddhDAO.findOne(id);
    }

    public int delete(String id) {
        return ddhDAO.delete(id);
    }

    public int isExist(int id) {
        return ddhDAO.isExist(id);
    }

    public int insert(DDH ddh) {
        return ddhDAO.insert(ddh);
    }

    public void update(DDH ddh) {
        ddhDAO.update(ddh);
    }
}
