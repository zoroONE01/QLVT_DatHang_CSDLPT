/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.PhieuXuat;
import com.mycompany.QLVT.model.PhieuXuatTableModel;
import com.mycompany.QLVT.service.CTPhieuXuatService;
import com.mycompany.QLVT.service.PhieuXuatService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class PhieuXuatController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    private JFXListView<String> lvHistoryCommand;
    @FXML
    private TableView<PhieuXuat> tbDSPhieuXuat;

    @FXML
    private TableColumn<PhieuXuat, String> clMaPhieuXuat;

    @FXML
    private TableColumn<PhieuXuat, String> clNgay;

    @FXML
    private TableColumn<PhieuXuat, String> clKhachHang;

    @FXML
    private TableColumn<PhieuXuat, String> clKho;

    @FXML
    private TableColumn<PhieuXuat, String> clNhanVien;

    @FXML
    private JFXButton btAdd;

    @FXML
    private JFXButton btEdit;

    @FXML
    private JFXButton btDelete;

    @FXML
    private JFXButton btReload;


    private ObservableList<PhieuXuat> listPhieuXuat;

    public static List<PhieuXuat> list;

    public PhieuXuat phieuXuat;

    public static List<CTPhieuXuat> listCTPhieuXuat = new ArrayList<>();

    public PhieuXuatTableModel PhieuXuatTableModel;


    @FXML
    void showAddFrom(ActionEvent event) {
        Platform.runLater(() -> {
            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
            JFXDialogLayout content = new JFXDialogLayout();
            Parent root = null;
            PhieuXuatDetailController PhieuXuatDetailController = new PhieuXuatDetailController();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/PhieuXuatDetail.fxml"));
                fxmlLoader.setController(PhieuXuatDetailController);
                root = (Parent) fxmlLoader.load();
                PhieuXuatDetailController.initAdd();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.setHeading(root);
            JFXDialog noti = new JFXDialog(parentStackPane, content, JFXDialog.DialogTransition.CENTER);
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
            });
            btAccept.setOnAction((ActionEvent event1) -> {
                String error = PhieuXuatDetailController.addPhieuXuat();
                if (error == "") {
                    noti.close();
                    initTable();
//                        initListCommandHistory();
                } else {
                    content.setBody(new Text(error));
                }
            });
            content.setActions(btAccept, btClose);
            noti.show();
        });
    }

    @FXML
    void showDeleteForm(ActionEvent event) {
        CTPhieuXuatService service = new CTPhieuXuatService();
        listCTPhieuXuat = (List<CTPhieuXuat>) service.findOne(phieuXuat.getMaPhieuXuat());
        Platform.runLater(() -> {
            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
            JFXDialogLayout content = new JFXDialogLayout();
            Parent root = null;
            PhieuXuatDetailController PhieuXuatDetailController = new PhieuXuatDetailController();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/PhieuXuatDetail.fxml"));
                fxmlLoader.setController(PhieuXuatDetailController);
                root = (Parent) fxmlLoader.load();
                PhieuXuatDetailController.initDelele(phieuXuat);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.setHeading(root);
            JFXDialog noti = new JFXDialog(parentStackPane, content, JFXDialog.DialogTransition.CENTER);
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
            });
            btAccept.setOnAction((ActionEvent event1) -> {
                String error = PhieuXuatDetailController.delelePhieuXuat(phieuXuat);
                if (error == "") {
                    noti.close();
                    initTable();
//                    initListCommandHistory();
                } else {
                    content.setBody(new Text(error));
                }
            });
            content.setActions(btAccept, btClose);
            noti.show();
        });
    }

    @FXML
    void showEditForm(ActionEvent event) {
        CTPhieuXuatService service = new CTPhieuXuatService();
        listCTPhieuXuat = (List<CTPhieuXuat>) service.findOne(phieuXuat.getMaPhieuXuat());
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                PhieuXuatDetailController PhieuXuatDetailController = new PhieuXuatDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/PhieuXuatDetail.fxml"));
                    fxmlLoader.setController(PhieuXuatDetailController);
                    root = (Parent) fxmlLoader.load();
                    PhieuXuatDetailController.initUpdate(phieuXuat);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                content.setHeading(root);
                JFXDialog noti = new JFXDialog(parentStackPane, content, JFXDialog.DialogTransition.CENTER);
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
                });
                btAccept.setOnAction((ActionEvent event1) -> {
                    String error = PhieuXuatDetailController.updatePhieuXuat();
                    if (error == "") {
                        noti.close();
                        initTable();
//                        initListCommandHistory();
                    } else {
                        content.setBody(new Text(error));
                    }
                });
                content.setActions(btAccept, btClose);
                noti.show();
            }
        });
    }

    @FXML
    void reloadTable(ActionEvent event) {
//        if (!MainController.PhieuXuatCommandHistory.isCommandStackEmpty()) {
////            Platform.runLater(() -> {
//            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
//            JFXDialogLayout content = new JFXDialogLayout();
//            content.setHeading(new Text("Thông Báo"));
//            content.setBody(new Text("Dữ liệu thay đổi chưa được lưu.\nTiếp tục thao tác mà không lưu thay đổi?"));
//            JFXDialog noti = new JFXDialog(parentStackPane, content, JFXDialog.DialogTransition.CENTER);
//            Image image1 = new Image(getClass().getResourceAsStream("../../../../img/delete_20px.png"));
//            Image image2 = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
//            JFXButton btClose = new JFXButton(null, new ImageView(image1));
//            JFXButton btAccept = new JFXButton(null, new ImageView(image2));
//            btClose.setButtonType(JFXButton.ButtonType.FLAT);
//            btAccept.setButtonType(JFXButton.ButtonType.FLAT);
//            btClose.setCursor(Cursor.HAND);
//            btAccept.setCursor(Cursor.HAND);
//            btClose.setOnAction((ActionEvent event1) -> {
//                noti.close();
//            });
//            btAccept.setOnAction((ActionEvent event1) -> {
//                noti.close();
////                MainController.PhieuXuatCommandHistory.clearAllStack();
//                initTableFromDatabase();
//            });
//            content.setActions(btAccept, btClose);
//            noti.show();
////            });
//        } else {
//            initTableFromDatabase();
//        }
        initTable();
//        initTableFromDatabase();
    }






    public void initTableFromDatabase() {
        clMaPhieuXuat.setCellValueFactory(new PropertyValueFactory<>("maPhieuXuat"));
        clNgay.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        clKhachHang.setCellValueFactory(new PropertyValueFactory<>("khachHang"));
        clKho.setCellValueFactory(new PropertyValueFactory<>("maKhoTenKho"));
        clNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNVHoTen"));
//        clTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        PhieuXuatTableModel = new PhieuXuatTableModel();
//        if (MainController.PhieuXuatCommandHistory.isCommandStackEmpty()) {

        list = new PhieuXuatService().findAll();

//        } else {
//            list = MainController.PhieuXuatCommandHistory.getCommandStack().peek().getList();
//        }
        PhieuXuatTableModel.setPhieuXuatList(list);
        tbDSPhieuXuat.setItems(PhieuXuatTableModel.getPhieuXuatList());
        tbDSPhieuXuat.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null && newSelection.getTrangThai().equals("Chưa nhập hàng")) {
////                System.out.println(newSelection.getTrangThai());
//                btEdit.setDisable(false);
//                btDelete.setDisable(false);
//                int index = tbDSPhieuXuat.getSelectionModel().getSelectedIndex();
//                phieuXuat = tbDSPhieuXuat.getItems().get(index);
//                return;
//            }
//            btImport.setDisable(true);
            btEdit.setDisable(true);
            btDelete.setDisable(true);
        });
