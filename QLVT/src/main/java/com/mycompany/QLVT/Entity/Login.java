/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Entity;

/**
 *
 * @author MinhTo
 */
public class Login {
   private String tenNhanVien;
   private String group;
   private String usernameDB;

    public Login() {
    }

    public Login(String tenNhanVien, String group, String usernameDB) {
        this.tenNhanVien = tenNhanVien;
        this.group = group;
        this.usernameDB = usernameDB;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUsernameDB() {
        return usernameDB;
    }

    public void setUsernameDB(String usernameDB) {
        this.usernameDB = usernameDB;
    }
   
}
