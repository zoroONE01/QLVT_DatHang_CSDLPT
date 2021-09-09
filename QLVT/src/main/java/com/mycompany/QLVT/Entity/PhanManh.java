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
public class PhanManh {
    String name;
    String server;
    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public PhanManh(String name, String server) {
        this.name = name;
        this.server = server;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhanManh() {
    }

    public PhanManh(String name) {
        this.name = name;
    }
    
}
