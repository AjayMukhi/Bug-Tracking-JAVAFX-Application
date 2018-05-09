package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Getway.CurrentEmployeeGetway;
import controller.application.employe.CurrentEmployee;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CurrentEmployeeBLL {
	
	
	
	DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    CurrentEmployeeGetway currentEmployeeGetway = new CurrentEmployeeGetway();

    public void save(CurrentEmployee currentEmployee) {
    	
    	System.out.println("Inside CurrentEmployeeBLL save()...");
        if (isUniqName(currentEmployee)) {
            currentEmployeeGetway.save(currentEmployee);
        }
        
    }

    public void update(CurrentEmployee currentEmployee) {
    	
    	System.out.println("Inside CurrentEmployeeBLL update()...");
        if(isNotNull(currentEmployee)){
        	
            if (isUpdate(currentEmployee)) {
                    if (checkUpdateCondition(currentEmployee)) {
                             currentEmployeeGetway.update(currentEmployee);
                    } else if (isUniqName(currentEmployee)) {
                           currentEmployeeGetway.update(currentEmployee);
            }
        }
        }
    }

    public boolean isUniqName(CurrentEmployee currentEmployee) {
        System.out.println("WE ARE IN CURRENTEMPLOYEEBLL FILE");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails where EmployeeId=?");
            pst.setString(1, currentEmployee.employeeId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Employee id" + "  '" + currentEmployee.employeeId + "' " + "id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }

    public boolean isUpdate(CurrentEmployee currentEmployee) {
        System.out.println("WE ARE IN UPDATE");
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails where Id=? and EmployeeId=? "
            		+ "and EmployeeName=? and Gender=? and DOB=? and Qualification=? and Address=? "
                    + "and ContactNo=? and EmailAddress=? and DOJ=? and Role=? ");
            pst.setString(1, currentEmployee.id);
            pst.setString(2, currentEmployee.employeeId);
            pst.setString(3, currentEmployee.employeeName);
            pst.setString(4, currentEmployee.gender);
            pst.setString(5, currentEmployee.dob);
            pst.setString(6, currentEmployee.qualification);
            pst.setString(7, currentEmployee.address);
            pst.setString(8, currentEmployee.contactNo);
            pst.setString(9, currentEmployee.emailAddress);
            pst.setString(10, currentEmployee.dateofJoining);
            pst.setString(11, currentEmployee.role);
            
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

    public boolean checkUpdateCondition(CurrentEmployee currentEmployee) {
        boolean isTrueUpdate = false;
        if (isUpdate(currentEmployee)) {
            try {
                pst = con.prepareStatement("select * from "+db+".EmployeeDetails where id=? and EmployeeId=?");
                pst.setString(1, currentEmployee.id);
                pst.setString(2, currentEmployee.employeeId);
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

    public boolean isNotNull(CurrentEmployee currentEmployee) {
        
        boolean isNotNull = false;
        if (currentEmployee.employeeId.isEmpty() || currentEmployee.employeeName.isEmpty() ) {
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

    public Object delete(CurrentEmployee currentEmployee){
    	
    	System.out.println("Here Inside CurrentEmployeeBLL file to delete...");
    	//if(isNotNull(currentEmployee)){    	
    	      if(isNotNull(currentEmployee)) {
    	    	  
    	    	   System.out.println("Here****");
                    currentEmployeeGetway.delete(currentEmployee);
              }else{
                   System.out.println("Nothing...");
              }
    	//}
        return currentEmployee;
    }
    

}
