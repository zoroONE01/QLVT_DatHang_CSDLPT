/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.VatTu;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zoroONE01
 */
public class VatTuMapper implements RowMapper<VatTu> {

    @Override
    public VatTu mapRow(ResultSet rs) {
        try {
            VatTu vatTu = new VatTu();
            vatTu.setMaVT(rs.getString(1));
            vatTu.setTenVT(rs.getString(2));
            vatTu.setDVT(rs.getString(3));
        } catch (SQLException e) {
            Logger.getLogger(VatTuMapper.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
