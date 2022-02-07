/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.mycompany.QLVT.Command.ActionDelete;
import com.mycompany.QLVT.Command.ActionHistory;
import com.mycompany.QLVT.Command.ActionListenerCommand;
import com.mycompany.QLVT.Command.ActionAdd;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.model.NhanVienTableModelTest;
import com.mycompany.QLVT.service.NhanVienServiceTest;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author MinhTo
 */
public class NhanVienViewControllerTest implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<NhanVien> nhanVienTable;

    @FXML
    private TableColumn<NhanVien, Integer> maNhanVien;

    @FXML
    private TableColumn<NhanVien, String> ho;

    @FXML
    private TableColumn<NhanVien, String> ten;

    @FXML
    private TableColumn<NhanVien, String> diaChi;

    @FXML
    private TableColumn<NhanVien, String> ngaySinh;

    @FXML
    private TableColumn<NhanVien, Integer> luong;

    @FXML
    private TableColumn<NhanVien, String> maCN;

    @FXML
    private TableColumn<NhanVien, Integer> trangThai;

    @FXML
    private Button themBTN;

    @FXML
    private Button luuBTN;

    @FXML
    private Button hoanTacBTN;
    @FXML
    private Button xoaBTN;

    private NhanVienTableModelTest nhanVienTableModel;

    private ActionHistory history = new ActionHistory();

    private NhanVienServiceTest nhanVienService = new NhanVienServiceTest();
    static int index = 1;
    private NhanVien nhanVienBeforeSave = new NhanVien();

    public void initModel(NhanVienTableModelTest nhanVienTableModel) {// ensure model is only set once:
        if (this.nhanVienTableModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.nhanVienTableModel = nhanVienTableModel;

        Callback<TableColumn, TableCell> cellFactory = new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn p) {
                return new TextFieldTableCell();
            }

        };
        // Định nghĩa cách để lấy dữ liệu cho mỗi ô.
        maNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));

        maNhanVien.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        maNhanVien.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, Integer> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setMaNhanVien(event.getNewValue());
                System.out.println(event.getNewValue());
            }
        });
        ho.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("ho"));
        ho.setCellFactory(TextFieldTableCell.forTableColumn());
        ho.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, String> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setHo(event.getNewValue());     //To change body of generated methods, choose Tools | Templates.
            }
        });

        ten.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("ten"));
        ten.setCellFactory(TextFieldTableCell.forTableColumn());
        ten.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, String> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setTen(event.getNewValue());
            }
        });
        diaChi.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("diaChi"));
        diaChi.setCellFactory(TextFieldTableCell.forTableColumn());
        diaChi.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, String> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setDiaChi(event.getNewValue());
            }
        });
        luong.setCellValueFactory(new PropertyValueFactory<>("luong"));
        luong.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        luong.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Integer>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<NhanVien, Integer> event) {
//                NhanVien nhanVien = event.getRowValue();
//                nhanVien.setLuong(event.getNewValue());
//            }
//        });
        ngaySinh.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("ngaySinh"));
        ngaySinh.setCellFactory(TextFieldTableCell.forTableColumn());
        ngaySinh.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, String> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setNgaySinh(event.getNewValue());
            }
        });
        maCN.setCellValueFactory(new PropertyValueFactory<>("maCN"));
        maCN.setCellFactory(TextFieldTableCell.forTableColumn());
        maCN.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, String> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setMaCN(event.getNewValue());
            }
        });
        trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        trangThai.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        trangThai.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, Integer> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setTrangThai(event.getNewValue());
            }
        });

        // Sét xắp xếp theo userName
        luong.setSortType(TableColumn.SortType.DESCENDING);
        //lastNameCol.setSortable(false);

        nhanVienTable.getColumns();
        nhanVienTable.setItems(nhanVienTableModel.getNhanVienList());

        nhanVienTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            nhanVienTableModel.setCurrentNhanVien(newValue);

            NhanVien nv = observable.getValue();
            System.out.println("row new");

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

        nhanVienTableModel.getCurrentNhanVienProperty().addListener((obs, oldNhanVien, newNhanVien) -> {
            if (newNhanVien == null) {
                nhanVienTable.getSelectionModel().clearSelection();
            } else {
                System.out.println(newNhanVien.getMaNhanVien());
                nhanVienTable.getSelectionModel().select(newNhanVien);
            }

        });

        nhanVienTable.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 0) {
                // edit dữ liê
                nhanVienBeforeSave = nhanVienTable.getSelectionModel().getSelectedItem();

                System.out.println("edit du lieu");
            }
        });

    }

    @Override //can view here
    public void initialize(URL url, ResourceBundle rb) {
        // hello
        System.out.println("Ban");
        NhanVienTableModelTest model = new NhanVienTableModelTest();
        List<NhanVien> list = new ArrayList<>();
        list = nhanVienService.findAll();
        model.setNhanVienList(list);
        initModel(model);

        nhanVienTable.setEditable(true);
        nhanVienTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nhanVienTable.getColumns().addListener(new ListChangeListener() {

            public boolean suspended;

            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {

                    this.suspended = true;
                    nhanVienTable.getColumns().setAll(maNhanVien, ho, ten, diaChi, luong, ngaySinh, maCN, trangThai);
                    this.suspended = false;
                }
            }
        });
    }

