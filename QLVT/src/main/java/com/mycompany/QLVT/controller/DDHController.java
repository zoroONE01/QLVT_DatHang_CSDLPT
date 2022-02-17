/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.mycompany.QLVT.Entity.CTDDH;
import com.mycompany.QLVT.Entity.CTPhieuXuat;
import com.mycompany.QLVT.Entity.DDH;
import com.mycompany.QLVT.Entity.ItemVatTu;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import static com.mycompany.QLVT.controller.PhieuXuatController.listCTPhieuXuat;
import com.mycompany.QLVT.model.DDHCommandModel;
import com.mycompany.QLVT.model.DDHTableModel;
import com.mycompany.QLVT.service.CTDDHService;
import com.mycompany.QLVT.service.CTPhieuXuatService;
import com.mycompany.QLVT.service.DDHService;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zoroONE01
 */
public class DDHController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    @FXML
//    private JFXListView<String> lvHistoryCommand;
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
    private TableColumn<DDH, String> clTrangThai;

    @FXML
    private JFXButton btAdd;

    @FXML
    private VBox vbOption;

    @FXML
    private JFXButton btEdit;

    @FXML
    private JFXButton btDelete;

//    @FXML
//    private JFXButton btSave;
    @FXML
    private JFXButton btImport;

//    @FXML
//    private JFXButton btOutput;
    @FXML
    private JFXButton btReload;

    @FXML
    private ContextMenu cmDSDDH;

    @FXML
    private MenuItem miView;

    private ObservableList<DDH> listDDH;

    public static List<DDH> list;

    public DDH ddh;

    public static List<CTDDH> listCTDDH = new ArrayList<>();

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
                    String error = DDHDetailController.addDDH();
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
    void showDeleteForm(ActionEvent event) {
        CTDDHService service = new CTDDHService();
        listCTDDH = (List<CTDDH>) service.findOne(ddh.getMaDDH());
        Platform.runLater(() -> {
            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
            JFXDialogLayout content = new JFXDialogLayout();
            Parent root = null;
            DDHDetailController DDHDetailController = new DDHDetailController();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../../../fxml/DDHDetail.fxml"));
                fxmlLoader.setController(DDHDetailController);
                root = (Parent) fxmlLoader.load();
                DDHDetailController.initDelele(ddh);
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
                String error = DDHDetailController.deleleDDH(ddh);
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
        CTDDHService service = new CTDDHService();
        listCTDDH = (List<CTDDH>) service.findOne(ddh.getMaDDH());
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
                    DDHDetailController.initUpdate(ddh);
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
                    String error = DDHDetailController.updateDDH();
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
    void showCTDDH(ActionEvent event) {
        CTDDHService service = new CTDDHService();
        listCTDDH = (List<CTDDH>) service.findOne(ddh.getMaDDH());
        if (listCTDDH == null) {
            System.out.println("list null");
            return;
        }
//        listCTDDH = (List<CTDDH>) service.findOne(ddh.getMaDDH());
        Platform.runLater(() -> {
            Stage owner = (Stage) miView.getParentPopup().getOwnerWindow();
            StackPane parentStackPane = (StackPane) owner.getScene().getRoot();

//            StackPane parentStackPane = (StackPane) ((Node) event.getTarget()).getScene().getRoot();
            JFXDialogLayout content = new JFXDialogLayout();
            Parent root = null;
            DDHDetailController DDHDetailController = new DDHDetailController();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DDHController.this.getClass().getResource("../../../../fxml/DDHDetail.fxml"));
                fxmlLoader.setController(DDHDetailController);
                root = (Parent) fxmlLoader.load();
                DDHDetailController.initView(ddh);
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            content.setHeading(root);
            JFXDialog noti = new JFXDialog(parentStackPane, content, JFXDialog.DialogTransition.CENTER);
            Image image1 = new Image(DDHController.this.getClass().getResourceAsStream("../../../../img/delete_20px.png"));
            JFXButton btClose = new JFXButton(null, new ImageView(image1));
            btClose.setButtonType(JFXButton.ButtonType.FLAT);
            btClose.setCursor(Cursor.HAND);
            btClose.setOnAction((ActionEvent event1) -> {
                noti.close();
            });
            content.setActions(btClose);
            noti.show();
        });
    }

    @FXML
    void reloadTable(ActionEvent event) {
//        if (!MainController.DDHCommandHistory.isCommandStackEmpty()) {
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
////                MainController.DDHCommandHistory.clearAllStack();
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
        clTrangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        DDHTableModel = new DDHTableModel();
//        if (MainController.DDHCommandHistory.isCommandStackEmpty()) {

        list = new DDHService().findAll();

//        } else {
//            list = MainController.DDHCommandHistory.getCommandStack().peek().getList();
//        }
        DDHTableModel.setDDHList(list);
        tbDSDDH.setItems(DDHTableModel.getDDHList());
        tbDSDDH.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
//                System.out.println(newSelection.getTrangThai());
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                btImport.setDisable(false);
                int index = tbDSDDH.getSelectionModel().getSelectedIndex();
                ddh = tbDSDDH.getItems().get(index);
            }
            if (newSelection.getTrangThai().equals("Chưa nhập hàng")) {
                btEdit.setDisable(false);
                btDelete.setDisable(false);
                btImport.setDisable(false);

            } else {
                btImport.setDisable(true);
                btEdit.setDisable(true);
                btDelete.setDisable(true);
            }

        });
//        initListCommandHistory();
    }

    public void initTable() {
        tbDSDDH.getItems().clear();
        list = new DDHService().findAll();
        DDHTableModel.setDDHList(list);
    }

    public void initListCommandHistory() {
        DDHCommandModel = new DDHCommandModel();
        DDHCommandModel.setCommandList(MainController.DDHCommandHistory.getCommandStack());
//        lvHistoryCommand.setItems(DDHCommandModel.getCommandList());
        if (!MainController.DDHCommandHistory.isCommandStackEmpty()) {
            btImport.setDisable(false);
//            btSave.setDisable(false);
        } else {
            btImport.setDisable(true);
//            btSave.setDisable(true);
        }
//        if (!MainController.DDHCommandHistory.isSubStackEmpty()) {
//            btOutput.setDisable(false);
//        } else {
//            btOutput.setDisable(true);
//        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        lvHistoryCommand.setDisable(true);
        if (DBConnectUtil.myGroup.equals("CONGTY")) {
            vbOption.disableProperty().set(true);
        } else {
            vbOption.disableProperty().set(false);
        }
        btEdit.setDisable(true);
        btDelete.setDisable(true);

//        btSave.setDisable(true);
        btImport.setDisable(true);
//        btOutput.setDisable(true);
        initTableFromDatabase();

//        icLoading = new ImageView(new Image(getClass().getResourceAsStream("../../../../img/loading.gif"), 40, 40, false, true));
    }

}
