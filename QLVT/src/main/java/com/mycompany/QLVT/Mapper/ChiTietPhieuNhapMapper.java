/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class ChiTietPhieuNhapMapper implements RowMapper<ChiTietPhieuNhap> {

    @Override
    public ChiTietPhieuNhap mapRow(ResultSet rs) {
         ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        try {
            ctpn.setMaPN(rs.getString(1));
            ctpn.setMaVT(rs.getString(2));
            ctpn.setTenVT(rs.getString(3));
            ctpn.setSoLuong(rs.getInt(4));
            ctpn.setDonGia(rs.getFloat(5));
        } catch (SQLException ex) {
            System.out.println("MAPPER ERROR");
            Logger.getLogger(ChiTietPhieuNhapMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctpn;
    }

}
