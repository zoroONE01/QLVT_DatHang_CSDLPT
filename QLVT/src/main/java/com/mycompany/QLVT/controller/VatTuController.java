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
import com.mycompany.QLVT.Entity.SoLuongTonKho;
import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.model.VatTuCommandModel;
import com.mycompany.QLVT.model.VatTuTableModel;
import com.mycompany.QLVT.model.VatTuTonKhoTableModel;
import com.mycompany.QLVT.service.VatTuService;
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
public class VatTuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXListView<String> lvHistoryCommand;

    @FXML
    private TableView<VatTu> tbDSVT;

    @FXML
    private TableView<SoLuongTonKho> tbPhanBoVT;

    @FXML
    private TableColumn<SoLuongTonKho, String> clKho;

    @FXML
    private TableColumn<SoLuongTonKho, String> clSoLuongTonKho;

    @FXML
    private TableColumn<VatTu, String> clMaVT;

    @FXML
    private TableColumn<VatTu, String> clTenVT;

    @FXML
    private TableColumn<VatTu, String> clDVT;

    @FXML
    private TableColumn<VatTu, String> clSoLuongTon;

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

    private ObservableList<VatTu> listVatTu;

    public static List<VatTu> list;

    public List<SoLuongTonKho> soLuongTonKho;

    private ImageView icLoading;

    public VatTu vatTu;

    public VatTuTableModel vatTuTableModel;

    public VatTuTonKhoTableModel vatTuTonKhoTableModel;

    public VatTuCommandModel vatTuCommandModel;

    @FXML
    void showAddFrom(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
                JFXDialogLayout content = new JFXDialogLayout();
                Parent root = null;
                VatTuDetailController vatTuDetailController = new VatTuDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/VatTuDetail.fxml"));
                    fxmlLoader.setController(vatTuDetailController);
                    root = (Parent) fxmlLoader.load();
                    vatTuDetailController.initAdd();
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
                    String error = vatTuDetailController.addVatTu();
                    if (error == "") {
                        noti.close();
                        initTable();
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
                VatTuDetailController vatTuDetailController = new VatTuDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/VatTuDetail.fxml"));
                    fxmlLoader.setController(vatTuDetailController);
                    root = (Parent) fxmlLoader.load();
                    vatTuDetailController.initDelele(vatTu);
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
                    String error = vatTuDetailController.deleleVatTu(vatTu);
                    if (error == "") {
                        noti.close();
                        initTable();
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
                VatTuDetailController VatTuDetailController = new VatTuDetailController();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/VatTuDetail.fxml"));
                    fxmlLoader.setController(VatTuDetailController);
                    root = (Parent) fxmlLoader.load();
                    VatTuDetailController.initUpdate(vatTu);
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
                    String error = VatTuDetailController.updateVatTu(vatTu);
                    if (error == "") {
                        noti.close();
                        initTable();
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
        if (!MainController.vatTuCommandHistory.isCommandStackEmpty()) {
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
                MainController.vatTuCommandHistory.clearAllStack();
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
        if (!MainController.vatTuCommandHistory.isCommandStackEmpty()) {
            list = MainController.vatTuCommandHistory.undo();
            initTable();
            initListCommandHistory();
        }

    }

    @FXML
    void redoCommand(ActionEvent event) {
        if (!MainController.vatTuCommandHistory.isSubStackEmpty()) {
            list = MainController.vatTuCommandHistory.redo();
            initTable();
            initListCommandHistory();
        }
    }

    @FXML
    void saveOnDatabase(ActionEvent event) {
        MainController.vatTuCommandHistory.ExectueAllToDatebase();
        initListCommandHistory();
    }

    public void initTableFromDatabase() {
        clMaVT.setCellValueFactory(new PropertyValueFactory<>("maVT"));
        clTenVT.setCellValueFactory(new PropertyValueFactory<>("tenVT"));
        clDVT.setCellValueFactory(new PropertyValueFactory<>("DVT"));
        clSoLuongTon.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));
        clSoLuongTonKho.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));
        clKho.setCellValueFactory(new PropertyValueFactory<>("kho"));
        vatTuTableModel = new VatTuTableModel();
        if (MainController.vatTuCommandHistory.isCommandStackEmpty()) {
            list = new VatTuService().findAll();
        } else {
            list = MainController.vatTuCommandHistory.getCommandStack().peek().getList();
        }
        vatTuTableModel.setVatTuList(list);
        tbPhanBoVT.visibleProperty().set(false);
        tbDSVT.setItems(vatTuTableModel.getVatTuList());
        tbDSVT.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                int index = tbDSVT.getSelectionModel().getSelectedIndex();
                vatTu = tbDSVT.getItems().get(index);
                soLuongTonKho = new VatTuService().findSoLuongTonKho(vatTu.getMaVT());
                if (soLuongTonKho != null) {
                    tbPhanBoVT.visibleProperty().set(true);
                    vatTuTonKhoTableModel = new VatTuTonKhoTableModel(soLuongTonKho);
                    tbPhanBoVT.setItems(vatTuTonKhoTableModel.getVatTuList());
                    tbPhanBoVT.setSelectionModel(null);
                } else {
                    tbPhanBoVT.visibleProperty().set(false);
                    tbPhanBoVT.getItems().clear();
                }
            }
        });
        initListCommandHistory();
    }

    public void initTable() {
        tbDSVT.getItems().clear();
        vatTuTableModel.setVatTuList(list);
    }

    public void initListCommandHistory() {
        vatTuCommandModel = new VatTuCommandModel();
        vatTuCommandModel.setCommandList(MainController.vatTuCommandHistory.getCommandStack());
        lvHistoryCommand.setItems(vatTuCommandModel.getCommandList());
        if (!MainController.vatTuCommandHistory.isCommandStackEmpty()) {
            btUndo.setDisable(false);
            btSave.setDisable(false);
        } else {
            btUndo.setDisable(true);
            btSave.setDisable(true);
        }
        if (!MainController.vatTuCommandHistory.isSubStackEmpty()) {
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
