package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Command.DDHCommandHistory;
import com.mycompany.QLVT.Command.KhoCommandHistory;
import com.mycompany.QLVT.Command.VatTuCommandHistory;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
        @FXML
    private RadioButton btNhanVien;
        
    @FXML
    private RadioButton btDashboard;

    @FXML
    private RadioButton btBaoCao;
            
    @FXML
    private RadioButton btPhieuNhap;

    @FXML
    private RadioButton btKho;

    @FXML
    private RadioButton btVatTu;

    @FXML
    private RadioButton btDatHang;

    @FXML
    private RadioButton btXuatHang;

    @FXML
    private StackPane pnMain;

    @FXML
    private JFXButton btUserInfo;

    @FXML
    private Label lbTitle;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbTime;

    @FXML
    private AnchorPane pnNavBar;

    @FXML
    private AnchorPane pnWorkspace;
   

    public static KhoCommandHistory khoCommandHistory;
    public static VatTuCommandHistory vatTuCommandHistory;
    public static DDHCommandHistory DDHCommandHistory;
    public final String tabDashboard = "RadioButton[id=btDashboard, styleClass=radio-button btn-slidebar]'Dashboard'";
    public final String tabNhanVien = "RadioButton[id=btNhanVien, styleClass=btn-slidebar toggle-button]'Nhân Viên'";
    public final String tabKho = "RadioButton[id=btKho, styleClass=btn-slidebar toggle-button]'Kho'";
    public final String tabVatTu = "RadioButton[id=btVatTu, styleClass=btn-slidebar toggle-button]'V?t T?'";
    public final String tabDatHang = "RadioButton[id=btDatHang, styleClass=btn-slidebar toggle-button]'??t Hàng'";
    public final String tabXuatHang = "RadioButton[id=btXuatHang, styleClass=btn-slidebar toggle-button]'Xu?t Hàng'";

    public MainController() {
        khoCommandHistory = new KhoCommandHistory();
        vatTuCommandHistory = new VatTuCommandHistory();
        DDHCommandHistory = new DDHCommandHistory();
    }
