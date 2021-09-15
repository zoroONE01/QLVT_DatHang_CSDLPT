/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.dao.VatTuDAO;
import java.util.List;

/**
 *
 * @author zoroONE01
 */
public class VatTuService {

    VatTuDAO vatTuDAO = new VatTuDAO();

    public VatTuService() {
    }

    public List<VatTu> findAll() {
        return vatTuDAO.findAll();
    }

    public VatTu findOne(String id) {
        return vatTuDAO.findOne(id);
    }

    public int checkExist(String value, String type) {
        return vatTuDAO.checkExist(value, type);
    }

    public void delete(String id) {
        vatTuDAO.delete(id);
    }

    public void save(VatTu vatTu) {
        vatTuDAO.save(vatTu);
    }

    public void update(VatTu vatTu) {
        vatTuDAO.update(vatTu);
    }
}
