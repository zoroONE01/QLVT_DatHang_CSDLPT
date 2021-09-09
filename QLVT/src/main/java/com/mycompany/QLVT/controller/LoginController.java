/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.mycompany.QLVT.App;
import com.mycompany.QLVT.Entity.Login;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.dao.AbstractDAO;
import com.mycompany.QLVT.dao.LoginDAO;
import com.mycompany.QLVT.dao.PhanManhDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author MinhTo
 */
public class LoginController {

    @FXML
    private FlowPane btLogin;

    @FXML
    private ComboBox<String> cbbLocation;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    void loginAction(ActionEvent event) {
        // lay du lieu input username and pass
        String userName = tfUsername.getText();
        String password = tfPassword.getText();
        String chiNhanh = cbbLocation.getValue();
        //ràng buộc
        DBConnectUtil.chiNhanhSelected = chiNhanh;
        DBConnectUtil.username = userName;
        DBConnectUtil.password = password;

        try {
            DBConnectUtil.getConnection();
            LoginDAO loginDAO = new LoginDAO();
            Login login = loginDAO.findOne(userName);
           Alert alert = new Alert(AlertType.INFORMATION);
           alert.setContentText("Tên: " + login.getTenNhanVien() + "/n Nhóm: " + login.getGroup() + "Mã nhân viên: " + login.getUsernameDB());
           App.setRoot("main");
           alert.show();
        } catch (SQLException e) {
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Username hoặc password không hợp lệ");
             alert.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

    @FXML
    void initialize() {
        //show danh sach chi nhanh

        DBConnectUtil.chiNhanhSelected = "";
        
        
        PhanManhDAO phanManhDAO = new PhanManhDAO();
        
        List<PhanManh> listPhanManh = new ArrayList<>();

        listPhanManh = phanManhDAO.findAll();
        // hien thi du lieu len combobox
        for (PhanManh p : listPhanManh) {
            cbbLocation.getItems().add(p.getName());
        }
        cbbLocation.getSelectionModel().select(0);

        assert btLogin != null : "fx:id=\"btLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert cbbLocation != null : "fx:id=\"cbbLocation\" was not injected: check your FXML file 'login.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'login.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'login.fxml'.";
    }

}
