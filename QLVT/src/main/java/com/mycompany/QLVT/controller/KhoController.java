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
import com.mycompany.QLVT.Entity.Kho;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.model.KhoCommandModel;
import com.mycompany.QLVT.model.KhoTableModel;
import com.mycompany.QLVT.service.KhoService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class KhoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane pnKho;

    @FXML
    private JFXListView<String> lvHistoryCommand;

    @FXML
    private VBox pnMenuBar;

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
    private TableView<Kho> tbDSKho;

    @FXML
    private TableColumn<Kho, String> clMaKho;

    @FXML
    private TableColumn<Kho, String> clTenKho;

    @FXML
    private TableColumn<Kho, String> clDiaChi;

    @FXML
    private TableColumn<Kho, String> clChiNhanh;

    private ObservableList<Kho> listKho;

    public static List<Kho> list;

    private ImageView icLoading;

    public Kho kho;

    public KhoTableModel khoTableModel;

    public KhoCommandModel khoCommandModel;

    @FXML
    void showAddFrom(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                KhoDetailController khoDetailController = new KhoDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/KhoDetail.fxml"));
                    fxmlLoader.setController(khoDetailController);
                    root = (Parent) fxmlLoader.load();
                    khoDetailController.initAdd();
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
                    content.setBody(icLoading);
                    String error = khoDetailController.addKho();
                    if (error == "") {
                        noti.close();
                        initTableKho();
                        initListCommandHistory();
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
    void showDeleteForm(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                KhoDetailController khoDetailController = new KhoDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/KhoDetail.fxml"));
                    fxmlLoader.setController(khoDetailController);
                    root = (Parent) fxmlLoader.load();
                    khoDetailController.initDelele(kho);
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
//                    content.setBody(icLoading);
                    String error = khoDetailController.deleleKho(kho);
                    if (error == "") {
                        noti.close();
                        initTableKho();
                        initListCommandHistory();
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
    void showEditForm(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                KhoDetailController khoDetailController = new KhoDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/KhoDetail.fxml"));
                    fxmlLoader.setController(khoDetailController);
                    root = (Parent) fxmlLoader.load();
                    khoDetailController.initUpdate(kho);
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
//                    content.setBody(icLoading);
                    String error = khoDetailController.updateKho(kho);
                    if (error == "") {
                        noti.close();
                        initTableKho();
                        initListCommandHistory();
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
        if (!MainController.khoCommandHistory.isCommandStackEmpty()) {
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
                MainController.khoCommandHistory.clearAllStack();
                initTableKhoFromDatabase();
            });
            content.setActions(btAccept, btClose);
            noti.show();
//            });
        } else {
            initTableKhoFromDatabase();
        }
    }

    @FXML
    void undoCommand(ActionEvent event) {
        if (!MainController.khoCommandHistory.isCommandStackEmpty()) {
            list = MainController.khoCommandHistory.undo();
            initTableKho();
            initListCommandHistory();
        }

    }

    @FXML
    void redoCommand(ActionEvent event) {
        if (!MainController.khoCommandHistory.isSubStackEmpty()) {
            list = MainController.khoCommandHistory.redo();
            initTableKho();
            initListCommandHistory();
        }
    }

    @FXML
    void saveOnDatabase(ActionEvent event) {
        MainController.khoCommandHistory.ExectueAllToDatebase();
        initListCommandHistory();
    }

    public void initTableKhoFromDatabase() {
        clMaKho.setCellValueFactory(new PropertyValueFactory<>("maKho"));
        clTenKho.setCellValueFactory(new PropertyValueFactory<>("tenKho"));
        clDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        clChiNhanh.setCellValueFactory(new PropertyValueFactory<>("TenCN"));
        khoTableModel = new KhoTableModel();
        if (MainController.khoCommandHistory.isCommandStackEmpty()) {
            list = new KhoService().findAll(DBConnectUtil.chiNhanhSelected);
        } else {
            list = MainController.khoCommandHistory.getCommandStack().peek().getList();
        }
        khoTableModel.setKhoList(list);
        tbDSKho.setItems(khoTableModel.getKhoList());
        tbDSKho.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                int index = tbDSKho.getSelectionModel().getSelectedIndex();
                kho = tbDSKho.getItems().get(index);
            }
        });
        initListCommandHistory();
    }

    public void initTableKho() {
        tbDSKho.getItems().clear();
        khoTableModel.setKhoList(list);
    }

    public void initListCommandHistory() {
        khoCommandModel = new KhoCommandModel();
        khoCommandModel.setCommandList(MainController.khoCommandHistory.getCommandStack());
        lvHistoryCommand.setItems(khoCommandModel.getCommandList());
        if (!MainController.khoCommandHistory.isCommandStackEmpty()) {
            btUndo.setDisable(false);
            btSave.setDisable(false);
        } else {
            btUndo.setDisable(true);
            btSave.setDisable(true);
        }
        if (!MainController.khoCommandHistory.isSubStackEmpty()) {
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
        initTableKhoFromDatabase();
//        icLoading = new ImageView(new Image(getClass().getResourceAsStream("../../../../img/loading.gif"), 40, 40, false, true));
    }

}
