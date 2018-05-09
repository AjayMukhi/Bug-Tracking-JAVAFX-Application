package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Getway.ReportGetway;
import controller.application.report.Report;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ReportBLL {
	
	
    ReportGetway reportGetway = new ReportGetway();
	
	
	DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    

	
	
	public Object delete(Report report){
        
		
		System.out.println("Here Inside ReportBLL file to delete...");
    	//if(isNotNull(currentEmployee)){    	
    	      if(isNotNull(report)) {
    	    	  
    	    	   System.out.println("Here****");
                    reportGetway.delete(report);
              }else{
                   System.out.println("Nothing...");
              }
    	//}
        return report;
    }
    
	
  


	 public void save(Report report) {
	    	
	    	System.out.println("Inside ReportBLL save()...");
	        if (isUniqName(report)) {
	            reportGetway.save(report);
	        }
	        
	    }

	 public void update(Report report) {
	    	
	    	System.out.println("Inside ReportBLL update()...");
	        if(isNotNull(report)){
	        	
	            if (isUpdate(report)) {
	                    if (checkUpdateCondition(report)) {
	                             reportGetway.update(report);
	                    } else if (isUniqName(report)) {
	                           reportGetway.update(report);
	            }
	        }
	        }
	    }


	private boolean isUniqName(Report report) {
		System.out.println("WE ARE IN ReportBLL FILE");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Report where ReportId=?");
            pst.setString(1, report.reportId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Report id" + "  '" + report.reportId + "' " + "id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
   
	}
	
	
	
	public boolean isUpdate(Report report) {
        System.out.println("WE ARE IN UPDATE");
        boolean isUpdate = false;
        try {
        	
        	
        	pst = con.prepareStatement("select * from  "+db+".Report where Id=? and ReportId=? and ReportName=? and BugName=?,"
            		+ "and Severity=? and ProjectName=? and RaisedDate=? and Status=? and ResolvedDate=? ");
        	pst.setString(1, report.id);
        	pst.setString(2, report.reportId);
            pst.setString(3, report.reportName);
            pst.setString(4, report.bugName);
            pst.setString(5, report.severity);
            pst.setString(6, report.projectName);
            pst.setString(7, report.raisedDate);
            pst.setString(8, report.status);   
            pst.setString(9, report.resolvedDate);
           
                       
            
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

    public boolean checkUpdateCondition(Report report) {
        boolean isTrueUpdate = false;
        if (isUpdate(report)) {
            try {
                pst = con.prepareStatement("select * from "+db+".Report where id=? and ReportId=?");
                pst.setString(1, report.id);
                pst.setString(2, report.reportId);
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

    public boolean isNotNull(Report report) {
        
        boolean isNotNull = false;
        if (report.reportId.isEmpty() || report.reportName.isEmpty() ) {
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

}
