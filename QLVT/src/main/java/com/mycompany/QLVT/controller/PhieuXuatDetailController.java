/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.service.PhieuXuatService;
import com.mycompany.QLVT.service.KhoService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class PhieuXuatDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbTitle;

    @FXML
    private JFXTextField tfNgay;

    @FXML
    private JFXTextField tfMaPhieuXuat;

    @FXML
    private JFXTextField tfKhachHang;

    @FXML
    private JFXTextField tfKho;

    @FXML
    private VBox vbListVT;

    @FXML
    private VBox vbPhieuXuatInfo;

    public static List<ItemVatTu> listItemVatTu = new ArrayList<>();

    private ItemVatTuPhieuXuatController itemVatTuPhieuXuat;

    private HashMap<String, String> mapKho;

    private String khoSelected;

//    public static String khoSelected = null;
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

    public PhieuXuatDetailController() {
    }

    public void initAdd() {
//        r??ng bu???t k?? t??? cho textfeil
        listItemVatTu.clear();
        vbListVT.getChildren().clear();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        tfNgay.setText(currentDate.format(df));
        tfMaPhieuXuat.setTextFormatter(new TextFormatter<>(change -> {
            if (ValidationRegEx.removeAscent(change.getText()).matches("[a-zA-Z0-9]")) {
                return change; //if change is a number
            } else {
                change.setText("");
                return change;
            }
        }));
        tfKhachHang.setTextFormatter(new TextFormatter<>(change -> {
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
        tfMaPhieuXuat.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfKhachHang.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfKho.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        lbTitle.setText("Th??m Phi???u Xu???t H??ng M???i");
        addItemVatTu();
    }

    public void initView(PhieuXuat phieuXuast) {
        tfMaPhieuXuat.setText(phieuXuast.getMaPhieuXuat());
        tfKhachHang.setText(phieuXuast.getKhachHang());
        tfKho.setText(phieuXuast.getMaKhoTenKho());
        tfNgay.setText(phieuXuast.getNgay());

//        tfMaPhieuXuat.setEditable(false);
        //ki???m tra xem c?? phi???u nh???p hay ch??a
//        tfKho.setDisable(true);
//        tfKhachHang.setTextFormatter(new TextFormatter<>(change -> {
//            if (ValidationRegEx.removeAscent(change.getText()).matches("[\\sa-zA-Z0-9_.,-/]")) {
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                return change;
//            }
//        }));
//        tfKho.setTextFormatter(new TextFormatter<>(change -> {
//            if (ValidationRegEx.removeAscent(change.getText()).matches("[a-zA-Z0-9]")) {
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                return change;
//            }
//        }));
        listItemVatTu.clear();
        vbListVT.getChildren().clear();
        for (CTPhieuXuat ctPhieuXuat : PhieuXuatController.listCTPhieuXuat) {
            itemVatTuPhieuXuat = new ItemVatTuPhieuXuatController(ctPhieuXuat);
            itemVatTuPhieuXuat.setParentController(this);
            vbListVT.getChildren().add(itemVatTuPhieuXuat);
            listItemVatTu.add(itemVatTuPhieuXuat.getItem());
        }
        this.tfNgay.editableProperty().set(false);
        this.tfKhachHang.editableProperty().set(false);
        this.tfMaPhieuXuat.editableProperty().set(false);
        this.tfKho.editableProperty().set(false);
    }

    public void initDelele(PhieuXuat phieuXuat) {
        lbTitle.setText("X??c Nh???n X??a ????n ?????t H??ng");
        tfMaPhieuXuat.setText(phieuXuat.getMaPhieuXuat());
        tfKhachHang.setText(phieuXuat.getKhachHang());
        tfKho.setText(phieuXuat.getMaKhoTenKho());
        tfNgay.setText(phieuXuat.getNgay());
        listItemVatTu.clear();
        for (CTPhieuXuat ctPhieuXuat : PhieuXuatController.listCTPhieuXuat) {
            itemVatTuPhieuXuat = new ItemVatTuPhieuXuatController(ctPhieuXuat);
//            itemVatTuPhieuXuat.setItem();
            itemVatTuPhieuXuat.setParentController(this);
            itemVatTuPhieuXuat.setDelete();
            vbListVT.getChildren().add(itemVatTuPhieuXuat);
            listItemVatTu.add(itemVatTuPhieuXuat.getItem());
        }
        tfMaPhieuXuat.setEditable(false);
        tfKhachHang.setEditable(false);
        tfKho.setEditable(false);
    }

    public String addPhieuXuat() {
        String error = "";
        if (!ValidationRegEx.valMaDDH(tfMaPhieuXuat.getText())) {
            error += "\tM?? Phi???u Xu???t sai ?????nh d???ng\n";
        }
        if (!ValidationRegEx.valNCC(tfKhachHang.getText())) {
            error += "\tT??n Kh??ch H??ng sai ?????nh d???ng\n";
        }
        if (!ValidationRegEx.valKho(tfKho.getText())) {
            error += "\tKho sai ?????nh d???ng\n";
        }
        for (ItemVatTu itemVatTu : listItemVatTu) {
            if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                    || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                    || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
                error += "\tItem V???t T?? sai ?????nh d???ng\n";
                break;
            }
        }
        if (error != "") {
            return error;
        }
//        System.out.println("hello" + Integer.valueOf(DBConnectUtil.myUserDB));
        PhieuXuat phieuXuat = new PhieuXuat(tfMaPhieuXuat.getText(), tfNgay.getText(), tfKhachHang.getText(), Integer.valueOf(DBConnectUtil.myUserDB), mapKho.get(tfKho.getText()));
        PhieuXuatService sevice = new PhieuXuatService();

        int checkMaPhieuXuat = sevice.checkExist(phieuXuat.getMaPhieuXuat(), "MAPX");
        System.out.println("check" + checkMaPhieuXuat);
        for (PhieuXuat PhieuXuat : PhieuXuatController.list) {
            if (phieuXuat.getMaPhieuXuat().equals(PhieuXuat.getMaPhieuXuat())) {
                checkMaPhieuXuat = 1;
                break;
            }
        }

        if (checkMaPhieuXuat == 1) {
            tfMaPhieuXuat.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tM?? Phi???u Xu???t ???? t???n t???i\n";
        }
        if (checkMaPhieuXuat == 2) {
            tfMaPhieuXuat.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"), true);
            error += "\tM?? Phi???u Xu???t ???? t???n t???i\n";
        }

        if (checkMaPhieuXuat == 0) {
            sevice.insert(phieuXuat, listItemVatTu);
            listItemVatTu.clear();
//            PhieuXuatController.list = MainController.PhieuXuatCommandHistory.addInsertCommand(PhieuXuatController.list, phieuXuat);
            error = "";
        }
        return error;
    }

    public String updatePhieuXuat() {
        String error = "";
        if (!ValidationRegEx.valNCC(tfKhachHang.getText())) {
            error += "\tT??n Kh??ch H??ng sai ?????nh d???ng\n";
        }
        if (!ValidationRegEx.valKho(tfKho.getText())) {
            error += "\tKho sai ?????nh d???ng\n";
        }
        for (ItemVatTu itemVatTu : listItemVatTu) {
            if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                    || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                    || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
                error += "\tItem V???t T?? sai ?????nh d???ng\n";
                break;
            }
        }
        if (error != "") {
            return error;
        }
//        System.out.println("hello" + Integer.valueOf(DBConnectUtil.myUserDB));
        PhieuXuat phieuXuat = new PhieuXuat(tfMaPhieuXuat.getText(), tfNgay.getText(), tfKhachHang.getText(), Integer.valueOf(DBConnectUtil.myUserDB), mapKho.get(tfKho.getText()));
        PhieuXuatService sevice = new PhieuXuatService();

//        int checkMaPhieuXuat = sevice.checkExist(phieuXuat.getMaPhieuXuat(), "MasoPhieuXuat");
//        System.out.println("check" + checkMaPhieuXuat);
//        for (PhieuXuat PhieuXuat : PhieuXuatController.list) {
//            if (phieuXuat.getMaPhieuXuat().equals(PhieuXuat.getMaPhieuXuat())) {
//                checkMaPhieuXuat = 1;
//                break;
//            }
//        }
//        if (checkMaPhieuXuat == 1) {
//            tfMaPhieuXuat.pseudoClassStateChanged(
//                    PseudoClass.getPseudoClass("error"), true);
//            error += "\tM?? ????n ?????t H??ng ???? t???n t???i\n";
//        }
//        if (checkMaPhieuXuat == 2) {
//            tfMaPhieuXuat.pseudoClassStateChanged(
//                    PseudoClass.getPseudoClass("error"), true);
//            error += "\tM?? ????n ?????t H??ng ???? t???n t???i\n";
//        }
//
//        if (checkMaPhieuXuat == 0) {
        System.out.println(phieuXuat.toString());
        sevice.update(phieuXuat, listItemVatTu);
        listItemVatTu.clear();
//            PhieuXuatController.list = MainController.PhieuXuatCommandHistory.addInsertCommand(PhieuXuatController.list, phieuXuat);
        error = "";
//        }
        return error;
    }

    public String delelePhieuXuat(PhieuXuat PhieuXuat) {
//        initTfInput();
        String error = "";
        PhieuXuatService service = new PhieuXuatService();
//        int checkFK_DatHang_VatTu = service.checkExist(vatTu.getMaVT(), "FK_DATHANG_VATTU");
//        int checkFK_PhieuNhap_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUNHAP_VATTU");
//        int checkFK_PhieuXuat_VatTu = service.checkExist(vatTu.getMaVT(), "FK_PHIEUXUAT_VATTU");
//        if (checkFK_DatHang_VatTu == 1) {
//            error += "\t????n ?????t H??ng c?? ????n ?????t H??ng\n";
//        }
//        if (checkFK_PhieuNhap_VatTu == 1) {
//
//            error += "\t????n ?????t H??ng c?? Phi???u Nh???p\n";//        }
//        if (checkFK_PhieuXuat_VatTu == 1) {
//
//            error += "\t????n ?????t H??ng c?? Phi???u Xu???t\n";
//        }
//
//        if (error != "") {
//            error = "\tKH??NG TH??? X??A ????n ?????t H??ng\n" + error;
//            return error;
//        }
//        VatTuController.list = MainController.vatTuCommandHistory.addDeleleCommand(VatTuController.list, vatTu);
        service.delete(PhieuXuat.getMaPhieuXuat());
        listItemVatTu.clear();
        error = "";
        return error;
    }

    public void initTfInput() {
        PseudoClass errorClass = PseudoClass.getPseudoClass("error");
        tfMaPhieuXuat.textProperty().addListener(event -> {
            tfMaPhieuXuat.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valMaDDH(tfMaPhieuXuat.getText())
            );
        });
        tfKhachHang.textProperty().addListener(event -> {
            tfKhachHang.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valNCC(tfKhachHang.getText())
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
                listItemVatTu.clear();
                vbListVT.getChildren().clear();
                vbListVT.disableProperty().set(false);
                this.khoSelected = mapKho.get(newValue).trim();
                addItemVatTu(khoSelected);
            } else {
                listItemVatTu.clear();
                vbListVT.getChildren().clear();
                addItemVatTu();
                vbListVT.disableProperty().set(true);
                Platform.runLater(() -> {
                    tfKho.pseudoClassStateChanged(
                            PseudoClass.getPseudoClass("error"), true);
                });
            }
        });
        tfNgay.setEditable(false);
    }

    public void addItemVatTu(String khoSelected) {
        itemVatTuPhieuXuat = new ItemVatTuPhieuXuatController();
        itemVatTuPhieuXuat.setParentController(this);
        vbListVT.getChildren().add(itemVatTuPhieuXuat);
        listItemVatTu.add(itemVatTuPhieuXuat.getItem());
        itemVatTuPhieuXuat.setKhoSelected(khoSelected);
        itemVatTuPhieuXuat.initTfInput();
//        System.out.println("========================================================");
//        for (ItemVatTu itemVatTu : listItemVatTu) {
//            System.out.println(itemVatTu.toString());
//        }
    }

    public void addItemVatTu() {
        itemVatTuPhieuXuat = new ItemVatTuPhieuXuatController();
        itemVatTuPhieuXuat.setParentController(this);
        vbListVT.getChildren().add(itemVatTuPhieuXuat);
        listItemVatTu.add(itemVatTuPhieuXuat.getItem());

//        System.out.println("========================================================");
//        for (ItemVatTu itemVatTu : listItemVatTu) {
//            System.out.println(itemVatTu.toString());
//        }
    }

//    public void addItemVatTu_S() {
//        itemVatTuPhieuXuat = new ItemVatTuPhieuXuatController();
//        itemVatTuPhieuXuat.setKhoSelected(this.khoSelected);
//        itemVatTuPhieuXuat.setParentController(this);
//        vbListVT.getChildren().add(itemVatTuPhieuXuat);
//        listItemVatTu.add(itemVatTuPhieuXuat.getItem());
//        itemVatTuPhieuXuat.initTfInput();
//        System.out.println("========================================================");
//        for (ItemVatTu itemVatTu : listItemVatTu) {
//            System.out.println(itemVatTu.toString());
//        }
//    }

    public void removeItemVatTu(ItemVatTuPhieuXuatController item) {
        if (vbListVT.getChildren().size() != 1) {
            vbListVT.getChildren().remove(item);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTfInput();
        vbListVT.disableProperty().set(true);
//        addItemVatTu();
        vbListVT.setSpacing(10);
    }

}
