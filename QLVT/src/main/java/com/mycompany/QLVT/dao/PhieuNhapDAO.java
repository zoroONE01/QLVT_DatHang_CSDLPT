/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Mapper.ChiTietPhieuNhapMapper;
import com.mycompany.QLVT.Mapper.PhieuNhapMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class PhieuNhapDAO extends AbstractDAO<PhieuNhap> {

    public List<PhieuNhap> findAll() {
        return queryProcedure("exec SP_DSPhieuNhap", new PhieuNhapMapper());
    }

    public List<PhieuNhap> findAllOtherSite() {
        return queryProcedure("exec LINK1.QLVT.DBO.SP_DSPhieuNhap", new PhieuNhapMapper());
    }

    public PhieuNhap findOne(int id) {
        List<PhieuNhap> listNV = queryProcedure("{call SP_LayPhieuNhap(?)}", new PhieuNhapMapper(), id);
        return listNV.isEmpty() ? null : listNV.get(0);
    }

    public PhieuNhap findOneByMaDon(String maDon) {
        List<PhieuNhap> listPn = queryProcedure("{CALL SP_TIM_PN_BY_MADON(?)}", new PhieuNhapMapper(), maDon);
        return listPn.isEmpty() ? null : listPn.get(0);
    }
     public List<ChiTietPhieuNhap> findChiTietPhieuNhapByMAPN(String maPN) {
        List<ChiTietPhieuNhap> listPn = queryProcedure("{CALL SP_TIM_CTPN_BY_MAPN(?)}", new ChiTietPhieuNhapMapper(), maPN);
        return listPn;
    }
    public int isExist(String id) {
//        List<Integer> listNV = (List<Integer>) queryProcedure("{call SP_CHECK_TRACUU(?,?)}", new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs) {
//                try {
//                    int i = rs.getInt(1);
//                    return i;
//                } catch (SQLException ex) {
//                    Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
//                    return 0;
//                }
//            }
//        }, id, "PhieuNhap");
//        return listNV.isEmpty() ? 0 : listNV.get(1);
        int b = queryReturnOfProcedure("{call SP_CHECK_TRACUU(?,?)}", id, "PhieuNhap");
        return b;
    }

    public int delete(int id) {
        return updateProcedure("{call SP_XOA_NHANVIEN(?)}", id);
    }

    public int insert(PhieuNhap pn) {
            return insert("{call SP_TAO_PN(?,?,?,?,?)}",pn.getMaPN(),pn.getNgay(),pn.getMaDDH(),pn.getMaNhanVien(),pn.getMaKhoa());
            
    }

    public int update(PhieuNhap pn) {
        return update("{call SP_CAPNHAT_PN(?,?,?,?,?)}",pn.getMaPN(),pn.getNgay(),pn.getMaDDH(),pn.getMaNhanVien(),pn.getMaKhoa());
    }
   public int kiemTraSoLuongVatTu(String maVatTu)
   {    
       return executeStamentAndGetReturn("{call SP_KIEMTRA_SOLUONG_VATTU(?,?)}",maVatTu,"");
      
   }
   public int capNhatSoLuongVatTu(String maVT,int soLuong)
   { 
       return update("{call  SP_CAPNHAT_SOLUONG_VATTU(?,?)}",maVT,soLuong );
   }
   public List<PhieuNhap> findByMaPhieuNhapStartingWith(String maVT)
   { 
       List<PhieuNhap> phieuNhaps=queryProcedure("{call SP_LayPhieuNhapStartingWith(?)}",new PhieuNhapMapper(), maVT);
       return phieuNhaps;
   }
}
