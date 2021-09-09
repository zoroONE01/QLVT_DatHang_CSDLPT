/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.service.NhanVienService;
import java.awt.event.ActionListener;

/**
 *
 * @author MinhTo
 */
public class ActionEdit extends ActionListenerCommand{
    NhanVienService nhanVienService;
    NhanVien nhanVien;

    public ActionEdit(NhanVienService nhanVienService, NhanVien nhanVien, String type) {
        super(type);
        this.nhanVienService = nhanVienService;
        this.nhanVien = nhanVien;
    }
    
    @Override
    public boolean execute() {
        NhanVien nv=nhanVienService.findOne(nhanVien.getMaNhanVien());
        nhanVienService.update(nhanVien);
        if(nhanVienService.findOne(nhanVien.getMaNhanVien())!=null)
            {
                backup(nv);
                return true;
            }
        return false;
    }
    
}
