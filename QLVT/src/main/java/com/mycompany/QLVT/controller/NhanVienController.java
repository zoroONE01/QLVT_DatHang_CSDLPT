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
import com.mycompany.QLVT.Command.ActionAddPhieuNhap;
import com.mycompany.QLVT.Entity.ChiNhanh;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Entity.PhieuNhap;
import com.mycompany.QLVT.Entity.TaiKhoan;
import com.mycompany.QLVT.Exception.LoginNameExistException;
import com.mycompany.QLVT.Exception.UserNameExistException;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.Utils.EnumGroup;
import com.mycompany.QLVT.Utils.FomaterDate;
import com.mycompany.QLVT.Utils.ValidationRegEx;
import static com.mycompany.QLVT.controller.BaoCaoController.runReport;
import com.mycompany.QLVT.dao.NhanVienDAO;
import com.mycompany.QLVT.dao.PhanManhDAO;
import com.mycompany.QLVT.model.ChiNhanhModel;
import com.mycompany.QLVT.model.NhanVienCbbModel;
import com.mycompany.QLVT.model.NhanVienTableModel;
import com.mycompany.QLVT.service.NhanVienService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
    private JFXComboBox<PhanManh> cbbChiNhanh;

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

  

    @FXML
    private JFXTextField tfTenDangNhap_TaiKhoan;

    @FXML
    private JFXTextField tfMatKhau_TaiKhoan;

    @FXML
    private JFXComboBox<String> cbChiNhanh_TaiKhoan;

    @FXML
    private JFXComboBox<NhanVien> cbNhanVien;
    @FXML
    private Label lbTaiKhoan;

    @FXML
    private JFXButton btTaoTaiKhoan;
    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton1;

    private ToggleGroup groupButton = new ToggleGroup();
    @FXML
    private StackPane stackPaneCreateAccount;

    @FXML
    void actionTaoTaiKhoan(ActionEvent event) {

    }

    private ActionHistory history = new ActionHistory();
    private NhanVienTableModel nhanVienTableModel;
    private NhanVienCbbModel nhanVienCbbModel;
    private ChiNhanhModel chiNhanhModel = new ChiNhanhModel();
    private ChiNhanhModel chiNhanhModelTaiKhoan = new ChiNhanhModel();
    private ChiNhanhModel chiNhanhModelChange = new ChiNhanhModel();
    private PhanManhDAO phanManhDAO = new PhanManhDAO();
    private NhanVienService nhanVienService = new NhanVienService();
    static int index = 1;
    private NhanVien nhanVienBeforeSave = new NhanVien();

    public NhanVienController() {
       
    }

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

        luong.setCellFactory(param -> new TableCell<NhanVien, Float>() {
            @Override
            protected void updateItem(Float item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                if (empty) {
                    setText(null);
                } else {
                    setText(BigDecimal.valueOf(Double.valueOf(item.toString())).toPlainString());
                }
            }

        });
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
        chiNhanhModel.setList(DBConnectUtil.listPhanManh);
        for (PhanManh pm : chiNhanhModel.getList()) {
            cbbChiNhanh.getItems().add(pm);
        }
        Callback<ListView<PhanManh>, ListCell<PhanManh>> cellFactory = new Callback<ListView<PhanManh>, ListCell<PhanManh>>() {

            @Override
            public ListCell<PhanManh> call(ListView<PhanManh> l) {
                return new ListCell<PhanManh>() {

                    @Override
                    protected void updateItem(PhanManh item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        cbbChiNhanh.setCellFactory(cellFactory);
        cbbChiNhanh.setButtonCell(cellFactory.call(null));
        chiNhanhModel.setCurrent(DBConnectUtil.phanManhCurrent);
        cbbChiNhanh.getSelectionModel().select(chiNhanhModel.getCurrent());
        cbbChiNhanh.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            chiNhanhModel.setCurrent(newValue);
        });
        //chuyen Chi nhanh cua nhan vien
        // List<PhanManh> listChiNhanhChange=phanManhDAO.findAll();
        chiNhanhModelChange.setList(DBConnectUtil.listPhanManh);
        for (PhanManh pm : chiNhanhModelChange.getList()) {
            cbbChangeLocation.getItems().add(pm.getName());
        }
        cbbChangeLocation.getSelectionModel().select(DBConnectUtil.phanManhCurrent.getName());

        //Chi nhánh của tạo tài khoản
        chiNhanhModelTaiKhoan.setList(DBConnectUtil.listPhanManh);

        for (PhanManh pm : chiNhanhModelTaiKhoan.getList()) {
            cbChiNhanh_TaiKhoan.getItems().add(pm.getName());
        }
        cbChiNhanh_TaiKhoan.getSelectionModel().select(chiNhanhModel.getCurrent().getName());
    }

    public void initModelCbbNhanVien(NhanVienCbbModel nhanVienCbbModel) {
        if (this.nhanVienCbbModel != null) {
            throw new IllegalStateException("Model can only be initialized once");
        }
        this.nhanVienCbbModel = nhanVienCbbModel;

        for (NhanVien nv : this.nhanVienCbbModel.getNhanVienList()) {
            cbNhanVien.getItems().add(nv);
        }
        Callback<ListView<NhanVien>, ListCell<NhanVien>> cellFactory = new Callback<ListView<NhanVien>, ListCell<NhanVien>>() {

            @Override
            public ListCell<NhanVien> call(ListView<NhanVien> l) {
                return new ListCell<NhanVien>() {

                    @Override
                    protected void updateItem(NhanVien item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getMaNhanVien() + "." + item.getHo() + " " + item.getTen() + " " + item.getNgaySinh());
                        }
                    }
                };
            }
        };

        cbNhanVien.setCellFactory(cellFactory);
        cbNhanVien.setButtonCell(cellFactory.call(null));
        // cbNhanVien.getItems().setAll(this.nhanVienCbbModel.getNhanVienList());
        cbNhanVien.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.nhanVienCbbModel.setCurrentNhanVien(newValue);
        });
    }

    public void initModelChiTiet() {
        if (nhanVienTableModel.getCurrentNhanVien() != null) {
            tfMaNV.setText(String.valueOf(nhanVienTableModel.getCurrentNhanVien().getMaNhanVien()));
            tfHoVaTenDem.setText(nhanVienTableModel.getCurrentNhanVien().getHo());
            tfTen.setText(nhanVienTableModel.getCurrentNhanVien().getTen());
            tfDiaChi.setText(nhanVienTableModel.getCurrentNhanVien().getDiaChi());
            dpkNgaySinh.setValue(FomaterDate.convertStringToLocalDate(nhanVienTableModel.getCurrentNhanVien().getNgaySinh()));
            tfLuong.setText(BigDecimal.valueOf(Double.valueOf(nhanVienTableModel.getCurrentNhanVien().getLuong())).toPlainString());
            tfChiNhanh.setText(nhanVienTableModel.getCurrentNhanVien().getMaCN());
        }
    }

    @FXML
    void initialize() {

//        NhanVienDAO nhan = new NhanVienDAO();
//        
//        nhan.testSP(7);
        //Không cho chỉnh sửa thuộc tính nhân viên và chi nhánh
//        tfMaNV.setDisable(true);
        tfChiNhanh.setDisable(true);
        if (DBConnectUtil.phanManhCurrent.getName().contains("Chi Nhánh 1")) {
            tfChiNhanh.setText("CN1");
        } else if (DBConnectUtil.phanManhCurrent.getName().contains("Chi Nhánh 2")) {
            tfChiNhanh.setText("CN2");
        }
        
        // init model table 
        NhanVienTableModel model = new NhanVienTableModel();
        List<NhanVien> list = new ArrayList<>();
        list = nhanVienService.findAll();
        model.setNhanVienList(list);
        initModel(model);

        // Initialize combobox Nhân Viên model 
        NhanVienCbbModel nhanVienModel = new NhanVienCbbModel();
        nhanVienModel.setNhanVienList(list);
        initModelCbbNhanVien(nhanVienModel);

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
        
            btDelete.setDisable(true);

        }
        

    }

    //ACTION
    @FXML
    void actionReportDSNV(ActionEvent event) {

    }

    @FXML
    void createAccountAction(ActionEvent event) {
        cbChiNhanh_TaiKhoan.setDisable(true);
        cbChiNhanh_TaiKhoan.getSelectionModel().select(cbbChiNhanh.getSelectionModel().getSelectedItem().getName());
        if (nhanVienTableModel.getCurrentNhanVien() != null && nhanVienCbbModel.getNhanVienList().contains(nhanVienTableModel.getCurrentNhanVien())) {
            cbNhanVien.getSelectionModel().select(nhanVienTableModel.getCurrentNhanVien());
        } else {
            cbNhanVien.getSelectionModel().select(0);
        }

        if (DBConnectUtil.myGroup.equals(EnumGroup.ChiNhanh.name())) {
            radioButton1.setUserData(EnumGroup.ChiNhanh.name());
            radioButton2.setUserData(EnumGroup.User.name());
            radioButton1.setText("Chi nhánh");
            radioButton2.setText("User");
            radioButton2.setVisible(true);
        } else if (DBConnectUtil.myGroup.equals(EnumGroup.CongTy.name())) {
            radioButton1.setText("Công ty");
            radioButton1.setUserData(EnumGroup.CongTy.name());
            radioButton2.setVisible(false);
        }

        radioButton1.setToggleGroup(groupButton);
        radioButton2.setToggleGroup(groupButton);
        radioButton1.setSelected(true);
        System.out.println("show dialog them");
        Dialog<TaiKhoan> dialog = new Dialog<>();
        dialog.setTitle("Thêm tài khoản");
        dialog.setResizable(true);
        dialog.getDialogPane().setContent(stackPaneCreateAccount);
        stackPaneCreateAccount.setVisible(true);

        ButtonType buttonTypeOk = new ButtonType("Tạo tài khoản", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        Button btn = (Button) dialog.getDialogPane().lookupButton(buttonTypeOk);
        btn.addEventFilter(ActionEvent.ACTION, ev -> {
            boolean isValidForm = true;
            try {
                String chiNhanh = cbChiNhanh_TaiKhoan.getValue();
                int maNhanVien = nhanVienCbbModel.getCurrentNhanVien().getMaNhanVien();
                String tenDangNhap = tfTenDangNhap_TaiKhoan.getText();
                String matKhau = tfMatKhau_TaiKhoan.getText();
                String nhom = groupButton.getSelectedToggle().getUserData().toString();
//                                //check rỗng
                if (nhanVienCbbModel.getCurrentNhanVien() == null || tenDangNhap.isEmpty() || matKhau.isEmpty() || nhom.isEmpty()) {
                    //isValidForm = false;
                    // messageDialog("Infomation is empty");
                    throw new Exception("Thông tin không được để trống");
                }
//
//              check ràng buộc 
                if (tenDangNhap.length() > 50 || tenDangNhap.length() < 1 || !ValidationRegEx.validationTextAndNumRegex(tenDangNhap)) {
                    isValidForm = false;
                    // messageDialog("Mã kho quá dài hoac sai dinh dang");
                    throw new Exception("Tên không hợp lệ(>50) hoặc sai định dạng(kí tự có dấu)");
                }
                if (matKhau.length() > 50 || matKhau.length() < 1) {
                    isValidForm = false;
                    // messageDialog("Mã phiếu nhập quá dài hoac sai dinh dang");
                    throw new Exception("Mật khẩu quá dài(>50) hoặc quá ngắn(<1)");
                }
                if (isValidForm) {
                    TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau, maNhanVien, nhom, chiNhanh);
                    boolean result = nhanVienService.createAccount(taiKhoan);
                    if (result == true) {
                        messageDialogTaiKhoan("Tạo tài khoản thành công");
                    }
                }
               ev.consume();
                //  return new PhieuNhap(tfMaPN_Dialog.getText(), new Date().toString(), tfMaDHH_Dialog.getText(), Integer.parseInt(tfMaNV_Dialog.getText()), tfMaKho_Dialog.getText());
            } catch (LoginNameExistException le) {
                messageDialogTaiKhoan(le.getMessage());
                ev.consume();
            } catch (UserNameExistException ue) {
                messageDialogTaiKhoan(ue.getMessage());
                ev.consume();
            } catch (RuntimeException re) {
                messageDialogTaiKhoan(re.getMessage());
                ev.consume();
            } catch (Exception e) {
                if (e.getMessage().equals("Thông tin không được để trống")) {
                    messageDialogTaiKhoan("Thông tin không được để trống");
                }
                if (e.getMessage().equals("Tên không hợp lệ(>50) hoặc sai định dạng(kí tự có dấu)")) {
                    messageDialogTaiKhoan("Tên không hợp lệ(>50) hoặc sai định dạng(kí tự có dấu)");
                }
                if (e.getMessage().equals("Mật khẩu quá dài(>50) hoặc quá ngắn(<1)")) {
                    messageDialogTaiKhoan("Mật khẩu quá dài(>50) hoặc quá ngắn(<1)");
                }
                ev.consume();
            }

        });
        dialog.setResultConverter(new Callback<ButtonType, TaiKhoan>() {
            @Override
            public TaiKhoan call(ButtonType b) {
                //  boolean isValidForm = true;
                if (b == buttonTypeOk) {
                    //kiểm tra dữ liệ
                    System.out.println(cbChiNhanh_TaiKhoan.getValue());
                    System.out.println(nhanVienCbbModel.getCurrentNhanVien().getMaNhanVien());
                    System.out.println(tfTenDangNhap_TaiKhoan.getText());
                    System.out.println(tfMatKhau_TaiKhoan.getText());
                    System.out.println(groupButton.getSelectedToggle().getUserData().toString());
                }
                return null;
            }
        });
        Optional<TaiKhoan> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("kết quả lưu xuống db");
            System.out.println(result.get().getChiNhanh());
            System.out.println(result.get().getMaNhanVien());
            System.out.println(result.get().getTenDangNhap());
            System.out.println(result.get().getMatKhau());
            System.out.println(result.get().getNhom());

        }
    }

    ;
    
    @FXML //để show dư liêu chi nhánh khác
    void changeChiNhanh(ActionEvent event) {
        checkAndSetBaseOnPermisson();
        System.out.println("đổi chi nhánh khác");
        if (!cbbChiNhanh.getValue().equals(DBConnectUtil.phanManhCurrent.getName())) {
            try {
                DBConnectUtil.phanManhCurrent = DBConnectUtil.listPhanManh.get(cbbChiNhanh.getSelectionModel().getSelectedIndex());
                DBConnectUtil.getConnection();
            } catch (SQLException ex) {
                System.out.println("Không thể kết nối tới server mới");
            }
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
        disableButtonForEdit(true);
        //valid form
//        NhanVien nv = nhanVienTableModel.getCurrentNhanVien();// chưa lấy dữ liệu từ form
//        NhanVien nvNew = new NhanVien(nv.getMaNhanVien(), nv.getHo(), nv.getTen(), nv.getDiaChi(), nv.getNgaySinh(), nv.getLuong(), nv.getMaCN());
//        System.out.println(nvNew.getHo() + " " + nvNew.getTen());
//        boolean rs = executeCommand(new ActionEdit(nhanVienService, nvNew, "edit"));
//
//        if (rs == true) {
//            refresh();
//        }
        boolean isValidForm = true;
        try {
            //Validate dữ liệu trước khi sua
            int maNVString = Integer.valueOf(tfMaNV.getText());
            String hoString = tfHoVaTenDem.getText();
            String tenString = tfTen.getText();
            String ngaySinhString = FomaterDate.convertLocalDateToString(dpkNgaySinh.getValue());
            float luongFloat = Float.valueOf(tfLuong.getText());
            String maCNString = tfChiNhanh.getText();
            String diaChi = this.tfDiaChi.getText();
            //check rỗng
            if (hoString.isEmpty() || tenString.isEmpty()
                    || maCNString.isEmpty()) {
                messageDialog("Thong tin khong duoc de trong");
                isValidForm = false;
                return;
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
                NhanVien newNhanVien = new NhanVien(maNVString, hoString, tenString, diaChi, ngaySinhString, luongFloat, maCNString);
                boolean rs = executeCommand(new ActionEdit(nhanVienService, newNhanVien, "edit"));
                if (rs) {
                    refresh();
                    messageDialog("Cap nhat thành công");
                    resetTextFieldNhanVien();
                } else {
                    messageDialog("Cap nhat thất bại");
                }

            }
        } catch (NumberFormatException e) {

            messageDialog("Number Fommatis invalid");

        } catch (DateTimeException e) {

            messageDialog("Date is invalid");

        } catch (Exception e) {

            messageDialog("Please check to infomation");

        } finally {
            disableButtonForEdit(false);
        }
    }

    @FXML
    void changeLocationAction(ActionEvent event) {
        //lấy ra chi nhánh cần chuyển;
        StackPane st = (StackPane) pnNhanVien.getParent().getParent().getParent();
        String chiNhanhNew = cbbChangeLocation.getSelectionModel().getSelectedItem();
        if (!chiNhanhNew.equals(DBConnectUtil.phanManhCurrent.getName())) {
            // xử lí logic chuyển chi nhánh
            int rs = nhanVienService.chuyenChiNhanh(Integer.valueOf(tfMaNV.getText()), chiNhanhNew);
            if (rs > 0) {

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Thông Báo"));
                content.setBody(new Text("Chuyển chi nhánh thành công"));
                JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER);
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
                resetTextFieldNhanVien();

            } else {

                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("Thông Báo"));
                content.setBody(new Text("Chuyển chi nhánh thất bại"));
                JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER);
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

        }

    }

    public void messageDialogTaiKhoan(String body) {
        // StackPane st = (StackPane) pnPhieuNhap.getParent().getParent().getParent().getParent();
        StackPane st = (StackPane) stackPaneCreateAccount;
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thong bao"));
        content.setBody(new Text(body));
        JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER, true);
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

    public void messageDialog(String body) {
        StackPane st = (StackPane) pnNhanVien.getParent().getParent().getParent();
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Thong bao"));
        content.setBody(new Text(body));
        JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER);
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
        disableButtonForAdd(true);
        boolean isValidForm = true;
        try {
            //Validate dữ liệu trước khí thêm
            int maNVString = Integer.valueOf(tfMaNV.getText());
            String hoString = tfHoVaTenDem.getText();
            String tenString = tfTen.getText();
            String ngaySinhString = FomaterDate.convertLocalDateToString(dpkNgaySinh.getValue());
            float luongFloat = Float.valueOf(tfLuong.getText());
            String maCNString = tfChiNhanh.getText();
            String diaChi = this.tfDiaChi.getText();
            //check rỗng
            if (hoString.isEmpty() || tenString.isEmpty()
                    || maCNString.isEmpty()) {
                messageDialog("Infomation is empty");
                isValidForm = false;
                return;
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
                NhanVien newNhanVien = new NhanVien(maNVString, hoString, tenString, diaChi, ngaySinhString, luongFloat, maCNString);
                boolean rs = executeCommand(new ActionAdd(nhanVienService, newNhanVien, "add"));
//
                if (rs) {
                    refresh();
                    resetTextFieldNhanVien();
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

        } finally {
            disableButtonForAdd(false);
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
        //cbNhanVien.getItems().clear();
        List<NhanVien> listNhanVien = nhanVienService.findAll();

        nhanVienTableModel.setNhanVienList(listNhanVien);
        nhanVienTableModel.setCurrentNhanVien(null);

        nhanVienCbbModel.setNhanVienList(listNhanVien);

        cbNhanVien.getItems().setAll(this.nhanVienCbbModel.getNhanVienList());

        tbvListNV.refresh();

    }

    @FXML
    void deleteAction(ActionEvent event) {
        disableButtonForDelete(true);
        ButtonType OKButton = new ButtonType("Đồng ý");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Bạn có muốn xoá nhân viên");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.CANCEL);
        alert.getButtonTypes().addAll(OKButton);

        Optional<ButtonType> optional = alert.showAndWait();
        try {
            if (optional.get() == OKButton) {
                System.out.println("Xoá nhân viên");
                NhanVien nv = nhanVienTableModel.getCurrentNhanVien();
                StackPane st = (StackPane) pnNhanVien.getParent().getParent().getParent();
                boolean rs = executeCommand(new ActionDelete(nhanVienService, nv, "delete"));
                if (rs == true) {
                    refresh();
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.setHeading(new Text("Thông Báo"));
                    content.setBody(new Text("Xoá nhân viên thành công"));
                    JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER);
                    Image image = new Image(getClass().getResourceAsStream("../../../../img/icons8_checkmark_20px.png"));
                    JFXButton button = new JFXButton(null, new ImageView(image));
                    button.setCursor(Cursor.HAND);
                    button.setButtonType(JFXButton.ButtonType.RAISED);
                    button.setOnAction((ActionEvent event1) -> {
                        noti.close();
                    });
                    content.setActions(button);
                    noti.show();
                    resetTextFieldNhanVien();
                } else {
                    JFXDialogLayout content = new JFXDialogLayout();
                    content.setHeading(new Text("Thông Báo"));
                    content.setBody(new Text("Xoá nhân viên không thành công"));
                    JFXDialog noti = new JFXDialog(st, content, JFXDialog.DialogTransition.CENTER);
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
            } else {
                alert.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            disableButtonForDelete(false);
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Danh sách undo trống");
            Optional<ButtonType> optional = alert.showAndWait();
            return -1;
        }

        ActionListenerCommand<NhanVien> command = history.peek();

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
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Undo hành động xoá thành công");
                    Optional<ButtonType> optional = alert.showAndWait();
                    return 1;
                } else {
                    if (nhanVienService.save(nv) > 0) {
                        history.pop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Undo hành động xoá thành công");
                        Optional<ButtonType> optional = alert.showAndWait();
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Undo hành động thêm thành công");
                Optional<ButtonType> optional = alert.showAndWait();
                return 1;
            }
        } else if (command != null && command.getType().equals("edit")) {
            NhanVien nv = command.undo();
            if (nv != null) {
                nhanVienService.update(nv);
                history.pop();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Undo hành động sửa thành công");
                Optional<ButtonType> optional = alert.showAndWait();
                return 1;
            }
        }
        return 0;
    }

    public void resetTextFieldNhanVien() {
        tfMaNV.setText("");
        tfHoVaTenDem.setText("");
        tfTen.setText("");
        tfLuong.setText("0");
        tfChiNhanh.setText("");
        tfDiaChi.setText("");
    }

    public void disableButtonForAdd(boolean b) {
        if (b == true) {
            btDelete.setDisable(true);
            btSave.setDisable(true);
            btEdit.setDisable(true);
            btUndo.setDisable(true);
            btChangeLocation.setDisable(true);
        } else {
            btDelete.setDisable(!true);
            btSave.setDisable(!true);
            btEdit.setDisable(!true);
            btUndo.setDisable(!true);
            btChangeLocation.setDisable(!true);
        }
        checkAndSetBaseOnPermisson();
    }

    public void disableButtonForEdit(boolean b) {
        if (b == true) {
            btAdd.setDisable(true);
            btDelete.setDisable(true);
            btSave.setDisable(true);
            btUndo.setDisable(true);
            btChangeLocation.setDisable(true);
        } else {
            btAdd.setDisable(!true);
            btDelete.setDisable(!true);
            btSave.setDisable(!true);
            btUndo.setDisable(!true);
            btChangeLocation.setDisable(!true);
        }
        checkAndSetBaseOnPermisson();
    }

    public void disableButtonForDelete(boolean b) {
        if (b == true) {
            btAdd.setDisable(true);
            btSave.setDisable(true);
            btEdit.setDisable(true);
            btUndo.setDisable(true);
            btChangeLocation.setDisable(true);
        } else {
            btAdd.setDisable(!true);
            btSave.setDisable(!true);
            btEdit.setDisable(!true);
            btUndo.setDisable(!true);
            btChangeLocation.setDisable(!true);
        }
        checkAndSetBaseOnPermisson();
    }

    public void checkAndSetBaseOnPermisson() {

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
            
            btDelete.setDisable(true);

        }
    }

}
