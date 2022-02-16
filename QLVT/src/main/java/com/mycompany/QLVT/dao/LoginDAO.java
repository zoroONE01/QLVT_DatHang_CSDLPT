    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.Login;
import com.mycompany.QLVT.Mapper.LoginMapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class LoginDAO extends AbstractDAO<Login> {

    public Login findOne(String tenLogin) {
        List<Login> list = new ArrayList<>();
        list = queryProcedure("{call SP_DANGNHAP(?)}", new LoginMapper(), tenLogin);
//        for (Login login : list) {
//            System.out.println(login.getUsernameDB());
//        }
        if (list != null) {
            return list.isEmpty() ? null : list.get(0);
        }
        return null;
    }
}
