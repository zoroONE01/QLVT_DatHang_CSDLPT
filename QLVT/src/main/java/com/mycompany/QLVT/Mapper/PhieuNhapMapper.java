/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class PhieuNhapMapper implements RowMapper<PhieuNhap>{

    @Override
    public PhieuNhap mapRow(ResultSet rs) {
        PhieuNhap pn=new PhieuNhap();
        try {
           
            pn.setMaPN(rs.getString(1));
            pn.setNgay(FomaterDate.convertDateToString(rs.getDate(2)));
            pn.setMaDDH(rs.getString(3));
            pn.setMaNhanVien(rs.getInt(4));
            pn.setMaKhoa(rs.getString(5));
            
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pn;
    }
    
}
