/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class CTPhieuXuatMapper implements RowMapper<CTPhieuXuat> {

    @Override
    public CTPhieuXuat mapRow(ResultSet rs) {
        try {
            CTPhieuXuat ctPhieuXuat = new CTPhieuXuat();
            ctPhieuXuat.setMaPhieuXuat(rs.getString(1));
            ctPhieuXuat.setMaVT(rs.getString(2));
            ctPhieuXuat.setSoLuong(rs.getInt(3));
            ctPhieuXuat.setDonGia(rs.getFloat(4));
            return ctPhieuXuat;
        } catch (SQLException e) {
            Logger.getLogger(CTPhieuXuatMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
