/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
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

    public int checkExist(String value, String type) {
        return ddhDAO.checkExist(value, type);
    }
    public List<DDH> findAllForPhieuNhap(){
        return ddhDAO.findAllForPhieuNhap();
    }
    public void insert(DDH ddh, List<ItemVatTu> listItemVatTu) {
        CTDDHService ctdddhService = new CTDDHService();
        ddhDAO.insert(ddh);
        listItemVatTu.forEach(itemVatTu -> {
            ctdddhService.insert(ddh, itemVatTu);
        });
    }

    public void update(DDH ddh, List<ItemVatTu> listItemVatTu) {
        CTDDHService ctdddhService = new CTDDHService();
        ddhDAO.update(ddh);
        ctdddhService.delete(ddh.getMaDDH());
        listItemVatTu.forEach(itemVatTu -> {
            System.out.println(itemVatTu.toString());
            ctdddhService.insert(ddh, itemVatTu);
        });
    }
}
