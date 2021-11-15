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

    public List<DDH> findAll() {
        return queryProcedure("{call SP_DS_DHH}", new DDHMapper());
    }

//    public List<DDH> findAllOtherSite() {
//       // return queryProcedure("exec LINK1.QLVT.DBO.SP_DSNhanVien", new NhanVienMapper());
//    }
//
    public DDH findOne(String id) {
        List<DDH> list = queryProcedure("{call SP_Tim_DHH(?)}", new DDHMapper(), id);
        return list.isEmpty() ? null : list.get(0);
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
