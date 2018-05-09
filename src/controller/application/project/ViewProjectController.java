package controller.application.project;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import BLL.ProjectBLL;
import Getway.ProjectGateway;
import List.ListProject;

import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class ViewProjectController implements Initializable {
    @FXML
    public AnchorPane acContent;
    SQL sql = new SQL();
    
    Project project = new Project();
    ProjectGateway projectGetway = new ProjectGateway();
    ProjectBLL projectBLL = new ProjectBLL();
    //History history = new History();

    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    public String id;
    private String usrId;
    private String creatorName;
    private String creatorId;
    private String supplyerId;
    private String userName;

    private userNameMedia media;


    @FXML
    private TableView<ListProject> tblProject;
    @FXML
    private TableColumn<Object, Object> clmId;
    @FXML
    private TableColumn<Object, Object> clmProjectId;
    @FXML
    private TableColumn<Object, Object> clmProjectName;
    @FXML
    private TableColumn<Object, Object> clmProjectDescription;
    @FXML
    private TableColumn<Object, Object> clmDeliveryDate;
    @FXML
    private TableColumn<Object, Object> clmDuration;
    @FXML
    private TableColumn<Object, Object> clmClientName;    
    @FXML
    private TableColumn<Object, Object> clmProjectManager;

    private final static int dataSize = 10_023;
    private final static int rowsPerPage = 1000;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfSearch;
    private Text text;
    @FXML
    private MenuItem mbSearch;
    @FXML
    private Button btnRefresh;


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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    private void tblProjectOnClick(MouseEvent event) {
        int click = event.getClickCount();
        if (click == 2) {
            viewDetails();
        }

    }

    @FXML
    private void tblProjectOnKeyPress(KeyEvent event) {
    	
    	project.projectDetails.removeAll();
        project.projectName = tfSearch.getText().trim();

        tblProject.setItems(project.projectDetails);
        clmProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        clmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clmProjectDescription.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        clmDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clmProjectManager.setCellValueFactory(new PropertyValueFactory<>("pManager"));
        projectGetway.searchView(project);

    }


    @FXML
    public void tfSearchOnType(Event event) {
        project.projectDetails.removeAll();
        project.projectName = tfSearch.getText().trim();

        tblProject.setItems(project.projectDetails);
        clmProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        clmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clmProjectDescription.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        clmDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clmProjectManager.setCellValueFactory(new PropertyValueFactory<>("pManager"));
        projectGetway.searchView(project);
    }


    public void showDetails() {
        tblProject.setItems(project.projectDetails);
        clmProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        clmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clmProjectDescription.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        clmDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clmProjectManager.setCellValueFactory(new PropertyValueFactory<>("pManager"));
        projectGetway.view(project);

    }

   
    
    
    

    

    @FXML
    private void mbDeleteItem(ActionEvent event) {
        System.out.println("clicked to delete");
        acContent.setOpacity(0.5);
        ListProject selectedProject = tblProject.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            project.id = selectedProject.getProjectId();
            System.out.println(project.id+ "*******");
            projectBLL.delete(project);
            tblProject.getItems().clear();
            showDetails();
        }else{
            
        }
        


    }

       

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        project.projectDetails.clear();
        showDetails();
    }


  //searching purpose
    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
    	project.projectId = tfSearch.getText();
    	project.projectName = tfSearch.getText();
    	projectGetway.searchView(project);

    }

    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
    	
    	System.out.println("Button addNew Project..");
        AddProjectController apc = new AddProjectController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddProject.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProjectController addProjectController = fxmlLoader.getController();
            media.setId(usrId);
            addProjectController.setNameMedia(media);
            addProjectController.lblHeader.setText("Add PROJECT");
            addProjectController.btnUpdate.setVisible(false);
            Stage nStage = new Stage();
//            addProductController.addSupplyerStage(nStage);
            nStage.setScene(scene);
            nStage.initModality(Modality.APPLICATION_MODAL);
            nStage.initStyle(StageStyle.TRANSPARENT);
            nStage.setMinHeight(500.0);
            nStage.setMinWidth(550.0);
            nStage.show();
           
            //btnRefreshOnACtion(event);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
    	
    	System.out.println("Button Update Project....");
        if (!tblProject.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
        
        btnRefreshOnACtion(event);        
        
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
    	
    	System.out.println("Button Delete Project....");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Project Delete Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String Id = tblProject.getSelectionModel().getSelectedItem().getId();
            String ProjectId = tblProject.getSelectionModel().getSelectedItem().getProjectId();
            System.out.println("id" + Id);
            System.out.println("Project id" +ProjectId);
            project.id = Id;
            projectBLL.delete(project);
            btnRefreshOnACtion(event);
        }

    }
    
    
    @FXML
    private void tblViewProjectOnClick(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            if (!tblProject.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblProject.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblProject.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }

    /*@FXML
    private void tblViewProjectOnClick(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            if (!tblProject.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblProject.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblProject.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    } */

    public void viewDetails() {
        System.out.println("CLICKED");
        
        
        tblProject.setItems(project.projectDetails);
        clmProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        clmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clmProjectDescription.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        clmDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clmProjectManager.setCellValueFactory(new PropertyValueFactory<>("pManager"));
        
        projectGetway.viewFirstTen(project); 
        
    }

    
    private void viewSelected() {
        AddProjectController apc = new AddProjectController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddProject.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddProjectController addProjectController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addProjectController.setNameMedia(userMedia);
            addProjectController.lblHeader.setText("PROJECT DETAILS");
            addProjectController.btnUpdate.setVisible(true);
            addProjectController.btnSave.setVisible(false);
            addProjectController.id = tblProject.getSelectionModel().getSelectedItem().getId();
            addProjectController.viewSelected();
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
        project.projectDetails.clear();
        tfSearch.clear();
        
        tblProject.setItems(project.projectDetails);
        clmProjectId.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        clmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        clmProjectDescription.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        clmDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        clmClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        clmProjectManager.setCellValueFactory(new PropertyValueFactory<>("pManager"));
        
        projectGetway.view(project);

    }

    

    @FXML
    private void tblViewProjectOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }

    

}
