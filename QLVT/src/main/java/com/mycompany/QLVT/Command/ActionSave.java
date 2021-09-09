/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.service.NhanVienService;

/**
 *
 * @author MinhTo
 */
public class ActionSave extends ActionListenerCommand{
      NhanVienService nhanVienService;
      NhanVien nhanVien;

    public ActionSave(NhanVienService nhanVienService, NhanVien nhanVien, String type) {
        super(type);
        this.nhanVienService = nhanVienService;
        this.nhanVien = nhanVien;
    }
     
     
    @Override
    public void execute() {
     //  NhanVien nv=nhanVienService.findOne(nhanVien);
       if(nhanVienService.save(nhanVien)!=-1)
            {
                backup(nhanVien);
            }
    }

      
    
}
