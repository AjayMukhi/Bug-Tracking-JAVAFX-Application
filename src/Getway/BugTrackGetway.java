package Getway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import List.ListBugTrack;
import controller.application.bug.BugTrack;
import dataBase.DBConnection;
import dataBase.DBProperties;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class BugTrackGetway {
	
	
	DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    //Save Project
    public void save(BugTrack bug) {
        con = dbCon.geConnection();
        if (isUniqBugName(bug)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".BugTrack values(?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, bug.bugId);
                pst.setString(3, bug.bugName);
                pst.setString(4, bug.bugType);
                pst.setString(5, bug.bugLevel);
                pst.setString(6, bug.severity);
                pst.setString(7, bug.projectName);
                pst.setString(8, bug.testerCode);
                pst.setString(9, bug.raisedOn);
                pst.setString(10, bug.developerCode);
                pst.setString(11, bug.status);
                pst.setString(12, bug.postSolution);
                
                pst.executeUpdate();
                con.close();
                pst.close();
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Bug" + "  '" + bug.bugName + "' " + "Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }

    }

    public void view(BugTrack bug) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".BugTrack");
            rs = pst.executeQuery();
            while (rs.next()) {
                bug.id = rs.getString(1);
                bug.bugId = rs.getString(2);
                bug.bugName = rs.getString(3);
                bug.bugType = rs.getString(4);
                bug.bugLevel= rs.getString(5);
                bug.severity= rs.getString(6);
                bug.projectName= rs.getString(7);
                bug.testerCode= rs.getString(8);
                bug.raisedOn= rs.getString(9);
                bug.developerCode= rs.getString(10);
                bug.status= rs.getString(11);
                bug.postSolution= rs.getString(12);
                
                bug.bugList.addAll(new ListBugTrack(bug.id,bug.bugId,bug.bugName,bug.bugType,bug.bugLevel,bug.severity,
                		bug.projectName,bug.testerCode,bug.raisedOn,bug.developerCode,bug.status,bug.postSolution));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Exception");
        }
    }

    public void searchView(BugTrack bug) {
        
    	bug.bugList.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".BugTrack where BugName like ? or BugType like ? ORDER BY BugName");
            pst.setString(1, "%" + bug.bugName + "%");
            pst.setString(2, "%" + bug.bugType + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	bug.id = rs.getString(1);
                bug.bugId = rs.getString(2);
                bug.bugName = rs.getString(3);
                bug.bugType = rs.getString(4);
                bug.bugLevel= rs.getString(5);
                bug.severity= rs.getString(6);
                bug.projectName= rs.getString(7);
                bug.testerCode= rs.getString(8);
                bug.raisedOn= rs.getString(9);
                bug.developerCode= rs.getString(10);
                bug.status= rs.getString(11);
                bug.postSolution= rs.getString(12);
                
                bug.bugList.addAll(new ListBugTrack(bug.id,bug.bugId,bug.bugName,bug.bugType,bug.bugLevel,bug.severity,
                		bug.projectName,bug.testerCode,bug.raisedOn,bug.developerCode,bug.status,bug.postSolution));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectedView(BugTrack bug) {
        System.out.println("name :" + bug.bugName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".BugTrack where id=?");
            pst.setString(1, bug.id);
            rs = pst.executeQuery();
            while (rs.next()) {
            	bug.id = rs.getString(1);
                bug.bugId = rs.getString(2);
                bug.bugName = rs.getString(3);
                bug.bugType = rs.getString(4);
                bug.bugLevel= rs.getString(5);
                bug.severity= rs.getString(6);
                bug.projectName= rs.getString(7);
                bug.testerCode= rs.getString(8);
                bug.raisedOn= rs.getString(9);
                bug.developerCode= rs.getString(10);
                bug.status= rs.getString(11);
                bug.postSolution= rs.getString(12);
                
                
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    public boolean isUniqBugName(BugTrack bug) {
        con = dbCon.geConnection();
        boolean uniqDep = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select BugName from "+db+".BugTrack where BugId=?");
            pst.setString(1, bug.bugId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Bug" + "  '" + bug.bugName + "' " + "Already exist");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

                return uniqDep;
            }
            rs.close();
            con.close();
            pst.close();
            uniqDep = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return uniqDep;
    }
    
    
    
// Method to update
    public void update(BugTrack bug) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".BugTrack set BugId=?, BugName=? , BugType=?,"
            		+ "BugLevel=? where Id=?");
            pst.setString(1, bug.bugId);
            pst.setString(2, bug.bugName);
            pst.setString(3, bug.bugType);
            pst.setString(4, bug.bugLevel);
            pst.setString(5, bug.id);
           
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Bug" + "  '" + bug.bugName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUpdate(BugTrack bug) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".BugTrack where Id=?");
            pst.setString(1, bug.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    
    public void delete(BugTrack bug) {
        con = dbCon.geConnection();
        try {
            System.out.println("Inside deletePermanently....");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".BugTrack where Id=?");
            pst.setString(1, bug.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

	
    
    public boolean isNotUse(BugTrack bug) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from "+db+".BugTrack where BugId=?");
            pst.setString(1, bug.bugId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("Bug Name:" + rs.getString(2) +" ");
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
    
    
    
    
    
    public void viewFirstTen(BugTrack bug) {
        con = dbCon.geConnection();

        bug.bugList.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".BugTrack limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {

            	bug.id = rs.getString(1);
                bug.bugId = rs.getString(2);
                bug.bugName = rs.getString(3);
                bug.bugType = rs.getString(4);
                bug.bugLevel= rs.getString(5);
                bug.severity= rs.getString(6);
                bug.projectName= rs.getString(7);
                bug.testerCode= rs.getString(8);
                bug.raisedOn= rs.getString(9);
                bug.developerCode= rs.getString(10);
                bug.status= rs.getString(11);
                bug.postSolution= rs.getString(12);
                
                bug.bugList.addAll(new ListBugTrack(bug.id, bug.bugId, bug.bugName, bug.bugType, bug.bugLevel, bug.severity,
                		bug.projectName, bug.testerCode, bug.raisedOn, bug.developerCode, bug.status, bug.postSolution));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
