/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Entity.SoLuongTonKho;
import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import static com.mycompany.QLVT.controller.PhieuXuatDetailController.listItemVatTu;
import com.mycompany.QLVT.service.VatTuService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author zoroONE01
 */
public final class ItemVatTuPhieuXuatController extends AnchorPane implements Initializable {

    @FXML
    private HBox hbItemVT;

    @FXML
    private JFXTextField tfMaVT;

    @FXML
    private ContextMenu cmMaVTSnippets;

    @FXML
    private JFXTextField tfTenVT;

    @FXML
    private ContextMenu cmTenVTSnippets;

    @FXML
    private JFXTextField tfDonGia;

    @FXML
    private Spinner<Integer> snSoLuong;

    @FXML
    private MenuButton mbMore;

    @FXML
    private JFXButton btAdd;

    @FXML
    private MenuItem miResetItem;

    @FXML
    private MenuItem miRemoveItem;

    private PhieuXuatDetailController parentController;

    private ItemVatTu item;

    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1, 1);

    private BooleanBinding valCheck;

    public void initModel(ItemVatTu item) {
        tfMaVT.textProperty().bindBidirectional(item.getMaVTProperty());
        tfTenVT.textProperty().bindBidirectional(item.getTenVTProperty());
        tfDonGia.textProperty().bindBidirectional(item.getDonGiaProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString().replace(".0", "");
            }

            @Override
            public Number fromString(String string) {
                if (string == null) {
                    return 0;
                } else {
                    try {
                        return Float.valueOf(string);
                    } catch (NumberFormatException ex) {
                        return 0;
                    }
                }
            }
        });
//        snSoLuong.getValueFactory().valueProperty().bindBidirectional(item.getSoLuongProperty().asObject());
        valueFactory.valueProperty().bindBidirectional(item.getSoLuongProperty().asObject());
        snSoLuong.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!"".equals(newValue)) {
                item.setSoLuong(Integer.valueOf(newValue));
//                System.out.println(newValue);
            }
        });
    }

    public void initModel() {
        item = new ItemVatTu();
        tfMaVT.textProperty().bindBidirectional(item.getMaVTProperty());
        tfTenVT.textProperty().bindBidirectional(item.getTenVTProperty());
        tfDonGia.textProperty().bindBidirectional(item.getDonGiaProperty(), new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return object == null ? "" : object.toString().replace(".0", "");
            }

            @Override
            public Number fromString(String string) {
                if (string == null) {
                    return 0;
                } else {
                    try {
                        return Float.valueOf(string);
                    } catch (NumberFormatException ex) {
                        return 0;
                    }
                }
            }
        });
