/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.dao.NhanVienDAO;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class NhanVienService{
    NhanVienDAO nhanVienDAO=new NhanVienDAO();
    
   public List<NhanVien> findAll()
           {
               return nhanVienDAO.findAll();
           }
   
     public NhanVien findOne(int id)
           {
               return nhanVienDAO.findOne(id);
           }
     
       public void delete(int id)
           {
               nhanVienDAO.delete(id);
           }
          
       public void save(NhanVien nv)
           {
               nhanVienDAO.save(nv);
           }
       public void update(NhanVien nv)
           {
               nhanVienDAO.update(nv);
           }
}
