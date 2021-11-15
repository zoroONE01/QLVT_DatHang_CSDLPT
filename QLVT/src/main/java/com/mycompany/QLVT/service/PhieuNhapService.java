/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Utils.FomaterDate;
import com.mycompany.QLVT.dao.PhieuNhapDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class PhieuNhapService {
    PhieuNhapDAO phieuNhapDAO=new PhieuNhapDAO();
    
    public List<PhieuNhap> findAll() {
        return phieuNhapDAO.findAll();
    }

    public List<PhieuNhap> findAllOthersite() {
        return phieuNhapDAO.findAllOtherSite();
    }

    public PhieuNhap findOne(int id) {
        return phieuNhapDAO.findOne(id);
    }

    public int delete(int id) {
        return phieuNhapDAO.delete(id);
    }

    public int isExist(String id) {
        return phieuNhapDAO.isExist(id);
    }

    public int save(PhieuNhap nv) {
      
        return phieuNhapDAO.insert(nv);
    }

    public void update(PhieuNhap nv) {
        phieuNhapDAO.update(nv);
    }
     public PhieuNhap findOneByMaDon(String maDon)
    {
        return phieuNhapDAO.findOneByMaDon(maDon);
    }
}
