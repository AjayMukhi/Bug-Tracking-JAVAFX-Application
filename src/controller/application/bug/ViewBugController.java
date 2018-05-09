package controller.application.bug;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import BLL.BugTrackBLL;
import Getway.BugTrackGetway;
import List.ListBugTrack;
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
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

public class ViewBugController implements Initializable {

	BugTrack bug = new BugTrack();
    BugTrackGetway bugTrackGetway = new BugTrackGetway();
    BugTrackBLL bugTrackBLL = new BugTrackBLL();
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    private String usrId;

    private userNameMedia media;
    @FXML
    public StackPane spBugContent;
    @FXML
    private TextField tfSearch;
    @FXML
    private ComboBox<String> cbSoteViewSupplyer;
    @FXML
    private ComboBox<String> cbSoteViewBrands;
    @FXML
    private ComboBox<String> cbSoteViewCatagory;
    @FXML
    private ComboBox<String> cbSoteViewRMA;
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<ListBugTrack> tblViewCurrentBug;
    @FXML
    private TableColumn<Object, Object> tblClmBugId;
    @FXML
    private TableColumn<Object, Object> tblClmBugName;
    @FXML
    private TableColumn<Object, Object> tblClmBugType;
    @FXML
    private TableColumn<Object, Object> tblClmBugLevel;
    @FXML
    private TableColumn<Object, Object> tblClmSeverity;
    @FXML
    private TableColumn<Object, Object> tblClmProjectName;
    @FXML
    private TableColumn<Object, Object> tblClmTesterCode;
    @FXML
    private TableColumn<Object, Object> tblClmRaisedOn;
    @FXML
    private TableColumn<Object, Object> tblClmDeveloperCode;
    @FXML
    private TableColumn<Object, Object> tblClmStatus;
    @FXML
    private TableColumn<Object, Object> tblClmSolution;
    
    
    String suplyerId;
    String suplyerName;
    String brandId;
    String brandName;
    String catagoryId;
    String catagoryName;
    String rmaID;
    String rmaName;

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

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {
        bug.bugId = tfSearch.getText();
        bug.bugName = tfSearch.getText();
        bugTrackGetway.searchView(bug);

    }

    
    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        AddBugController apc = new AddBugController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBug.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddBugController addBugController = fxmlLoader.getController();
            media.setId(usrId);
            addBugController.setNameMedia(media);
            addBugController.lblHeader.setText("ADD BUG DETAILS");
            addBugController.btnUpdate.setVisible(false);
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
        if (!tblViewCurrentBug.getSelectionModel().isEmpty()) {
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
            String item = tblViewCurrentBug.getSelectionModel().getSelectedItem().getId();
            System.out.println("Bug id" + item);
            bug.id = item;
            bugTrackBLL.delete(bug);
            btnRefreshOnACtion(event);
        }

    }

    @FXML
    private void tblViewCurrentBugOnClick(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            if (!tblViewCurrentBug.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
        	tblViewCurrentBug.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                	tblViewCurrentBug.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        tblViewCurrentBug.setItems(bug.bugList);
        tblClmBugId.setCellValueFactory(new PropertyValueFactory<>("bugId"));
        tblClmBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblClmBugType.setCellValueFactory(new PropertyValueFactory<>("bugType"));
        tblClmBugLevel.setCellValueFactory(new PropertyValueFactory<>("bugLevel"));
        tblClmSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblClmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblClmTesterCode.setCellValueFactory(new PropertyValueFactory<>("testerCode"));
        tblClmRaisedOn.setCellValueFactory(new PropertyValueFactory<>("raisedOn"));
        tblClmDeveloperCode.setCellValueFactory(new PropertyValueFactory<>("developerCode"));
        tblClmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblClmSolution.setCellValueFactory(new PropertyValueFactory<>("postSolution"));        
        bugTrackGetway.viewFirstTen(bug);
    }

    private void viewSelected() {
        AddBugController apc = new AddBugController();
        userNameMedia userMedia = new userNameMedia();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/application/stock/AddBug.fxml"));
        try {
            fxmlLoader.load();
            Parent parent = fxmlLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddBugController addBugController = fxmlLoader.getController();
            userMedia.setId(usrId);
            addBugController.setNameMedia(userMedia);
            addBugController.lblHeader.setText("PRODUCT DETAILS");
            addBugController.btnUpdate.setVisible(true);
            addBugController.btnSave.setVisible(false);
            addBugController.id = tblViewCurrentBug.getSelectionModel().getSelectedItem().getId();
            addBugController.viewSelected();
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
    private void btnRefreshOnACtion(ActionEvent event) {
        bug.bugList.clear();
        tfSearch.clear();
        
        tblViewCurrentBug.setItems(bug.bugList);
        tblClmBugId.setCellValueFactory(new PropertyValueFactory<>("bugId"));
        tblClmBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblClmBugType.setCellValueFactory(new PropertyValueFactory<>("bugType"));
        tblClmBugLevel.setCellValueFactory(new PropertyValueFactory<>("bugLevel"));
        tblClmSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblClmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblClmTesterCode.setCellValueFactory(new PropertyValueFactory<>("testerCode"));
        tblClmRaisedOn.setCellValueFactory(new PropertyValueFactory<>("raisedOn"));
        tblClmDeveloperCode.setCellValueFactory(new PropertyValueFactory<>("developerCode"));
        tblClmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblClmSolution.setCellValueFactory(new PropertyValueFactory<>("postSolution"));
       
        bugTrackGetway.view(bug);

    }

    
    public void showDetails() {
    	tblViewCurrentBug.setItems(bug.bugList);
        tblClmBugId.setCellValueFactory(new PropertyValueFactory<>("bugId"));
        tblClmBugName.setCellValueFactory(new PropertyValueFactory<>("bugName"));
        tblClmBugType.setCellValueFactory(new PropertyValueFactory<>("bugType"));
        tblClmBugLevel.setCellValueFactory(new PropertyValueFactory<>("bugLevel"));
        tblClmSeverity.setCellValueFactory(new PropertyValueFactory<>("severity"));
        tblClmProjectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        tblClmTesterCode.setCellValueFactory(new PropertyValueFactory<>("testerCode"));
        tblClmRaisedOn.setCellValueFactory(new PropertyValueFactory<>("raisedOn"));
        tblClmDeveloperCode.setCellValueFactory(new PropertyValueFactory<>("developerCode"));
        tblClmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tblClmSolution.setCellValueFactory(new PropertyValueFactory<>("postSolution"));
       
        bugTrackGetway.view(bug);

    }


    
    
    
    
}
