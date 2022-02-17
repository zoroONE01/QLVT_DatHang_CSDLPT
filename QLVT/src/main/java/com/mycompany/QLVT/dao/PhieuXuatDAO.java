/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.Mapper.PhieuXuatMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class PhieuXuatDAO extends AbstractDAO<PhieuXuat> {


//    public List<DonDatHang> findAll() {
//        return queryProcedure("{call SP_DS_DHH}", new DonDatHangMapper());
//            }
    public List<PhieuXuat> findAll() {
        return queryProcedure("{call SP_DS_PhieuXuat}", new PhieuXuatMapper());

    }

//    public List<PhieuXuat> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
   
//
    public PhieuXuat findOne(String id) {
        List<PhieuXuat> list = queryProcedure("{call SP_Tim_PhieuXuat(?)}", new PhieuXuatMapper(), id);
        return list.isEmpty() ? null : list.get(0);
    }

    public int checkExist(String value, String type) {
        System.out.println(queryReturnOfProcedure("{call spCheckID(?,?)}", value, type));
        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
    }

    public int delete(String id) {
        return updateProcedure("{call SP_Xoa_PhieuXuat(?)}", id);
    }

    
    public int insert(PhieuXuat phieuXuat) {
        return insert("{call spInsertPhieuXuat(?,?,?,?,?)}", phieuXuat.getMaPhieuXuat(), phieuXuat.getNgay(), phieuXuat.getKhachHang(), phieuXuat.getMaNV(), phieuXuat.getMaKho());
    }

    public int update(PhieuXuat PhieuXuat) {
        System.out.println(PhieuXuat.toString());
        return updateProcedure("{call SP_CapNhat_PhieuXuat(?,?,?)}", PhieuXuat.getMaPhieuXuat(), PhieuXuat.getKhachHang(), PhieuXuat.getMaKho());
    }
}
