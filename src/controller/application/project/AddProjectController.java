package controller.application.project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BLL.ProjectBLL;
import Getway.ProjectGateway;
import controller.application.employe.CurrentEmployeeController;
import custom.EffectUtility;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class AddProjectController implements Initializable {
	
	
	
	public String id;
    private String usrId;
    private userNameMedia nameMedia;

    Project project = new Project();
    ProjectGateway projectGetway = new ProjectGateway();
    ProjectBLL projectBLL = new ProjectBLL();
    
    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
	
	
	

	@FXML
    private DatePicker dpDeliveryDate;

    @FXML
    private TextField tfProjectId;

    @FXML
    private Button btnClose;

    @FXML
    private TextField tfProjectDescription;

    @FXML 
    public Button btnSave;

    @FXML
    private TextField tfProjectManager;

    @FXML
    public Button btnUpdate;

    @FXML
    private TextField tfProjectName;

    @FXML
    private TextField tfClientName;

    @FXML
    private TextField tfDuration;

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
            
        	   project.projectId = tfProjectId.getText().trim();
        	   project.projectName = tfProjectName.getText().trim();
        	   project.projectDescription = tfProjectDescription.getText().trim();
        	   project.deliveryDate = dpDeliveryDate.getValue().toString();
        	   project.duration = tfDuration.getText().trim();
        	   project.clientName = tfClientName.getText().trim();
        	   project.pManager = tfProjectManager.getText().trim();        	   
                
                projectBLL.save(project);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Project added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();   
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    	
    	
    	System.out.println("Press Update.");
        if (isNotNull()) {
           project.projectId = tfProjectId.getText().trim();
     	   project.projectName = tfProjectName.getText().trim();
     	   project.projectDescription = tfProjectDescription.getText().trim();
     	   project.deliveryDate = dpDeliveryDate.getValue().toString();
     	   project.duration = tfDuration.getText().trim();
     	   project.clientName = tfClientName.getText().trim();
     	   project.pManager = tfProjectManager.getText().trim();
     	  
     	   projectBLL.update(project);
     	   
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
        if (tfProjectId.getText().isEmpty() || tfProjectName.getText().isEmpty()){
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
        project.id = id;
        projectGetway.selectedView(project);
        tfProjectId.setText(project.projectId);
        tfProjectName.setText(project.projectName);
        tfProjectDescription.setText(project.projectDescription);
        dpDeliveryDate.setValue(LocalDate.parse(project.deliveryDate));
        
        tfDuration.setText(project.duration);        
        tfClientName.setText(project.clientName);
        tfProjectManager.setText(project.pManager);       
        
    }

	
	
	
}
