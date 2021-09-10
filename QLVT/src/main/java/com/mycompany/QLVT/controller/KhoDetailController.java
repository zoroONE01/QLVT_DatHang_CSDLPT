/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.dao.KhoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class KhoDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField tfMaKho;

    @FXML
    private JFXTextField tfDiaChi;

    @FXML
    private JFXTextField tfTenKho;

    @FXML
    private JFXTextField tfChiNhanh;

    @FXML
    private Label lbTitle;

    public KhoDetailController() {
    }

    public void initAdd() {
        lbTitle.setText("Thêm Kho Mới");
    }

    public void initUpdate(Kho kho) {
        tfMaKho.setText(kho.getMaKho());
        tfMaKho.setEditable(false);
    }

    public void initDelele(Kho kho) {
        lbTitle.setText("Xác Nhận Xóa Kho");
        tfMaKho.setText(kho.getMaKho());
        tfTenKho.setText(kho.getTenKho());
        tfDiaChi.setText(kho.getDiaChi());
        tfChiNhanh.setText(kho.getMaCN());
        tfMaKho.setEditable(false);
        tfTenKho.setEditable(false);
        tfDiaChi.setEditable(false);
        tfChiNhanh.setEditable(false);
    }

    public void addKho() {
        Kho kho = new Kho(tfMaKho.getText(), tfTenKho.getText(), tfDiaChi.getText(), tfChiNhanh.getText());
        KhoDAO khoDAO = new KhoDAO();
        khoDAO.save(kho);
    }

    public void updateKho() {
        Kho kho = new Kho(tfMaKho.getText(), tfTenKho.getText(), tfDiaChi.getText(), tfChiNhanh.getText());
        KhoDAO khoDAO = new KhoDAO();
        khoDAO.update(kho);
    }

    public void deleleKho() {
        Kho kho = new Kho(tfMaKho.getText(), tfTenKho.getText(), tfDiaChi.getText(), tfChiNhanh.getText());
        KhoDAO khoDAO = new KhoDAO();
        khoDAO.delete(kho.getMaKho());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
