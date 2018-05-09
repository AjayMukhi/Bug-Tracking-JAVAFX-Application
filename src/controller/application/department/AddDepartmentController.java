package controller.application.department;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BLL.DepartmentBLL;
import Getway.DepartmentGateway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class AddDepartmentController implements Initializable {
	
	
	public String id;
    private String usrId;
    private userNameMedia nameMedia;

    Department department = new Department();
    DepartmentGateway departmentGetway = new DepartmentGateway();
    DepartmentBLL departmentBLL = new DepartmentBLL();
    
    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
	
	
	

	
    @FXML
    private TextField tfDepartmentId;

    @FXML
    private Button btnClose;

    @FXML
    private TextField tfDepartmentName;

    @FXML 
    public Button btnSave;

    @FXML
    private TextField tfDepartmentLocation;

    @FXML
    public Button btnUpdate;

    @FXML
    private TextField tfDepartmentType;

    
    @FXML 
   public Label lblHeader;
    
    
    
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    
    
    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println("Press Save");
        if (isNotNull()) {
            
        	   department.id =id;
        	   department.departmentId = tfDepartmentId.getText().trim();
        	   department.departmentName = tfDepartmentName.getText().trim();
        	   department.departmentLocation = tfDepartmentLocation.getText().trim();
        	   department.departmentType = tfDepartmentType.getText().trim();
        	    
                departmentBLL.save(department);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Department added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();   
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    	
    	
    	System.out.println("Press Update.");
        if (isNotNull()) {
        	department.departmentId = tfDepartmentId.getText().trim();
     	   department.departmentName = tfDepartmentName.getText().trim();
     	   department.departmentLocation = tfDepartmentLocation.getText().trim();
     	   department.departmentType = tfDepartmentType.getText().trim();
     	  
     	   departmentBLL.update(department);
     	   
     	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Update Employee");
          alert.setHeaderText("Success : save sucess ");
          alert.setContentText("Department Updated successfully");
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
        if (tfDepartmentId.getText().isEmpty() || tfDepartmentName.getText().isEmpty()){
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
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void viewSelected() {
        department.id = id;
        departmentGetway.selectedView(department);
        tfDepartmentId.setText(department.departmentId);
        tfDepartmentName.setText(department.departmentName);
        tfDepartmentLocation.setText(department.departmentLocation);
        tfDepartmentType.setText(department.departmentType);
        
    }

	


}
