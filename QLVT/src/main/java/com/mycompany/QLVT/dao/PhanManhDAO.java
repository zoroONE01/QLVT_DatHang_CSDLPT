/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.dao;

import com.mycompany.QLVT.Entity.ChiNhanh;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Mapper.PhanManhMapper;
import java.util.List;

/**
 *
 * @author MinhTo
 */
public class PhanManhDAO extends AbstractDAO<PhanManh> {

    public List<PhanManh> findAll() {
        return (List<PhanManh>) query("Select * from V_DS_PHANMANH", new PhanManhMapper());
    }

}