//    @FXML
//    void hoanTacAction(ActionEvent event) {
//
//        undo();
//        System.out.println(nhanVienService.findAll().size());
//        nhanVienTable.getItems().clear();
//        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
//
//        nhanVienTable.refresh();
//
//    }
//
//    @FXML
//    void luuAction(ActionEvent event) {
//        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
//        NhanVien nvNew = new NhanVien(nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());
//        System.out.println(nvNew.getHo() + " " + nvNew.getTen());
//        executeCommand(new ActionAdd(nhanVienService, nhanVienBeforeSave, "save"));
//        System.out.println(nhanVienService.findAll().size());
//
//        nhanVienTable.getItems().clear();
//
//        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
//        nhanVienTable.refresh();
//
//    }
//
//    @FXML
//    void themAction(ActionEvent event) {
//        NhanVien nvien = new NhanVien(index++, "Minh0", "To", "DangVanLanh", "15-50-2000", 2000, "CN1");
//
//        executeCommand(new ActionAdd(nhanVienService, nvien, "add"));
//        System.out.println(nhanVienService.findAll().size());
//
//        nhanVienTable.getItems().clear();
//        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
//        nhanVienTable.refresh();
//        // System.out.println(nhanVien.getHo()+" "+nhanVien.getMaNhanVien()+"  "+nhanVien.getTen());
//    }
//
//    @FXML
//    void xoaAction(ActionEvent event) {
//
//        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
//        executeCommand(new ActionDelete(nhanVienService, nv, "delete"));
//        System.out.println(nhanVienService.findAll().size());
//
//        nhanVienTable.getItems().clear();
//
//        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
//        nhanVienTable.refresh();
//    }
//
//    private void executeCommand(ActionListenerCommand command) {
//        command.execute();
//        history.push(command);
//
//    }
//
//    private int undo() {
//
//        if (history.getHistory().isEmpty()) {
//            return -1;
//        }
//
//        ActionListenerCommand command = history.pop();
//
//        if (command != null && command.getType().equals("save")) {
//            NhanVien nv = command.undo();
//            if (nv != null) {
//                nhanVienService.save(nv);
//                return 0;
//            }
//        } else if (command != null && command.getType().equals("delete")) {
//            NhanVien nv = command.undo();
//            if (nv != null) {
//                nhanVienService.save(nv);
//                return 0;
//            }
//        } else if (command != null && command.getType().equals("add")) {
//            NhanVien nv = command.undo();
//            if (nv != null) {
//                nhanVienService.delete(nv);
//                return 0;
//            }
//        }
//        return 1;
//    }

}
