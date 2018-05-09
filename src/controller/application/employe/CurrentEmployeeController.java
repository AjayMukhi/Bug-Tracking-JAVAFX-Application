package controller.application.employe;


import Getway.CurrentEmployeeGetway;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import BLL.CurrentEmployeeBLL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;


import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;


public class CurrentEmployeeController implements Initializable {

    CurrentEmployee employeeCurrent = new CurrentEmployee();
    CurrentEmployeeGetway currentEmployeeGetway = new CurrentEmployeeGetway();
    CurrentEmployeeBLL currentEmployeeBLL = new CurrentEmployeeBLL();
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    private String usrId;

    private userNameMedia media;
    @FXML
    public StackPane spEmployeeContent;
    @FXML
    private TextField tfSearch;
 
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
   
    
   
    //for new Employee
    @FXML
    private TableView<ListEmployee> tblViewCurrentEmployee;    
    @FXML
    private TableColumn<Object, Object> tblClmEmployeeId;
    @FXML
    private TableColumn<Object, Object> tblClmEmployeeName;
    @FXML
    private TableColumn<Object, Object> tblClmGender;
    @FXML
    private TableColumn<Object, Object> tblClmDOB;
    @FXML
    private TableColumn<Object, Object> tblClmQualification;
    @FXML
    private TableColumn<Object, Object> tblClmAddress;
    @FXML
    private TableColumn<Object, Object> tblClmContactNo;
    @FXML
    private TableColumn<Object, Object> tblClmEmailAddress;
    @FXML
    private TableColumn<Object, Object> tblClmDateofJoining;
    @FXML
    private TableColumn<Object, Object> tblClmRole;
    
    
    
    
    
    SQL sql = new SQL();
    @FXML
    private Button btnRefresh;
    @FXML
    public AnchorPane apCombobox;

    public userNameMedia getMedia() {
        return media;
    }

    public void setMedia(userNameMedia media) {
        usrId = media.getId();
        this.media = media;
    }

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    
    //searching purpose
    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
    	employeeCurrent.employeeId = tfSearch.getText();
    	employeeCurrent.employeeName = tfSearch.getText();
    	currentEmployeeGetway.searchView(employeeCurrent);

    }

    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
    	
    	System.out.println("Button addNew Employee..");
        AddEmployeeController apc = new AddEmployeeController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddEmployee.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddEmployeeController addEmpController = fxmlLoader.getController();
            media.setId(usrId);
            addEmpController.setNameMedia(media);
            addEmpController.lblHeader.setText("Add EMPLOYEE");
            addEmpController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.setMinHeight(500.0);
            nStage.setMinWidth(550.0);
            nStage.show();
           
            btnRefreshOnACtion(event);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    	
    	System.out.println("Button Update Employee....");
        if (!tblViewCurrentEmployee.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
        
        btnRefreshOnACtion(event);        
        
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    	
    	System.out.println("Button Delete Employee....");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Employee Delete Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String Id = tblViewCurrentEmployee.getSelectionModel().getSelectedItem().getId();
            String EmployeeId = tblViewCurrentEmployee.getSelectionModel().getSelectedItem().getEmployeeId();
            System.out.println("id" + Id);
            System.out.println("Employee id" + EmployeeId);
            employeeCurrent.id = Id;
            currentEmployeeBLL.delete(employeeCurrent);
            btnRefreshOnACtion(event);
        }

    }

    @FXML
    private void tblViewCurrentEmployeeOnClick(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            if (!tblViewCurrentEmployee.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblViewCurrentEmployee.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblViewCurrentEmployee.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }

    public void viewDetails() {
        System.out.println("CLICKED");
        tblViewCurrentEmployee.setItems(employeeCurrent.currentEmployeeList);
        tblClmEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblClmEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tblClmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblClmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblClmQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        tblClmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblClmContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblClmEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tblClmDateofJoining.setCellValueFactory(new PropertyValueFactory<>("dateofJoining"));
        tblClmRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        currentEmployeeGetway.viewFistTen(employeeCurrent);
    }

    private void viewSelected() {
        AddEmployeeController apc = new AddEmployeeController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddEmployee.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddEmployeeController addEmployeeController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addEmployeeController.setNameMedia(userMedia);
            addEmployeeController.lblHeader.setText("EMPLOYEE DETAILS");
            addEmployeeController.btnUpdate.setVisible(true);
            addEmployeeController.btnSave.setVisible(false);
            addEmployeeController.id = tblViewCurrentEmployee.getSelectionModel().getSelectedItem().getId();
            addEmployeeController.viewSelected();
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
    public void btnRefreshOnACtion(ActionEvent event) {
    	
    	
    	System.out.println("Inside btnRefreshOnACtion ...");
        employeeCurrent.currentEmployeeList.clear();
        tfSearch.clear();
        
        tblViewCurrentEmployee.setItems(employeeCurrent.currentEmployeeList);
        tblClmEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblClmEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tblClmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblClmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblClmQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        tblClmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblClmContactNo.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblClmEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        tblClmDateofJoining.setCellValueFactory(new PropertyValueFactory<>("dateofJoining"));
        tblClmRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        currentEmployeeGetway.view(employeeCurrent);

    }

    

    @FXML
    private void tblViewCurrentEmployeeOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }

}
