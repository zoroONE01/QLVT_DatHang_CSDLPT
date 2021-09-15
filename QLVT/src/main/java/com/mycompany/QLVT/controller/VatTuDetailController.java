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
 *
 * @author zoroONE01
 */
public class VatTuDetailController implements Initializable {

    @FXML
    private Label lbTitle;

    @FXML
    private JFXTextField tfMaVT;

    @FXML
    private JFXTextField tfDVT;

    @FXML
    private JFXTextField tfTenVT;

    public VatTuDetailController() {
    }

    public void initAdd() {
//        ràng buột ký tự cho textfeild
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

    public String addVatTu() {
        String error = "";
        if (!ValidationRegEx.valMaVT(tfMaVT.getText())) {
            error += "\tMã Vật Tư sai định dạng\n";
        }
        if (!ValidationRegEx.valTenVT(tfTenVT.getText())) {
            error += "\tTên Tên Vật Tư sai định dạng\n";
        }
        if (!ValidationRegEx.valDVT(tfDVT.getText())) {
            error += "\tĐơn Vị Tính sai định dạng\n";
        }
        if (error != "") {
            return error;
        }
        VatTuService service = new VatTuService();
        int checkMaVT = service.checkExist(tfMaVT.getText(), "MAVT");
        if (checkMaVT == 1) {
            tfMaVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Vật Tư đã tồn tại trên chi nhánh này\n";
        }
        if (checkMaVT == 2) {
            tfMaVT.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Vật Tư đã tồn tại trên chi nhánh khác\n";
        }

        if (checkMaVT == 0) {
            VatTu vatTu = new VatTu(tfMaVT.getText(), tfTenVT.getText(), tfDVT.getText());
            service.save(vatTu);
            error = "";
        }
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
    public void initialize(URL location, ResourceBundle resources) {
        initTfInput();
    }

}
