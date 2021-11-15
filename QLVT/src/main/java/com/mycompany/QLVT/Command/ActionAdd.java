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
public class ActionAdd extends ActionListenerCommand<NhanVien> {

    NhanVienService nhanVienService;
    NhanVien nhanVien;

    public ActionAdd(NhanVienService nhanVienService, NhanVien nhanVien, String type) {
        super(type);
        this.nhanVienService = nhanVienService;
        this.nhanVien = nhanVien;
    }

    @Override
    public boolean execute() {
      int nv = nhanVienService.isExist(nhanVien.getMaNhanVien());
        if (nv==0) 
        {
            int rs=nhanVienService.save(nhanVien);
            if (rs>0){
                backup(nhanVien);
                return true;
            } 
        }
//        else{
//         nhanVienService.update(nhanVien);
//            if(nhanVienService.findOne(nhanVien.getMaNhanVien())!=null)
//            {
//                backup(nv);
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public void backup(NhanVien t) {
      this.nhanVien=t;
    }

    @Override
    public NhanVien undo() {
        return nhanVien;
    }
}
