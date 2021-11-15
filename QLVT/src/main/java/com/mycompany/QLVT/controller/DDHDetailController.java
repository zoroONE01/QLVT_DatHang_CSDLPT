/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.service.DDHService;
import com.mycompany.QLVT.service.KhoService;
import com.mycompany.QLVT.service.VatTuService;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

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
    private JFXTextField tfNgay;

    @FXML
    private JFXTextField tfMaDDH;

    @FXML
    private JFXTextField tfNCC;

    @FXML
    private JFXTextField tfKho;

    @FXML
    private VBox vbListVT;

    public static List<ItemVatTu> listItemVatTu = new ArrayList<>();

    private ItemVatTuDDHController itemVatTuDDH;

    HashMap<String, String> mapKho;

    public static String itemError = "";
//    public static BooleanBinding bbValCheck = new BooleanBinding() {
////        {
////            super.bind(tfMaVT.textProperty(),
////                    tfTenVT.textProperty(),
////                    tfDonGia.textProperty());
////        }
//        @Override
//        protected boolean computeValue() {
//            for (ItemVatTu itemVatTu : listItemVatTu) {
//                if (!ValidationRegEx.valMaVT(itemVatTu.getMaVT())
//                        || !ValidationRegEx.valTenVT(itemVatTu.getTenVT())
//                        || !ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia()))) {
//                    return false;
//                }
//            }
//            return true;
////            return (!ValidationRegEx.valMaVT(tfMaVT.getText())
////                    || !ValidationRegEx.valTenVT(tfMaVT.getText())
////                    || !ValidationRegEx.valDonGia(tfDonGia.getText()));
//        }
//    };

    public DDHDetailController() {
    }

    public void initAdd() {
//        ràng buột ký tự cho textfeil
        listItemVatTu.clear();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        tfNgay.setText(currentDate.format(df));
        tfMaDDH.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[a-zA-Z0-9]")) {
                return change; //if change is a number
            } else {
                change.setText("");
                return change;
            }
        }));
        tfNCC.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
//        tfKho.setTextFormatter(new TextFormatter<>(change -> {
//            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                return change;
//            }
//        }));
        tfMaDDH.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfNCC.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfKho.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        lbTitle.setText("Thêm Đơn Đặt Hàng Mới");
        addItemVatTu();
    }

    public void initUpdate(DDH donDatHang) {
        tfMaDDH.setText(donDatHang.getMaDDH());
        tfNCC.setText(donDatHang.getNCC());
        tfKho.setText(donDatHang.getMaKhoTenKho());
        tfNgay.setText(donDatHang.getNgay());
        tfMaDDH.setEditable(false);

        //kiểm tra xem có phiếu nhập hay chưa
//        tfKho.setDisable(true);
        tfNCC.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
                return change; //if change is a number
            } else {
                change.setText(""); //else make no change
                return change;
            }
        }));
//        tfKho.setTextFormatter(new TextFormatter<>(change -> {
//            if (ValidationRegEx.removeAscent(change.getText()).matches("[a-zA-Z0-9]")) {
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                return change;
//            }
//        }));
        listItemVatTu.clear();
        for (CTDDH ctddh : DDHController.listCTDDH) {
            itemVatTuDDH = new ItemVatTuDDHController(ctddh);
//            itemVatTuDDH.setItem();
            itemVatTuDDH.setParentController(this);
            vbListVT.getChildren().add(itemVatTuDDH);
            listItemVatTu.add(itemVatTuDDH.getItem());
        }
