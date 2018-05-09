
package controller;

import DAL.Users;
import Getway.UsersGetway;
import controller.application.EmployeController;
import controller.application.ReportController;
import controller.application.SellController;
import controller.application.SettingsController;
import controller.application.StockController;
import controller.application.UserControlController;
import controller.application.home.HomeController;
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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import media.userNameMedia;


public class ApplicationController implements Initializable {

    @FXML
    private StackPane acContent;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private ImageView imgMenuBtn;
    @FXML
    private BorderPane appContent;
    @FXML
    private Button btnLogOut;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private AnchorPane acDashBoard;
    @FXML
    private AnchorPane acHead;
    @FXML
    private AnchorPane acMain;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView imgHomeBtn;
    @FXML
    private Button btnUserControl;
    @FXML
    private ImageView imgUserControlBtn;
    
    
    @FXML
    private Button btnEmployee;
    @FXML
    private ImageView imgEmployeeBtn;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnReport;
    
    @FXML
    private ImageView imgSellBtn;
    
    @FXML
    private ImageView imgReportBtn;
    
    @FXML
    private Button btnSettings;
    @FXML
    private ImageView imgSettingsBtn;
    @FXML
    private Button btnAbout;
    @FXML
    private ImageView imgAboutBtn;
    @FXML
    private Label lblUsrName;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUserId;

    String usrName;
    String id;

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
     DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    Users users = new Users();
    UsersGetway usersGetway = new UsersGetway();

    private userNameMedia usrNameMedia;

    public userNameMedia getUsrNameMedia() {
        return usrNameMedia;
    }

    public void setUsrNameMedia(userNameMedia usrNameMedia) {
        lblUserId.setText(usrNameMedia.getId());
        lblUsrName.setText(usrNameMedia.getUsrName());
        id = usrNameMedia.getId();
        usrName = usrNameMedia.getUsrName();

        this.usrNameMedia = usrNameMedia;
    }

    Image menuImage = new Image("/icon/menu.png");
    Image menuImageRed = new Image("/icon/menuRed.png");
    Image image;

    String defaultStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:none";

    String activeStyle = "-fx-border-width: 0px 0px 0px 5px;"
            + "-fx-border-color:#FF4E3C";

    Image home = new Image("/icon/home.png");
    Image homeRed = new Image("/icon/homeRed.png");
   
    Image userControl = new Image("/icon/user-control.png");
    Image userControlRed = new Image("/icon/user-control-red.png");
    
    Image report = new Image("/icon/sell2.png");
    Image reportRed = new Image("/icon/sell2Red.png");

    Image employee = new Image("/icon/employe.png");
    Image employeeRed = new Image("/icon/employeRed.png");
    
    Image setting = new Image("/icon/settings.png");
    Image settingRed = new Image("/icon/settingsRed.png");
    
    Image about = new Image("/icon/about.png");
    Image aboutRed = new Image("/icon/aboutRed.png");
    
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgMenuBtn.setImage(menuImage);
        Image usrImg = new Image("/image/image-1.jpg");

