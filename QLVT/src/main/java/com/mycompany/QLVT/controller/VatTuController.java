/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Entity.VatTu;
import com.mycompany.QLVT.model.VatTuTableModel;
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
    private TableView<VatTu> tbDSVT;

    @FXML
    private TableColumn<VatTu, String> clMaVT;

    @FXML
    private TableColumn<VatTu, String> clTenVT;

    @FXML
    private TableColumn<VatTu, String> clDVT;

    private ObservableList<VatTu> listVatTu;

    private ImageView icLoading;

    public VatTu vatTu;

    @FXML
    void reloadTable(ActionEvent event) {

    }

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
//                    System.out.println(new FXMLLoader(getClass().getResource("../../../../fxml/KhoDetail.fxml")));
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
                    content.setBody(icLoading);
                    String error = vatTuDetailController.addVatTu();
                    if (error == "") {
                        noti.setContent(content);
                        noti.close();
                        initTableVatTu();

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

    }

    @FXML
    void showEditForm(ActionEvent event) {

    }

    public void initTableVatTu() {
        clMaVT.setCellValueFactory(new PropertyValueFactory<>("maVT"));
        clTenVT.setCellValueFactory(new PropertyValueFactory<>("tenVT"));
        clDVT.setCellValueFactory(new PropertyValueFactory<>("DVT"));
        VatTuTableModel model = new VatTuTableModel();
        List<VatTu> list = new VatTuService().findAll();
        model.setVatTuList(list);
        tbDSVT.setItems(model.getVatTuList());
        tbDSVT.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                int index = tbDSVT.getSelectionModel().getSelectedIndex();
                vatTu = tbDSVT.getItems().get(index);
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
