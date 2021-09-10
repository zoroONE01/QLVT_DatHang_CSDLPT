/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.mycompany.QLVT.Command.ActionDelete;
import com.mycompany.QLVT.Command.ActionEdit;
import com.mycompany.QLVT.Command.ActionHistory;
import com.mycompany.QLVT.Command.ActionListenerCommand;
import com.mycompany.QLVT.Command.ActionAdd;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.model.NhanVienTableModel;
import com.mycompany.QLVT.service.NhanVienService;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author MinhTo
 */
public class NhanVienController {

    @FXML
    private MenuButton mnbtAccount;

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
    private AnchorPane pnNhanVien;

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
    private JFXButton btChangeLocation;

    @FXML
    private TableView<NhanVien> tbvListNV;

    @FXML
    private TableColumn<NhanVien, Integer> maNV;

    @FXML
    private TableColumn<NhanVien, String> ho;

    @FXML
    private TableColumn<NhanVien, String> ten;

    @FXML
    private TableColumn<NhanVien, String> ngaySinh;

    @FXML
    private TableColumn<NhanVien, Float> luong;

    @FXML
    private TableColumn<NhanVien, String> diaChi;

    @FXML
    private TableColumn<NhanVien, String> maCN;

    @FXML
    private TableColumn<NhanVien, String> trangThai;

    private ActionHistory history = new ActionHistory();
    private NhanVienTableModel nhanVienTableModel;
    private NhanVienService nhanVienService = new NhanVienService();
    static int index = 1;
    private NhanVien nhanVienBeforeSave = new NhanVien();

    public void initModel(NhanVienTableModel nhanVienTableModel) {// ensure model is only set once:
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
        maNV.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));

        maNV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        maNV.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Integer>>() {
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
                System.out.println(event.getTablePosition().getColumn());
                System.out.println(event.getTablePosition().getRow());
                // event.getTableColumn()
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
        luong.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        luong.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<NhanVien, Float> event) {
                NhanVien nhanVien = event.getRowValue();
                nhanVien.setLuong(event.getNewValue());
            }
        });

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
//        trangThai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
//        trangThai.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
//        trangThai.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<NhanVien, Integer>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<NhanVien, Integer> event) {
//                NhanVien nhanVien = event.getRowValue();
//                nhanVien.setTrangThai(event.getNewValue());
//            }
//        });
        // Sét xắp xếp theo userName
        luong.setSortType(TableColumn.SortType.DESCENDING);
        //lastNameCol.setSortable(false);

        tbvListNV.getColumns();
        tbvListNV.setItems(nhanVienTableModel.getNhanVienList());

        tbvListNV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
                tbvListNV.getSelectionModel().clearSelection();
            } else {
                System.out.println(newNhanVien.getMaNhanVien());
                tbvListNV.getSelectionModel().select(newNhanVien);
            }

        });

        tbvListNV.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 0) {
                // edit dữ liê
                nhanVienBeforeSave = tbvListNV.getSelectionModel().getSelectedItem();

                System.out.println("edit du lieu");
            }
        });

    }

    @FXML
    void initialize() {
        // hello
        System.out.println("Ban");
        NhanVienTableModel model = new NhanVienTableModel();
        List<NhanVien> list = new ArrayList<>();
        list = nhanVienService.findAll();
        model.setNhanVienList(list);
        initModel(model);

        tbvListNV.setEditable(true);
        tbvListNV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvListNV.getColumns().addListener(new ListChangeListener() {

            public boolean suspended;

            @Override
            public void onChanged(ListChangeListener.Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {

                    this.suspended = true;
                    tbvListNV.getColumns().setAll(maNV, ho, ten, diaChi, luong, ngaySinh, maCN);
                    this.suspended = false;
                }
            }
        });
    }

    @FXML
    void hoanTacAction(ActionEvent event) {

        undo();
        System.out.println(nhanVienService.findAll().size());
        tbvListNV.getItems().clear();
        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
        tbvListNV.refresh();

    }

    @FXML
    void editAction(ActionEvent event) {
        //valid form
        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
        NhanVien nvNew = new NhanVien(nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());
        System.out.println(nvNew.getHo() + " " + nvNew.getTen());
        boolean rs = executeCommand(new ActionEdit(nhanVienService, nvNew, "edit"));

        if (rs == true) {
            System.out.println(nhanVienService.findAll().size());

            tbvListNV.getItems().clear();

            nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
            tbvListNV.refresh();
        }
    }

    @FXML
    void addAction(ActionEvent event) {
        NhanVien nvien = new NhanVien(15, "Minh", "Tiến", "DangVanLanh", "2000-5-15", 7000000, "CN1");

        boolean rs = executeCommand(new ActionAdd(nhanVienService, nvien, "add"));

        if (rs) {
            System.out.println(nhanVienService.findAll().size());
            tbvListNV.getItems().clear();
            nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
            tbvListNV.refresh();
            // System.out.println(nhanVien.getHo()+" "+nhanVien.getMaNhanVien()+"  "+nhanVien.getTen());
        } else {
            System.out.println("Nhan vien bi trung");
        }
    }

    @FXML
    void saveAction(ActionEvent event) {

    }

    @FXML
    void deleteAction(ActionEvent event) {

        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
        boolean rs = executeCommand(new ActionDelete(nhanVienService, nv, "delete"));
        if (rs == true) {
            System.out.println(nhanVienService.findAll().size());
            tbvListNV.getItems().clear();
            nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
            tbvListNV.refresh();

            System.out.println("Xoas thanh cong");
        }

    }

    private boolean executeCommand(ActionListenerCommand command) {
        boolean rs = false;
        rs = command.execute();
        if (rs == true) {
            history.push(command);
        }
        return rs;
    }

    private int undo() {

        if (history.getHistory().isEmpty()) {
            return -1;
        }

        ActionListenerCommand command = history.pop();

//        if (command != null && command.getType().equals("save")) {
//            NhanVien nv = command.undo();
//            if (nv != null) {
//                nhanVienService.delete(nv.getMaNhanVien());
//                return 0;
//            }
//        } 
        if (command != null && command.getType().equals("delete")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                if (nhanVienService.findOne(nv.getMaNhanVien())!=null) {
                    nhanVienService.update(nv); //cap nhat trang thai
                    return 0;
                } else 
                {
                    nhanVienService.save(nv); //thêm mới
                    return 0;
                }
            }
        } else if (command != null && command.getType().equals("add")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                nhanVienService.delete(nv.getMaNhanVien());
                return 0;
            }
        } else if (command != null && command.getType().equals("edit")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                nhanVienService.update(nv);
                return 0;
            }
        }
        return 1;
    }

}
