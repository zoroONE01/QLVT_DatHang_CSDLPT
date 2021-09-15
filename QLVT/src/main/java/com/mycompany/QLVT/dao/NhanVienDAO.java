/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Mapper.NhanVienMapper;
import com.mycompany.QLVT.Mapper.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class NhanVienDAO extends AbstractDAO<NhanVien> {

    public List<NhanVien> findAll() {
        return queryProcedure("exec SP_DSNhanVien", new NhanVienMapper());
    }

    public List<NhanVien> findAllOtherSite() {
        return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
    }

    public NhanVien findOne(int id) {
        List<NhanVien> listNV = queryProcedure("{call SP_LayNhanVien(?)}", new NhanVienMapper(), id);
        return listNV.isEmpty() ? null : listNV.get(0);
    }

    public int isExist(int id) {
//        List<Integer> listNV = (List<Integer>) queryProcedure("{call SP_CHECK_TRACUU(?,?)}", new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs) {
//                try {
//                    int i = rs.getInt(1);
//                    return i;
//                } catch (SQLException ex) {
//                    Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    return 0;
//                }
//            }
//        }, id, "NhanVien");
//        return listNV.isEmpty() ? 0 : listNV.get(1);
        int b = queryReturnOfProcedure("{call SP_CHECK_TRACUU(?,?)}", id, "NhanVien");
        return b;
    }

    public int delete(int id) {
        return updateProcedure("{call SP_XOA_NHANVIEN(?)}", id);
    }

    public int save(NhanVien nv) {
        return insert("{call SP_Them_NhanVien(?,?,?,?,?,?,?)}", nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());

    }

    public int update(NhanVien nv) {
        return update("{call SP_CapNhat_NhanVien(?,?,?,?,?,?,?,?)}", nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN(), nv.getTrangThai());

    }

    public int chuyenChiNhanh(int id, String maChiNhanhNew) {
        return updateProcedure("{call SP_ChuyenChiNhanh(?,?)}", id, maChiNhanhNew);
    }
}
