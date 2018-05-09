package controller.application.bug;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BLL.BugTrackBLL;
import Getway.BugTrackGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
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

public class AddBugController implements Initializable {
	
	
	public String id;
    private String usrId;
    private userNameMedia nameMedia;

    BugTrack bug = new BugTrack();
    BugTrackGetway bugTrackGetway = new BugTrackGetway();
    BugTrackBLL bugTrackBLL = new BugTrackBLL();
    
    SQL sql = new SQL();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
	
	
	

	
    @FXML
    private TextField tfBugId;

    @FXML
    private Button btnClose;

    @FXML
    private TextField tfBugName;

    @FXML 
    public Button btnSave;

    @FXML
    private TextField tfSeverity;

    @FXML
    public Button btnUpdate;

    

    @FXML
    private TextArea taPostSolution;
    
    @FXML
    private DatePicker dpRaisedDate;
    
    @FXML
    private ComboBox<String> cmbBugType;
    
    
    @FXML
    private ComboBox<String> cmbBugLevel;
    
    @FXML 
   public Label lblHeader;
    
    
    @FXML
    private TextField tfProjectName; 
    
    @FXML
    private TextField tfTesterCode;
    
    @FXML
    private TextField tfDeveloperCode;
    
    @FXML
    private TextField tfStatus;
    
    
    public userNameMedia getNameMedia() {
        return nameMedia;
    }

    public void setNameMedia(userNameMedia nameMedia) {
        usrId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    
    ObservableList<String> list1 = FXCollections.observableArrayList("High","Moderate","Low");
    ObservableList<String> list2 = FXCollections.observableArrayList("L1","L2","L3","L4");
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	cmbBugType.setItems(list1);
    	cmbBugLevel.setItems(list2);
	}
	
    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
        System.out.println("Press Save");
        if (isNotNull()) {
            
        	   bug.id =id;
        	   bug.bugId = tfBugId.getText().trim();
        	   bug.bugName = tfBugName.getText().trim();
        	   bug.bugType = cmbBugType.getValue().toString();
        	   bug.bugLevel =cmbBugLevel.getValue().toString();
        	   bug.severity = tfSeverity.getText().trim();
        	   bug.projectName = tfProjectName.getText().trim();
        	   bug.testerCode = tfTesterCode.getText().trim();
        	   bug.raisedOn = dpRaisedDate.getValue().toString();
        	   bug.developerCode = tfDeveloperCode.getText().trim();
        	   bug.status = tfStatus.getText().trim();
        	   bug.postSolution = taPostSolution.getText().trim();
        	   
        	    
                bugTrackBLL.save(bug);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setHeaderText("Sucess : save sucess ");
                alert.setContentText("Bug added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();   
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    	
    	
    	System.out.println("Press Update.");
        if (isNotNull()) {
        	bug.bugId = tfBugId.getText().trim();
     	   bug.bugName = tfBugName.getText().trim();
     	   bug.bugType = cmbBugType.getValue().toString();
     	   bug.bugLevel =cmbBugLevel.getValue().toString();
     	   bug.severity = tfSeverity.getText().trim();
     	   bug.projectName = tfProjectName.getText().trim();
     	   bug.testerCode = tfTesterCode.getText().trim();
     	   bug.raisedOn = dpRaisedDate.getValue().toString();
     	   bug.developerCode = tfDeveloperCode.getText().trim();
     	   bug.status = tfStatus.getText().trim();
     	   bug.postSolution = taPostSolution.getText().trim();
     	   
     	    
             bugTrackBLL.update(bug);
     	  
     	        	   
     	  Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Update Employee");
          alert.setHeaderText("Success : save sucess ");
          alert.setContentText("Bug Updated successfully");
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
        if (tfBugId.getText().isEmpty() || tfBugName.getText().isEmpty()){
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
        bug.id = id;
        bugTrackGetway.selectedView(bug);
        
        tfBugId.setText(bug.bugId);
        tfBugName.setText(bug.bugName);
        cmbBugType.setPromptText(bug.bugType);
        cmbBugLevel.setPromptText(bug.bugLevel);
        tfSeverity.setText(bug.severity);        
        tfProjectName.setText(bug.projectName);
        tfTesterCode.setText(bug.testerCode);
        dpRaisedDate.setValue(LocalDate.parse(bug.raisedOn));
        tfDeveloperCode.setText(bug.developerCode);
        tfStatus.setText(bug.status);
        taPostSolution.setText(bug.postSolution);
        
    }

	

}
