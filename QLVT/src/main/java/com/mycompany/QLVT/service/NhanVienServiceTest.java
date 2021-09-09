/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.service;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.dao.NhanVienDaoTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class NhanVienServiceTest {
    
    
     private NhanVienDaoTest nhanVienDao;
       
    
     public NhanVienServiceTest() {
        nhanVienDao=new NhanVienDaoTest();
    }
      public List<NhanVien> findAll()
            {   
                
                return nhanVienDao.findAll();
            }
    public NhanVien findOne(NhanVien nv)
            {       
                return nhanVienDao.find(nv);
            }
      public int save(NhanVien nv)
            {
                NhanVien nvNew=new NhanVien(nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());
                return nhanVienDao.save(nvNew);
            }
      public boolean delete(NhanVien nv)
            {
                return nhanVienDao.delete(nv);
            }
      public int update(NhanVien nv)
            {
                return nhanVienDao.save(nv);
            }
}
