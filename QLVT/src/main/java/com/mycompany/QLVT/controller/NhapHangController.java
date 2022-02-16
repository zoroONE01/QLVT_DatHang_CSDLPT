/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Command.ActionAdd;
import com.mycompany.QLVT.Command.ActionAddCTPN;
import com.mycompany.QLVT.Command.ActionAddPhieuNhap;
import com.mycompany.QLVT.Command.ActionHistory;
import com.mycompany.QLVT.Command.ActionListenerCommand;
import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.ChiTietDDH;
import com.mycompany.QLVT.Entity.ChiTietPhieuNhap;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Mapper.DonDatHangMapper;
import com.mycompany.QLVT.Utils.FomaterDate;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.model.ChiTietDDHModel;
import com.mycompany.QLVT.model.ChiTietPhieuNhapModel;
import com.mycompany.QLVT.model.DDHTableModel;
import com.mycompany.QLVT.model.PhieuNhapModel;
import com.mycompany.QLVT.service.CTDDHService;
import com.mycompany.QLVT.service.ChiTietPhieuNhapService;
import com.mycompany.QLVT.service.DDHService;
import com.mycompany.QLVT.service.PhieuNhapService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author MinhTo
 */
public class NhapHangController {

    @FXML
    private TableView<DDH> tbDSDDH;

    @FXML
    private TableColumn<DDH, String> clMaDDH;

    @FXML
    private TableColumn<DDH, String> clNCC;

    @FXML
    private TableColumn<DDH, String> clNgay;

    @FXML
    private TableColumn<DDH, String> clKho;

    @FXML
    private TableColumn<DDH, Integer> clNhanVien;

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
    private JFXButton btRedo;

    @FXML
    private JFXButton btReload;

    @FXML
    private AnchorPane pnPhieuNhap;
    @FXML
    private AnchorPane pnPhieuNhap_Dialog;
    @FXML
    private Label lbTitlePN;
    @FXML
    private StackPane stackPanePhieuNhap_Dialog;

    @FXML
    private JFXTextField tfMaPN_Dialog;

    @FXML
    private JFXTextField tfNgay_Dialog;

    @FXML
    private JFXTextField tfMaDHH_Dialog;

    @FXML
    private JFXTextField tfMaNV_Dialog;

    @FXML
    private JFXTextField tfMaKho_Dialog;

    @FXML
    private TableView<PhieuNhap> tbPN;

    @FXML
    private TableColumn<PhieuNhap, String> clMaPN;

    @FXML
    private TableColumn<PhieuNhap, String> clNgayPN;

    @FXML
    private TableColumn<PhieuNhap, String> clMaDDH_PN;

    @FXML
    private TableColumn<PhieuNhap, Integer> clNhanVienPN;

    @FXML
    private TableColumn<PhieuNhap, String> clKhoa;

    @FXML
    private TableView<ChiTietPhieuNhap> tbCTPN;

    @FXML
    private TableColumn<ChiTietPhieuNhap, String> clMaPNCT;

    @FXML
    private TableColumn<ChiTietPhieuNhap, String> clMaVT;

    @FXML
    private TableColumn<ChiTietPhieuNhap, Integer> clSoLuong;

    @FXML
    private TableColumn<ChiTietPhieuNhap, Float> clDonGia;

    @FXML
    private StackPane stackPaneCTPhieuNhap_Dialog;

    @FXML
    private AnchorPane pnCTPhieuNhap_Dialog;

    @FXML
    private Label lbTitlePN1;

    @FXML
    private JFXTextField tfMaPN_CTPN;

    @FXML
    private JFXTextField tfMaVT_CTPN;

    @FXML
    private JFXTextField tfSoLuong_CTPN;

    @FXML
    private JFXTextField tfDonGia_CTPN;

    @FXML
    private TableView<CTDDH> tbCTDDH;

    @FXML
    private TableColumn<CTDDH, String> clMaDDH_CTDDH;

    @FXML
    private TableColumn<CTDDH, String> clMaVT_CTDDH;

    @FXML
    private TableColumn<CTDDH, Integer> clSL_CTDDH;

    @FXML
    private TableColumn<CTDDH, Float> clDonGia_CTDDH;

    @FXML
    private ContextMenu contextMenuPN;

    @FXML
    private ContextMenu contextMenuCTPN;

    @FXML
    private MenuItem miAddCTPN;

    @FXML
    private MenuItem miAddPN;