//        initListCommandHistory();
    }

    public void initTable() {
        tbDSPhieuXuat.getItems().clear();
        list = new PhieuXuatService().findAll();
        PhieuXuatTableModel.setPhieuXuatList(list);
    }

//    public void initListCommandHistory() {
//        PhieuXuatCommandModel = new PhieuXuatCommandModel();
//        PhieuXuatCommandModel.setCommandList(MainController.PhieuXuatCommandHistory.getCommandStack());
////        lvHistoryCommand.setItems(PhieuXuatCommandModel.getCommandList());
//        if (!MainController.PhieuXuatCommandHistory.isCommandStackEmpty()) {
//            btImport.setDisable(false);
////            btSave.setDisable(false);
//        } else {
//            btImport.setDisable(true);
////            btSave.setDisable(true);
//        }
////        if (!MainController.PhieuXuatCommandHistory.isSubStackEmpty()) {
////            btOutput.setDisable(false);
////        } else {
////            btOutput.setDisable(true);
////        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        lvHistoryCommand.setDisable(true);
        btEdit.setDisable(true);
        btDelete.setDisable(true);

//        btSave.setDisable(true);
//        btImport.setDisable(true);
//        btOutput.setDisable(true);
        initTableFromDatabase();

//        icLoading = new ImageView(new Image(getClass().getResourceAsStream("../../../../img/loading.gif"), 40, 40, false, true));
    }

}
