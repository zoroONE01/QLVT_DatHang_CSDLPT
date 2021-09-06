/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.mycompany.QLVT.Entity.NhanVien;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author MinhTo
 */
public class MainController {
    
    @FXML
    private MenuButton mnbtAccount;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbTime;

    @FXML
    private AnchorPane pnNavBar;

    @FXML
    private ToggleButton tgbtDashboard;

    @FXML
    private ToggleButton tgbtNhanVien;

    @FXML
    private ToggleButton tgbtKho;

    @FXML
    private ToggleButton tgbtVatTu;

    @FXML
    private ToggleButton tgbtDonDatHang;

    @FXML
    private AnchorPane pnNhanVien;

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
    private JFXButton btChangeLocation;

    @FXML
    private TableView<NhanVien> tbvListNV;

    @FXML
    private TableColumn<NhanVien, Integer> maNV;

    @FXML
    private TableColumn<NhanVien, String> ho;

    @FXML
    private TableColumn<NhanVien, String> ten;

    @FXML
    private TableColumn<NhanVien, String> ngaySinh;

    @FXML
    private TableColumn<NhanVien, Integer> luong;

    @FXML
    private TableColumn<NhanVien, String> diaChi;
    
    @FXML
    private TableColumn<NhanVien, String> maCN;

    @FXML
    private TableColumn<NhanVien, String> trangThai;
}
