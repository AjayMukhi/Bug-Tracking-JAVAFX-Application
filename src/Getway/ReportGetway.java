package Getway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import List.ListProject;
import List.ListReport;
import controller.application.report.Report;
import dataBase.DBConnection;
import dataBase.DBProperties;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ReportGetway {
	
	
	DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    //Save Report
    public void save(Report report) {
        con = dbCon.geConnection();
        if (isUniqReportName(report)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".Report values(?,?,?,?,?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, report.reportId);
                pst.setString(3, report.reportName);
                pst.setString(4, report.bugName);
                pst.setString(5, report.severity);
                pst.setString(6, report.projectName);
                pst.setString(7, report.raisedDate);
                pst.setString(8, report.status);
                pst.setString(9, report.resolvedDate);
                
                pst.executeUpdate();
                con.close();
                pst.close();
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Report" + "  '" + report.reportName + "' " + "Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }

    }

    public void view(Report report) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".Report");
            rs = pst.executeQuery();
            while (rs.next()) {
                report.id = rs.getString(1);
                report.reportId = rs.getString(2);
                report.reportName = rs.getString(3);
                report.bugName = rs.getString(4);
                report.severity= rs.getString(5);
                report.projectName = rs.getString(6);
                report.raisedDate = rs.getString(7);
                report.status = rs.getString(8);
                report.resolvedDate = rs.getString(9);
                
                report.reportList.addAll(new ListReport(report.id,report.reportId,report.reportName,report.bugName,report.severity,
                		report.projectName,report.raisedDate,report.status,report.resolvedDate));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Exception");
        }
    }

    public void searchView(Report report) {
        report.reportList.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Report where ReportName like ? or BugName like ? ORDER BY ReportName");
            pst.setString(1, "%" + report.reportName + "%");
            pst.setString(2, "%" + report.bugName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	report.id = rs.getString(1);
                report.reportId = rs.getString(2);
                report.reportName = rs.getString(3);
                report.bugName = rs.getString(4);
                report.severity= rs.getString(5);
                report.projectName = rs.getString(6);
                report.raisedDate = rs.getString(7);
                report.status = rs.getString(8);
                report.resolvedDate = rs.getString(9);
                
                report.reportList.addAll(new ListReport(report.id,report.reportId,report.reportName,report.bugName,report.severity,
                		report.projectName,report.raisedDate,report.status,report.resolvedDate));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectedView(Report report) {
        System.out.println("name :" + report.reportName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Report where id=?");
            pst.setString(1, report.id);
            rs = pst.executeQuery();
            while (rs.next()) {
            	report.id = rs.getString(1);
                report.reportId = rs.getString(2);
                report.reportName = rs.getString(3);
                report.bugName = rs.getString(4);
                report.severity= rs.getString(5);
                report.projectName = rs.getString(6);
                report.raisedDate = rs.getString(7);
                report.status = rs.getString(8);
                report.resolvedDate = rs.getString(9);
                
                
                
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    public boolean isUniqReportName(Report report) {
        con = dbCon.geConnection();
        boolean uniqProject = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select ReportName from "+db+".Report where ReportName=?");
            pst.setString(1, report.reportName);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Report" + "  '" + report.reportName + "' " + "Already exist");
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
    public void update(Report report) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".Report set ReportId=?, ReportName=? , BugName=?,"
            		+ ",Severity=?, ProjectName=?,RaisedDate=? ,Status=?,ResolvedDate=?   where Id=?");
            pst.setString(1, report.reportId);
            pst.setString(2, report.reportName);
            pst.setString(3, report.bugName);
            pst.setString(4, report.severity);
            pst.setString(5, report.projectName);
            pst.setString(6, report.raisedDate);
            pst.setString(7, report.status);   
            pst.setString(8, report.resolvedDate);
            pst.setString(9, report.id);
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Report" + "  '" + report.reportName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUpdate(Report report) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Report where Id=?");
            pst.setString(1, report.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    
    public void delete(Report report) {
        con = dbCon.geConnection();
        try {
            System.out.println("Inside deletePermanently....");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".Report where Id=?");
            pst.setString(1, report.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

	
    
    public boolean isNotUse(Report report) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Report where ReportId=?");
            pst.setString(1, report.reportId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("Report Name:" + rs.getString(2) +" ");
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
    
    
    
    
    
    public void viewFirstTen(Report report) {
        con = dbCon.geConnection();

        report.reportList.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".Report limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {

            	report.id = rs.getString(1);
                report.reportId = rs.getString(2);
                report.reportName = rs.getString(3);
                report.bugName = rs.getString(4);
                report.severity= rs.getString(5);
                report.projectName = rs.getString(6);
                report.raisedDate = rs.getString(7);
                report.status = rs.getString(8);
                report.resolvedDate = rs.getString(9);
                
                report.reportList.addAll(new ListReport(report.id,report.reportId,report.reportName,report.bugName,report.severity,
                		report.projectName,report.raisedDate,report.status,report.resolvedDate));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
	
	

}
