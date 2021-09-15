/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Utils;

import com.mycompany.QLVT.Entity.PhanManh;
import java.math.MathContext;
import java.sql.Connection;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author MinhTo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DBConnectUtil {

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
//    private static PropertiseUtil propertiesUtil = new PropertiseUtil();
//
//    private static String url = propertiesUtil.getValue("url", ConfigReader.getStringDataBaseUrl());
//    private static String driver = propertiesUtil.getValue("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//    private static String username = propertiesUtil.getValue("username", ConfigReader.getAdminUserDataBase());
//    private static String password = propertiesUtil.getValue("password", ConfigReader.getPassWordDataBase());
//

    public static String url = "jdbc:sqlserver://DESKTOP-HFPR9E7\\SITE0;databaseName=QLVT";
    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String usernameMain = "HTKN";
    public static String passwordMain = "123456";
    public static String url1 = "jdbc:sqlserver://DESKTOP-HFPR9E7\\SITE1;databaseName=QLVT";
    public static String driver1 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url2 = "jdbc:sqlserver://DESKTOP-HFPR9E7\\SITE2;databaseName=QLVT";
    public static String driver2 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url3 = "jdbc:sqlserver://DESKTOP-HFPR9E7\\SITE3;databaseName=QLVT";
    public static String driver3 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String myGroup;
    public static String myName;
    public static String myUserDB;
    public static String username = "";
    public static String password = "";
    public static String subcriberServer1= "DESKTOP-HFPR9E7\\SITE1";
    public static String subcriberServer2 = "DESKTOP-HFPR9E7\\SITE2";   
    public static String subcriberServer3 = "";
    public static List<PhanManh> listPhanManh;
    public static String chiNhanhSelected;
    public static String subcriberCurrent;
    static {
        try {
            Class.forName(driver);
            System.out.println("thanh cong");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        // If there is no connection in the container, get a connection from the connection pool to ThreadLocal
        if (conn == null || conn.isClosed()) {
            if (subcriberCurrent.equals(subcriberServer1)) {
                conn = DriverManager.getConnection(url1, username, password);
            } else if (subcriberCurrent.equals(subcriberServer2)) {
                conn = DriverManager.getConnection(url2, username, password);
            } 
//            else if (chiNhanhSelected.equals(chiNhanh3)) {
//                conn = DriverManager.getConnection(url3, username, password);
//            } 
            else if (subcriberCurrent.equals("")) {
                conn = DriverManager.getConnection(url, usernameMain, passwordMain);
            }
            tl.set(conn);

        }
        return conn;
    }

    public static void begin() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {

        }
    }

    public static void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            tl.remove();
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
