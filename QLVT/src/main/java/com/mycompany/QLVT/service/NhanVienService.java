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
     
       public int delete(int id)
           {
               return nhanVienDAO.delete(id);
           }
          
       public int isExist(int id)
       {
           return nhanVienDAO.isExist(id);
       }
       public int save(NhanVien nv)
           {
               return nhanVienDAO.save(nv);
           }
       public void update(NhanVien nv)
           {
               nhanVienDAO.update(nv);
           }
}
