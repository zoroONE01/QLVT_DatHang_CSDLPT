/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Mapper.NhanVienMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class NhanVienDAO extends AbstractDAO<NhanVien> {

    public List<NhanVien> findAll() {
        return queryProcedure("exec SP_DSNhanVien", new NhanVienMapper());
    }

    public NhanVien findOne(int id) {
        List<NhanVien> listNV = queryProcedure("{call SP_LayNhanVien(?)}", new NhanVienMapper(), id);
        return listNV.isEmpty() ? null : listNV.get(0);
    }

    public void delete(int id) {
        update("{call SP_XOA_NHANVIEN(?)}", id);
    }

    public void save(NhanVien nv) {
    insert("{call SP_Them_NhanVien(?,?,?,?,?,?,?)}", nv.getMaNhanVien(),nv.getHo(),nv.getTen(),nv.getDiaChi(),nv.getNgaySinh(),nv.getLuong(),nv.getMaCN());

    }
    public void update(NhanVien nv) {
    update("{call SP_CapNhat_NhanVien(?,?,?,?,?,?,?)}", nv.getMaNhanVien(),nv.getHo(),nv.getTen(),nv.getDiaChi(),nv.getNgaySinh(),nv.getLuong(),nv.getMaCN());

    }
}
