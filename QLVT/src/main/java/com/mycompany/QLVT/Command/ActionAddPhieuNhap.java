/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.service.PhieuNhapService;

/**
 *
 * @author MinhTo
 */
public class ActionAddPhieuNhap extends ActionListenerCommand<PhieuNhap>{

    private PhieuNhap phieuNhapBackup;
    private PhieuNhapService phieuNhapService;

    public ActionAddPhieuNhap(PhieuNhap phieuNhapBackup, PhieuNhapService phieuNhapService, String type) {
        super(type);
        this.phieuNhapBackup = phieuNhapBackup;
        this.phieuNhapService = phieuNhapService;
    }

    @Override
    public void backup(PhieuNhap pn) {
        this.phieuNhapBackup = pn;
    }

    @Override
    public PhieuNhap undo() {
        return this.phieuNhapBackup;

    }

    @Override
    public boolean execute() {
        int nv = phieuNhapService.isExist(phieuNhapBackup.getMaPN());
        if (nv == 0) {
            int rs = phieuNhapService.save(phieuNhapBackup);
            if (rs >0) {
                backup(phieuNhapBackup);
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

}
