/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.QLVT.Report;

import static com.mycompany.QLVT.Utils.DBConnectUtil.driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MinhTo
 */
public class TestReport {

    /**
     * Define connection
     */
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "sample";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "yourName";
    String password = "yourPassword";
    String fileName = "C:\\Documents and Settings\\compaq\\My Documents\\SampleDatabase.jasper";
    String outFileNamePDF = "c:\\SampleDatabaseTable.pdf";
    String outFilenameExcel = "c:\\SampleDatabaseTable.xls";

    public TestReport() {
        super();
    }

    @SuppressWarnings("deprecation")
    /**
     * This method is used to connect database with Jasper report Engine and to
     * export to .pdf and .els files. Jasper's JRExporter and JRXlsExporter are
     * used for the export.
     */

    public void generateReport(HashMap hm) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager
                    .getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            System.out.println("Filling report...");
            // used for synchronization between database and source file.
            JasperPrint print = JasperFillManager
                    .fillReport(fileName, hm, conn);

            System.out.println(conn);
            // define and initialize jasperreport engine's exporter for .pdf
            JRExporter exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();

//            // parameter used for the destined file.
//            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//                    outFileNamePDF);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//            // export to .pdf
//            exporter.exportReport();
//
//            JRXlsExporter exporterXLS = new JRXlsExporter();
//            exporterXLS
//                    .setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
//                            Boolean.FALSE);
//            exporterXLS.setParameter(
//                    JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE,
//                    Boolean.TRUE);
//            exporterXLS.setParameter(
//                    JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
//                    Boolean.FALSE);
//            exporterXLS.setParameter(
//                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//                    Boolean.TRUE);
            // parameter used for the destined file.
//            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME,
//                    outFilenameExcel);
//            exporterXLS
//                    .setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
//            // export to .xls
//            exporterXLS.exportReport();
//            System.out.println("Created file: " + outFileNamePDF);
//            System.out.println("Created file: " + outFilenameExcel);
//            System.out.println("Done!");
            conn.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void duaDuLieu() {
        try {
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/phan_cong_nhan_vien_1_1_nam?useUnicode=true&amp;characterEncoding=utf8", "root", "123456");

            JasperDesign jd = JRXmlLoader.load("H:\\Java_Project\\06012016Report\\src\\pkg06012016report\\report1.jrxml");

            JasperReport jr = JasperCompileManager.compileReport("H:\\Java_Project\\06012016Report\\src\\pkg06012016report\\report1.jrxml");

            JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);

            JasperViewer.viewReport(jp);

            JasperExportManager.exportReportToPdfFile(
                    jp, "H:\\Java_Project\\06012016Report\\src\\pkg06012016report\\report1.pdf");
        } catch (SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Cannot show report" + e.getMessage());
        }
    }

//    @SuppressWarnings("unchecked")

//    public static void main(String[] args) {
//        HashMap hm = new HashMap();
//        hm.put("EmployeeId", "1"); //or else hm.put("EmployeeId", args[o]);
//        hm.put("EmployeeName", "ABC");// or else hm.put("EmployeeName", args[1]);
//        hm.put("EmployeeEmail", "ABC@XYZ.COM");//or else hm.put("EmployeeEmail",args[2]);
//        new TestReport().generateReport(hm);
//        System.out.println("No. of argumetns are: " + args.length);
//        System.exit(1);
//    }
    public static void main(String[] args) {
//      String sourceFileName = "C://tools/jasperreports-5.0.1/test" + 
//         "/jasper_report_template.jrxml";

      System.out.println("Compiling Report Design ...");
      try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           Connection con = (Connection) DriverManager.getConnection("jdbc:sqlserver://MINHTO-PC\\MTSITE2;databaseName=QLVT", "cn2", "sa");
          JasperReport jr = JasperCompileManager.compileReport("src\\main\\java\\com\\mycompany\\QLVT\\Report\\report3.jrxml");

            JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
//
          JasperViewer.viewReport(jp);
          /**
           * 
          * Compile the report to a file name same as
          * the JRXML file name
          */
        //JasperCompileManager.compileReportToFile("E:\\du an git\\QLVT\\src\\main\\java\\com\\mycompany\\QLVT\\Report\\report2.jrxml");
      } catch (JRException e) {
         e.printStackTrace();
      } catch (SQLException ex) {
            Logger.getLogger(TestReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestReport.class.getName()).log(Level.SEVERE, null, ex);
        }
      System.out.println("Done compiling!!! ...");
   }
}