//        itemVatTuDDH = new ItemVatTuDDHController();
//        itemVatTuDDH.setParentController(this);
//        vbListVT.getChildren().add(itemVatTuDDH);
//        listItemVatTu.add(itemVatTuDDH.getItem());
    }

    public void initDelele(DDH donDatHang) {
        lbTitle.setText("Xác Nhận Xóa Đơn Đặt Hàng");
        tfMaDDH.setText(donDatHang.getMaDDH());
        tfNCC.setText(donDatHang.getNCC());
        tfKho.setText(donDatHang.getMaKhoTenKho());
        tfNgay.setText(donDatHang.getNgay());
        listItemVatTu.clear();
        for (CTDDH ctddh : DDHController.listCTDDH) {
            itemVatTuDDH = new ItemVatTuDDHController(ctddh);
//            itemVatTuDDH.setItem();
            itemVatTuDDH.setParentController(this);
            itemVatTuDDH.setDelete();
            vbListVT.getChildren().add(itemVatTuDDH);
            listItemVatTu.add(itemVatTuDDH.getItem());
        }
        tfMaDDH.setEditable(false);
        tfNCC.setEditable(false);
        tfKho.setEditable(false);
    }

    public String addDDH() {
        String error = "";
        if (!ValidationRegEx.valMaDDH(tfMaDDH.getText())) {
            error += "\tMã Đơn Đặt Hàng sai định dạng\n";
        }
        if (!ValidationRegEx.valNCC(tfNCC.getText())) {
            error += "\tTên Nhà Cung Cấp sai định dạng\n";
        }
        if (!ValidationRegEx.valKho(tfKho.getText())) {
            error += "\tKho sai định dạng\n";
        }
        for (ItemVatTu itemVatTu : listItemVatTu) {
            if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                    || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                    || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
                error += "\tItem Vật Tư sai định dạng\n";
                break;
            }
        }
        if (error != "") {
            return error;
        }
//        System.out.println("hello" + Integer.valueOf(DBConnectUtil.myUserDB));
        DDH donDatHang = new DDH(tfMaDDH.getText(), tfNgay.getText(), tfNCC.getText(), Integer.valueOf(DBConnectUtil.myUserDB), mapKho.get(tfKho.getText()));
        DDHService sevice = new DDHService();

        int checkMaDDH = sevice.checkExist(donDatHang.getMaDDH(), "MasoDDH");
        System.out.println("check" + checkMaDDH);
        for (DDH ddh : DDHController.list) {
            if (donDatHang.getMaDDH().equals(ddh.getMaDDH())) {
                checkMaDDH = 1;
                break;
            }
        }

        if (checkMaDDH == 1) {
            tfMaDDH.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Đơn Đặt Hàng đã tồn tại\n";
        }
        if (checkMaDDH == 2) {
            tfMaDDH.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tMã Đơn Đặt Hàng đã tồn tại\n";
        }

        if (checkMaDDH == 0) {
            sevice.insert(donDatHang, listItemVatTu);
            listItemVatTu.clear();
//            DDHController.list = MainController.DDHCommandHistory.addInsertCommand(DDHController.list, donDatHang);
            error = "";
        }
        return error;
    }

    public String updateDDH() {
        String error = "";
        if (!ValidationRegEx.valNCC(tfNCC.getText())) {
            error += "\tTên Nhà Cung Cấp sai định dạng\n";
        }
        if (!ValidationRegEx.valKho(tfKho.getText())) {
            error += "\tKho sai định dạng\n";
        }
        for (ItemVatTu itemVatTu : listItemVatTu) {
            if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                    || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                    || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
                error += "\tItem Vật Tư sai định dạng\n";
                break;
            }
        }
        if (error != "") {
            return error;
        }
//        System.out.println("hello" + Integer.valueOf(DBConnectUtil.myUserDB));
        DDH donDatHang = new DDH(tfMaDDH.getText(), tfNgay.getText(), tfNCC.getText(), Integer.valueOf(DBConnectUtil.myUserDB), mapKho.get(tfKho.getText()));
        DDHService sevice = new DDHService();

//        int checkMaDDH = sevice.checkExist(donDatHang.getMaDDH(), "MasoDDH");
//        System.out.println("check" + checkMaDDH);
//        for (DDH ddh : DDHController.list) {
//            if (donDatHang.getMaDDH().equals(ddh.getMaDDH())) {
//                checkMaDDH = 1;
//                break;
//            }
//        }
//        if (checkMaDDH == 1) {
//            tfMaDDH.pseudoClassStateChanged(
//                    PseudoClass.getPseudoClass("error"), true);
//            error += "\tMã Đơn Đặt Hàng đã tồn tại\n";
//        }
//        if (checkMaDDH == 2) {
//            tfMaDDH.pseudoClassStateChanged(
//                    PseudoClass.getPseudoClass("error"), true);
//            error += "\tMã Đơn Đặt Hàng đã tồn tại\n";
//        }
//
//        if (checkMaDDH == 0) {
        System.out.println(donDatHang.toString());
        sevice.update(donDatHang, listItemVatTu);
        listItemVatTu.clear();
