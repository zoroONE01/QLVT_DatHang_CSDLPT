/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Mapper.ChiTietPhieuNhapMapper;
import com.mycompany.QLVT.Mapper.PhieuNhapMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class ChiTietPhieuNhapDAO extends AbstractDAO<ChiTietPhieuNhapDAO> {

    public List<ChiTietPhieuNhap> findAll() {
        return queryProcedure("exec SP_DSChiTietPhieuNhap", new ChiTietPhieuNhapMapper());
    }

    public List<ChiTietPhieuNhap> findAllOtherSite() {
        return queryProcedure("exec LINK1.QLVT.DBO.SP_DSChiTietPhieuNhap", new ChiTietPhieuNhapMapper());
    }

    public ChiTietPhieuNhap findOne(int id) {
        List<ChiTietPhieuNhap> listNV = queryProcedure("{call SP_LayChiTietPhieuNhap(?)}", new ChiTietPhieuNhapMapper(), id);
        return listNV.isEmpty() ? null : listNV.get(0);
    }
      public List<ChiTietPhieuNhap> findByMAPN(String maPN) {
        List<ChiTietPhieuNhap> listPn = queryProcedure("{CALL SP_TIM_CTPN_BY_MAPN(?)}", new ChiTietPhieuNhapMapper(), maPN);
        return listPn;
    }

    public int isExist(String pn,String vt) {
//        List<Integer> listNV = (List<Integer>) queryProcedure("{call SP_CHECK_TRACUU(?,?)}", new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs) {
//                try {
//                    int i = rs.getInt(1);
//                    return i;
//                } catch (SQLException ex) {
//                    Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    return 0;
//                }
//            }
//        }, id, "ChiTietPhieuNhap");
//        return listNV.isEmpty() ? 0 : listNV.get(1);
        int b = queryReturnOfProcedure("{call SP_TIM_CTPN_BY_PN_VT(?,?)}",  pn,vt);
        return b;
    }

    public int delete(int id) {
        return updateProcedure("{call SP_XOA_NHANVIEN(?)}", id);
    }

    public int insert(ChiTietPhieuNhap nv) {
        return insert("{call SP_TAO_CTPN(?,?,?,?)}",nv.getMaPN(),nv.getMaVT(),nv.getSoLuong(),nv.getDonGia());
    }

    public int update(ChiTietPhieuNhap nv) {
        return 0;
    }

}
