/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Command.KhoCommand;
import com.mycompany.QLVT.Command.KhoInsert;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.model.KhoTableModel;
import com.mycompany.QLVT.service.KhoService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;

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
    private Label lbTitle;

    @FXML
    private JFXTextField tfMaKho;

    @FXML
    private JFXTextField tfDiaChi;

    @FXML
    private JFXTextField tfTenKho;

    @FXML
    private JFXTextField tfChiNhanh;

    public KhoDetailController() {
    }

    public void initAdd() {
//        ràng buột ký tự cho textfeild
        tfMaKho.setTextFormatter(new TextFormatter<>(change -> {
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
        tfTenKho.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfDiaChi.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfMaKho.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfDiaChi.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfTenKho.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        if (DBConnectUtil.phanManhCurrent.getName().equals("Chi Nhánh 1")) {
            tfChiNhanh.setText("CN1");
        }
        if (DBConnectUtil.phanManhCurrent.getName().equals("Chi Nhánh 2")) {
            tfChiNhanh.setText("CN2");
        }
        lbTitle.setText("Thêm Kho Mới");
    }

    public void initUpdate(Kho kho) {
        tfMaKho.setText(kho.getMaKho());
        tfTenKho.setText(kho.getTenKho());
        tfDiaChi.setText(kho.getDiaChi());
        tfChiNhanh.setText(kho.getMaCN());
        tfMaKho.setDisable(true);
        tfChiNhanh.setDisable(true);
        tfTenKho.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
        tfDiaChi.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
    }

    public void initDelele(Kho kho) {
        lbTitle.setText("Xác Nhận Xóa Kho");
        tfMaKho.setText(kho.getMaKho());
        tfTenKho.setText(kho.getTenKho());
        tfDiaChi.setText(kho.getDiaChi());
        tfChiNhanh.setText(kho.getMaCN());
        tfMaKho.setDisable(true);
        tfTenKho.setDisable(true);
        tfDiaChi.setDisable(true);
        tfChiNhanh.setDisable(true);

    }

    public String addKho() {
        String error = "";
        if (!ValidationRegEx.valMaKho(tfMaKho.getText())) {
            error += "\tMã Kho sai định dạng\n";
        }
        if (!ValidationRegEx.valTenKho(tfTenKho.getText())) {
            error += "\tTên Kho sai định dạng\n";
        }
        if (!ValidationRegEx.valDiaChi(tfDiaChi.getText())) {
            error += "\tĐịa chỉ sai định dạng\n";
        }
        if (error != "") {
            return error;
        }
        String tenCN = "";
        if (tfChiNhanh.getText().equals("CN1")) {
            tenCN = "Chi nhánh 1 TP HCM";
        }
        if (tfChiNhanh.getText().equals("CN2")) {
            tenCN = "Chi nhánh 2 TP HCM";
        }
        Kho kho = new Kho(tfMaKho.getText(), tfTenKho.getText(), tfDiaChi.getText(), tfChiNhanh.getText(), tenCN);
        KhoService sevice = new KhoService();
        int checkMaKho = sevice.checkExist(tfMaKho.getText(), "MAKHO");
        int checkTenKho = sevice.checkExist(tfTenKho.getText(), "TENKHO");
        for (Kho k : KhoController.list) {
            if (kho.getMaKho().equals(k.getMaKho())) {
                checkMaKho = 1;
                break;
            }
        }
        for (Kho k : KhoController.list) {
            if (kho.getTenKho().equals(k.getTenKho())) {
                checkTenKho = 1;
                break;
            }
        }
        if (checkMaKho == 1) {
            tfMaKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Kho đã tồn tại trên chi nhánh này\n";
        }
        if (checkMaKho == 2) {
            tfMaKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Kho đã tồn tại trên chi nhánh khác\n";
        }
        if (checkTenKho == 1) {
            tfTenKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Kho đã tồn tại trên chi nhánh này\n";
        }
        if (checkTenKho == 2) {
            tfTenKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Kho đã tồn tại trên chi nhánh khác\n";
        }
        if (checkMaKho == 0 && checkTenKho == 0) {
            KhoController.list = MainController.khoCommandHistory.addInsertCommand(KhoController.list, kho);
            error = "";
        }
        return error;
    }

    public String updateKho(Kho kho) {
        String error = "";
        if (!ValidationRegEx.valTenKho(tfTenKho.getText())) {
            error += "\tTên Kho sai định dạng\n";
        }
        if (!ValidationRegEx.valDiaChi(tfDiaChi.getText())) {
            error += "\tĐịa Chỉ sai định dạng\n";
        }
        if (error != "") {
            return error;
        }
        KhoService service = new KhoService();
        int checkTenKho = 0;
        if (!tfTenKho.getText().equals(kho.getTenKho())) {
            checkTenKho = service.checkExist(tfTenKho.getText(), "TENKHO");
            for (Kho k : KhoController.list) {
                if (tfTenKho.getText().equals(k.getTenKho())) {
                    checkTenKho = 1;
                    break;
                }
            }
        }

        if (checkTenKho == 1) {
            tfTenKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Kho đã tồn tại trên chi nhánh này\n";
        }
        if (checkTenKho == 2) {
            tfTenKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tTên Kho đã tồn tại trên chi nhánh khác\n";
        }
        if (checkTenKho == 0) {
            Kho k = new Kho(tfMaKho.getText(), tfTenKho.getText(), tfDiaChi.getText(), tfChiNhanh.getText(), kho.getTenCN());
//            System.out.println(kho.toString());
//            System.out.println(k.toString());
//            System.out.println(KhoController.list.indexOf(kho));
            KhoController.list = MainController.khoCommandHistory.addUpdateCommand(KhoController.list, kho, k);
            error = "";
        }
        return error;
    }

    public String deleleKho(Kho kho) {
//        initTfInput();
        String error = "";
        KhoService service = new KhoService();
        int checkFK_DatHang_Kho = service.checkExist(kho.getMaKho(), "FK_DATHANG_KHO");
        int checkFK_PhieuNhap_Kho = service.checkExist(kho.getMaKho(), "FK_PHIEUNHAP_KHO");
        int checkFK_PhieuXuat_Kho = service.checkExist(kho.getMaKho(), "FK_PHIEUXUAT_KHO");
        if (checkFK_DatHang_Kho == 1) {
            error += "\tKho có Đơn Đặt Hàng\n";
        }
        if (checkFK_PhieuNhap_Kho == 1) {

            error += "\tKho có Phiếu Nhập\n";
        }
        if (checkFK_PhieuXuat_Kho == 1) {

            error += "\tKho có Phiếu Xuất\n";
        }

        if (error != "") {
            error = "\tKHÔNG THỂ XÓA KHO\n" + error;
            return error;
        }
        KhoController.list = MainController.khoCommandHistory.addDeleleCommand(KhoController.list, kho);
        error = "";
        return error;
    }

    public void initTfInput() {
        PseudoClass errorClass = PseudoClass.getPseudoClass("error");

        tfMaKho.textProperty().addListener(event -> {
            tfMaKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valMaKho(tfMaKho.getText())
            );
        });
        tfTenKho.textProperty().addListener(event -> {
            tfTenKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valTenKho(tfTenKho.getText())
            );
        });
        tfDiaChi.textProperty().addListener(event -> {
            tfDiaChi.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valDiaChi(tfDiaChi.getText())
            );
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTfInput();
        tfChiNhanh.setDisable(true);
    }

}
