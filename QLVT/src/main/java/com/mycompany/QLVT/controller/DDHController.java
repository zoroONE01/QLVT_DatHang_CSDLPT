/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.model.DDHCommandModel;
import com.mycompany.QLVT.model.DDHTableModel;
import com.mycompany.QLVT.service.DDHService;
import java.io.IOException;
import java.net.URL;
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
public class DDHController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXListView<String> lvHistoryCommand;

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
    private TableColumn<DDH, String> clNhanVien;

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


    private ObservableList<DDH> listDDH;

    public static List<DDH> list;

    public DDH ddh;

    public DDHTableModel DDHTableModel;

    public DDHCommandModel DDHCommandModel;

    @FXML
    void showAddFrom(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                DDHDetailController DDHDetailController = new DDHDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/DDHDetail.fxml"));
                    fxmlLoader.setController(DDHDetailController);
                    root = (Parent) fxmlLoader.load();
                    DDHDetailController.initAdd();
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
//                    String error = DDHDetailController.addDDH();
//                    if (error == "") {
//                        noti.close();
//                        initTable();
//                        initListCommandHistory();
//                    } else {
//                        content.setBody(new Text(error));
//                    }
                });
                content.setActions(btAccept, btClose);
                noti.show();
            }
        });
    }

    @FXML
    void showDeleteForm(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                DDHDetailController DDHDetailController = new DDHDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/DDHDetail.fxml"));
                    fxmlLoader.setController(DDHDetailController);
                    root = (Parent) fxmlLoader.load();
//                    DDHDetailController.initDelele(ddh);
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
//                    String error = DDHDetailController.deleleDDH(ddh);
//                    if (error == "") {
//                        noti.close();
//                        initTable();
//                        initListCommandHistory();
//                    } else {
//                        content.setBody(new Text(error));
//                    }
                });
                content.setActions(btAccept, btClose);
                noti.show();
            }
        });
    }

    @FXML
    void showEditForm(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                DDHDetailController DDHDetailController = new DDHDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/DDHDetail.fxml"));
                    fxmlLoader.setController(DDHDetailController);
                    root = (Parent) fxmlLoader.load();
//                    DDHDetailController.initUpdate(ddh);
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
//                    String error = DDHDetailController.updateDDH(ddh);
//                    if (error == "") {
//                        noti.close();
//                        initTable();
//                        initListCommandHistory();
//                    } else {
//                        content.setBody(new Text(error));
//                    }
                });
                content.setActions(btAccept, btClose);
                noti.show();
            }
        });
    }

    @FXML
    void reloadTable(ActionEvent event) {
        if (!MainController.DDHCommandHistory.isCommandStackEmpty()) {
//            Platform.runLater(() -> {
            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("Thông Báo"));
            content.setBody(new Text("Dữ liệu thay đổi chưa được lưu.\nTiếp tục thao tác mà không lưu thay đổi?"));
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
                noti.close();
                MainController.DDHCommandHistory.clearAllStack();
                initTableFromDatabase();
            });
            content.setActions(btAccept, btClose);
            noti.show();
//            });
        } else {
            initTableFromDatabase();
        }
    }

    @FXML
    void undoCommand(ActionEvent event) {
        if (!MainController.DDHCommandHistory.isCommandStackEmpty()) {
            list = MainController.DDHCommandHistory.undo();
            initTable();
            initListCommandHistory();
        }

    }

    @FXML
    void redoCommand(ActionEvent event) {
        if (!MainController.DDHCommandHistory.isSubStackEmpty()) {
            list = MainController.DDHCommandHistory.redo();
            initTable();
            initListCommandHistory();
        }
    }

    @FXML
    void saveOnDatabase(ActionEvent event) {
        MainController.DDHCommandHistory.ExectueAllToDatebase();
        initListCommandHistory();
    }

    public void initTableFromDatabase() {
        clMaDDH.setCellValueFactory(new PropertyValueFactory<>("maDDH"));
        clNgay.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        clNCC.setCellValueFactory(new PropertyValueFactory<>("NCC"));
        clKho.setCellValueFactory(new PropertyValueFactory<>("maKhoTenKho"));
        clNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNVHoTen"));
        DDHTableModel = new DDHTableModel();
//        if (MainController.DDHCommandHistory.isCommandStackEmpty()) {
//            list = new DDHService().findAll();
//        } else {
//            list = MainController.DDHCommandHistory.getCommandStack().peek().getList();
//        }
        DDHTableModel.setDDHList(list);
        tbDSDDH.setItems(DDHTableModel.getDDHList());
        tbDSDDH.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                int index = tbDSDDH.getSelectionModel().getSelectedIndex();
                ddh = tbDSDDH.getItems().get(index);
            }
        });
        initListCommandHistory();
    }

    public void initTable() {
        tbDSDDH.getItems().clear();
        DDHTableModel.setDDHList(list);
    }

    public void initListCommandHistory() {
        DDHCommandModel = new DDHCommandModel();
        DDHCommandModel.setCommandList(MainController.DDHCommandHistory.getCommandStack());
        lvHistoryCommand.setItems(DDHCommandModel.getCommandList());
        if (!MainController.DDHCommandHistory.isCommandStackEmpty()) {
            btUndo.setDisable(false);
            btSave.setDisable(false);
        } else {
            btUndo.setDisable(true);
            btSave.setDisable(true);
        }
        if (!MainController.DDHCommandHistory.isSubStackEmpty()) {
            btRedo.setDisable(false);
        } else {
            btRedo.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvHistoryCommand.setDisable(true);
        btEdit.setDisable(true);
        btDelete.setDisable(true);
        btSave.setDisable(true);
        btUndo.setDisable(true);
        btRedo.setDisable(true);
       initTableFromDatabase();
//        icLoading = new ImageView(new Image(getClass().getResourceAsStream("../../../../img/loading.gif"), 40, 40, false, true));

    }

}