//            DDHController.list = MainController.DDHCommandHistory.addInsertCommand(DDHController.list, donDatHang);
        error = "";
//        }
        return error;
    }

    public String deleleDDH(DDH ddh) {
//        initTfInput();
        String error = "";
        DDHService service = new DDHService();
//        int checkFK_DatHang_VatTu = service.checkExist(vatTu.getMaVT(), "FK_DATHANG_VATTU");
//        int checkFK_PhieuNhap_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUNHAP_VATTU");
//        int checkFK_PhieuXuat_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUXUAT_VATTU");
//        if (checkFK_DatHang_VatTu == 1) {
//            error += "\tĐơn Đặt Hàng có Đơn Đặt Hàng\n";
//        }
//        if (checkFK_PhieuNhap_VatTu == 1) {
//
//            error += "\tĐơn Đặt Hàng có Phiếu Nhập\n";
//        }
//        if (checkFK_PhieuXuat_VatTu == 1) {
//
//            error += "\tĐơn Đặt Hàng có Phiếu Xuất\n";
//        }
//
//        if (error != "") {
//            error = "\tKHÔNG THỂ XÓA Đơn Đặt Hàng\n" + error;
//            return error;
//        }
//        VatTuController.list = MainController.vatTuCommandHistory.addDeleleCommand(VatTuController.list, vatTu);
        service.delete(ddh.getMaDDH());
        listItemVatTu.clear();
        error = "";
        return error;
    }

    public void initTfInput() {
        PseudoClass errorClass = PseudoClass.getPseudoClass("error");
        tfMaDDH.textProperty().addListener(event -> {
            tfMaDDH.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valMaDDH(tfMaDDH.getText())
            );
        });
        tfNCC.textProperty().addListener(event -> {
            tfNCC.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valNCC(tfNCC.getText())
            );
        });
        tfKho.textProperty().addListener(event -> {
            tfKho.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valKho(tfKho.getText())
            );
        });

        List<Kho> listKho = new ArrayList<>(new KhoService().findAll());
        List<String> listKhoSnippet = new ArrayList<>();
        mapKho = new HashMap<>();
        listKho.forEach(kho -> {
            listKhoSnippet.add("[" + kho.getMaKho() + "] " + kho.getTenKho());
            mapKho.put("[" + kho.getMaKho() + "] " + kho.getTenKho(), kho.getMaKho());
        });
        TextFields.bindAutoCompletion(tfKho, listKhoSnippet);
        tfKho.textProperty().addListener((observable, oldValue, newValue) -> {
            if (listKhoSnippet.contains(newValue)) {
                tfKho.setText(newValue);
            } else {
//                if (listKhoSnippet.contains(tfKho.getText())) {
//                    tfKho.setText(oldValue);
//                }
//                tfKho.setText(oldValue);
                Platform.runLater(() -> {
                    tfKho.pseudoClassStateChanged(
                            PseudoClass.getPseudoClass("error"), true);
                });
            }
        });
        tfNgay.setEditable(false);
    }

    public void addItemVatTu() {
        itemVatTuDDH = new ItemVatTuDDHController();
        itemVatTuDDH.setParentController(this);
        vbListVT.getChildren().add(itemVatTuDDH);
        listItemVatTu.add(itemVatTuDDH.getItem());

//        System.out.println("========================================================");
//        for (ItemVatTu itemVatTu : listItemVatTu) {
//            System.out.println(itemVatTu.toString());
//        }
    }

    public void removeItemVatTu(ItemVatTuDDHController item) {
        if (vbListVT.getChildren().size() != 1) {
            vbListVT.getChildren().remove(item);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTfInput();
//        addItemVatTu();
        vbListVT.setSpacing(10);
    }

}
