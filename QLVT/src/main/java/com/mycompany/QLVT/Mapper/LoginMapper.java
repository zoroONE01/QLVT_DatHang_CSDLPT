/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import com.mycompany.QLVT.Entity.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MinhTo
 */
public class LoginMapper implements RowMapper<Login> {

    @Override
    public Login mapRow(ResultSet rs) {
        try {
            Login login = new Login();
            login.setTenNhanVien(rs.getString("hoten"));
            login.setGroup(rs.getString("tennhom"));
            login.setUsernameDB(String.valueOf(rs.getInt("manv")));
            System.out.println(login.getUsernameDB());
            return login;
        } catch (SQLException ex) {
            Logger.getLogger(LoginMapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
