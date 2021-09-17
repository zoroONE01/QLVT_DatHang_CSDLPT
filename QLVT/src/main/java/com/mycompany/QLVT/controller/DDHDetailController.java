/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.service.VatTuService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class DDHDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbTitle;
    
    @FXML
    private JFXTextField tfMaVT;
    
    @FXML
    private JFXTextField tfDVT;
    
    @FXML
    private JFXTextField tfTenVT;
    
    @FXML
    private JFXTextField tfSoLuongTon;
    
    public DDHDetailController() {
    }
    
    public void initAdd() {
//        ràng buột ký tự cho textfeil
        tfMaVT.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[a-zA-Z0-9]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
//                change.setRange( //don't remove any selected text either.
//                        change.getRangeStart(),
//                        change.getRangeStart()
//                );
                return change;
            }
        }));
        tfTenVT.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfDVT.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfMaVT.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfTenVT.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfDVT.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        lbTitle.setText("Thêm Vật Tư Mới");
    }
    
    public void initUpdate(VatTu vatTu) {
        tfMaVT.setText(vatTu.getMaVT());
        tfTenVT.setText(vatTu.getTenVT());
        tfDVT.setText(vatTu.getDVT());
        tfSoLuongTon.setText(String.valueOf(vatTu.getSoLuongTon()));
        tfMaVT.setDisable(true);
        tfTenVT.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfDVT.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
    }
    
    public void initDelele(VatTu vatTu) {
        lbTitle.setText("Xác Nhận Xóa Vật Tư");
        tfMaVT.setText(vatTu.getMaVT());
        tfTenVT.setText(vatTu.getTenVT());
        tfDVT.setText(vatTu.getDVT());
        tfSoLuongTon.setText(String.valueOf(vatTu.getSoLuongTon()));
        
        tfMaVT.setDisable(true);
        tfTenVT.setDisable(true);
        tfDVT.setDisable(true);
        
    }
    
    public String addVatTu() {
        String error = "";
        if (!ValidationRegEx.valMaVT(tfMaVT.getText())) {
            error += "\tMã Vật Tư sai định dạng\n";
        }
        if (!ValidationRegEx.valTenVT(tfTenVT.getText())) {
            error += "\tTên Vật Tư sai định dạng\n";
        }
        if (!ValidationRegEx.valDVT(tfDVT.getText())) {
            error += "\tĐơn Vị Tính sai định dạng\n";
        }
        if (error != "") {
            return error;
        }
        
        VatTu vatTu = new VatTu(tfMaVT.getText(), tfTenVT.getText(), tfDVT.getText());
        VatTuService sevice = new VatTuService();
        int checkMaVT = sevice.checkExist(tfMaVT.getText(), "MAVT");
        int checkTenVT = sevice.checkExist(tfTenVT.getText(), "TENVT");
        
        for (VatTu vt : VatTuController.list) {
            if (vatTu.getMaVT().equals(vt.getMaVT())) {
                checkMaVT = 1;
                break;
            }
        }
        for (VatTu vt : VatTuController.list) {
            if (vatTu.getTenVT().equals(vt.getTenVT())) {
                checkTenVT = 1;
                break;
            }
        }
        if (checkMaVT == 1) {
            tfMaVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Vật Tư đã tồn tại\n";
        }
        if (checkMaVT == 2) {
            tfMaVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Vật Tư đã tồn tại\n";
        }
        if (checkTenVT == 1) {
            tfTenVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Vật Tư đã tồn tại\n";
        }
        if (checkTenVT == 2) {
            tfTenVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Vật Tư đã tồn tại\n";
        }
        if (checkMaVT == 0 && checkTenVT == 0) {
            VatTuController.list = MainController.vatTuCommandHistory.addInsertCommand(VatTuController.list, vatTu);
            error = "";
        }
        return error;
    }
    
    public String updateVatTu(VatTu vatTu) {
        String error = "";
        if (!ValidationRegEx.valTenVT(tfTenVT.getText())) {
            error += "\tTên Vật Tư sai định dạng\n";
        }
        if (!ValidationRegEx.valDVT(tfDVT.getText())) {
            error += "\tĐơn Vị Tính sai định dạng\n";
        }
        if (error != "") {
            return error;
        }
        VatTuService service = new VatTuService();
        int checkTenVT = 0;
        if (!tfTenVT.getText().equals(vatTu.getTenVT())) {
            checkTenVT = service.checkExist(tfTenVT.getText(), "TENVT");
            for (VatTu vt : VatTuController.list) {
                if (tfTenVT.getText().equals(vt.getTenVT())) {
                    checkTenVT = 1;
                    break;
                }
            }
        }
        
        if (checkTenVT == 1) {
            tfTenVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Vật Tư đã tồn tại\n";
        }
        if (checkTenVT == 2) {
            tfTenVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Vật Tư đã tồn tại\n";
        }
        if (checkTenVT == 0) {
            VatTu vt = new VatTu(tfMaVT.getText(), tfTenVT.getText(), tfDVT.getText(), vatTu.getSoLuongTon());
            VatTuController.list = MainController.vatTuCommandHistory.addUpdateCommand(VatTuController.list, vatTu, vt);
            error = "";
        }
        return error;
    }
    
    public String deleleVatTu(VatTu vatTu) {
//        initTfInput();
        String error = "";
        VatTuService service = new VatTuService();
        int checkFK_DatHang_VatTu = service.checkExist(vatTu.getMaVT(), "FK_DATHANG_VATTU");
        int checkFK_PhieuNhap_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUNHAP_VATTU");
        int checkFK_PhieuXuat_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUXUAT_VATTU");
        if (checkFK_DatHang_VatTu == 1) {
            error += "\tVật Tư có Đơn Đặt Hàng\n";
        }
        if (checkFK_PhieuNhap_VatTu == 1) {
            
            error += "\tVật Tư có Phiếu Nhập\n";
        }
        if (checkFK_PhieuXuat_VatTu == 1) {
            
            error += "\tVật Tư có Phiếu Xuất\n";
        }
        
        if (error != "") {
            error = "\tKHÔNG THỂ XÓA VẬT TƯ\n" + error;
            return error;
        }
        VatTuController.list = MainController.vatTuCommandHistory.addDeleleCommand(VatTuController.list, vatTu);
        error = "";
        return error;
    }
    
    public void initTfInput() {
        PseudoClass errorClass = PseudoClass.getPseudoClass("error");
        
        tfMaVT.textProperty().addListener(event -> {
            tfMaVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valMaVT(tfMaVT.getText())
            );
        });
        tfTenVT.textProperty().addListener(event -> {
            tfTenVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valTenVT(tfTenVT.getText())
            );
        });
        tfDVT.textProperty().addListener(event -> {
            tfDVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valDVT(tfDVT.getText())
            );
        });
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTfInput();
        tfSoLuongTon.setDisable(true);
    }
    
}
