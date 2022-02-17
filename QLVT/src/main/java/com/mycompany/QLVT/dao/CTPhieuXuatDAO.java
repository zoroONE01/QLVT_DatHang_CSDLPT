/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.Mapper.CTDDHMapper;
import com.mycompany.QLVT.Mapper.CTPhieuXuatMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class CTPhieuXuatDAO extends AbstractDAO<CTPhieuXuat> {

    public List<CTPhieuXuat> findAll() {
        return queryProcedure("{call SP_DS_CTPhieuXuat}", new CTPhieuXuatMapper());
    }

//    public List<CTPhieuXuat> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
//    }
//
    public List<CTPhieuXuat> findOne(String id) {
        List<CTPhieuXuat> list = queryProcedure("{call spShowDSVTPhieuXuat(?)}", new CTPhieuXuatMapper(), id);
        return list.isEmpty() ? null : list;
    }

//    public int checkExist(String value, String type) {
//        return queryReturnOfProcedure("{call spCheckID(?,?)}", value, type);
//    }
    public int delete(String id) {
        return updateProcedure("{call SP_Xoa_CTPhieuXuat(?)}", id);
    }
    public int insert(PhieuXuat phieuXuat, ItemVatTu itemVatTu) {
        return insert("{call SP_Tao_CTPhieuXuat(?,?,?,?)}", phieuXuat.getMaPhieuXuat(), itemVatTu.getMaVT(), itemVatTu.getSoLuong(), itemVatTu.getDonGia());
    }

//    public int update(DDH ddh, ItemVatTu itemVatTu) {
//        return insert("{call SP_CapNhat_CTPhieuXuat(?,?,?,?)}", ddh.getMaDDH(), itemVatTu.getMaVT(), itemVatTu.getSoLuong(), itemVatTu.getDonGia());
//    }
}
