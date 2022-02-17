/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.SoLuongTonKho;
import com.mycompany.QLVT.service.KhoService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class SoLuongTonKhoMapper implements RowMapper<SoLuongTonKho> {

    @Override
    public SoLuongTonKho mapRow(ResultSet rs) {
        try {
            SoLuongTonKho obj = new SoLuongTonKho();
            obj.setKho(rs.getString(1));
            obj.setSoLuongTon(rs.getInt(2));
            return obj;
        } catch (SQLException e) {
            Logger.getLogger(SoLuongTonKhoMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
