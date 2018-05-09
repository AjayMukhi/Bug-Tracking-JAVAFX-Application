package controller.application.employe;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import BLL.CurrentEmployeeBLL;
import Getway.CurrentEmployeeGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class AddEmployeeController  implements Initializable{
	
	
	public String id;
    private String usrId;
    private userNameMedia nameMedia;

    CurrentEmployee employeeCurrent = new CurrentEmployee();
    CurrentEmployeeGetway currentEmployeeGetway = new CurrentEmployeeGetway();
    CurrentEmployeeBLL currentEmployeeBLL = new CurrentEmployeeBLL();
    
    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    
    @FXML
    private TextField tfEmailAddress;

    @FXML
    private Button btnClose;

    @FXML
    private TextArea taAddress;

    @FXML
    private DatePicker dpDateofJoining;

    @FXML 
    public Label lblHeader;

    @FXML
    private TextField tfEmployeeId;

    @FXML
    private TextField tfEmployeeName;

    @FXML 
    public Button btnSave;

    @FXML
    private TextField tfQualification;

    @FXML
    private TextField tfContactNo;

    @FXML
    private TextField tfGender;

    @FXML
    private TextField tfRole;

    @FXML
    private DatePicker dpDOB;

    
    @FXML
    public Button btnUpdate;
    

    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    
    private boolean isNotNull() {
        boolean insNotNull = false;
        if (tfEmployeeId.getText().isEmpty() || tfEmployeeName.getText().isEmpty()){
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

    
    
    
    
    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println("Press Save");
        if (isNotNull()) {
            
        	   employeeCurrent.employeeId = tfEmployeeId.getText().trim();
        	   employeeCurrent.employeeName = tfEmployeeName.getText().trim();
        	   employeeCurrent.gender = tfGender.getText().trim();
        	   employeeCurrent.dob = dpDOB.getValue().toString();
        	   employeeCurrent.qualification = tfQualification.getText().trim();
        	   employeeCurrent.address = taAddress.getText().trim();
        	   employeeCurrent.contactNo = tfContactNo.getText().trim();
        	   employeeCurrent.emailAddress = tfEmailAddress.getText().trim();
        	   employeeCurrent.dateofJoining = dpDateofJoining.getValue().toString();
        	   employeeCurrent.role = tfRole.getText().trim();        	   
        	   
                
                currentEmployeeBLL.save(employeeCurrent);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Employee added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();   
        }
    }

    
    

    

    
    
    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
   	
    	System.out.println("Press Update.");
        if (isNotNull()) {
           employeeCurrent.employeeId = tfEmployeeId.getText().trim();
     	   employeeCurrent.employeeName = tfEmployeeName.getText().trim();
     	   employeeCurrent.gender = tfGender.getText().trim();
     	   employeeCurrent.dob = dpDOB.getValue().toString();
     	   employeeCurrent.qualification = tfQualification.getText().trim();
     	   employeeCurrent.address = taAddress.getText().trim();
     	   employeeCurrent.contactNo = tfContactNo.getText().trim();
     	   employeeCurrent.emailAddress = tfEmailAddress.getText().trim();
     	   employeeCurrent.dateofJoining = dpDateofJoining.getValue().toString();
     	   employeeCurrent.role = tfRole.getText().trim();  
            
     	   currentEmployeeBLL.update(employeeCurrent);
     	   
     	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Update Employee");
          alert.setHeaderText("Success : save sucess ");
          alert.setContentText("Employee Updated successfully");
          alert.initStyle(StageStyle.UNDECORATED);
          alert.showAndWait();
     	   
          //refreshEmployeeList();
        }

    }

    public void viewSelected() {
        employeeCurrent.id = id;
        currentEmployeeGetway.selectedView(employeeCurrent);
        tfEmployeeId.setText(employeeCurrent.employeeId);
        tfEmployeeName.setText(employeeCurrent.employeeName);
        tfGender.setText(employeeCurrent.gender);
        dpDOB.setValue(LocalDate.parse(employeeCurrent.dob));
        
        tfQualification.setText(employeeCurrent.qualification);
        taAddress.setText(employeeCurrent.address);
        tfContactNo.setText(employeeCurrent.contactNo);
        tfEmailAddress.setText(employeeCurrent.emailAddress);
        dpDateofJoining.setValue(LocalDate.parse(employeeCurrent.dateofJoining));
        tfRole.setText(employeeCurrent.role);
        
        
    }

    

    public void refreshEmployeeList(ActionEvent event) {
        try {
            CurrentEmployeeController asc = new CurrentEmployeeController();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/application/stock/CurrentStore.fxml").openStream());
            CurrentEmployeeController currentEmployeeController = fXMLLoader.getController();
            currentEmployeeController.viewDetails();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