//        snSoLuong.getValueFactory().valueProperty().bindBidirectional(item.getSoLuongProperty().asObject());
        valueFactory.valueProperty().bindBidirectional(item.getSoLuongProperty().asObject());
        snSoLuong.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!"".equals(newValue)) {
                item.setSoLuong(Integer.valueOf(newValue));
//                System.out.println(newValue);
            }
        });
    }

    public ItemVatTuPhieuXuatController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/ItemVatTuPX.fxml"));
        fxmlLoader.setController(this);
        try {
            this.getChildren().add(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initModel();
        miResetItem.setOnAction((ActionEvent t) -> {
//            Platform.runLater(() -> {
//                PhieuXuatDetailController.listItemVatTu.remove(item);
//            });
            reset();
        });
        btAdd.setOnAction((ActionEvent t) -> {
//            PhieuXuatDetailController.itemError = "";
            for (ItemVatTu itemVatTu : listItemVatTu) {
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println(itemVatTu.toString());
//                System.out.println(!(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
//                        || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
//                        || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia()))));
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                        || !ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                        || !ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
//                    PhieuXuatDetailController.itemError = "\tItem Vật Tư sai định dạng\n";
                    return;
                }
            }
            Platform.runLater(() -> {
                parentController.addItemVatTu();
            });
        });
        miRemoveItem.setOnAction((ActionEvent t) -> {
            Platform.runLater(() -> {
                PhieuXuatDetailController.listItemVatTu.remove(item);
                parentController.removeItemVatTu(ItemVatTuPhieuXuatController.this);
            });
        });

//        initModel();
    }

    public ItemVatTuPhieuXuatController(CTPhieuXuat ctPhieuXuat) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/ItemVatPX.fxml"));
        fxmlLoader.setController(this);
        try {
            this.getChildren().add(fxmlLoader.load());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initModel();
        miResetItem.setOnAction((ActionEvent t) -> {
//            Platform.runLater(() -> {
//                PhieuXuatDetailController.listItemVatTu.remove(item);
//            });
            reset();
        });
        btAdd.setOnAction((ActionEvent t) -> {
//            PhieuXuatDetailController.itemError = "";
            for (ItemVatTu itemVatTu : listItemVatTu) {
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println(itemVatTu.toString());
//                System.out.println(!(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
//                        || ValidationRegEx.valTenVT(itemVatTu.getTenVT())
//                        || ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia()))));
//                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
                if (itemVatTu.getDonGia() == 0 || !(ValidationRegEx.valMaVT(itemVatTu.getMaVT())
                        || !ValidationRegEx.valTenVT(itemVatTu.getTenVT())
                        || !ValidationRegEx.valDonGia(String.valueOf(itemVatTu.getDonGia())))) {
//                    PhieuXuatDetailController.itemError = "\tItem Vật Tư sai định dạng\n";
                    return;
                }
            }
            Platform.runLater(() -> {
                parentController.addItemVatTu();
            });
        });
        miRemoveItem.setOnAction((ActionEvent t) -> {
            Platform.runLater(() -> {
                PhieuXuatDetailController.listItemVatTu.remove(item);
                parentController.removeItemVatTu(ItemVatTuPhieuXuatController.this);
            });
        });
        setItem(ctPhieuXuat);
//        initModel();
    }

    public void setParentController(PhieuXuatDetailController parentController) {
        this.parentController = parentController;
    }

    public boolean getValCheck() {
        return valCheck.get();
    }

    public ItemVatTu getItem() {
        return item;
    }

    public void setDelete() {
        tfMaVT.setEditable(false);
        tfTenVT.setEditable(false);
        tfDonGia.setEditable(false);
//        snSoLuong.setEditable(false);
        snSoLuong.setDisable(true);
        btAdd.setDisable(true);
        mbMore.setDisable(true);
    }

    public void setItem(CTPhieuXuat ctPhieuXuat) {
        item.setMaVT(ctPhieuXuat.getMaVT());
        item.setSoLuong(ctPhieuXuat.getSoLuong());
        item.setDonGia(ctPhieuXuat.getDonGia());
        valueFactory.setValue(item.getSoLuong());
        snSoLuong.setValueFactory(valueFactory);
        initModel(item);
//        tfMaVT.textProperty().bindBidirectional(item.getMaVTProperty());
////        System.out.println(ctPhieuXuat);
////        System.out.println(String.valueOf(ctPhieuXuat.getDonGia()).replace(".0", ""));
//        tfDonGia.setText(Float.toString(item.getDonGia()).replace(".0", ""));
////        tfDonGia.setText(String.valueOf(ctPhieuXuat.getDonGia()));

    }

    public void initAdd() {
//        tfDonGia.setTextFormatter(new TextFormatter<>(change -> {
//            if (ValidationRegEx.removeAscent(change.getText()).matches("^[0-9,]$")) {
//                return change; //if change is a number
//            } else {
//                change.setText(""); //else make no change
//                return change;
//            }
//        }));
        valueFactory.setValue(1);
        snSoLuong.setValueFactory(valueFactory);
        tfMaVT.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfTenVT.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
        tfDonGia.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"), true);
    }

    public void initTfInput() {
//        valCheck = new BooleanBinding() {
//            {
//                super.bind(tfMaVT.textProperty(),
//                        tfTenVT.textProperty(),
//                        tfDonGia.textProperty());
//            }
//
//            @Override
//            protected boolean computeValue() {
//                return (!ValidationRegEx.valMaVT(tfMaVT.getText())
//                        || !ValidationRegEx.valTenVT(tfMaVT.getText())
//                        || !ValidationRegEx.valDonGia(tfDonGia.getText()));
//            }
//        };
//        btAdd.disableProperty().bind(valCheck);
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
                    !ValidationRegEx.valTenVT(tfMaVT.getText())
            );
        });
        tfDonGia.textProperty().addListener(event -> {
            tfDonGia.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !ValidationRegEx.valDonGia(tfDonGia.getText())
            );
        });

        List<VatTu> listVatTu = new ArrayList<>(new VatTuService().findAll());
//        List<SoLuongTonKho> listTonKho = new ArrayList<>(new VatTuService().findSoLuongTonKho(maVT))
        HashMap<String, String> mapMaVTSnippet = new HashMap<>();
        HashMap<String, String> mapTenVTSnippet = new HashMap<>();
        HashMap<String, Integer> mapSLTonSnippet = new HashMap<>();
        listVatTu.forEach(vatTu -> {
            if (vatTu.getSoLuongTon() > 0) {
                mapMaVTSnippet.put(vatTu.getMaVT(), vatTu.getTenVT());
                mapTenVTSnippet.put(vatTu.getTenVT(), vatTu.getMaVT());
                mapSLTonSnippet.put(vatTu.getMaVT(), vatTu.getSoLuongTon());
            }
        });
        TextFields.bindAutoCompletion(tfMaVT, mapMaVTSnippet.keySet().toArray());
        TextFields.bindAutoCompletion(tfTenVT, mapMaVTSnippet.values().toArray());
        tfMaVT.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Arrays.asList(mapMaVTSnippet.keySet().toArray()).contains(newValue)) {
                tfTenVT.setText(mapMaVTSnippet.get(newValue));
                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, mapSLTonSnippet.get(tfMaVT.getText()), 1, 1);
                valueFactory.setValue(mapSLTonSnippet.get(tfMaVT.getText()));
                snSoLuong.setValueFactory(valueFactory);
                for (ItemVatTu itemVatTu : PhieuXuatDetailController.listItemVatTu) {
                    if (itemVatTu.getMaVT().equals(newValue)) {
                        reset();
                        break;
                    }
                }
            } else {
                Platform.runLater(() -> {
                    tfTenVT.clear();
                });
            }
        });
        tfTenVT.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Arrays.asList(mapMaVTSnippet.values().toArray()).contains(newValue)) {
                tfMaVT.setText(mapTenVTSnippet.get(newValue));
                valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, mapSLTonSnippet.get(tfMaVT.getText()), 1, 1);
                valueFactory.setValue(mapSLTonSnippet.get(tfMaVT.getText()));
                snSoLuong.setValueFactory(valueFactory);
            } else {
                Platform.runLater(() -> {
                    tfMaVT.clear();
                });
            }
        });
    }

    public void reset() {
        Platform.runLater(() -> {
            tfMaVT.clear();
            tfTenVT.clear();
            tfDonGia.clear();
        });
        valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1, 1);
        valueFactory.setValue(1);
        snSoLuong.setValueFactory(valueFactory);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTfInput();
        initAdd();
    }
}