        imgUsrTop.setFill(new ImagePattern(usrImg));
        circleImgUsr.setFill(new ImagePattern(usrImg));

    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) throws IOException {
        if (sideMenuToogleBtn.isSelected()) {
            imgMenuBtn.setImage(menuImageRed);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBoard);
            sideMenu.setByX(-130);
            sideMenu.play();
            acDashBoard.getChildren().clear();
        } else {
            imgMenuBtn.setImage(menuImage);
            TranslateTransition sideMenu = new TranslateTransition(Duration.millis(200.0), acDashBoard);
            sideMenu.setByX(130);
            sideMenu.play();
            acDashBoard.getChildren().add(leftSideBarScroolPan);
        }
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        acContent.getChildren().clear();
        acContent.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
        acDashBoard.getChildren().clear();
        acHead.getChildren().clear();
        acHead.setMaxHeight(0.0);
    }

    @FXML
    private void acMain(KeyEvent event) {
        if (event.getCode() == KeyCode.F11) {
            Stage stage = (Stage) acMain.getScene().getWindow();
            stage.setFullScreen(false);
            stage.setMaximized(false);
            stage.setMaxHeight(500.0);
            stage.setMaxWidth(850.0);
            stage.show();
        }
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event){
        homeActive();
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/application/home/Home.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        acContent.getChildren().clear();
        acContent.getChildren().add(root);

        System.out.println(lblUsrName.getText());
        System.out.println(lblUserId.getText());

    }

    @FXML
    private void btnUserControlOnClick(ActionEvent event) throws IOException {
        sotreActive();
        UserControlController sc = new UserControlController();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/UserControl.fxml").openStream());
        nm.setId(id);
        UserControlController usrControlController = fXMLLoader.getController();
        usrControlController.bpStore.getStylesheets().add("/style/MainStyle.css");
        usrControlController.setUserId(usrNameMedia);
        usrControlController.btnEmployeeOnAction(event);
        usrControlController.settingPermission();
        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);
    }
    
    @FXML
    private void btnReportOnClick(ActionEvent event) {
        sellActive();
        ReportController controller = new ReportController();
        userNameMedia nm = new userNameMedia();
        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/application/Report.fxml").openStream());
            nm.setId(id);
            ReportController reportController = fXMLLoader.getController();
            reportController.setNameMedia(usrNameMedia);
            reportController.acMainReports.getStylesheets().add("/style/MainStyle.css");
            reportController.tbtnReportsOnAction(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    @FXML
    private void btnEmployeeOnClick(ActionEvent event) throws IOException {
        employeeActive();
        EmployeController ec = new EmployeController();
        userNameMedia nm = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/Employe.fxml").openStream());
        nm.setId(id);
        EmployeController employeController = fXMLLoader.getController();
        employeController.bpContent.getStylesheets().add("/style/MainStyle.css");
        employeController.setNameMedia(usrNameMedia);
        employeController.btnViewEmployeeOnAction(event);

        AnchorPane acPane = fXMLLoader.getRoot();

        acContent.getChildren().clear();

        acContent.getChildren().add(acPane);

    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) throws IOException {
        settingsActive();
        //inithilize Setting Controller
        SettingsController settingController = new SettingsController();
        //inithilize UserNameMedia
        userNameMedia usrMedia = new userNameMedia();
        // Define a loader to inithilize Settings.fxml controller
        FXMLLoader settingLoader = new FXMLLoader();
        //set the location of Settings.fxml by fxmlloader
        settingLoader.load(getClass().getResource("/view/application/Settings.fxml").openStream());

        //Send id to userMedia
        usrMedia.setId(id);
        //take control of settingController elements or node
        SettingsController settingControl = settingLoader.getController();
        settingControl.bpSettings.getStylesheets().add("/style/MainStyle.css");

        settingControl.setUsrMedia(usrMedia);
        settingControl.miMyASccountOnClick(event);
        settingControl.settingPermission();

        AnchorPane acPane = settingLoader.getRoot();
        //acContent clear and make useul for add next node
        acContent.getChildren().clear();
        //add a node call "acPane" to acContent
        acContent.getChildren().add(acPane);

    }

    @FXML
    private void btnAboutOnClick(ActionEvent event) {

        try {
            aboutActive();
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/application/about/AboutMe.fxml").openStream());

//            SellController sellController = fXMLLoader.getController();
//            sellController.acMainSells.getStylesheets().add("/style/MainStyle.css");
//            sellController.tbtnSellOnAction(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSellOnClick(ActionEvent event) {
        sellActive();
        SellController controller = new SellController();
        userNameMedia nm = new userNameMedia();
        try {

            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.load(getClass().getResource("/view/application/Sell.fxml").openStream());
            nm.setId(id);
            SellController sellController = fXMLLoader.getController();
            sellController.setNameMedia(usrNameMedia);
            sellController.acMainSells.getStylesheets().add("/style/MainStyle.css");
            sellController.tbtnSellOnAction(event);
            AnchorPane anchorPane = fXMLLoader.getRoot();
            acContent.getChildren().clear();
            acContent.getChildren().add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void hlUpdateAccount(ActionEvent event) {

    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {

    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {

    }

    public void permission() {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from "+db+".UserPermission where UserId=?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getInt(17) == 0) {
                    btnEmployee.setDisable(true);
                }
                if (rs.getInt(15) == 0) {
                    btnSell.setDisable(true);
                } else {

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void homeActive() {
        imgHomeBtn.setImage(homeRed);
        imgUserControlBtn.setImage(userControl);
        imgReportBtn.setImage(report);
        imgEmployeeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        
        btnHome.setStyle(activeStyle);
        btnUserControl.setStyle(defaultStyle);
        btnReport.setStyle(defaultStyle);        
        btnEmployee.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void sotreActive() {
        imgHomeBtn.setImage(home);
        imgUserControlBtn.setImage(userControlRed);
        imgReportBtn.setImage(report);
        imgEmployeeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        
        btnHome.setStyle(defaultStyle);
        btnUserControl.setStyle(activeStyle);
        btnEmployee.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void sellActive() {
        imgHomeBtn.setImage(home);
        imgUserControlBtn.setImage(userControl);
        imgReportBtn.setImage(reportRed);
        imgEmployeeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        
        btnHome.setStyle(defaultStyle);
        btnUserControl.setStyle(defaultStyle);
        btnReport.setStyle(activeStyle);
        btnEmployee.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void employeeActive() {
        imgHomeBtn.setImage(home);
        imgUserControlBtn.setImage(userControl);
        imgReportBtn.setImage(report);
        imgEmployeeBtn.setImage(employeeRed);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(about);
        
        btnHome.setStyle(defaultStyle);
        btnUserControl.setStyle(defaultStyle);
        btnReport.setStyle(defaultStyle);
        btnEmployee.setStyle(activeStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void settingsActive() {
        imgHomeBtn.setImage(home);
        imgUserControlBtn.setImage(userControl);        
        imgReportBtn.setImage(report);
        imgEmployeeBtn.setImage(employee);
        imgSettingsBtn.setImage(settingRed);
        imgAboutBtn.setImage(about);
        
        btnHome.setStyle(defaultStyle);
        btnUserControl.setStyle(defaultStyle);
        btnReport.setStyle(defaultStyle);
        btnEmployee.setStyle(defaultStyle);
        btnSettings.setStyle(activeStyle);
        btnAbout.setStyle(defaultStyle);
    }

    private void aboutActive() {
        imgHomeBtn.setImage(home);
        imgUserControlBtn.setImage(userControl);
        imgReportBtn.setImage(report);
        imgEmployeeBtn.setImage(employee);
        imgSettingsBtn.setImage(setting);
        imgAboutBtn.setImage(aboutRed);
        
        btnHome.setStyle(defaultStyle);
        btnUserControl.setStyle(defaultStyle);
        btnReport.setStyle(defaultStyle);
        btnEmployee.setStyle(defaultStyle);
        btnSettings.setStyle(defaultStyle);
        btnAbout.setStyle(activeStyle);
    }

    public void viewDetails() {
        users.id = id;
        usersGetway.selectedView(users);
        image = users.image;
        circleImgUsr.setFill(new ImagePattern(image));
        imgUsrTop.setFill(new ImagePattern(image));
        lblFullName.setText(users.fullName);
        lblUsrNamePopOver.setText(users.userName);
    }
}
