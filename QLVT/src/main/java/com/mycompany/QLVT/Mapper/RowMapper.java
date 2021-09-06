/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Mapper;

import java.sql.ResultSet;

/**
 *
 * @author MinhTo
 */
public interface RowMapper<T> {
   
    public T mapRow(ResultSet rs); // ánh xạ dữ liệu lấy từ DB lên model
   
}
