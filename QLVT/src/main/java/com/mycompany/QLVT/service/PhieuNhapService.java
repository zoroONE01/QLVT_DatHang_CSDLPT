/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
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

    public int update(PhieuNhap nv) {
        return phieuNhapDAO.update(nv);
    }
     public PhieuNhap findOneByMaDon(String maDon)
    {
        return phieuNhapDAO.findOneByMaDon(maDon);
    }
     public List<ChiTietPhieuNhap> findChiTietPhieuNhapByMAPN(String maPN)
    {
        return phieuNhapDAO.findChiTietPhieuNhapByMAPN(maPN);
    }
     
     public int kiemTraSoLuongVatTu(String maVatTu)
   {    
        return phieuNhapDAO.kiemTraSoLuongVatTu(maVatTu);
      
   }
 
     public int capNhatSoLuongVatTu(String maVT,int soLuong){
         return phieuNhapDAO.capNhatSoLuongVatTu(maVT, soLuong);
     }
     
     public List<PhieuNhap> findByMaPhieuNhapStartingWith(String maVT)
   { 
      List<PhieuNhap> phieuNhaps=phieuNhapDAO.findByMaPhieuNhapStartingWith(maVT);
      return phieuNhaps.isEmpty()? null:phieuNhaps;
   }
     
}
