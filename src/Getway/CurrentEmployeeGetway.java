package Getway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import controller.application.employe.CurrentEmployee;
import controller.application.employe.ListEmployee;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class CurrentEmployeeGetway {
	
	
	DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    SQL sql = new SQL();

    public void save(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("insert into "+db+".EmployeeDetails values(?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, null);
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
            
            pst.executeUpdate();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }

    }

    public void view(CurrentEmployee currentEmployee) {
        currentEmployee.currentEmployeeList.clear();
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("SELECT SQL_NO_CACHE * FROM "+db+".EmployeeDetails");
            rs = pst.executeQuery();
            while (rs.next()) {

                currentEmployee.id = rs.getString(1);
                currentEmployee.employeeId = rs.getString(2);
                currentEmployee.employeeName = rs.getString(3);
                currentEmployee.gender = rs.getString(4);
                currentEmployee.dob = rs.getString(5);
                currentEmployee.qualification = rs.getString(6);
                currentEmployee.address = rs.getString(7);
                currentEmployee.contactNo = rs.getString(8);
                currentEmployee.emailAddress = rs.getString(9);
                currentEmployee.dateofJoining = rs.getString(10);
                currentEmployee.role = rs.getString(11);
                
                currentEmployee.currentEmployeeList.addAll(new ListEmployee(
                		currentEmployee.id, currentEmployee.employeeId, currentEmployee.employeeName, currentEmployee.gender, 
                		currentEmployee.dob, currentEmployee.qualification, currentEmployee.address, currentEmployee.contactNo, 
                		currentEmployee.emailAddress, currentEmployee.dateofJoining, currentEmployee.role));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectedView(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails where id=?");
            pst.setString(1, currentEmployee.id);
            rs = pst.executeQuery();
            while (rs.next()) {
//                id = rs.getString(1);
                currentEmployee.employeeId = rs.getString(2);
                currentEmployee.employeeName = rs.getString(3);
                currentEmployee.gender = rs.getString(4);
                currentEmployee.dob = rs.getString(5);
                currentEmployee.qualification = rs.getString(6);
                currentEmployee.address = rs.getString(7);
                currentEmployee.contactNo = rs.getString(8);
                currentEmployee.emailAddress = rs.getString(9);
                currentEmployee.dateofJoining = rs.getString(10);
                currentEmployee.role = rs.getString(11);
            }   
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewFistTen(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();

        currentEmployee.currentEmployeeList.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {

            	currentEmployee.id = rs.getString(1);
                currentEmployee.employeeId = rs.getString(2);
                currentEmployee.employeeName = rs.getString(3);
                currentEmployee.gender = rs.getString(4);
                currentEmployee.dob = rs.getString(5);
                currentEmployee.qualification = rs.getString(6);
                currentEmployee.address = rs.getString(7);
                currentEmployee.contactNo = rs.getString(8);
                currentEmployee.emailAddress = rs.getString(9);
                currentEmployee.dateofJoining = rs.getString(10);
                currentEmployee.role = rs.getString(11);
                
                currentEmployee.currentEmployeeList.addAll(new ListEmployee(
                		currentEmployee.id, currentEmployee.employeeId, currentEmployee.employeeName, currentEmployee.gender, 
                		currentEmployee.dob, currentEmployee.qualification, currentEmployee.address, currentEmployee.contactNo, 
                		currentEmployee.emailAddress, currentEmployee.dateofJoining, currentEmployee.role));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchView(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();

        currentEmployee.currentEmployeeList.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails where EmployeeId like ? or EmployeeName like ?");
            pst.setString(1, "%" + currentEmployee.employeeId + "%");
            pst.setString(2, "%" + currentEmployee.employeeName + "%");
            rs = pst.executeQuery();
            while (rs.next()) {

            	
            	currentEmployee.id = rs.getString(1);
                currentEmployee.employeeId = rs.getString(2);
                currentEmployee.employeeName = rs.getString(3);
                currentEmployee.gender = rs.getString(4);
                currentEmployee.dob = rs.getString(5);
                currentEmployee.qualification = rs.getString(6);
                currentEmployee.address = rs.getString(7);
                currentEmployee.contactNo = rs.getString(8);
                currentEmployee.emailAddress = rs.getString(9);
                currentEmployee.dateofJoining = rs.getString(10);
                currentEmployee.role = rs.getString(11);
                
                currentEmployee.currentEmployeeList.addAll(new ListEmployee(
                		currentEmployee.id, currentEmployee.employeeId, currentEmployee.employeeName, currentEmployee.gender, 
                		currentEmployee.dob, currentEmployee.qualification, currentEmployee.address, currentEmployee.contactNo, 
                		currentEmployee.emailAddress, currentEmployee.dateofJoining, currentEmployee.role));
                
                
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public void sView(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("select * from "+db+".EmployeeDetails where EmployeeId=?");
            pst.setString(1, currentEmployee.employeeId);
            rs = pst.executeQuery();
            while (rs.next()) {
            	currentEmployee.id = rs.getString(1);
                currentEmployee.employeeId = rs.getString(2);
                currentEmployee.employeeName = rs.getString(3);
                currentEmployee.gender = rs.getString(4);
                currentEmployee.dob = rs.getString(5);
                currentEmployee.qualification = rs.getString(6);
                currentEmployee.address = rs.getString(7);
                currentEmployee.contactNo = rs.getString(8);
                currentEmployee.emailAddress = rs.getString(9);
                currentEmployee.dateofJoining = rs.getString(10);
                currentEmployee.role = rs.getString(11);

            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(CurrentEmployee currentEmployee) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("update "+db+".EmployeeDetails set EmployeeId=?, EmployeeName=?, Gender=?, DOB=?, "
                    + "Qualification=?, Address=?, ContactNo=?,"
                    + " EmailAddress=?, DOJ=?, Role=?  where Id=?");
            pst.setString(1, currentEmployee.employeeId);
            pst.setString(2, currentEmployee.employeeName);
            pst.setString(3, currentEmployee.gender);
            pst.setString(4, currentEmployee.dob);
            pst.setString(5, currentEmployee.qualification);
            pst.setString(6, currentEmployee.address);
            pst.setString(7, currentEmployee.contactNo);
            pst.setString(8, currentEmployee.emailAddress);
            pst.setString(9, currentEmployee.dateofJoining);
            pst.setString(10, currentEmployee.role);
            pst.setString(11, currentEmployee.id);
            
            pst.executeUpdate();
            pst.close();
            con.close();            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(CurrentEmployee currentEmployee) {
    	
    	System.out.println("Here Inside CurrentEmployeeGetway file to delete...");
    	System.out.println("Deleting the Employee Record...");
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("delete from "+db+".EmployeeDetails where id=?");
            pst.setString(1, currentEmployee.id);
            pst.executeUpdate();
            pst.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

	
	
    

}
