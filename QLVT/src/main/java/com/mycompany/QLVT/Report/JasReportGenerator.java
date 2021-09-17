/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Report;

/**
 *
 * @author MinhTo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRParameter;
import java.util.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

public class JasReportGenerator {
    
    public JasReportGenerator() {
    }
    
    public static Connection connectToDatabase(String databaseName, String userName, String password) {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(databaseName, userName, password);
        } catch (Exception e) {
            String text = "Could not connect to the database: " + e.getMessage() + " "
                    + e.getLocalizedMessage();
            System.out.println(text);
        }
        return connection;
    }
    
    public static void runReport(String databaseName, String userName, String password, String reportFile) throws net.sf.jasperreports.engine.JRException {
//    try {
        Connection connection = connectToDatabase(databaseName, userName, password
        );
        System.out.println("Done with connectToDatabase!");
        JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
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
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
        System.out.println("Done with fillReport!");
        
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
    
        JRXlsExporter exporter = new JRXlsExporter();
        /// export.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    //   exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "output.xls");
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      //  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("output.xls"));
        SimpleXlsReportConfiguration simpleXlsReportConfiguration = new SimpleXlsReportConfiguration();
        SimpleXlsExporterConfiguration xlsExporterConfiguration = new SimpleXlsExporterConfiguration();
        simpleXlsReportConfiguration.setOnePagePerSheet(Boolean.FALSE);
        simpleXlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
        simpleXlsReportConfiguration.setDetectCellType(Boolean.TRUE);
        simpleXlsReportConfiguration.setWhitePageBackground(Boolean.FALSE);
        exporter.setConfiguration(simpleXlsReportConfiguration);
       // exporter.exportReport();
        
        System.out.println("Done with setJasperPrint!");
      
        System.out.println("Done with exportReport!");
        JasperViewer.viewReport(jasperPrint);
// System.out.println("Done with viewReport!");
//   } catch(Exception e) {
//     String text = "Could not create the report " + e.getMessage() + " " + e.g
        //etLocalizedMessage();
//      System.out.println(text);
//    }
    }
    
    public static void main(String[] args) throws net.sf.jasperreports.engine.JRException {
        
        String databaseName = "jdbc:sqlserver://MINHTO-PC\\MTSITE2;databaseName=QLVT";
        String userName = "HTKN";
        String password = "sa";
        String reportFile = "src\\main\\java\\com\\mycompany\\QLVT\\Report\\DSNV.jrxml";
        runReport(databaseName, userName, password, reportFile);
        
    }
}
