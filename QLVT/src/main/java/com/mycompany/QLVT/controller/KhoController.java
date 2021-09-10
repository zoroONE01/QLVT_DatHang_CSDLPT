/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Entity.Kho;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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

    private Kho kho;

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
                JFXButton btAdd = new JFXButton(null, new ImageView(image2));
                btClose.setButtonType(JFXButton.ButtonType.RAISED);
                btAdd.setButtonType(JFXButton.ButtonType.RAISED);
                btClose.setCursor(Cursor.HAND);
                btAdd.setCursor(Cursor.HAND);
                btClose.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                btAdd.setOnAction((ActionEvent event1) -> {

//                    khoDetailController.addKho();
                });
                content.setActions(btAdd, btClose);
//                content.setActions(btClose);
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
                JFXButton btAdd = new JFXButton(null, new ImageView(image2));
                btClose.setButtonType(JFXButton.ButtonType.RAISED);
                btAdd.setButtonType(JFXButton.ButtonType.RAISED);
                btClose.setCursor(Cursor.HAND);
                btAdd.setCursor(Cursor.HAND);
                btClose.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                btAdd.setOnAction((ActionEvent event1) -> {

//                    khoDetailController.addKho();
                });
                content.setActions(btAdd, btClose);
//                content.setActions(btClose);
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
                JFXButton btAdd = new JFXButton(null, new ImageView(image2));
                btClose.setButtonType(JFXButton.ButtonType.RAISED);
                btAdd.setButtonType(JFXButton.ButtonType.RAISED);
                btClose.setCursor(Cursor.HAND);
                btAdd.setCursor(Cursor.HAND);
                btClose.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                btAdd.setOnAction((ActionEvent event1) -> {

//                    khoDetailController.addKho();
                });
                content.setActions(btAdd, btClose);
//                content.setActions(btClose);
                noti.show();
            }
        });
    }

    public void initTableKho() {
        clMaKho.setCellValueFactory(new PropertyValueFactory<>("maKho"));
        clTenKho.setCellValueFactory(new PropertyValueFactory<>("tenKho"));
        clDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        clChiNhanh.setCellValueFactory(new PropertyValueFactory<>("TenCN"));
        KhoTableModel model = new KhoTableModel();
        List<Kho> list = new KhoService().findAll();
        model.setKhoList(list);
        tbDSKho.setItems(model.getKhoList());
        tbDSKho.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                int index = tbDSKho.getSelectionModel().getSelectedIndex();
                kho = tbDSKho.getItems().get(index);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableKho();
        btEdit.setDisable(true);
        btDelete.setDisable(true);
    }

}
