/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class PhieuXuatMapper implements RowMapper<PhieuXuat> {
    
    @Override
    public PhieuXuat mapRow(ResultSet rs) {
        try {
            PhieuXuat phieuXuat = new PhieuXuat();
            phieuXuat.setMaPhieuXuat(rs.getString(1));
            phieuXuat.setNgay(FomaterDate.convertDateToString(rs.getDate(2)));
            phieuXuat.setKhachHang(rs.getString(3));
            phieuXuat.setMaNV(rs.getInt(4));
            phieuXuat.setHo(rs.getString(5));
            phieuXuat.setTen(rs.getString(6));
            phieuXuat.setMaKho(rs.getString(7));
            phieuXuat.setTenKho(rs.getString(8));
            phieuXuat.setMaNVHoTen();
            phieuXuat.setMaKhoTenKho();
            return phieuXuat;
        } catch (SQLException e) {
            Logger.getLogger(PhieuXuatMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
}
