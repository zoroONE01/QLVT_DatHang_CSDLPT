/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class KhoMapper implements RowMapper<Kho> {

    @Override
    public Kho mapRow(ResultSet rs) {
        try {
            Kho kho = new Kho();
            kho.setMaKho(rs.getString(1));
            kho.setTenKho(rs.getString(2));
            kho.setDiaChi(rs.getString(3));
            kho.setMaCN(rs.getString(4));
            kho.setTenCN(rs.getString(5));
            return kho;
        } catch (SQLException ex) {
            System.out.println("");
            Logger.getLogger(KhoMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
