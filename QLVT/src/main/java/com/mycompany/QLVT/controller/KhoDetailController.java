/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

    private String maKho;

    public KhoDetailController(String maKho) {
        this.maKho = maKho;
    }

    public KhoDetailController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
