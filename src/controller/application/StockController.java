
package controller.application;

import controller.ApplicationController;
import controller.application.bug.ViewBugController;
import controller.application.department.ViewDepartmentController;
import controller.application.employe.CurrentEmployeeController;
import controller.application.project.ViewProjectController;
import controller.application.stock.ViewBrandController;
import controller.application.stock.ViewCatagoryController;
import controller.application.stock.ViewRMAController;
import controller.application.stock.ViewSupplyerController;
import controller.application.stock.ViewUnitController;
import dataBase.DBConnection;
import dataBase.DBProperties;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


import media.userNameMedia;


public class StockController implements Initializable {
    @FXML
    private AnchorPane acHeadStore;
    @FXML
    private StackPane spMainContent;

    private String usrId;

    private userNameMedia userId;
    @FXML
    public BorderPane bpStore;
    @FXML
    private Label lblHeader;
    @FXML
    private ToggleButton btnStock;
    @FXML
    private ToggleButton btnSupplyer;
    
    @FXML
    private ToggleButton btnProject;
    @FXML
    private ToggleButton btnDepartment;
    @FXML
    private ToggleButton btnBugTrack;
    @FXML
    private ToggleButton btnUnit;
    @FXML
    private ToggleButton btnRma;
    @FXML
    private ToggleButton btnRepoerts;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public userNameMedia getUserId() {
        return userId;
    }

    public void setUserId(userNameMedia userId) {
        usrId = userId.getId();
        this.userId = userId;
    }
    
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ToggleGroup toggleGroup = new ToggleGroup();
        btnStock.setSelected(true);
        btnStock.setToggleGroup(toggleGroup);
        //btnSupplyer.setToggleGroup(toggleGroup);
        btnProject.setToggleGroup(toggleGroup);
        btnDepartment.setToggleGroup(toggleGroup);
        btnBugTrack.setToggleGroup(toggleGroup);
        btnUnit.setToggleGroup(toggleGroup);
        btnRma.setToggleGroup(toggleGroup);
        btnRepoerts.setToggleGroup(toggleGroup);


    }


    
    //Changes made for employee
    @FXML
    public void btnStockOnAction(ActionEvent event) throws IOException {
    	
    	System.out.println("Coming here user Control..");
        lblHeader.setText("Employee");
        //CurrentStoreController asc = new CurrentStoreController();
        CurrentEmployeeController asc = new CurrentEmployeeController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/CurrentStore.fxml").openStream());
        media.setId(usrId);
        /*CurrentStoreController currentStoreController = fXMLLoader.getController();
        currentStoreController.setMedia(userId);
        currentStoreController.viewDetails();
        currentStoreController.apCombobox.getStylesheets().add("/style/StoreCombobox.css");
        currentStoreController.settingPermission();
        */
        
        CurrentEmployeeController currentEmployeeeController = fXMLLoader.getController();
        currentEmployeeeController.setMedia(userId);
        currentEmployeeeController.viewDetails();
        currentEmployeeeController.apCombobox.getStylesheets().add("/style/StoreCombobox.css");
        currentEmployeeeController.settingPermission();
        StackPane acPane = fXMLLoader.getRoot();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnSupplyerOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Project");
        ViewSupplyerController vsc = new ViewSupplyerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewSupplier.fxml").openStream());
        media.setId(usrId);
        ViewSupplyerController viewSupplyerController = fXMLLoader.getController();
        viewSupplyerController.setMedia(userId);
        viewSupplyerController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
    
    
    // Added for Project Tab
    @FXML
    private void btnProjectOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Project");
        ViewProjectController vsc = new ViewProjectController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewProject.fxml").openStream());
        media.setId(usrId);
        ViewProjectController viewProjectController = fXMLLoader.getController();
        viewProjectController.setMedia(userId);
        viewProjectController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    
    
    
 // Added for Department Tab
    @FXML
    private void btnDepartmentOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Department");
        ViewDepartmentController vsc = new ViewDepartmentController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewDepartment.fxml").openStream());
        media.setId(usrId);
        ViewDepartmentController viewDepartmentController = fXMLLoader.getController();
        viewDepartmentController.setMedia(userId);
        viewDepartmentController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }
    

    //method call for Bug-Track
    @FXML
    private void btnBugTrackOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("BugTracking");
        ViewBugController vbc = new ViewBugController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewBug.fxml").openStream());
        media.setId(usrId);
        ViewBugController viewBugController = fXMLLoader.getController();
        viewBugController.setMedia(userId);
        viewBugController.showDetails();
        StackPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    
    
    
    
    

    @FXML
    private void btnBrandsOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Brands");
        ViewBrandController vbc = new ViewBrandController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewBrand.fxml").openStream());
        media.setId(usrId);
        ViewBrandController viewBrandController = fXMLLoader.getController();
        viewBrandController.setMedia(userId);
        viewBrandController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnCatagoryOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Catagories");
        ViewCatagoryController vcc = new ViewCatagoryController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewCategory.fxml").openStream());
        media.setId(usrId);
        ViewCatagoryController viewCatagoryController = fXMLLoader.getController();
        viewCatagoryController.setMedia(userId);
        viewCatagoryController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnUnitOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("Unit");
        ViewUnitController vuc = new ViewUnitController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewUnit.fxml").openStream());
        media.setId(usrId);
        ViewUnitController viewUnitController = fXMLLoader.getController();
        viewUnitController.setMedia(userId);
        viewUnitController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRmaOnAction(ActionEvent event) throws IOException {
        lblHeader.setText("RMA");
        ViewRMAController vrmac = new ViewRMAController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/stock/ViewRMA.fxml").openStream());
        media.setId(usrId);
        ViewRMAController viewRMAController = fXMLLoader.getController();
        viewRMAController.setMedia(userId);
        viewRMAController.showDetails();
        AnchorPane acPane = fXMLLoader.getRoot();

        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(acPane);
    }

    @FXML
    private void btnRepoertsOnAction(ActionEvent event) {
    }
    
    public void settingPermission(){
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where id=?");
            pst.setString(1, usrId);
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(2)==0 && rs.getInt(9) == 0){
                    btnProject.setDisable(true);
                }if(rs.getInt(4) == 0 && rs.getInt(10) == 0){
                    btnDepartment.setDisable(true);
                }if(rs.getInt(5) == 0 && rs.getInt(11) == 0){
                    btnBugTrack.setDisable(true);
                }if(rs.getInt(6) == 0 && rs.getInt(12) == 0){
                    btnUnit.setDisable(true);
                }if(rs.getInt(14) == 0){
                    btnRma.setDisable(true);
                }
                
                else{
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
