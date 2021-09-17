/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.DonDatHang;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Utils.FomaterDate;
import java.sql.ResultSet;
import com.mycompany.QLVT.Entity.NhanVien;

/**
 *
 * @author MinhTo
 */
public class DonDatHangMapper implements RowMapper<DonDatHang> {

    @Override
    public DonDatHang mapRow(ResultSet rs) {
        DonDatHang dhh = new DonDatHang();
        NhanVien nv = new NhanVien();
        Kho kho = new Kho();
        try {
            dhh.setMaDHH(rs.getString(1));
            dhh.setNgay(FomaterDate.convertDateToString(rs.getDate(2)));
            dhh.setNhaCC(rs.getString(3));
            nv.setMaNhanVien(rs.getInt(4));
            nv.setTen(rs.getString(5));
            kho.setMaKho(rs.getString(6));
            kho.setTenKho(rs.getString(7));
            return dhh;
        } catch (Exception e) {

        }
        return null;
    }

}