    private PhieuNhapService phieuNhapService = new PhieuNhapService();
    private PhieuNhapModel phieuNhapModel;
    private ChiTietPhieuNhapModel chiTietPhieuNhapModel;
    private DDHTableModel ddhModel;
    private ChiTietDDHModel chiTietDDHModel;
    private ChiTietPhieuNhapService chiTietPhieuNhapService = new ChiTietPhieuNhapService();
    private ActionHistory history = new ActionHistory();
    private DDHService donDatHangService = new DDHService();
    private CTDDHService chiTietDatHangService = new CTDDHService();

    @FXML
    void redoCommand(ActionEvent event) {

    }

    @FXML
    void reloadTable(ActionEvent event) {

    }

    @FXML
    void saveOnDatabase(ActionEvent event) {

    }

    @FXML
    void showAddFrom(ActionEvent event) {

    }

    @FXML
    void showDeleteForm(ActionEvent event) {

    }

    @FXML
    void showEditForm(ActionEvent event) {

    }

    @FXML
    void undoCommand(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @FXML
    void initialize() {

        DDHTableModel ddhModel = new DDHTableModel();
        PhieuNhapModel pnModel = new PhieuNhapModel();
        ChiTietPhieuNhapModel ctpnm = new ChiTietPhieuNhapModel();
        ChiTietDDHModel ctddhm = new ChiTietDDHModel();
        List<DDH> donDatHangs = new ArrayList();
        donDatHangs = donDatHangService.findAllForPhieuNhap();
        ddhModel.setDDHList(donDatHangs);
        initTableDHFromDatabase(ddhModel, pnModel, ctpnm, ctddhm);
    }

    public void initTableDHFromDatabase(DDHTableModel ddhModelTable, PhieuNhapModel pnModel, ChiTietPhieuNhapModel ctpnModel, ChiTietDDHModel ctddhModel) {
        if (this.ddhModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        if (this.phieuNhapModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        if (this.chiTietPhieuNhapModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        if (this.chiTietDDHModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.chiTietDDHModel = ctddhModel;
        this.ddhModel = ddhModelTable;
        this.phieuNhapModel = pnModel;
        this.chiTietPhieuNhapModel = ctpnModel;
        //Don dat hang
        clMaDDH.setCellValueFactory(new PropertyValueFactory<>("maDDH"));
        clNgay.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        clNCC.setCellValueFactory(new PropertyValueFactory<>("NCC"));
        clKho.setCellValueFactory(new PropertyValueFactory<>("maKho"));
        clNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNV"));
        //PhieuNhap
        clMaPN.setCellValueFactory(new PropertyValueFactory<>("maPN"));
        clNgayPN.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        clMaDDH_PN.setCellValueFactory(new PropertyValueFactory<>("maDDH"));
        clNhanVienPN.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        clKhoa.setCellValueFactory(new PropertyValueFactory<>("maKhoa"));
        //Chi Tiet Phieu Nhap
        clMaPNCT.setCellValueFactory(new PropertyValueFactory<>("maPN"));
        clMaVT.setCellValueFactory(new PropertyValueFactory<>("tenVT"));
        clSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        clDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        //CT DDH
        clMaDDH_CTDDH.setCellValueFactory(new PropertyValueFactory<>("maSoDDH"));
        clMaVT_CTDDH.setCellValueFactory(new PropertyValueFactory<>("tenVT"));
        clSL_CTDDH.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        clDonGia_CTDDH.setCellValueFactory(new PropertyValueFactory<>("donGia"));
//        if (MainController.DDHCommandHistory.isCommandStackEmpty()) {
//            list = new DDHService().findAll();
//        } else {
//            list = MainController.DDHCommandHistory.getCommandStack().peek().getList();
//        }
        // ddhModel.setDDHList2(list);
        tbDSDDH.setItems(ddhModel.getDDHList());
//        tbDSDDH.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                btEdit.setDisable(false);
//                btDelete.setDisable(false);
//                int index = tbDSDDH.getSelectionModel().getSelectedIndex();
//                ddh = tbDSDDH.getItems().get(index);
//            }
//        });

        tbDSDDH.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ddhModel.setCurrentDDH(newValue);

            initTablePhieuNhap(ddhModel.getCurrentDDH().getMaDDH());

            //  initTablePhieuNhap vs CTPN
//            // Hỏi người dùng muốn lưu dữ liệu không
//            ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
//             Dialog<ButtonType> dialog = new Dialog<>();
//             dialog.setTitle("Lưu dữ liệu");
//             dialog.setContentText(oldValue.getHo()+" "+oldValue.getMaNhanVien()+"  "+oldValue.getTen());
//              dialog.getDialogPane().getButtonTypes().add(okButtonType);
//              Optional<ButtonType> result = dialog.showAndWait();
//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                   // executeCommand(new ActionAdd(newValue));
//                }
        });

        //set items for TABLE PHIEUNHAP
        tbPN.setItems(phieuNhapModel.getPhieuNhapList());
        tbCTPN.setItems(chiTietPhieuNhapModel.getChiTietPhieuNhapList());
        tbCTDDH.setItems(chiTietDDHModel.getChiTietDDHList());

        tbCTDDH.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chiTietDDHModel.setCurrentChiTietDDH(newValue);
            if (newValue != null) {
                tfMaVT_CTPN.setText(newValue.getMaVT());
                tfSoLuong_CTPN.setText(String.valueOf(newValue.getSoLuong()));
                tfDonGia_CTPN.setText(String.valueOf(newValue.getDonGia()));
            }
        });
        tbPN.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            phieuNhapModel.setCurrentPhieuNhap(newValue);
        });
        miAddPN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //kiểm tra Đơn đặt hàng đã có phiếu nhập chưa
                if (ddhModel.getCurrentDDH() != null) {
                    String maDon = ddhModel.getCurrentDDH().getMaDDH();
                    PhieuNhap pn = phieuNhapService.findOneByMaDon(maDon);
                    if (pn == null) {
                        System.out.println("show dialog them");
                        Dialog<PhieuNhap> dialog = new Dialog<>();
                        dialog.setTitle("Thêm phiếu nhập");
                        dialog.setResizable(true);
                        dialog.getDialogPane().setContent(stackPanePhieuNhap_Dialog);
                        stackPanePhieuNhap_Dialog.setVisible(true);

                        tfMaKho_Dialog.setText(ddhModel.getCurrentDDH().getMaKho());
                        tfMaDHH_Dialog.setText(ddhModel.getCurrentDDH().getMaDDH());
                        tfMaNV_Dialog.setText(String.valueOf(ddhModel.getCurrentDDH().getMaNV()));
                        tfNgay_Dialog.setText(FomaterDate.convertUtilDateToString(new Date()));

                        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
                        Button btn = (Button) dialog.getDialogPane().lookupButton(buttonTypeOk);
                        // btn.addEventFilter(EventType., this);
                        btn.addEventFilter(ActionEvent.ACTION, ev -> {
                            boolean isValidForm = true;
                            try {
                                String maPn = tfMaPN_Dialog.getText();
                                String ngay = tfNgay_Dialog.getText();
                                String madhh = tfMaDHH_Dialog.getText();
                                int maNV = Integer.parseInt(tfMaNV_Dialog.getText());
                                String maKho = tfMaKho_Dialog.getText();
                                //check rỗng
                                if (maPn.isEmpty() || maKho.isEmpty()) {
                                    //isValidForm = false;
                                    // messageDialog("Infomation is empty");
                                    throw new Exception("Infomation is empty");
                                }

                                //check ràng buộc 
                                if (maKho.length() > 4 || !ValidationRegEx.validationTextAndNumRegex(maPn)) {
                                    isValidForm = false;
                                    // messageDialog("Mã kho quá dài hoac sai dinh dang");
                                    throw new Exception("Mã kho quá dài hoac sai dinh dang");
                                }
                                if (maPn.length() > 8 || !ValidationRegEx.validationTextAndNumRegex(maPn)) {
                                    isValidForm = false;
                                    // messageDialog("Mã phiếu nhập quá dài hoac sai dinh dang");
                                    throw new Exception("Mã phiếu nhập quá dài hoac sai dinh dang");
                                }
                                if (isValidForm) {
                                    System.out.println("Xu li du lieu xuong table");
                                    PhieuNhap phieuNhapNew = new PhieuNhap(maPn, ngay, madhh, maNV, maKho);
                                    //kiểm tra mã phiếu nhập
                                    Boolean rs = executeCommand(new ActionAddPhieuNhap(phieuNhapNew, phieuNhapService, "PhieuNhap"));
                                    if (rs == true) {
                                        messageDialog("Thêm thành công");
                                    } else {
                                        throw new Exception("Phiếu nhập tồn tại");
                                        //messageDialog("Phiếu nhập đã tồn tại");
                                    }
                                }
                                //  return new PhieuNhap(tfMaPN_Dialog.getText(), new Date().toString(), tfMaDHH_Dialog.getText(), Integer.parseInt(tfMaNV_Dialog.getText()), tfMaKho_Dialog.getText());
                            } catch (Exception e) {
                                if (e.getMessage().equals("Infomation is empty")) {
                                    messageDialog("Infomation is empty");
                                }
                                if (e.getMessage().equals("Mã kho quá dài hoac sai dinh dang")) {
                                    messageDialog("Mã kho quá dài hoặc  sai định dạng");
                                }
                                if (e.getMessage().equals("Mã phiếu nhập quá dài hoac sai dinh dang")) {
                                    messageDialog("Mã phiếu nhập quá dài hoặc sai định dạng");
                                }
                                if (e.getMessage().equals("Phiếu nhập tồn tại")) {
                                    messageDialog("Phiếu nhập đã tồn tại");
                                }
                                ev.consume();
                            }

                        });

                        dialog.setResultConverter(new Callback<ButtonType, PhieuNhap>() {
                            @Override
                            public PhieuNhap call(ButtonType b) {
                                //  boolean isValidForm = true;
                                if (b == buttonTypeOk) {
                                    //kiểm tra dữ liệ
                                    System.out.println(tfMaPN_Dialog.getText());
                                    System.out.println(new Date().toString());
                                    System.out.println(tfMaDHH_Dialog.getText());
                                    System.out.println(Integer.parseInt(tfMaNV_Dialog.getText()));
                                    System.out.println(tfMaKho_Dialog.getText());
                                }
                                return null;
                            }
                        });
                        Optional<PhieuNhap> result = dialog.showAndWait();

                        if (result.isPresent()) {
                            System.out.println("result");
                            System.out.println(tfMaPN_Dialog.getText());
                            System.out.println(new Date().toString());
                            System.out.println(tfMaDHH_Dialog.getText());
                            System.out.println(Integer.parseInt(tfMaNV_Dialog.getText()));
                            System.out.println(tfMaKho_Dialog.getText());
                        }
                    } else {
                        System.out.println("Không được thêm");
                    }

                } else {
                    System.out.println("Không được thêm");
                }

            }
        });

        miAddCTPN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (phieuNhapModel.getCurrentPhieuNhap() != null) {
                    System.out.println("show dialog CTPN them");
                    Dialog<PhieuNhap> dialog = new Dialog<>();
                    dialog.setTitle("Thêm chi tiết phiếu nhập");
                    dialog.setResizable(true);
                    dialog.getDialogPane().setContent(stackPaneCTPhieuNhap_Dialog);
                    //set value text field CTDDH
                    tfMaPN_CTPN.setText(phieuNhapModel.getCurrentPhieuNhap().getMaPN());

                    stackPaneCTPhieuNhap_Dialog.setVisible(true);
                    ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
                    dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
                    Button btn = (Button) dialog.getDialogPane().lookupButton(buttonTypeOk);

                    String maDonDat = phieuNhapModel.getCurrentPhieuNhap().getMaDDH();
                    initTableCTDDH(maDonDat);
                    btn.addEventFilter(ActionEvent.ACTION, evt -> {
                        boolean isValidForm = true;
                        try {
                            String maVT = tfMaVT_CTPN.getText();
                            int soLuong = Integer.parseInt(tfSoLuong_CTPN.getText());
                            float donGia = Float.parseFloat(tfDonGia_CTPN.getText());
                            String maPN = tfMaPN_CTPN.getText();
                            //check rỗng
                            if (maVT.isEmpty()) {
                                //isValidForm = false;
                                // messageDialog("Infomation is empty");
                                throw new Exception("Infomation is empty");
                            }
                            //check ràng buộc 
                            if (maVT.length() > 4 || !ValidationRegEx.validationTextAndNumRegex(maVT)) {
                                isValidForm = false;
                                // messageDialog("Mã kho quá dài hoac sai dinh dang");
                                throw new Exception("Mã vật tư quá dài hoac sai dinh dang");
                            } else if (soLuong < 0 || soLuong > chiTietDDHModel.getCurrentChiTietDDH().getSoLuong()) {
                                isValidForm = false;
                                // messageDialog("Mã kho quá dài hoac sai dinh dang");
                                throw new Exception("Số lượng không hợp lệ");
                            } else if (donGia < 0 || donGia > chiTietDDHModel.getCurrentChiTietDDH().getDonGia()) {
                                isValidForm = false;
                                // messageDialog("Mã kho quá dài hoac sai dinh dang");
                                throw new Exception("Đơn giá không hợp lệ");
                            }

                            if (isValidForm) {
                                System.out.println("Xu li du lieu xuong table");
                                ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(maPN, maVT, soLuong, donGia);
                                // kiểm tra mã phiếu nhập 
                                Boolean rs = executeCommand(new ActionAddCTPN(chiTietPhieuNhap, chiTietPhieuNhapService, "PhieuNhap"));
                                if (rs == true) {
                                    messageDialog("Thêm thành công");
                                } else {
                                    System.out.println("Vui lòng kiểm tra Mã vật tư và Mã phiếu nhập");
                                    throw new Exception("Vui lòng kiểm tra Mã vật tư và Mã phiếu nhập");
                                    //messageDialog("Phiếu nhập đã tồn tại");
                                }

                            }
                            evt.consume();
                            //  return new PhieuNhap(tfMaPN_Dialog.getText(), new Date().toString(), tfMaDHH_Dialog.getText(), Integer.parseInt(tfMaNV_Dialog.getText()), tfMaKho_Dialog.getText());
                        } catch (NumberFormatException formatE) {
                            messageDialog("Thông tin sai định dạng");
                            evt.consume();
                        } catch (Exception e) {

                            if (e.getMessage().equals("Infomation is empty")) {
                                messageDialog("Infomation is empty");
                            } else if (e.getMessage().equals("Mã vật tư quá dài hoặc sai định dạng")) {
                                messageDialog("Mã vật tư quá dài hoặc sai định dạng");
                            } else if (e.getMessage().equals("Số lượng không hợp lệ")) {
                                messageDialog("Số lượng không hợp lệ");
                            } else if (e.getMessage().equals("Vui lòng kiểm tra Mã vật tư và Mã phiếu nhập")) {
                                messageDialog("Vui lòng kiểm tra Mã vật tư và Mã phiếu nhập");
                            } else if (e.getMessage().equals("Đơn giá không hợp lệ")) {
                                messageDialog("Đơn giá không hợp lệ");
                            } else {
                                messageDialog("Vui lòng kiểm tra thông tin");
                            }
                            evt.consume();
                        }

                    });

                    Optional<PhieuNhap> result = dialog.showAndWait();
                    if (result.isPresent()) {

                    }
                }
            }
        });

    }

    public void messageDialog(String body) {
        // StackPane st = (StackPane) pnPhieuNhap.getParent().getParent().getParent().getParent();
        StackPane st = (StackPane) stackPaneCTPhieuNhap_Dialog;
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thong bao"));
        content.setBody(new Text(body));
        JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER, true);
        Image image = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
        JFXButton button = new JFXButton(null, new ImageView(image));
        button.setCursor(Cursor.HAND);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setOnAction((ActionEvent event1) -> {
            noti.close();
        });
        content.setActions(button);
        noti.show();
    }

    public void initTablePhieuNhap(String maDon) {
        tbPN.getItems().clear();
        List<PhieuNhap> listPN = new ArrayList();
        listPN.add(phieuNhapService.findOneByMaDon(maDon));
        phieuNhapModel.setPhieuNhapList(listPN);
        if (listPN.get(0) != null) {
            initTableChiTietPhieuNhap(listPN.get(0).getMaPN());
        }
    }

    public void initTableCTDDH(String id) {
        tbCTDDH.getItems().clear();
        List<CTDDH> listDDH = new ArrayList();
        listDDH = chiTietDatHangService.findAllByMaDon(id);
        chiTietDDHModel.setChiTietDDHList(listDDH);
    }

    public void initTableChiTietPhieuNhap(String maPN) {
        tbCTPN.getItems().clear();
        List<ChiTietPhieuNhap> listPN = new ArrayList();
        listPN = chiTietPhieuNhapService.findByMAPN(maPN);
        chiTietPhieuNhapModel.setChiTietPhieuNhapList(listPN);

    }

    private boolean executeCommand(ActionListenerCommand command) {
        boolean rs = false;
        rs = command.execute();
        if (rs == true) {
            history.push(command);
        }
        return rs;
    }
}
