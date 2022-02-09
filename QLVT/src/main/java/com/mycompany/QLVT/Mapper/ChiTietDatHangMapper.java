/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.CTDDH;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class ChiTietDatHangMapper implements RowMapper<CTDDH>{
    @Override
    public CTDDH mapRow(ResultSet rs) {
        try {
            CTDDH ctDonDatHang = new CTDDH();
            ctDonDatHang.setMaSoDDH(rs.getString(1));
            ctDonDatHang.setMaVT(rs.getString(2));
            ctDonDatHang.setTenVT(rs.getString(3));
            ctDonDatHang.setSoLuong(rs.getInt(4));
            ctDonDatHang.setDonGia(rs.getFloat(5));
            return ctDonDatHang;
        } catch (SQLException e) {
            Logger.getLogger(CTDDHMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
