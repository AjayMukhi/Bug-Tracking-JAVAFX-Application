package controller.application.report;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BLL.ReportBLL;
import BLL.UnitBLL;
import DAL.Unit;
import Getway.ReportGetway;
import Getway.UnitGetway;
import custom.CustomTf;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class AddReportController implements Initializable {

	public String id;
    Report report = new Report();
    ReportGetway reportGetway = new ReportGetway();
    ReportBLL reportBLL = new ReportBLL();

    public String unitId;
    
    @FXML
    private TextField tfUnitName;
    
    @FXML
    private Button btnClrUnitName;
    
    private String usrId;
    
    private userNameMedia nameMedia;
    
    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
	
	
    @FXML
    private DatePicker dpRaisedDate;
	

	@FXML
    private DatePicker dpResolvedDate;
	
	
	@FXML
    private TextField tfReportName;

    @FXML
    private TextField tfProjectName;
    @FXML
    private TextField tfReportId;   
    
    @FXML
    private TextField tfBugName;

    @FXML
    private TextField tfStatus;
    
    @FXML
    private ComboBox<String> cmbSeverity;

    
    @FXML
    private Button btnClose;

    

    @FXML 
    public Button btnSave;

  

    @FXML
    public Button btnUpdate;

    
    
    @FXML 
   public Label lblHeader;
    
    
    
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    
    
    ObservableList<String> list1 = FXCollections.observableArrayList("Sev1","Sev2","Sev3","Sev4");
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		cmbSeverity.setItems(list1);
	}
	
    
    
    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println("Press Save");
        if (isNotNull()) {
            
        	   report.reportId = tfReportId.getText().trim();
        	   report.reportName = tfReportName.getText().trim();
        	   report.bugName = tfBugName.getText().trim();
        	   report.severity = cmbSeverity.getValue().trim();
        	   report.projectName =tfProjectName.getText().trim();
        	   report.raisedDate = dpRaisedDate.getValue().toString();
        	   report.status =tfStatus.getText().trim();
        	   report.resolvedDate = dpResolvedDate.getValue().toString();
        		
                reportBLL.save(report);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Report added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();   
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    	
    	
    	System.out.println("Press Update.");
        if (isNotNull()) {
        	report.reportId = tfReportId.getText().trim();
     	   report.reportName = tfReportName.getText().trim();
     	   report.bugName = tfBugName.getText().trim();
     	   report.severity = cmbSeverity.getValue().trim();
     	   report.projectName =tfProjectName.getText().trim();
     	   report.raisedDate = dpRaisedDate.getValue().toString();
     	   report.status =tfStatus.getText().trim();
     	   report.resolvedDate = dpResolvedDate.getValue().toString();
     		
     	  
     	   reportBLL.update(report);
     	   
     	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Update Employee");
          alert.setHeaderText("Success : save sucess ");
          alert.setContentText("Project Updated successfully");
          alert.initStyle(StageStyle.UNDECORATED);
          alert.showAndWait();
     	   
          //refreshEmployeeList();
        }

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    
    private boolean isNotNull() {
        boolean insNotNull = false;
        if (tfReportId.getText().isEmpty() || tfReportName.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("ERROR : NULL FOUND");
            alert.setContentText("Please fill all require field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

            insNotNull = false;
        } else {
            insNotNull = true;
        }
        return insNotNull;
    }
    
    
    
	
	
	public void viewSelected() {
		report.id = id;
        reportGetway.selectedView(report);
        
        tfReportId.setText(report.reportId);
        tfReportName.setText(report.reportName);
        tfBugName.setText(report.bugName);
        cmbSeverity.setPromptText(report.severity);        
        tfProjectName.setText(report.projectName); 
        dpRaisedDate.setValue(LocalDate.parse(report.raisedDate));
        tfStatus.setText(report.status);
        dpResolvedDate.setValue(LocalDate.parse(report.resolvedDate));
         
        
    }

	
}
