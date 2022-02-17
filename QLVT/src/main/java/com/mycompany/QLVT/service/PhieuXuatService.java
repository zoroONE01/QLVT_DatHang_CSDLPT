/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.dao.PhieuXuatDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class PhieuXuatService {

    PhieuXuatDAO phieuXuatDAO = new PhieuXuatDAO();

    public List<PhieuXuat> findAll() {
        return phieuXuatDAO.findAll();
    }

    public PhieuXuat findOne(String id) {
        return phieuXuatDAO.findOne(id);
    }

    public int delete(String id) {
        return phieuXuatDAO.delete(id);
    }

    public int checkExist(String value, String type) {
        return phieuXuatDAO.checkExist(value, type);
    }

    public void insert(PhieuXuat PhieuXuat, List<ItemVatTu> listItemVatTu) {
        CTPhieuXuatService ctdPhieuXuatService = new CTPhieuXuatService();
        phieuXuatDAO.insert(PhieuXuat);
        listItemVatTu.forEach(itemVatTu -> {
            ctdPhieuXuatService.insert(PhieuXuat, itemVatTu);
        });
    }

    public void update(PhieuXuat PhieuXuat, List<ItemVatTu> listItemVatTu) {
        CTPhieuXuatService ctdPhieuXuatService = new CTPhieuXuatService();
        phieuXuatDAO.update(PhieuXuat);
        ctdPhieuXuatService.delete(PhieuXuat.getMaPhieuXuat());
        listItemVatTu.forEach(itemVatTu -> {
            System.out.println(itemVatTu.toString());
            ctdPhieuXuatService.insert(PhieuXuat, itemVatTu);
        });
    }
}
