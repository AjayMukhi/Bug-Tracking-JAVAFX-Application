package Getway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import List.ListDepartment;
import controller.application.department.Department;
import dataBase.DBConnection;
import dataBase.DBProperties;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class DepartmentGateway {
	
	DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    //Save Project
    public void save(Department dep) {
        con = dbCon.geConnection();
        if (isUniqDepName(dep)) {
            try {
                con = dbCon.geConnection();
                pst = con.prepareCall("insert into "+db+".Department values(?,?,?,?,?)");
                pst.setString(1, null);
                pst.setString(2, dep.departmentId);
                pst.setString(3, dep.departmentName);
                pst.setString(4, dep.departmentLocation);
                pst.setString(5, dep.departmentType);
                
                pst.executeUpdate();
                con.close();
                pst.close();
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("Sucess : save sucess");
                alert.setContentText("Department" + "  '" + dep.departmentName + "' " + "Added successfully");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();

            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }

    }

    public void view(Department dep) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select * from "+db+".Department");
            rs = pst.executeQuery();
            while (rs.next()) {
                dep.id = rs.getString(1);
                dep.departmentId = rs.getString(2);
                dep.departmentName = rs.getString(3);
                dep.departmentLocation = rs.getString(4);
                dep.departmentType= rs.getString(5);
                
                dep.departmentList.addAll(new ListDepartment(dep.id,dep.departmentId, dep.departmentName,dep.departmentLocation,
                		dep.departmentType));
            }
            con.close();
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Exception");
        }
    }

    public void searchView(Department dep) {
        dep.departmentList.clear();
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Department where DepartmentName like ? or DepartmentLocation like ? ORDER BY DepartmentName");
            pst.setString(1, "%" + dep.departmentName + "%");
            pst.setString(2, "%" + dep.departmentLocation + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
            	dep.id = rs.getString(1);
                dep.departmentId = rs.getString(2);
                dep.departmentName = rs.getString(3);
                dep.departmentLocation = rs.getString(4);
                dep.departmentType= rs.getString(5);
                
                dep.departmentList.addAll(new ListDepartment(dep.id,dep.departmentId, dep.departmentName,dep.departmentLocation,
                		dep.departmentType));
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void selectedView(Department dep) {
        System.out.println("name :" + dep.departmentName);
        con = dbCon.geConnection();
        try {
            con = dbCon.geConnection();
            pst = con.prepareCall("select * from "+db+".Department where id=?");
            pst.setString(1, dep.id);
            rs = pst.executeQuery();
            while (rs.next()) {
            	dep.id = rs.getString(1);
                dep.departmentId = rs.getString(2);
                dep.departmentName = rs.getString(3);
                dep.departmentLocation = rs.getString(4);
                dep.departmentType= rs.getString(5);
                
                
            }
            rs.close();
            con.close();
            pst.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    
    public boolean isUniqDepName(Department dep) {
        con = dbCon.geConnection();
        boolean uniqDep = false;
        con = dbCon.geConnection();
        try {
            pst = con.prepareCall("select DepartmentName from "+db+".Department where DepartmentId=?");
            pst.setString(1, dep.departmentId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Action Denied");
                alert.setContentText("Department" + "  '" + dep.departmentName + "' " + "Already exist");
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
    public void update(Department dep) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("update "+db+".Department set DepartmentId=?, DepartmentName=? , DepartmentLocation=?,"
            		+ "DepartmentType=? where Id=?");
            pst.setString(1, dep.departmentId);
            pst.setString(2, dep.departmentName);
            pst.setString(3, dep.departmentLocation);
            pst.setString(4, dep.departmentType);
            pst.setString(5, dep.id);
           
            pst.executeUpdate();
            con.close();
            pst.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText("Updated : Updated sucess");
            alert.setContentText("Department" + "  '" + dep.departmentName + "' " + "Updated successfully");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUpdate(Department dep) {
        con = dbCon.geConnection();
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Department where Id=?");
            pst.setString(1, dep.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdate;
    }
    
    
    public void delete(Department dep) {
        con = dbCon.geConnection();
        try {
            System.out.println("Inside deletePermanently....");
            con = dbCon.geConnection();
            pst = con.prepareCall("delete from "+db+".Department where Id=?");
            pst.setString(1, dep.id);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

	
    
    public boolean isNotUse(Department dep) {
        con = dbCon.geConnection();
        boolean isNotUse = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Department where DepartmentId=?");
            pst.setString(1, dep.departmentId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("WARNING : ");
            alert.setContentText("Department Name:" + rs.getString(2) +" ");
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
    
    
    
    
    
    public void viewFirstTen(Department dep) {
        con = dbCon.geConnection();

        dep.departmentList.clear();
        try {
            pst = con.prepareStatement("select * from "+db+".Department limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {

            	dep.id = rs.getString(1);
                dep.departmentId = rs.getString(2);
                dep.departmentName = rs.getString(3);
                dep.departmentLocation = rs.getString(4);
                dep.departmentType= rs.getString(5);
                
                dep.departmentList.addAll(new ListDepartment(dep.id,dep.departmentId, dep.departmentName,dep.departmentLocation,
                		dep.departmentType));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    

}
