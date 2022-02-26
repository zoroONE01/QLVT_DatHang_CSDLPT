/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import com.mycompany.QLVT.Entity.Login;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.dao.LoginDAO;
import com.mycompany.QLVT.dao.PhanManhDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author MinhTo
 */
public class LoginController {

    @FXML
    private StackPane stackPane;

    @FXML
    private ComboBox<String> cbbLocation;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private JFXButton btLogin;

    PhanManhDAO phanManhDAO;

    PhanManh phanManhCurrent;

    @FXML
    void loginAction(ActionEvent event) throws IOException {
        // lay du lieu input username and pass
        String userName = tfUsername.getText();
        String password = tfPassword.getText();
        DBConnectUtil.phanManhCurrent = DBConnectUtil.listPhanManh.get(cbbLocation.getSelectionModel().getSelectedIndex());
        //ràng buộc
//      DBConnectUtil.chiNhanhSelected = chiNhanh;
        DBConnectUtil.username = userName;
        DBConnectUtil.password = password;
        DBConnectUtil.chiNhanhMain = DBConnectUtil.phanManhCurrent.getName();
        try {

            DBConnectUtil.getConnection();
            LoginDAO loginDAO = new LoginDAO();
            Login login = loginDAO.findOne(userName);
//            System.out.println(login);
            if (login != null) {
                MainController mainController = new MainController();
                Parent root = FXMLLoader.load(getClass().getResource("../../../../fxml/main.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                DBConnectUtil.myGroup = login.getGroup();
                DBConnectUtil.myName = login.getTenNhanVien();
                DBConnectUtil.myUserDB = login.getUsernameDB();
//                App.setRoot("Main");
                
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
            else{
                throw new SQLException("user or password invalid");
            }
        } catch (SQLException e) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.setHeading(new Text("Thông Báo"));
                    content.setBody(new Text("Username hoặc Password không hợp lệ"));
                    JFXDialog noti = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
                    Image image = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                    JFXButton button = new JFXButton(null, new ImageView(image));
                    button.setCursor(Cursor.HAND);
                    button.setButtonType(JFXButton.ButtonType.FLAT);
                    button.setOnAction((ActionEvent event1) -> {
                        noti.close();
                    });
                    content.setActions(button);
                    noti.show();
                }
            });
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setContentText("Username hoặc password không hợp lệ");
//            alert.show();

        }
    }

    @FXML
    void initialize() {
        
        //show danh sach chi nhanh
        phanManhDAO = new PhanManhDAO();
        DBConnectUtil.listPhanManh = phanManhDAO.findAll();
        // hien thi du lieu len combobox
        for (PhanManh p : DBConnectUtil.listPhanManh) {
            cbbLocation.getItems().add(p.getName());
        }
        cbbLocation.getSelectionModel().select(0);

        assert btLogin != null : "fx:id=\"btLogin\" was not injected: check your FXML file 'login.fxml'.";
        assert cbbLocation != null : "fx:id=\"cbbLocation\" was not injected: check your FXML file 'login.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'login.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'login.fxml'.";
        assert btLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'login.fxml'.";
    }

}
