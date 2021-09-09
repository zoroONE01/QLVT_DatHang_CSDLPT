/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.PhanManh;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class PhanManhMapper implements RowMapper<PhanManh>{

    @Override
    public PhanManh mapRow(ResultSet rs) {
       PhanManh phanManh=new PhanManh();
        try {
            phanManh.setName(rs.getString("tencn"));
            phanManh.setServer(rs.getString("tenserver"));
            return phanManh;
        } catch (SQLException ex) {
            Logger.getLogger(PhanManhMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
