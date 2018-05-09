package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Getway.DepartmentGateway;
import controller.application.department.Department;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class DepartmentBLL {
	
	
	DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    DepartmentGateway departmentGetway = new DepartmentGateway();

    public void save(Department department) {
    	
    	System.out.println("Inside DepartmentBLL save()...");
        if (isUniqName(department)) {
        	departmentGetway.save(department);
        }
        
    }

    public void update(Department department) {
    	
    	System.out.println("Inside CurrentEmployeeBLL update()...");
        if(isNotNull(department)){
        	
            if (isUpdate(department)) {
                    if (checkUpdateCondition(department)) {
                             departmentGetway.update(department);
                    } else if (isUniqName(department)) {
                           departmentGetway.update(department);
            }
        }
        }
    }

    public boolean isUniqName(Department department) {
        System.out.println("WE ARE IN DepartmentBLL FILE");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Department where DepartmentId=?");
            pst.setString(1, department.departmentId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Department id" + "  '" + department.departmentId + "' " + "id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
    }

    public boolean isUpdate(Department dep) {
        System.out.println("WE ARE IN UPDATE");
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Department where Id=? and DepartmentId=? "
            		+ "and DepartmentName=? and DepartmentLocation=? and DepartmentType=? ");
            pst.setString(1, dep.id);
            pst.setString(2, dep.departmentId);
            pst.setString(3, dep.departmentName);
            pst.setString(4, dep.departmentLocation);
            pst.setString(5, dep.departmentType);
              
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

    public boolean checkUpdateCondition(Department dep) {
        boolean isTrueUpdate = false;
        if (isUpdate(dep)) {
            try {
                pst = con.prepareStatement("select * from "+db+".Department where id=? and DepartmentId=?");
                pst.setString(1, dep.id);
                pst.setString(2, dep.departmentId);
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

    public boolean isNotNull(Department dep) {
        
        boolean isNotNull = false;
        if (dep.departmentId.isEmpty() || dep.departmentName.isEmpty() ) {
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

    public Object delete(Department dep){
    	
    	System.out.println("Here Inside CurrentEmployeeBLL file to delete...");
    	//if(isNotNull(currentEmployee)){    	
    	      if(isNotNull(dep)) {
    	    	  
    	    	   System.out.println("Here****");
                    departmentGetway.delete(dep);
              }else{
                   System.out.println("Nothing...");
              }
    	//}
        return dep;
    }
    


}
