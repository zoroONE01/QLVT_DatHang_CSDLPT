/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.dao.CTDDHDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class CTDDHService {

    CTDDHDAO ctDonDatHangDAO = new CTDDHDAO();

    public List<CTDDH> findAll() {
        return ctDonDatHangDAO.findAll();
    }

    public List<CTDDH> findOne(String id) {
        return ctDonDatHangDAO.findOne(id);
    }

    public int delete(String id) {
        return ctDonDatHangDAO.delete(id);
    }
    public List<CTDDH> findAllByMaDon(String id)
    {
        return ctDonDatHangDAO.findAllByMaDon(id);
    }

//    public int checkExist(String value, String type) {
//        return ctDonDatHangDAO.checkExist(value, type);
//    }

    public int insert(DDH ddh, ItemVatTu itemVatTu) {
        return ctDonDatHangDAO.insert(ddh, itemVatTu);
    }

//    public int update(DDH ddh, ItemVatTu itemVatTu) {
//        return ctDonDatHangDAO.update(ddh, itemVatTu);
//    }
}
