/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class DDHMapper implements RowMapper<DDH> {

    @Override
    public DDH mapRow(ResultSet rs) {
        try {
            DDH donDatHang = new DDH();
            donDatHang.setMaDDH(rs.getString(1));
            donDatHang.setNgay(FomaterDate.convertDateToString(rs.getDate(2)));
            donDatHang.setNCC(rs.getString(3));
            donDatHang.setMaNV(rs.getInt(4));
            donDatHang.setMaKho(rs.getString(5));
            return donDatHang;
        } catch (SQLException e) {
            Logger.getLogger(DDHMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
