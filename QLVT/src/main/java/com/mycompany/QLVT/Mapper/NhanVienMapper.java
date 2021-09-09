/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class NhanVienMapper implements RowMapper<NhanVien>{

    @Override
    public NhanVien mapRow(ResultSet rs) {
        try {
            NhanVien nhanVien=new NhanVien();
            nhanVien.setMaNhanVien(rs.getInt(1));
            nhanVien.setHo(rs.getString(2));
            nhanVien.setTen(rs.getString(3));
            nhanVien.setNgaySinh(FomaterDate.convertDateToString(rs.getDate(4)));
            nhanVien.setDiaChi(rs.getString(5));
            nhanVien.setLuong(rs.getFloat(6));
            nhanVien.setMaCN(rs.getString(7));
            return nhanVien;
        } catch (SQLException ex) {
            System.out.println("");
            Logger.getLogger(NhanVienMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