//
//    @FXML
//    private AnchorPane pnMain;

    @FXML
    void showUserInfo(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Thông Tin Tài Khoản"));
                content.setBody(new Text("User: " + DBConnectUtil.myUserDB + "\nHọ và tên: " + DBConnectUtil.myName + "\nNhóm quyền: " + DBConnectUtil.myGroup));
                JFXDialog noti = new JFXDialog(pnMain, content, JFXDialog.DialogTransition.CENTER);
                Image image1 = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                Image image2 = new Image(getClass().getResourceAsStream("../../../../img/logout_rounded_left_20px.png"));
                JFXButton btClose = new JFXButton(null, new ImageView(image1));
                JFXButton btLogout = new JFXButton(null, new ImageView(image2));
                btClose.setButtonType(JFXButton.ButtonType.FLAT);
                btLogout.setButtonType(JFXButton.ButtonType.FLAT);
                btClose.setCursor(Cursor.HAND);
                btLogout.setCursor(Cursor.HAND);
                btClose.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                btLogout.setOnAction((ActionEvent event2) -> {
                    noti.close();
                    DBConnectUtil.reset();
                    LoginController loginController = new LoginController();
                    Parent root = null;
                    try {
                        System.out.println(getClass().getResource("../../../../fxml/login.fxml"));
                        root = FXMLLoader.load(getClass().getResource("../../../../fxml/login.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                });
                content.setActions(btLogout, btClose);
//                content.setActions(btClose);
                noti.show();
            }
        });
    }

    private void initButtonMenuBar() {
        final ToggleGroup group = new ToggleGroup();
        btDashboard.setToggleGroup(group);
        btNhanVien.setToggleGroup(group);
        btKho.setToggleGroup(group);
        btVatTu.setToggleGroup(group);

        btDatHang.setToggleGroup(group);
        btXuatHang.setToggleGroup(group);

        group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            if (newValue != null) {
                if (saveCheck((RadioButton) oldValue)) {
                    newValue.setSelected(true);
                } else {
                    Platform.runLater(() -> {
                        JFXDialogLayout content = new JFXDialogLayout();
                        content.setHeading(new Text("Thông Báo"));
                        content.setBody(new Text("Dữ liệu thay đổi chưa được lưu.\nTiếp tục thao tác mà không lưu thay đổi?"));
                        JFXDialog noti = new JFXDialog(pnMain, content, JFXDialog.DialogTransition.CENTER);
                        Image image1 = new Image(getClass().getResourceAsStream("../../../../img/delete_20px.png"));
                        Image image2 = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                        JFXButton btClose = new JFXButton(null, new ImageView(image1));
                        JFXButton btAccept = new JFXButton(null, new ImageView(image2));
                        btClose.setButtonType(JFXButton.ButtonType.FLAT);
                        btAccept.setButtonType(JFXButton.ButtonType.FLAT);
                        btClose.setCursor(Cursor.HAND);
                        btAccept.setCursor(Cursor.HAND);
                        btClose.setOnAction((ActionEvent event1) -> {
                            noti.close();
                            oldValue.setSelected(true);
                        });
                        btAccept.setOnAction((ActionEvent event1) -> {
                            switch (((RadioButton) oldValue).toString()) {
                                case tabKho:
                                    khoCommandHistory.clearAllStack();
                                    break;
                                case tabVatTu:
                                    vatTuCommandHistory.clearAllStack();
                                    break;
                            }
                            noti.close();
                            newValue.setSelected(true);
                        });
                        content.setActions(btAccept, btClose);
                        noti.show();
                    });
                }
                if (btDashboard.isSelected()) {
                    //             try {
//                initWorkspace("Dashboard");
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
                    lbTitle.setText("Dashboard");
                }
                if (btNhanVien.isSelected()) {
                    try {
                        initWorkspace("NhanVienTableView");
//                pnWorkspace.getChildren().clear();
//               Parent parrent= App.loadFXML("NhanVienTableView");
//               pnWorkspace.getChildren().add(parrent);
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("Nhân Viên");
                }
                if (btKho.isSelected()) {
                    try {
                        initWorkspace("Kho");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("Kho");
                }
                if (btVatTu.isSelected()) {
                    try {
                        initWorkspace("VatTu");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("Vật Tư");
                }
                if (btDatHang.isSelected()) {
                    try {
                        initWorkspace("DDH");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("Đặt Hàng");
                }
                if (btXuatHang.isSelected()) {
//                    try {
//                        initWorkspace("DDH");
//                    } catch (IOException ex) {
//                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    lbTitle.setText("Xuất Hàng");
                }
                if (btPhieuNhap.isSelected()) {
                    try {
                        initWorkspace("NhapHang");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("PhieuNhap");
                }
                 if (btBaoCao.isSelected()) {
                    try {
                        initWorkspace("BaoCao");
                    } catch (IOException ex) {
                        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbTitle.setText("Báo Cáo");
                }
            }
        });

        btDashboard.setSelected(true);
//       
    }

    public boolean saveCheck(RadioButton button) {
        if (button != null) {
            String btToString = button.toString();
//            System.out.println(btToString);
            if (btToString.equals(tabKho)) {
                return khoCommandHistory.isCommandStackEmpty();
            }
            if (btToString.equals(tabVatTu)) {
                return vatTuCommandHistory.isCommandStackEmpty();
            }
        }
        return true;
    }

    private void initClock() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");
                    DateTimeFormatter tf = DateTimeFormatter.ofPattern("hh:mm:ss a");
                    LocalTime currentTime = LocalTime.now();
                    LocalDate currentDate = LocalDate.now();
                    lbDate.setText(currentDate.format(df));
                    lbTime.setText(currentTime.format(tf));
                }),
                        new KeyFrame(Duration.seconds(1))
                );
                clock.setCycleCount(Animation.INDEFINITE);
                clock.play();
            }
        });
    }

    private void initWorkspace(String fxml) throws IOException {
        pnWorkspace.getChildren().clear();
        Parent parrent = FXMLLoader.load(getClass().getResource("../../../../fxml/" + fxml + ".fxml"));
        AnchorPane.setTopAnchor(parrent, 0.0);
        AnchorPane.setRightAnchor(parrent, 0.0);
        AnchorPane.setBottomAnchor(parrent, 0.0);
        AnchorPane.setLeftAnchor(parrent, 0.0);
        pnWorkspace.getChildren().add(parrent);

    }

    @FXML
    void initialize() throws IOException {
        btUserInfo.setText(DBConnectUtil.username);
        initClock();
        initButtonMenuBar();
        btDashboard.getStyleClass().remove("radio-button");
        btDashboard.getStyleClass().add("toggle-button");
        btNhanVien.getStyleClass().remove("radio-button");
        btNhanVien.getStyleClass().add("toggle-button");
        btKho.getStyleClass().remove("radio-button");
        btKho.getStyleClass().add("toggle-button");
        btVatTu.getStyleClass().remove("radio-button");
        btVatTu.getStyleClass().add("toggle-button");

        btDatHang.getStyleClass().remove("radio-button");
        btDatHang.getStyleClass().add("toggle-button");
        btXuatHang.getStyleClass().remove("radio-button");
        btXuatHang.getStyleClass().add("toggle-button");

    }
}
