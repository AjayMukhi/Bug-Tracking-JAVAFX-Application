package controller.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.application.report.ViewReportController;
import controller.application.sell.ViewCustomerController;
import controller.application.sell.ViewSellController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;

public class ReportController implements Initializable{
	
	@FXML
    private ToggleButton tbtnReports;
    @FXML
    private ToggleButton tbtnCharts;
 
    @FXML
    private Label lblPathInfo;
    @FXML
    private StackPane spMainContent;

    userNameMedia nameMedia;

    String userId;
    @FXML
    public AnchorPane acMainReports;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup group = new ToggleGroup();
        tbtnReports.setSelected(true);
        tbtnCharts.setToggleGroup(group);
        tbtnReports.setToggleGroup(group);
    }

    @FXML
    public void tbtnReportsOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("Report");
        ViewReportController viewreportController = new ViewReportController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/report/ViewReport.fxml").openStream());
        media.setId(userId);
        ViewReportController controller = fXMLLoader.getController();
        controller.setMedia(nameMedia);
        controller.showDetails();
//        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    @FXML
    private void tbtnChartsOnAction(ActionEvent event) throws IOException {
        lblPathInfo.setText("charts");
        ViewCustomerController customerController = new ViewCustomerController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.load(getClass().getResource("/view/application/sell/ViewCustomer.fxml").openStream());
        media.setId(userId);
        ViewCustomerController controller = fXMLLoader.getController();
        controller.setNameMedia(nameMedia);
        controller.viewDetails();
        spMainContent.getChildren().clear();
        spMainContent.getChildren().add(fXMLLoader.getRoot());
    }

    

}
