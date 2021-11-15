/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.dao.ChiTietPhieuNhapDAO;
import com.mycompany.QLVT.dao.PhieuNhapDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class ChiTietPhieuNhapService {

    ChiTietPhieuNhapDAO phieuNhapDAO = new ChiTietPhieuNhapDAO();

    public List< ChiTietPhieuNhap> findAll() {
        return phieuNhapDAO.findAll();
    }

    public List<ChiTietPhieuNhap> findAllOthersite() {
        return phieuNhapDAO.findAllOtherSite();
    }

    public ChiTietPhieuNhap findOne(int id) {
        return phieuNhapDAO.findOne(id);
    }

    public int delete(int id) {
        return phieuNhapDAO.delete(id);
    }

    public int isExist(String pn,String vt) {
        return phieuNhapDAO.isExist(pn,vt);
    }

    public int save(ChiTietPhieuNhap nv) {
        return phieuNhapDAO.insert(nv);
    }

    public void update(ChiTietPhieuNhap nv) {
        phieuNhapDAO.update(nv);
    }
     public List<ChiTietPhieuNhap> findByMAPN(String maPN)
    {
        return phieuNhapDAO.findByMAPN(maPN);
    }
}
