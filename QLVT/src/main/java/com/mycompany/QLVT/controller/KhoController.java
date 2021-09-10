/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.model.KhoTableModel;
import com.mycompany.QLVT.service.KhoService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class KhoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private StackPane pnStackPane;

    @FXML
    private AnchorPane pnKho;

    @FXML
    private VBox pnMenuBar;

    @FXML
    private JFXButton btAdd;

    @FXML
    private JFXButton btEdit;

    @FXML
    private JFXButton btDelete;

    @FXML
    private JFXButton btSave;

    @FXML
    private JFXButton btUndo;

    @FXML
    private JFXButton btReload;

    @FXML
    private TableView<Kho> tbDSKho;

    @FXML
    private TableColumn<Kho, String> clMaKho;

    @FXML
    private TableColumn<Kho, String> clTenKho;

    @FXML
    private TableColumn<Kho, String> clDiaChi;

    @FXML
    private TableColumn<Kho, String> clChiNhanh;

    private ObservableList<Kho> listKho;

    @FXML
    void showAddFrom(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                JFXDialogLayout content = new JFXDialogLayout();
                KhoController khoController = new KhoController();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../../../../fxml/KhoDetail.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                content.setHeading(root);
                JFXDialog noti = new JFXDialog(pnStackPane, content, JFXDialog.DialogTransition.CENTER);
                Image image1 = new Image(getClass().getResourceAsStream("../../../../img/delete_20px.png"));
                Image image2 = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                JFXButton btClose = new JFXButton(null, new ImageView(image1));
                JFXButton btAdd = new JFXButton(null, new ImageView(image2));
                btClose.setButtonType(JFXButton.ButtonType.RAISED);
                btAdd.setButtonType(JFXButton.ButtonType.RAISED);
                btClose.setCursor(Cursor.HAND);
                btAdd.setCursor(Cursor.HAND);
                btClose.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                btAdd.setOnAction((ActionEvent event1) -> {
                    //thêm kho bằng kho controller
                });
                content.setActions(btAdd, btClose);
//                content.setActions(btClose);
                noti.show();
            }
        });
    }

    public void initTableKho() {
        System.out.println("Hello");
        clMaKho.setCellValueFactory(new PropertyValueFactory<>("maKho"));
        clTenKho.setCellValueFactory(new PropertyValueFactory<>("tenKho"));
        clDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        clChiNhanh.setCellValueFactory(new PropertyValueFactory<>("TenCN"));
        KhoTableModel model = new KhoTableModel();
        List<Kho> list = new KhoService().findAll();
        model.setKhoList(list);
        tbDSKho.setItems(model.getKhoList());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableKho();
    }

}
