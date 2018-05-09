package controller.application.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import BLL.ReportBLL;
import Getway.ReportGetway;
import List.ListReport;
import controller.application.bug.AddBugController;
import controller.application.stock.AddUnitController;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ViewReportController implements Initializable{
	
	
	
        
    
    //buttons
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnClear;        

    @FXML
    private Button btnRefresh;
   
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnPrint;

    
    @FXML
    private TextField tfbugName;

    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
        
    

    @FXML
    private TextField tfReportName;

    @FXML
    private TextField tfProjectName;
    @FXML
    private TextField tfReportId;    

    @FXML
    private TextField tfStatus;
    
    
    
    //Image View
    @FXML
    private ImageView imgSearchBtn;
    @FXML
    private ImageView imgRefreshBtn;
    @FXML
    private ImageView imgPrintBtn;
    @FXML
    private ImageView imgExportBtn;
    
    
    //Table Columns
    @FXML
    private TableColumn<Object, Object> tblId;
    @FXML
    private TableColumn<Object, Object> tblReportId;
    @FXML
    private TableColumn<Object, Object> tblReportName;
    @FXML
    private TableColumn<Object, Object> tblBugName;
    @FXML
    private TableColumn<Object, Object> tblSeverity;
    @FXML
    private TableColumn<Object, Object> tblProjectName;
    @FXML
    private TableColumn<Object, Object> tblRaisedDate;
    @FXML
    private TableColumn<Object, Object> tblStatus;
    @FXML
    private TableColumn<Object, Object> tblResolvedDate;
    
    @FXML
    private TableView<ListReport> tblreportTable;
    
    
    
    @FXML
    public AnchorPane acContent;
    SQL sql = new SQL();

    Report report = new Report();
    ReportGetway reportGetway = new ReportGetway();
    ReportBLL reportBLL = new ReportBLL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public String id;
    private String usrId;
    private String creatorName;
    private String creatorId;
    private String supplyerId;
    private String userName;

    private userNameMedia media;
    
    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }



  /*  
    Image search  = new Image("/pkgimages/Search1.png");
    Image refresh  = new Image("/pkgimages/refresh.png");
    Image print  = new Image("/pkgimages/print.png");
    Image export  = new Image("/pkgimages/excel.png"); */
    
    
    
    ObservableList<String> list1 = FXCollections.observableArrayList("Sev1","Sev2","Sev3","Sev4");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        report.reportId = tfSearch.getText();
        report.reportName = tfSearch.getText();
        reportGetway.searchView(report);

    }

    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddReportController apc = new AddReportController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/report/AddReport.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddReportController addReportController = fxmlLoader.getController();
            media.setId(usrId);
            addReportController.setNameMedia(media);
            addReportController.lblHeader.setText("ADD REPORT DETAILS");
            addReportController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblreportTable.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String item = tblreportTable.getSelectionModel().getSelectedItem().getId();
            System.out.println("Bug id" + item);
            report.id = item;
            reportBLL.delete(report);
            btnRefreshOnAction(event);
        }

    }

    @FXML
    private void tblViewReportOnClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            if (!tblreportTable.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
        	tblreportTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	tblreportTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }
    
    
    @FXML
    private void tblViewCurrentBugOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }


    public void viewDetails() {
        System.out.println("CLCKED");
        tblreportTable.setItems(report.reportList);
        tblReportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        tblReportName.setCellValueFactory(new PropertyValueFactory<>("reportName"));
        tblBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblRaisedDate.setCellValueFactory(new PropertyValueFactory<>("raisedDate"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblResolvedDate.setCellValueFactory(new PropertyValueFactory<>("resolvedDate"));       
       
        reportGetway.viewFirstTen(report);
    }

    private void viewSelected() {
        AddReportController apc = new AddReportController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/report/AddReport.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddReportController addreportController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addreportController.setNameMedia(userMedia);
            addreportController.lblHeader.setText("PRODUCT DETAILS");
            addreportController.btnUpdate.setVisible(true);
            addreportController.btnSave.setVisible(false);
            addreportController.id = tblreportTable.getSelectionModel().getSelectedItem().getId();
            addreportController.viewSelected();
            Stage nStage = new Stage();
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void settingPermission() {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where id=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(8) == 0) {
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                }
                if (rs.getInt(3) == 0) {
                    btnAddNew.setDisable(true);
                }
                else {

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        report.reportList.clear();
        tfSearch.clear();
        
        tblreportTable.setItems(report.reportList);
        tblReportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        tblReportName.setCellValueFactory(new PropertyValueFactory<>("reportName"));
        tblBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblRaisedDate.setCellValueFactory(new PropertyValueFactory<>("raisedDate"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblResolvedDate.setCellValueFactory(new PropertyValueFactory<>("resolvedDate"));       
              
        reportGetway.view(report);

    }

    
    public void showDetails() {
    	tblreportTable.setItems(report.reportList);
        tblReportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        tblReportName.setCellValueFactory(new PropertyValueFactory<>("reportName"));
        tblBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblRaisedDate.setCellValueFactory(new PropertyValueFactory<>("raisedDate"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblResolvedDate.setCellValueFactory(new PropertyValueFactory<>("resolvedDate"));       
              
        reportGetway.view(report);

    }


    
    
    
    
    public List<Report> findAll(){    	
         List <Report> listReport = new ArrayList<Report>();
         
         
                 listReport.add(report);
             
           return listReport;
    }

    
    
    private void ShowReport(){
    	
    	try{
    		
    		/*List<Map<String, Object>> dataSource = new ArrayList<Map<String, Object>>();
    		
    		for(Report report : findAll()){
    			Map<String, Object> m = new HashMap<String,Object>();
    			
    			String ReportID =report.reportId;
    			System.out.println("ReportID:"+ReportID);
    			
    			m.put("ReportID", report.reportId);
    			 m.put("ReportName", report.reportName);
    			 m.put("BugName", report.bugName);
    			 m.put("Severity", report.severity);
    			 m.put("ProjectName", report.projectName);    			 
    			 m.put("RaisedDate", report.raisedDate);
    			 m.put("Status", report.status);
    			 m.put("ResolvedDate", report.resolvedDate);
    			 
    			 dataSource.add(m);
    			 
    			 
    			
    		      m.put("ReportTitle", "ReportList of Bugs/Errors");
    		      m.put("Author", "Prepared By Ajay");
    			 
    		      rs = con.createStatement().executeQuery("select * from "+db+".Report");
    		      Map<String, Object> map = new HashMap<String,Object>();
    		      while(rs.next()){
    		    	  
    		    	  Integer id = rs.getInt("Id");
    		    	  String reportId = rs.getString("ReportId");
    		    	  String reportName = rs.getString("ReportName");
    		    	  String bugName = rs.getString("BugName");
    		    	  String severity = rs.getString("Severity");
    		    	  String projectName = rs.getString("ProjectName");
    		    	  String raisedDate = rs.getString("RaisedDate");
    		    	  
    		    	  
    		    	  
    		      }
    		      
    		     
    			 JRDataSource datasource = new JRBeanCollectionDataSource(dataSource); 
    			
    			 */
    			 String source ="src\\controller\\application\\report\\BugReport.jrxml";
    			 JasperReport jreport = JasperCompileManager.compileReport(source);
    			 JasperPrint jprint = JasperFillManager.fillReport(jreport, null, con);
    		     JasperViewer.viewReport(jprint, false);

    			
    		//}
    		
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    	
    	
    }
        
    
    @FXML
    private void btnPrintOnAction(ActionEvent event) throws JRException {

        ShowReport();
    }

    @FXML
    void btnExportOnAction(ActionEvent event) {
    	
    	FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(btnExport.getScene().getWindow());
        if (file != null) {
            saveXLSFile(file);

        }

    }
    
    
    
    private void saveXLSFile(File file) {
        try {
            //System.out.println("Clicked,waiting to export....");
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            Row row1 = workSheet.createRow((short) 0);
            row1.createCell(0).setCellValue("REPORT ID");
            row1.createCell(1).setCellValue("REPORT NAME");
            row1.createCell(2).setCellValue("BUG NAME");
            row1.createCell(3).setCellValue("SEVERITY");
            row1.createCell(4).setCellValue("PROJECT NAME");
            row1.createCell(5).setCellValue("RAISED DATE");
            row1.createCell(6).setCellValue("STATUS");
            row1.createCell(7).setCellValue("RESOLVED DATE");
            
            Row row2;

            rs = con.createStatement().executeQuery("select * from "+db+".Report");
            while (rs.next()) {
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(rs.getString(2));
                row2.createCell(1).setCellValue(rs.getString(3));
                row2.createCell(2).setCellValue(rs.getString(4));
                row2.createCell(3).setCellValue(rs.getString(5));
                row2.createCell(4).setCellValue(rs.getString(6));
                row2.createCell(5).setCellValue(rs.getDate(7));
                row2.createCell(6).setCellValue(rs.getString(8));
                row2.createCell(7).setCellValue(rs.getDate(9));
                
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            rs.close();

            con.close();
            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Specified excel file successfully generated", NotificationType.SUCCESS);
            tn.showAndWait();
        } catch (SQLException | IOException e) {
            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Could not generate specified file", NotificationType.ERROR);
            tn.showAndWait();
            System.err.println(e);

        }
    }

   	

}
