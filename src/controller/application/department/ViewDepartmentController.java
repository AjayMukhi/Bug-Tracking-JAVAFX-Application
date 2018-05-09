package controller.application.department;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import BLL.DepartmentBLL;
import Getway.DepartmentGateway;
import List.ListDepartment;
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

public class ViewDepartmentController implements Initializable {



@FXML
public AnchorPane acContent;
SQL sql = new SQL();

Department department = new Department();
DepartmentGateway departmentGetway = new DepartmentGateway();
DepartmentBLL departmentBLL = new DepartmentBLL();


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
private TableView<ListDepartment> tblDepartment;
@FXML
private TableColumn<Object, Object> tblclmId;
@FXML
private TableColumn<Object, Object> tblclmDepartmentId;
@FXML
private TableColumn<Object, Object> tblclmDepartmentName;
@FXML
private TableColumn<Object, Object> tblclmDepartmentLocation;
@FXML
private TableColumn<Object, Object> tblclmDepartmentType;

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
private void tblDepartmentOnClick(MouseEvent event) {
    int click = event.getClickCount();
    if (click == 2) {
        viewDetails();
    }

}

@FXML
private void tblDepartmentOnKeyPress(KeyEvent event) {
	
	department.departmentList.removeAll();
    department.departmentName = tfSearch.getText().trim();

    tblDepartment.setItems(department.departmentList);
    tblclmDepartmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    tblclmDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    tblclmDepartmentLocation.setCellValueFactory(new PropertyValueFactory<>("departmentLocation"));
    tblclmDepartmentType.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
    
    departmentGetway.searchView(department);

}


@FXML
public void tfSearchOnType(Event event) {
	department.departmentList.removeAll();
    department.departmentName = tfSearch.getText().trim();

    tblDepartment.setItems(department.departmentList);
    tblclmDepartmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    tblclmDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    tblclmDepartmentLocation.setCellValueFactory(new PropertyValueFactory<>("departmentLocation"));
    tblclmDepartmentType.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
    
    departmentGetway.searchView(department);
}


public void showDetails() {
	
    tblDepartment.setItems(department.departmentList);
    tblclmDepartmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    tblclmDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    tblclmDepartmentLocation.setCellValueFactory(new PropertyValueFactory<>("departmentLocation"));
    tblclmDepartmentType.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
    
    departmentGetway.view(department);
    

}



@FXML
private void mbDeleteItem(ActionEvent event) {
    System.out.println("clicked to delete");
    acContent.setOpacity(0.5);
    ListDepartment selectedDepartment = tblDepartment.getSelectionModel().getSelectedItem();
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Login Now");
    alert.setHeaderText("Confirm");
    alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
    alert.initStyle(StageStyle.UNDECORATED);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        department.id = selectedDepartment.getDepartmentId();
        System.out.println(department.id+ "*******");
        departmentBLL.delete(department);
        tblDepartment.getItems().clear();
        showDetails();
    }else{
        
    }
    


}

   

@FXML
private void btnRefreshOnAction(ActionEvent event) {
    department.departmentList.clear();
    showDetails();
}


//searching purpose
@FXML
private void tfSearchOnKeyRelese(KeyEvent event) {
	department.departmentId= tfSearch.getText();
	department.departmentName = tfSearch.getText();
	departmentGetway.searchView(department);

}


@FXML
private void btnAddNewOnAction(ActionEvent event) {
	
	System.out.println("Button addNew Project..");
    AddDepartmentController apc = new AddDepartmentController();
    userNameMedia media = new userNameMedia();
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddDepartment.fxml"));
    try {
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        AddDepartmentController addDepartmentController = fxmlLoader.getController();
        media.setId(usrId);
        addDepartmentController.setNameMedia(media);
        addDepartmentController.lblHeader.setText("ADD DEPARTMENT");
        addDepartmentController.btnUpdate.setVisible(false);
        Stage nStage = new Stage();
//        addProductController.addSupplyerStage(nStage);
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
    if (!tblDepartment.getSelectionModel().isEmpty()) {
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
    alert.setTitle("Department Delete Now");
    alert.setHeaderText("Confirm");
    alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
    alert.initStyle(StageStyle.UNDECORATED);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        String Id = tblDepartment.getSelectionModel().getSelectedItem().getId();
        String ProjectId = tblDepartment.getSelectionModel().getSelectedItem().getDepartmentId();
        System.out.println("id" + Id);
        System.out.println("Project id" +ProjectId);
        department.id = Id;
        departmentBLL.delete(department);
        btnRefreshOnACtion(event);
    }

}


@FXML
private void tblViewProjectOnClick(MouseEvent event
) {
    if (event.getClickCount() == 2) {
        if (!tblDepartment.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    } else {
        tblDepartment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tblDepartment.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    
    
    tblDepartment.setItems(department.departmentList);
    tblclmDepartmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    tblclmDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    tblclmDepartmentLocation.setCellValueFactory(new PropertyValueFactory<>("departmentLocation"));
    tblclmDepartmentType.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
    
    departmentGetway.viewFirstTen(department);
    
     
    
}


private void viewSelected() {
    AddDepartmentController apc = new AddDepartmentController();
    userNameMedia userMedia = new userNameMedia();
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddDepartment.fxml"));
    try {
        fxmlLoader.load();
        Parent parent = fxmlLoader.getRoot();
        Scene scene = new Scene(parent);
        scene.setFill(new Color(0, 0, 0, 0));
        AddDepartmentController addDepartmentController = fxmlLoader.getController();
        userMedia.setId(usrId);
        addDepartmentController.setNameMedia(userMedia);
        addDepartmentController.lblHeader.setText("DEPARTMENT DETAILS");
        addDepartmentController.btnUpdate.setVisible(true);
        addDepartmentController.btnSave.setVisible(false);
        addDepartmentController.id = tblDepartment.getSelectionModel().getSelectedItem().getId();
        addDepartmentController.viewSelected();
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
    department.departmentList.clear();
    tfSearch.clear();
    
    tblDepartment.setItems(department.departmentList);
    tblclmDepartmentId.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    tblclmDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    tblclmDepartmentLocation.setCellValueFactory(new PropertyValueFactory<>("departmentLocation"));
    tblclmDepartmentType.setCellValueFactory(new PropertyValueFactory<>("departmentType"));
    
    departmentGetway.view(department);    
    
    
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
