/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.SoLuongTonKho;
import com.mycompany.QLVT.Mapper.SoLuongTonKhoMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class SoLuongTonKhoDAO extends AbstractDAO<SoLuongTonKho> {

//    public List<DonDatHang> findAll() {
//        return queryProcedure("{call SP_DS_DHH}", new DonDatHangMapper());
//            }
    public List<SoLuongTonKho> findAll() {
        return queryProcedure("{call spFindSoLuongTonKho}", new SoLuongTonKhoMapper());

    }

//    public List<DDH> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
//
    public List<SoLuongTonKho> findSoLuongTonKho(String id) {
        List<SoLuongTonKho> list = queryProcedure("{call spFindSoLuongTonKho(?)}", new SoLuongTonKhoMapper(), id);
        for (SoLuongTonKho soLuongTonKho : list) {
            soLuongTonKho.setKho("[" + soLuongTonKho.getKho() + "]" + new KhoDAO().findOne(soLuongTonKho.getKho()).getTenKho());
        }
        return list.isEmpty() ? null : list;
    }

    public int checkExist(String value, String type) {
        System.out.println(queryReturnOfProcedure("{call spCheckID(?,?)}", value, type));
        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
    }

    public int delete(String id) {
        return updateProcedure("{call SP_Xoa_DHH(?)}", id);
    }

    public int insert(DDH ddh) {
        System.out.println(ddh.toString());
        return insert("{call spInsertDDH(?,?,?,?,?)}", ddh.getMaDDH(), ddh.getNgay(), ddh.getNCC(), ddh.getMaNV(), ddh.getMaKho());
    }

    public int update(DDH ddh) {
        System.out.println(ddh.toString());
        return updateProcedure("{call SP_CapNhat_DDH(?,?,?)}", ddh.getMaDDH(), ddh.getNCC(), ddh.getMaKho());
    }
}
