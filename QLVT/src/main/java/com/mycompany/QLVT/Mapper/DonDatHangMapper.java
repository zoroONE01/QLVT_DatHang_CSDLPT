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
 * @author MinhTo
 */
public class DonDatHangMapper implements RowMapper<DDH>{

    @Override
    public DDH mapRow(ResultSet rs) {
        try {
            DDH ddh=new DDH();
            ddh.setMaDDH(rs.getString(1));
            ddh.setNgay(FomaterDate.convertDateToString(rs.getDate(2)));
            ddh.setNCC(rs.getString(3));
            ddh.setMaNV(rs.getInt(4));
            ddh.setMaKho(rs.getString(7));
            return ddh;
        } catch (SQLException ex) {
            Logger.getLogger(DonDatHangMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
