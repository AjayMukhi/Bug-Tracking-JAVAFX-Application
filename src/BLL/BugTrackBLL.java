package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Getway.BugTrackGetway;
import controller.application.bug.BugTrack;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class BugTrackBLL {
	
	
	DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    BugTrackGetway bugTrackGetway = new BugTrackGetway();

    public void save(BugTrack bug) {
    	
    	System.out.println("Inside DepartmentBLL save()...");
        if (isUniqName(bug)) {
        	bugTrackGetway.save(bug);
        }
        
    }

    public void update(BugTrack bug) {
    	
    	System.out.println("Inside BugTrackBLL update()...");
        if(isNotNull(bug)){
        	
            if (isUpdate(bug)) {
                    if (checkUpdateCondition(bug)) {
                    	
                             bugTrackGetway.update(bug);
                             
                    } else if (isUniqName(bug)) {
                    	bugTrackGetway.update(bug);
            }
        }
        }
    }

    public boolean isUniqName(BugTrack bug) {
        System.out.println("WE ARE IN BugTrackBLL FILE");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".BugTrack where BugId=?");
            pst.setString(1, bug.bugId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Bug id" + "  '" + bug.bugId + "' " + "id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }

    public boolean isUpdate(BugTrack bug) {
        System.out.println("WE ARE IN UPDATE");
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".BugTrack where Id=? and BugId=? "
            		+ "and BugName=? and BugType=? and BugLevel=? and Severity=? and ProjectName=? and TesterCode=? "
            		+ "and RaisedOn=? and DeveloperCode=? and Status=? and Solution=?");
            pst.setString(1, bug.id);
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
            
              
            rs = pst.executeQuery();
            while (rs.next()) {
                return isUpdate;
            }
            isUpdate = true;
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return isUpdate;
    }

    public boolean checkUpdateCondition(BugTrack bug) {
        boolean isTrueUpdate = false;
        if (isUpdate(bug)) {
            try {
                pst = con.prepareStatement("select * from "+db+".BugTrack where id=? and BugId=?");
                pst.setString(1, bug.id);
                pst.setString(2, bug.bugId);
                rs = pst.executeQuery();
                while (rs.next()) {

                    return isTrueUpdate = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return isTrueUpdate;
    }

    public boolean isNotNull(BugTrack bug) {
        
        boolean isNotNull = false;
        if (bug.bugId.isEmpty() || bug.bugName.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Null Found");
                alert.setContentText("Please fill requrer field");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
            
            return isNotNull;
        }
        else
        	isNotNull =true;
        
        return isNotNull;
    }

    public Object delete(BugTrack bug){
    	
    	System.out.println("Here Inside BugTrackBLL file to delete...");
    	//if(isNotNull(currentEmployee)){    	
    	      if(isNotNull(bug)) {
    	    	  
    	    	   System.out.println("Here****");
    	    	   bugTrackGetway.delete(bug);
              }else{
                   System.out.println("Nothing...");
              }
    	//}
        return bug;
    }
    



}
