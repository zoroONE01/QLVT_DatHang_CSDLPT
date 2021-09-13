package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.App;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

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
    private ToggleButton tgbtDashboard;

    @FXML
    private ToggleButton tgbtNhanVien;

    @FXML
    private ToggleButton tgbtKho;

    @FXML
    private ToggleButton tgbtVatTu;
    
    @FXML
    private ToggleButton tgbtDonDatHang;

    @FXML
    private AnchorPane pnWorkspace;

    public MainController() {
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
                    DBConnectUtil.close();
                    LoginController loginController = new LoginController();
                    Parent root = null;
                    try {
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
        tgbtDashboard.setToggleGroup(group);
        tgbtNhanVien.setToggleGroup(group);
        tgbtDonDatHang.setToggleGroup(group);
        tgbtVatTu.setToggleGroup(group);
        tgbtKho.setToggleGroup(group);

        tgbtKho.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                initWorkspace("Kho");
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbTitle.setText("Kho");
        }));
        tgbtDashboard.selectedProperty().addListener(((observable, oldValue, newValue) -> {
//            try {
//                initWorkspace("Dashboard");
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            lbTitle.setText("Dashboard");
        }));
        tgbtNhanVien.selectedProperty().addListener(((observable, oldValue, newValue) -> {
//            try {
//                initWorkspace("NhanVienTableView");
////                pnWorkspace.getChildren().clear();
////               Parent parrent= App.loadFXML("NhanVienTableView");
////               pnWorkspace.getChildren().add(parrent);
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            lbTitle.setText("Nhân Viên");
        }));
        tgbtDonDatHang.selectedProperty().addListener(((observable, oldValue, newValue) -> {
//            try {
//                initWorkspace("DonDatHang");
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            lbTitle.setText("Đơn Đặt Hàng");
        }));
        tgbtVatTu.selectedProperty().addListener(((observable, oldValue, newValue) -> {
//            try {
//                initWorkspace("VatTu");
//            } catch (IOException ex) {
//                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            lbTitle.setText("Vật Tư");
        }));
        tgbtDashboard.setSelected(true);
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
    }
}
