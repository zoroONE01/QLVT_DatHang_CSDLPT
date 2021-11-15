/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.service.NhanVienService;
import com.mycompany.QLVT.service.NhanVienServiceTest;

/**
 *
 * @author MinhTo
 */
public class ActionDelete extends ActionListenerCommand<NhanVien>{
    NhanVienService nhanVienService;
    NhanVien nhanVien;

    public ActionDelete(NhanVienService nhanVienService, NhanVien nhanVien, String type) {
        super(type);
        this.nhanVienService = nhanVienService;
        this.nhanVien = nhanVien;
    }
    
    @Override
    public boolean execute() {
       // NhanVien nv=nhanVienService.findOne(nhanVien.getMaNhanVien());
        int rs=nhanVienService.delete(nhanVien.getMaNhanVien());
         if(rs>0)
            {
                backup(nhanVien);
                return true;
            }
         return false;
    }

  

    
    
}
