/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Command.ActionDelete;
import com.mycompany.QLVT.Command.ActionEdit;
import com.mycompany.QLVT.Command.ActionHistory;
import com.mycompany.QLVT.Command.ActionListenerCommand;
import com.mycompany.QLVT.Command.ActionAdd;
import com.mycompany.QLVT.Entity.ChiNhanh;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.FomaterDate;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import com.mycompany.QLVT.dao.NhanVienDAO;
import com.mycompany.QLVT.dao.PhanManhDAO;
import com.mycompany.QLVT.model.ChiNhanhModel;
import com.mycompany.QLVT.model.NhanVienTableModel;
import com.mycompany.QLVT.service.NhanVienService;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private JFXButton btCreateAC;
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

    @FXML
    private JFXComboBox<String> cbbChiNhanh;

    @FXML
    private Label lbChuyenChiNhanh;
    @FXML
    private JFXComboBox<String> cbbChangeLocation;

    //chi tiêt nhan vien
    @FXML
    private JFXTextField tfMaNV;

    @FXML
    private JFXTextField tfHoVaTenDem;

    @FXML
    private JFXTextField tfTen;

    @FXML
    private JFXTextField tfChiNhanh;

    @FXML
    private JFXDatePicker dpkNgaySinh;

    @FXML
    private JFXTextField tfLuong;

    @FXML
    private JFXTextField tfDiaChi;

    private ActionHistory history = new ActionHistory();
    private NhanVienTableModel nhanVienTableModel;
    private ChiNhanhModel chiNhanhModel = new ChiNhanhModel();
    private ChiNhanhModel chiNhanhModelChange = new ChiNhanhModel();
    private PhanManhDAO phanManhDAO = new PhanManhDAO();
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
            initModelChiTiet();
            NhanVien nv = observable.getValue();

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
//
//        nhanVienTableModel.getCurrentNhanVienProperty().addListener((obs, oldNhanVien, newNhanVien) -> {
//            if (newNhanVien == null) {
//                tbvListNV.getSelectionModel().clearSelection();
//            } else {
//                System.out.println(newNhanVien.getMaNhanVien());
//                tbvListNV.getSelectionModel().select(newNhanVien);
//            }
//
//        });

        tbvListNV.setOnMouseClicked((event) -> {
            if (event.getClickCount() > 0) {
                nhanVienBeforeSave = tbvListNV.getSelectionModel().getSelectedItem();

            }
        });

    }

    public void initModelChiNhanh() {
        //chuyen Chi nhanh de xem thong tin nhan vien
        ;
        chiNhanhModel.setList(DBConnectUtil.listPhanManh);
        for (PhanManh pm : chiNhanhModel.getList()) {
            cbbChiNhanh.getItems().add(pm.getName());
        }
        cbbChiNhanh.getSelectionModel().select(DBConnectUtil.chiNhanhSelected);

        //chuyen Chi nhanh cua nhan vien
        // List<PhanManh> listChiNhanhChange=phanManhDAO.findAll();
        chiNhanhModelChange.setList(DBConnectUtil.listPhanManh);
        for (PhanManh pm : chiNhanhModelChange.getList()) {
            cbbChangeLocation.getItems().add(pm.getName());
        }

        cbbChangeLocation.getSelectionModel().select(DBConnectUtil.chiNhanhSelected);
    }

    public void initModelChiTiet() {
        if (nhanVienTableModel.getCurrentNhanVien() != null) {
            tfMaNV.setText(String.valueOf(nhanVienTableModel.getCurrentNhanVien().getMaNhanVien()));
            tfHoVaTenDem.setText(nhanVienTableModel.getCurrentNhanVien().getHo());
            tfTen.setText(nhanVienTableModel.getCurrentNhanVien().getTen());
            tfDiaChi.setText(nhanVienTableModel.getCurrentNhanVien().getDiaChi());
            dpkNgaySinh.setValue(FomaterDate.convertStringToLocalDate(nhanVienTableModel.getCurrentNhanVien().getNgaySinh()));
            tfLuong.setText(String.valueOf(nhanVienTableModel.getCurrentNhanVien().getLuong()));
            tfChiNhanh.setText(nhanVienTableModel.getCurrentNhanVien().getMaCN());
        }
    }

    @FXML
    void initialize() {
        
        NhanVienDAO nhan=new NhanVienDAO();
       
       nhan.testSP(7);
        //Không cho chỉnh sửa thuộc tính nhân viên và chi nhánh
//        tfMaNV.setDisable(true);
        tfChiNhanh.setDisable(true);
        if (DBConnectUtil.chiNhanhSelected.contains("Chi Nhánh 1")) {
            tfChiNhanh.setText("CN1");
        } else if (DBConnectUtil.chiNhanhSelected.contains("Chi Nhánh 2")) {
            tfChiNhanh.setText("CN2");
        }
        //kiểm tra quyền
        if (DBConnectUtil.myGroup.equals("CongTy")) {
            btAdd.setDisable(true);
            btDelete.setDisable(true);
            btSave.setDisable(true);
            btEdit.setDisable(true);
            btUndo.setDisable(true);
            btChangeLocation.setDisable(true);
        }
        if (DBConnectUtil.myGroup.equals("ChiNhanh")) {
            cbbChiNhanh.setDisable(true);
        }
        if (DBConnectUtil.myGroup.equals("User")) {
            cbbChiNhanh.setDisable(true);
            btChangeLocation.setDisable(true);
            btCreateAC.setDisable(true);
        }
        // init model table 
        NhanVienTableModel model = new NhanVienTableModel();
        List<NhanVien> list = new ArrayList<>();
        list = nhanVienService.findAll();
        model.setNhanVienList(list);
        initModel(model);

        tbvListNV.setEditable(false);
        tbvListNV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbvListNV.getColumns().addListener(new ListChangeListener() {
            public boolean suspended = false;

            @Override
            public void onChanged(ListChangeListener.Change change) {
                change.next();
                if (change.wasReplaced() && !suspended) {
                    this.suspended = true;
                    tbvListNV.getColumns().setAll(maNV, ho, ten, ngaySinh, luong, diaChi, maCN);
                    this.suspended = false;
                }
            }
        });

        // init model ChiNhanh
        initModelChiNhanh();
        //init model view chi tiết
        initModelChiTiet();

    }

    @FXML
    void createAccountAction(ActionEvent event) {

    }

    @FXML //để show dư liêu chi nhánh khác
    void changeChiNhanh(ActionEvent event) {
        System.out.println("đổi chi nhánh khác");
        List<NhanVien> list = new ArrayList<>();
        if (!cbbChiNhanh.getValue().equals(DBConnectUtil.chiNhanhSelected)) {
            list = new ArrayList<>();
            list = nhanVienService.findAllOthersite();
        } else {
            list = nhanVienService.findAll();
        }
        refresh();
    }

    @FXML
    void hoanTacAction(ActionEvent event) {

        undo();
        refresh();

    }

    @FXML
    void editAction(ActionEvent event) {
        //valid form
        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
        NhanVien nvNew = new NhanVien(nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());
        System.out.println(nvNew.getHo() + " " + nvNew.getTen());
        boolean rs = executeCommand(new ActionEdit(nhanVienService, nvNew, "edit"));

        if (rs == true) {
            refresh();
        }
    }

    @FXML
    void changeLocationAction(ActionEvent event) {
        //lấy ra chi nhánh cần chuyển;
        String chiNhanhNew = cbbChangeLocation.getSelectionModel().getSelectedItem();
        if (!chiNhanhNew.equals(DBConnectUtil.chiNhanhSelected)) {
            // xử lí logic chuyển chi nhánh
            int rs = nhanVienService.chuyenChiNhanh(Integer.valueOf(tfMaNV.getText()), chiNhanhNew);
            if (rs > 0) {

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Thông Báo"));
                content.setBody(new Text("Chuyển chi nhánh thành công"));
                JFXDialog noti = new JFXDialog((StackPane) pnNhanVien.getParent(), content, JFXDialog.DialogTransition.CENTER);
                Image image = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                JFXButton button = new JFXButton(null, new ImageView(image));
                button.setCursor(Cursor.HAND);
                button.setButtonType(JFXButton.ButtonType.RAISED);
                button.setOnAction((ActionEvent event1) -> {
                    noti.close();
                });
                content.setActions(button);
                noti.show();
                //Reload lại dữ liêu
                tbvListNV.getItems().clear();
                nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
                tbvListNV.refresh();

            }

        }

    }

    public void messageDialog(String body) {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thong bao"));
        content.setBody(new Text(body));
        JFXDialog noti = new JFXDialog((StackPane) pnNhanVien.getParent(), content, JFXDialog.DialogTransition.CENTER);
        Image image = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
        JFXButton button = new JFXButton(null, new ImageView(image));
        button.setCursor(Cursor.HAND);
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.setOnAction((ActionEvent event1) -> {
            noti.close();
        });
        content.setActions(button);
        noti.show();
    }

    @FXML
    void addAction(ActionEvent event) {
        boolean isValidForm = true;
        try {
            //Validate dữ liệu trước khí thêm
            int maNVString = Integer.valueOf(tfMaNV.getText());
            String hoString = tfHoVaTenDem.getText();
            String tenString = tfTen.getText();
            String ngaySinhString = FomaterDate.convertLocalDateToString(dpkNgaySinh.getValue());
            float luongFloat = Float.valueOf(tfLuong.getText());
            String maCNString = tfChiNhanh.getText();

            //check rỗng
            if (hoString.isEmpty() || tenString.isEmpty()
                    || maCNString.isEmpty()) {
                messageDialog("Infomation is empty");
                isValidForm = false;
            }
            //check ràng buộc 
            if (String.valueOf(maNVString).length() > 15) {
                isValidForm = false;
                messageDialog("Mã nhân viên quá dài");

                return;
            } else if (hoString.length() > 40 || !ValidationRegEx.validationTextRegex(hoString)) {
                isValidForm = false;
                messageDialog("Ho ten quá dài hoac sai dinh dang");

                return;
            } else if (tenString.length() > 10 || !ValidationRegEx.validationTextRegex(tenString)) {
                isValidForm = false;
                messageDialog("Ten quá dài hoac sai dinh dang");
            } else if (luongFloat < 4000000) {
                isValidForm = false;
                messageDialog("So tien phai lon hon 4000000");

                return;
            } else if (luongFloat > 50000000) {
                isValidForm = false;
                messageDialog("So tien phai nho hon 50000000");

                return;
            }

            if (isValidForm) {
                System.out.println("Xu li du lieu xuong db");
                NhanVien newNhanVien = new NhanVien(maNVString, hoString, tenString, hoString, ngaySinhString, luongFloat, maCNString);
                boolean rs = executeCommand(new ActionAdd(nhanVienService, newNhanVien, "add"));
//
                if (rs) {
                    refresh();
                    messageDialog("Lưu thành công");
                } else {
                    messageDialog("Lưu thất bại");
                }

            }
        } catch (NumberFormatException e) {

            messageDialog("Number Fommatis invalid");

        } catch (DateTimeException e) {

            messageDialog("Date is invalid");

        } catch (Exception e) {

            messageDialog("Please check to infomation");

        }

        // NhanVien nv=new NhanVien(index, maCN, maCN, maCN, maCN, luong, maCN);
//        NhanVien nvien = new NhanVien(15, "Minh", "Tiến", "DangVanLanh", "2000-5-15", 7000000, "CN1");
//
//        boolean rs = executeCommand(new ActionAdd(nhanVienService, nvien, "add"));
//
//        if (rs) {
//            System.out.println(nhanVienService.findAll().size());
//            tbvListNV.getItems().clear();
//            nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
//            tbvListNV.refresh();
//            // System.out.println(nhanVien.getHo()+" "+nhanVien.getMaNhanVien()+"  "+nhanVien.getTen());
//        } else {
//            System.out.println("Nhan vien bi trung");
//        }
    }

    @FXML
    void saveAction(ActionEvent event) {

    }

    public void refresh() {
        tbvListNV.getItems().clear();
        nhanVienTableModel.setNhanVienList(nhanVienService.findAll());
        tbvListNV.refresh();
    }

    @FXML
    void deleteAction(ActionEvent event) {
        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
        boolean rs = executeCommand(new ActionDelete(nhanVienService, nv, "delete"));
        if (rs == true) {
            refresh();
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

        ActionListenerCommand command = history.peek();

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
                if (nhanVienService.findOne(nv.getMaNhanVien()) != null) {
                    nhanVienService.update(nv); //cap nhat trang thai
                     history.pop();

                    return 1;
                } else {
                    if (nhanVienService.save(nv) > 0) {
                        history.pop();
                        return 1;
                    } //thêm mới
                    return 0;
                }
            }
        } else if (command != null && command.getType().equals("add")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                nhanVienService.delete(nv.getMaNhanVien());
                history.pop();
                return 1;
            }
        } else if (command != null && command.getType().equals("edit")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                nhanVienService.update(nv);
                history.pop();
                return 1;
            }
        }
        return 0;
    }

}
