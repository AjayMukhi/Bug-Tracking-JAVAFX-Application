package Getway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import DAL.Supplyer;
import List.ListProject;
import controller.application.employe.CurrentEmployee;
import controller.application.employe.ListEmployee;
import controller.application.project.Project;
import dataBase.DBConnection;
import dataBase.DBProperties;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ProjectGateway {
	
	DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    //Save Project
    public void save(Project project) {
        con = dbCon.geConnection();
        if (isUniqProjectName(project)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".Project values(?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, project.projectId);
                pst.setString(3, project.projectName);
                pst.setString(4, project.projectDescription);
                pst.setString(5, project.deliveryDate);
                pst.setString(6, project.duration);
                pst.setString(7, project.clientName);
                pst.setString(8, project.pManager);
                
                pst.executeUpdate();
                con.close();
                pst.close();
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Project" + "  '" + project.projectName + "' " + "Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }

    }

    public void view(Project project) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".Project");
            rs = pst.executeQuery();
            while (rs.next()) {
                project.id = rs.getString(1);
                project.projectId = rs.getString(2);
                project.projectName = rs.getString(3);
                project.projectDescription = rs.getString(4);
                project.deliveryDate= rs.getString(5);
                project.duration = rs.getString(6);
                project.clientName = rs.getString(7);
                project.pManager = rs.getString(8);
                
                project.projectDetails.addAll(new ListProject(project.id,project.projectId, project.projectName, 
                		project.projectDescription, project.deliveryDate, project.duration, project.clientName, project.pManager));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Exception");
        }
    }

    public void searchView(Project project) {
        project.projectDetails.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Project where ProjectName like ? or ProjectDescription like ? ORDER BY ProjectName");
            pst.setString(1, "%" + project.projectName + "%");
            pst.setString(2, "%" + project.projectDescription + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	project.id = rs.getString(1);
                project.projectId = rs.getString(2);
                project.projectName = rs.getString(3);
                project.projectDescription = rs.getString(4);
                project.deliveryDate= rs.getString(5);
                project.duration = rs.getString(6);
                project.clientName = rs.getString(7);
                project.pManager = rs.getString(8);
                
                project.projectDetails.addAll(new ListProject(project.id,project.projectId, project.projectName, 
                		project.projectDescription, project.deliveryDate, project.duration, project.clientName, project.pManager));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectedView(Project project) {
        System.out.println("name :" + project.projectName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Project where id=?");
            pst.setString(1, project.id);
            rs = pst.executeQuery();
            while (rs.next()) {
            	project.id = rs.getString(1);
                project.projectId = rs.getString(2);
                project.projectName = rs.getString(3);
                project.projectDescription = rs.getString(4);
                project.deliveryDate= rs.getString(5);
                project.duration = rs.getString(6);
                project.clientName = rs.getString(7);
                project.pManager = rs.getString(8);
                
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    public boolean isUniqProjectName(Project project) {
        con = dbCon.geConnection();
        boolean uniqProject = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select ProjectName from "+db+".Project where ProjectName=?");
            pst.setString(1, project.projectName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Project" + "  '" + project.projectName + "' " + "Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return uniqProject;
            }
            rs.close();
            con.close();
            pst.close();
            uniqProject = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return uniqProject;
    }
    
    
    
// Method to update
    public void update(Project project) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".Project set ProjectId=?, ProjectName=? , ProjectDescription=?,"
            		+ "DeliveryDate=? ,Duration=?, ClientName=?,ProjectManager=?  where Id=?");
            pst.setString(1, project.projectId);
            pst.setString(2, project.projectName);
            pst.setString(3, project.projectDescription);
            pst.setString(4, project.deliveryDate);
            pst.setString(5, project.duration);
            pst.setString(6, project.clientName);
            pst.setString(7, project.pManager);            
            pst.setString(8, project.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Project" + "  '" + project.projectName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUpdate(Project project) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Project where Id=?");
            pst.setString(1, project.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    
    public void delete(Project project) {
        con = dbCon.geConnection();
        try {
            System.out.println("Inside deletePermanently....");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".Project where Id=?");
            pst.setString(1, project.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

	
    
    public boolean isNotUse(Project project) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Project where ProjectId=?");
            pst.setString(1, project.projectId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("Project Name:" + rs.getString(2) +" ");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
                return isNotUse;
            }
            rs.close();
            pst.close();
            con.close();
            isNotUse = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isNotUse;
    }
    
    
    
    
    
    public void viewFirstTen(Project project) {
        con = dbCon.geConnection();

        project.projectDetails.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".Project limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {

            	project.id = rs.getString(1);
                project.projectId = rs.getString(2);
                project.projectName = rs.getString(3);
                project.projectDescription = rs.getString(4);
                project.deliveryDate = rs.getString(5);
                project.duration = rs.getString(6);
                project.clientName = rs.getString(7);
                project.pManager = rs.getString(8);
                
                project.projectDetails.addAll(new ListProject(
                		project.id, project.projectId,project.projectName,project.projectDescription,project.deliveryDate,
                		project.duration,project.clientName,project.pManager ));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
}
