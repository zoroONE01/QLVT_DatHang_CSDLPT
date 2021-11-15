/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class CTDDHMapper implements RowMapper<CTDDH> {

    @Override
    public CTDDH mapRow(ResultSet rs) {
        try {
            CTDDH ctDonDatHang = new CTDDH();
            ctDonDatHang.setMaSoDDH(rs.getString(1));
            ctDonDatHang.setMaVT(rs.getString(2));
            ctDonDatHang.setSoLuong(rs.getInt(3));
            ctDonDatHang.setDonGia(rs.getFloat(4));
            return ctDonDatHang;
        } catch (SQLException e) {
            Logger.getLogger(CTDDHMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
