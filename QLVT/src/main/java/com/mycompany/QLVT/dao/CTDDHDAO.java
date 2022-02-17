/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Mapper.CTDDHMapper;
import com.mycompany.QLVT.Mapper.ChiTietDatHangMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class CTDDHDAO extends AbstractDAO<CTDDH> {

    public List<CTDDH> findAll() {
        return queryProcedure("{call SP_DS_CTDHH}", new CTDDHMapper());
    }

//    public List<CTDDH> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
//    }
//
    public List<CTDDH> findOne(String id) {
        List<CTDDH> list = queryProcedure("{call spShowDSVatTuDatHang(?)}", new CTDDHMapper(), id);
        return list.isEmpty() ? null : list;
    }
    public List<CTDDH> findAllByMaDon(String id)
    {
        List<CTDDH> list = queryProcedure("{call SP_DS_CTDDH_BY_MADON(?)}", new ChiTietDatHangMapper(), id);
        return list.isEmpty() ? null : list;
    }
     public List<CTDDH> findAllByMaDonNotCTPN(String id)
    {
        List<CTDDH> list = queryProcedure("{call SP_DS_CTDDH_PN_BY_MADON(?)}", new ChiTietDatHangMapper(), id);
        return list.isEmpty() ? null : list;
    }
//    public int checkExist(String value, String type) {
//        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
//    }
    public int delete(String id) {
        return updateProcedure("{call SP_Xoa_CTDDH(?)}", id);
    }
    public int insert(DDH ddh, ItemVatTu itemVatTu) {
        return insert("{call SP_Tao_CTDonDatHang(?,?,?,?)}", ddh.getMaDDH(), itemVatTu.getMaVT(), itemVatTu.getSoLuong(), itemVatTu.getDonGia());
    }

//    public int update(DDH ddh, ItemVatTu itemVatTu) {
//        return insert("{call SP_CapNhat_CTDDH(?,?,?,?)}", ddh.getMaDDH(), itemVatTu.getMaVT(), itemVatTu.getSoLuong(), itemVatTu.getDonGia());
//    }
}
