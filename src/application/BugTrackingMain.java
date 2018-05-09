package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataBase.DBModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BugTrackingMain extends Application {
    
    public BugTrackingMain() {
        
        DBModel model = new DBModel();
        model.createDataBase();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome to BugTracking System -Login");
            primaryStage.getIcons().add(new Image("/image/icon.png"));
            primaryStage.setMaximized(false);
            primaryStage.setMinHeight(500.0);
            primaryStage.setMinWidth(850.0);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
