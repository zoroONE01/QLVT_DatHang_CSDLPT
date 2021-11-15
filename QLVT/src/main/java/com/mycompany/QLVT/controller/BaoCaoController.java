/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mycompany.QLVT.Entity.HDNV;
import com.mycompany.QLVT.Entity.NhanVien;
import com.mycompany.QLVT.Entity.PhanManh;
import com.mycompany.QLVT.Entity.ThoiGian;
import com.mycompany.QLVT.Utils.DBConnectUtil;
import com.mycompany.QLVT.dao.PhanManhDAO;
import com.mycompany.QLVT.model.ChiNhanhModel;
import com.mycompany.QLVT.service.NhanVienService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author MinhTo
 */
public class BaoCaoController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane pnNhanVien;

    @FXML
    private JFXComboBox<String> cbbChiNhanh;

    @FXML
    private JFXButton btnReportDSNV;

    @FXML
    private JFXButton btnReportDMVT;

    @FXML
    private JFXButton btnReportNhapXuat;

    @FXML
    private JFXButton btnReportNhapHang;

    @FXML
    private JFXButton btnReportHDNV;
    @FXML
    private Label lbChuyenChiNhanh;
    @FXML
    private DatePicker datePickerFrom;

    @FXML
    private DatePicker datePickerTo;
    @FXML
    private AnchorPane pnThongKeCTNX;

    @FXML
    private AnchorPane pnHDNV;

    @FXML
    private Label lbTitleHDNV;

    @FXML
    private JFXTextField tfTenNV_HDNV;

    @FXML
    private DatePicker datePickerFrom_HDNV;

    @FXML
    private JFXTextField tfMaNV_HDNV;

    @FXML
    private DatePicker datePickerTo_HDNV;
    private ChiNhanhModel chiNhanhModel = new ChiNhanhModel();
    private PhanManhDAO phanManhDAO = new PhanManhDAO();
    HashMap<Integer, NhanVien> hashNhanViens;
    private NhanVienService nhanVienService = new NhanVienService();

    @FXML
    void actionReportDMVT(ActionEvent event) {
        ButtonType previewButton = new ButtonType("Preview");
        ButtonType exportButton = new ButtonType("Export");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.CANCEL);

        alert.getButtonTypes().addAll(previewButton, exportButton);

        Optional<ButtonType> optional = alert.showAndWait();

        if (optional.get() == previewButton) {
            //show report;
            runReport("DMVT", false);
        } else if (optional.get() == exportButton) {
            //export
            runReport("DMVT", true);
        } else {
            alert.close();
        }
    }

    @FXML
    void actionReportDSNV(ActionEvent event) {
        ButtonType previewButton = new ButtonType("Preview");
        ButtonType exportButton = new ButtonType("Export");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.CANCEL);

        alert.getButtonTypes().addAll(previewButton, exportButton);

        Optional<ButtonType> optional = alert.showAndWait();

        if (optional.get() == previewButton) {
            //show report;
            runReport("DSNV", false);
        } else if (optional.get() == exportButton) {
            //export
            runReport("DSNV", true);
        } else {
            alert.close();
        }

    }

    @FXML
    void actionReportHDNV(ActionEvent event) {
        ButtonType previewButton = new ButtonType("Preview");
        ButtonType exportButton = new ButtonType("Export");

        Dialog<HDNV> dialog = new Dialog<>();
        dialog.setTitle("Hoạt động nhân viên");
        dialog.setResizable(true);
        dialog.getDialogPane().setContent(pnHDNV);
        pnHDNV.setVisible(true);
        tfMaNV_HDNV.setText(DBConnectUtil.myUserDB);

        dialog.getDialogPane().getButtonTypes().add(previewButton);
        dialog.getDialogPane().getButtonTypes().add(exportButton);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Button btnPrv = (Button) dialog.getDialogPane().lookupButton(previewButton);
        btnPrv.addEventFilter(ActionEvent.ACTION, (evt) -> {
            LocalDate dateFrom = datePickerFrom_HDNV.getValue();
            LocalDate dateTo = datePickerTo_HDNV.getValue();
            if (dateFrom.isAfter(dateTo)) {
                evt.consume();
            }
        });
        dialog.setResultConverter(new Callback<ButtonType, HDNV>() {
            @Override
            public HDNV call(ButtonType param) {
                LocalDate dateFrom = datePickerFrom_HDNV.getValue();
                LocalDate dateTo = datePickerTo_HDNV.getValue();
                String to=dateTo.format(DateTimeFormatter.ISO_DATE);
                String from=dateFrom.format(DateTimeFormatter.ISO_DATE);
                System.out.println(dateFrom.getMonthValue() + "  " + dateFrom.getYear());
                System.out.println(dateTo.getMonthValue() + "  " + dateTo.getYear());
                HDNV hdnv = new HDNV(from, to, Integer.parseInt(tfMaNV_HDNV.getText()));

                if (param == previewButton) {
                    hdnv.setType("preview");
                    return hdnv;
                } else if (param == exportButton) {
                    hdnv.setType("export");
                    return hdnv;
                }
                return null;
            }
        });
        Optional<HDNV> result = dialog.showAndWait();
        if (result.isPresent()) {

            if (result.get().getType().equals("preview")) {
                runReport("reportHDNV", result.get().getMaNV(), result.get().getDateFrom(), result.get().getDateTo(), false);
            } else if (result.get().getType().equals("export")) {
                runReport("reportHDNV", result.get().getMaNV(), result.get().getDateFrom(), result.get().getDateTo(), true);
            }

        }
    }

    @FXML
    void actionReportNhapHang(ActionEvent event) {

    }

    @FXML
    void actionReportNhapXuat(ActionEvent event) {
        ButtonType previewButton = new ButtonType("Preview");
        ButtonType exportButton = new ButtonType("Export");

        Dialog<ThoiGian> dialog = new Dialog<>();
        dialog.setTitle("Thống kế chi tiết-giá trị nhập xuất");
        dialog.setResizable(true);
        dialog.getDialogPane().setContent(pnThongKeCTNX);
        pnThongKeCTNX.setVisible(true);

        dialog.getDialogPane().getButtonTypes().add(previewButton);
        dialog.getDialogPane().getButtonTypes().add(exportButton);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Button btnPrv = (Button) dialog.getDialogPane().lookupButton(previewButton);
        btnPrv.addEventFilter(ActionEvent.ACTION, (evt) -> {
            LocalDate dateFrom = datePickerFrom.getValue();
            LocalDate dateTo = datePickerTo.getValue();
            if (dateFrom.isAfter(dateTo)) {
                evt.consume();
            }
        });
        dialog.setResultConverter(new Callback<ButtonType, ThoiGian>() {
            @Override
            public ThoiGian call(ButtonType param) {
                LocalDate dateFrom = datePickerFrom.getValue();
                LocalDate dateTo = datePickerTo.getValue();
                System.out.println(dateFrom.getMonthValue() + "  " + dateFrom.getYear());
                System.out.println(dateTo.getMonthValue() + "  " + dateTo.getYear());
                ThoiGian thoiGian = new ThoiGian(dateFrom.getMonthValue(), dateTo.getMonthValue(), dateFrom.getYear(), dateTo.getYear());

                if (param == previewButton) {
                    thoiGian.setType("preview");
                    return thoiGian;
                } else if (param == exportButton) {
                    thoiGian.setType("export");
                    return thoiGian;
                }
                return null;
            }
        });
        Optional<ThoiGian> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get().getThangFrom());
            System.out.println(result.get().getThangTo());
            System.out.println(result.get().getNamFrom());
            System.out.println(result.get().getNamTo());
            int i = result.get().getThangFrom();
            int j = result.get().getThangTo();
            int k = result.get().getNamFrom();
            int l = result.get().getNamTo();
            if (result.get().getType().equals("preview")) {

                runReport("CTGTNX", i, j, k, l, false);
            } else if (result.get().getType().equals("export")) {
                runReport("CTGTNX", i, j, k, l, true);
            }

        }
    }

    @FXML
    void changeChiNhanh(ActionEvent event) {

        if (!cbbChiNhanh.getValue().equals(DBConnectUtil.phanManhCurrent.getName())) {
            try {
                DBConnectUtil.phanManhCurrent = DBConnectUtil.listPhanManh.get(cbbChiNhanh.getSelectionModel().getSelectedIndex());
                DBConnectUtil.getConnection();
                System.out.println("phan manh hien tai" + DBConnectUtil.phanManhCurrent.getName());
                System.out.println("phan manh hien tai" + DBConnectUtil.phanManhCurrent.getSubscriberServer());
                System.out.println("đổi chi nhánh khác");
            } catch (SQLException ex) {
                System.out.println("Không thể kết nối tới server mới");
            }
        }
    }

    public void initModelChiNhanh() {
        //chuyen Chi nhanh de xem thong tin nhan vien
        ;
        chiNhanhModel.setList(DBConnectUtil.listPhanManh);
        for (PhanManh pm : chiNhanhModel.getList()) {
            cbbChiNhanh.getItems().add(pm.getName());
        }
        cbbChiNhanh.getSelectionModel().select(DBConnectUtil.phanManhCurrent.getName());
    }

    public static void runReport(String reportFile, boolean export) {
        String file = "src\\main\\java\\com\\mycompany\\QLVT\\Report\\" + reportFile + ".jrxml";
        try {

            //try {
            Connection connection = DBConnectUtil.getConnection();
            System.out.println("Done with connectToDatabase!: ");
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("Done with load!");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("Done with compileReport!");
            JRParameter[] jrParameters = jasperReport.getParameters();
            for (JRParameter param : jrParameters) {
                System.out.println("Parameter : " + param.getName());
                System.out.println("    Class Name: " + param.getValueClassName());
                System.out.println("    isSystemDefined: " + param.isSystemDefined());
            }
            HashMap jasperParameter = new HashMap();
            /*
            BigDecimal cutoffAmt = new BigDecimal("5000.00");
            jasperParameter.put("CutoffAmt",cutoffAmt);
             */
            jasperParameter.put("id", 7);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            System.out.println("Done with fillReport!");

            JRXlsExporter jRXlsExporter = new JRXlsExporter();
            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            jRXlsExporter.setExporterInput(exporterInput);

            OutputStreamExporterOutput exporterOutputXSL = new SimpleOutputStreamExporterOutput(reportFile + ".xls");

            jasperPrint.setProperty("net.sf.jasperreports.export.xls.create.custom.palette", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.size.fix.enabled", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.collapse.row.span", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.border", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.max.rows.per.sheet", "0");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.wrap.text", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.use.timezone", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xlsx.exclude.origin.band.2", "pageFooter");
            //config xls
            SimpleXlsReportConfiguration simpleXlsReportConfiguration = new SimpleXlsReportConfiguration();
            SimpleXlsExporterConfiguration xlsExporterConfiguration = new SimpleXlsExporterConfiguration();

            simpleXlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
            simpleXlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
            simpleXlsReportConfiguration.setDetectCellType(Boolean.TRUE);
            simpleXlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);

            jRXlsExporter.setConfiguration(xlsExporterConfiguration);
            jRXlsExporter.setExporterOutput(exporterOutputXSL);

//        JasperExportManager.exportReportToPdfFile(
//                jasperPrint, "reportHDNV2.pdf");
            JRPdfExporter jRPDFexporter = new JRPdfExporter();
            jRPDFexporter.setExporterInput(exporterInput);
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            jRPDFexporter.setConfiguration(configuration);
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(reportFile + ".pdf");
            jRPDFexporter.setExporterOutput(exporterOutput);

            JasperViewer.viewReport(jasperPrint, false);

            if (export == true) {
                jRPDFexporter.exportReport();
                jRXlsExporter.exportReport();
            }
            System.out.println("Done with setJasperPrint!");
            System.out.println("Done with exportReport!");

        } catch (SQLException ex) {
            System.out.println("Connect DB error" + ex.getMessage());
            Logger.getLogger(BaoCaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException jre) {
            System.out.println("JRE Exception " + jre.getMessage());
        } finally {
            DBConnectUtil.close();
        }
    }

    public static void runReport(String reportFile, int thangFrom, int thangTo, int namFrom, int namTo, boolean export) {
        String file = "src\\main\\java\\com\\mycompany\\QLVT\\Report\\" + reportFile + ".jrxml";
        try {

            //try {
            Connection connection = DBConnectUtil.getConnection();
            System.out.println("Done with connectToDatabase!: ");
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("Done with load!");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("Done with compileReport!");
//            JRParameter[] jrParameters = jasperReport.getParameters();
//            for (JRParameter param : jrParameters) {
//                System.out.println("Parameter : " + param.getName());
//                System.out.println("    Class Name: " + param.getValueClassName());
//                System.out.println("    isSystemDefined: " + param.isSystemDefined());
//            }
            HashMap jasperParameter = new HashMap();
            /*
            BigDecimal cutoffAmt = new BigDecimal("5000.00");
            jasperParameter.put("CutoffAmt",cutoffAmt);
             */
//       
            int i = thangFrom;
            int j = thangTo;
            int k = namFrom;
            int l = namTo;
            jasperParameter.put("thangFrom", i);
            jasperParameter.put("thangTo", j);
            jasperParameter.put("namFrom", k);
            jasperParameter.put("namTo", l);

            System.out.println("thangFrom" + thangFrom);
            System.out.println("thangTo" + thangTo);
            System.out.println("NAMFrom" + namFrom);
            System.out.println("namTo" + namTo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);

            System.out.println("Done with fillReport!");

            JasperViewer.viewReport(jasperPrint, false);

            JRXlsExporter jRXlsExporter = new JRXlsExporter();
            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            jRXlsExporter.setExporterInput(exporterInput);

            OutputStreamExporterOutput exporterOutputXSL = new SimpleOutputStreamExporterOutput(reportFile + ".xls");

            jasperPrint.setProperty("net.sf.jasperreports.export.xls.create.custom.palette", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.size.fix.enabled", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.collapse.row.span", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.border", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.max.rows.per.sheet", "0");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.wrap.text", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.use.timezone", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xlsx.exclude.origin.band.2", "pageFooter");
            //config xls
            SimpleXlsReportConfiguration simpleXlsReportConfiguration = new SimpleXlsReportConfiguration();
            SimpleXlsExporterConfiguration xlsExporterConfiguration = new SimpleXlsExporterConfiguration();

            simpleXlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
            simpleXlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
            simpleXlsReportConfiguration.setDetectCellType(Boolean.TRUE);
            simpleXlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);

            jRXlsExporter.setConfiguration(xlsExporterConfiguration);
            jRXlsExporter.setExporterOutput(exporterOutputXSL);

//        JasperExportManager.exportReportToPdfFile(
//                jasperPrint, "reportHDNV2.pdf");
            JRPdfExporter jRPDFexporter = new JRPdfExporter();
            jRPDFexporter.setExporterInput(exporterInput);
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            jRPDFexporter.setConfiguration(configuration);
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(reportFile + ".pdf");
            jRPDFexporter.setExporterOutput(exporterOutput);

            if (export == true) {
                jRPDFexporter.exportReport();
                jRXlsExporter.exportReport();
            }
            System.out.println("Done with setJasperPrint!");
            System.out.println("Done with exportReport!");

        } catch (SQLException ex) {
            System.out.println("Connect DB error" + ex.getMessage());
            Logger.getLogger(BaoCaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException jre) {
            System.out.println("JRE Exception " + jre.getMessage());
        } finally {
            DBConnectUtil.close();
        }
    }

    public static void runReport(String reportFile, int maNV, String dateFrom, String dateTo, boolean export) {
        String file = "src\\main\\java\\com\\mycompany\\QLVT\\Report\\" + reportFile + ".jrxml";
        try {

            //try {
            Connection connection = DBConnectUtil.getConnection();
            System.out.println("Done with connectToDatabase!: ");
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("Done with load!");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("Done with compileReport!");
//            JRParameter[] jrParameters = jasperReport.getParameters();
//            for (JRParameter param : jrParameters) {
//                System.out.println("Parameter : " + param.getName());
//                System.out.println("    Class Name: " + param.getValueClassName());
//                System.out.println("    isSystemDefined: " + param.isSystemDefined());
//            }
            HashMap jasperParameter = new HashMap();
            /*
            BigDecimal cutoffAmt = new BigDecimal("5000.00");
            jasperParameter.put("CutoffAmt",cutoffAmt);
             */
            System.out.println("Ma nv" + maNV);
            jasperParameter.put("id", maNV);
            jasperParameter.put("dateFrom", dateFrom);
            jasperParameter.put("dateTo", dateTo);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);

            System.out.println("Done with fillReport!");

            JasperViewer.viewReport(jasperPrint, false);

            JRXlsExporter jRXlsExporter = new JRXlsExporter();
            ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
            jRXlsExporter.setExporterInput(exporterInput);

            OutputStreamExporterOutput exporterOutputXSL = new SimpleOutputStreamExporterOutput(reportFile + ".xls");

            jasperPrint.setProperty("net.sf.jasperreports.export.xls.create.custom.palette", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.one.page.per.sheet", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.rows", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.remove.empty.space.between.columns", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.white.page.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.detect.cell.type", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.size.fix.enabled", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.graphics", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.collapse.row.span", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.border", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.ignore.cell.background", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.max.rows.per.sheet", "0");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.wrap.text", "true");
            jasperPrint.setProperty("net.sf.jasperreports.export.xls.use.timezone", "false");
            jasperPrint.setProperty("net.sf.jasperreports.export.xlsx.exclude.origin.band.2", "pageFooter");
            //config xls
            SimpleXlsReportConfiguration simpleXlsReportConfiguration = new SimpleXlsReportConfiguration();
            SimpleXlsExporterConfiguration xlsExporterConfiguration = new SimpleXlsExporterConfiguration();

            simpleXlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
            simpleXlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
            simpleXlsReportConfiguration.setDetectCellType(Boolean.TRUE);
            simpleXlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);

            jRXlsExporter.setConfiguration(xlsExporterConfiguration);
            jRXlsExporter.setExporterOutput(exporterOutputXSL);

