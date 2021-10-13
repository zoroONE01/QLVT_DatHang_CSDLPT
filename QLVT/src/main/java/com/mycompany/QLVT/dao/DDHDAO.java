/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Mapper.DDHMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class DDHDAO extends AbstractDAO<DDH> {


//    public List<DonDatHang> findAll() {
//        return queryProcedure("{call SP_DS_DHH}", new DonDatHangMapper());
//            }
    public List<DDH> findAll() {
        return queryProcedure("{call SP_DS_DHH}", new DDHMapper());

    }

//    public List<DDH> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
   
//
    public DDH findOne(String id) {
        List<DDH> list = queryProcedure("{call SP_Tim_DHH(?)}", new DDHMapper(), id);
        return list.isEmpty() ? null : list.get(0);
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
        int b = queryReturnOfProcedure("{call SP_CHECK_TRACUU(?,?)}", id, "DatHang");
        return b;
    }

    public int delete(String id) {
        return updateProcedure("{call SP_XOA_NHANVIEN(?)}", id);
    }

    public int insert(DDH ddh) {
        return insert("{call SP_CapNhat_DHH(?,?,?)}", ddh.getMaDDH(), ddh.getNCC(), ddh.getMaKho());
    }

    public int update(DDH ddh) {
        return insert("{call SP_CapNhat_DHH(?,?,?)}", ddh.getMaDDH(), ddh.getNCC(), ddh.getMaKho());

    }
}
