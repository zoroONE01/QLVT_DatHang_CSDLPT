/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.DonDatHang;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Mapper.DonDatHangMapper;
import com.mycompany.QLVT.Mapper.NhanVienMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class DonDatHangDAO extends AbstractDAO<DonDatHang> {

    public List<DonDatHang> findAll() {
        return queryProcedure("{call SP_DS_DHH(?)}", new DonDatHangMapper());
    }

//    public List<DonDatHang> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
//    }
//
    public DonDatHang findOne(String ma) {
        List<DonDatHang> list=queryProcedure("{call SP_Tim_DHH(?)}", new DonDatHangMapper(),ma);
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

    public int save(DonDatHang nv) {
      //  return insert("{call SP_CapNhat_DHH(?,?,?)}",nv.getMaDHH(),nv.getNhaCC(),nv.getKho().getMaKho() );
      return 0;
    }

    public int update(DonDatHang nv) {
    return insert("{call SP_CapNhat_DHH(?,?,?)}",nv.getMaDHH(),nv.getNhaCC(),nv.getKho().getMaKho() );

    }
}
