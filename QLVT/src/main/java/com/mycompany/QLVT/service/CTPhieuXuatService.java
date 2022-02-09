/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.dao.CTPhieuXuatDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class CTPhieuXuatService {

    CTPhieuXuatDAO ctPhieuXuatDAO = new CTPhieuXuatDAO();

    public List<CTPhieuXuat> findAll() {
        return ctPhieuXuatDAO.findAll();
    }

    public List<CTPhieuXuat> findOne(String id) {
        return ctPhieuXuatDAO.findOne(id);
    }

    public int delete(String id) {
        return ctPhieuXuatDAO.delete(id);
    }

//    public int checkExist(String value, String type) {
//        return ctDonDatHangDAO.checkExist(value, type);
//    }
    public int insert(PhieuXuat phieuXuat, ItemVatTu itemVatTu) {
        return ctPhieuXuatDAO.insert(phieuXuat, itemVatTu);
    }

//    public int update(DDH ddh, ItemVatTu itemVatTu) {
//        return ctDonDatHangDAO.update(ddh, itemVatTu);
//    }
}