//        JasperExportManager.exportReportToPdfFile(
//                jasperPrint, "reportHDNV2.pdf");
            JRPdfExporter jRPDFexporter = new JRPdfExporter();
            jRPDFexporter.setExporterInput(exporterInput);
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            jRPDFexporter.setConfiguration(configuration);
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(reportFile + ".pdf");
            jRPDFexporter.setExporterOutput(exporterOutput);

            if (export == true) {
                jRPDFexporter.exportReport();
                jRXlsExporter.exportReport();
            }
            System.out.println("Done with setJasperPrint!");
            System.out.println("Done with exportReport!");

        } catch (SQLException ex) {
            System.out.println("Connect DB error" + ex.getMessage());
            Logger.getLogger(BaoCaoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException jre) {
            System.out.println("JRE Exception " + jre.getMessage());
        } finally {
            DBConnectUtil.close();
        }
    }

    @FXML
    void initialize() {
        initModelChiNhanh();

        //Hoạt động nhân viên initModel
        List<NhanVien> nhanVienList = nhanVienService.findAll();
        hashNhanViens = (HashMap<Integer, NhanVien>) nhanVienList.stream().collect(Collectors.toMap(NhanVien::getMaNhanVien, nv -> nv));
     
        tfTenNV_HDNV.setEditable(false);
       
        tfMaNV_HDNV.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfMaNV_HDNV.setText(newValue.replaceAll("[^\\d]", ""));
            }else{
               // tfTenNV_HDNV.setText(hashNhanViens.get(newValue).getTen());
            }
        });

    }

}
