/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Command;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.service.ChiTietPhieuNhapService;

/**
 *
 * @author MinhTo
 */
public class ActionAddCTPN extends ActionListenerCommand<ChiTietPhieuNhap>{
    ChiTietPhieuNhap ctpn;
    ChiTietPhieuNhapService ctpnService;

    public ActionAddCTPN(ChiTietPhieuNhap ctpn, ChiTietPhieuNhapService ctpnService, String type) {
        super(type);
        this.ctpn = ctpn;
        this.ctpnService = ctpnService;
    }
    
    @Override
    public boolean execute() {
        int isExist=ctpnService.isExist(ctpn.getMaPN(), ctpn.getMaVT());
        if(isExist==0){
            int rs=ctpnService.save(ctpn);
            if(rs>0){
                backup(ctpn);
                return true;
            }
        }
        return false;
    }
    
    
}
