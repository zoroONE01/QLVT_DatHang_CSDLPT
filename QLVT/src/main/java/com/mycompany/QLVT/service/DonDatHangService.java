/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.DonDatHang;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.dao.DonDatHangDAO;
import com.mycompany.QLVT.dao.NhanVienDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class DonDatHangService {

    DonDatHangDAO objectDAO = new DonDatHangDAO();

    public List<DonDatHang> findAll() {
        return objectDAO.findAll();
    }

    public DonDatHang findOne(String id) {
        return objectDAO.findOne(id);
    }

    public int delete(String id) {
        return objectDAO.delete(id);
    }

    public int isExist(int id) {
        return objectDAO.isExist(id);
    }

    public int save(DonDatHang nv) {
        return objectDAO.save(nv);
    }

    public void update(DonDatHang nv) {
        objectDAO.update(nv);
    }
}
