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
    String subscriberServer;
    public String getName() {
        return name;
    }

    public String getSubscriberServer() {
        return subscriberServer;
    }

    public void setSubscriberServer(String subscriberServer) {
        this.subscriberServer = subscriberServer;
    }

    

    public PhanManh(String name, String server) {
        this.name = name;
        this.subscriberServer = server;
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
