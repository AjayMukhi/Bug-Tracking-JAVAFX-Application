package BLL;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Getway.ProjectGateway;
import controller.application.project.Project;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class ProjectBLL {
	
	
	ProjectGateway projectGetway = new ProjectGateway();
	
	
	DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;
    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();
    
    SQL sql = new SQL();
    

	
	
	public Object delete(Project project){
        
		
		System.out.println("Here Inside ProjectBLL file to delete...");
    	//if(isNotNull(currentEmployee)){    	
    	      if(isNotNull(project)) {
    	    	  
    	    	   System.out.println("Here****");
                    projectGetway.delete(project);
              }else{
                   System.out.println("Nothing...");
              }
    	//}
        return project;
    }
    
	
  


	 public void save(Project project) {
	    	
	    	System.out.println("Inside ProjectBLL save()...");
	        if (isUniqName(project)) {
	            projectGetway.save(project);
	        }
	        
	    }

	 public void update(Project project) {
	    	
	    	System.out.println("Inside ProjectBLL update()...");
	        if(isNotNull(project)){
	        	
	            if (isUpdate(project)) {
	                    if (checkUpdateCondition(project)) {
	                             projectGetway.update(project);
	                    } else if (isUniqName(project)) {
	                           projectGetway.update(project);
	            }
	        }
	        }
	    }


	private boolean isUniqName(Project project) {
		System.out.println("WE ARE IN ProjectBLL FILE");
        boolean isUniqname = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Project where ProjectId=?");
            pst.setString(1, project.projectId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucess");
                alert.setHeaderText("ERROR : Not Uniq");
                alert.setContentText("Project id" + "  '" + project.projectId + "' " + "id not Uniq");
                alert.initStyle(StageStyle.UNDECORATED);
                alert.showAndWait();
                
                return isUniqname;
            }
            isUniqname = true;
        } catch (SQLException e) {

        }
        return isUniqname;
   
	}
	
	
	
	public boolean isUpdate(Project project) {
        System.out.println("WE ARE IN UPDATE");
        boolean isUpdate = false;
        try {
            pst = con.prepareStatement("select * from "+db+".Project where Id=? and ProjectId=? "
            		+ "and ProjectName=? and ProjectDescription=? and DeliveryDate=? and Duration=? and ClientName=? "
                    + "and ProjectManager=? ");
            pst.setString(1, project.id);
            pst.setString(2, project.projectId);
            pst.setString(3, project.projectName);
            pst.setString(4, project.projectDescription);
            pst.setString(5, project.deliveryDate);
            pst.setString(6, project.duration);
            pst.setString(7, project.clientName);
            pst.setString(8, project.pManager);
            
            
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

    public boolean checkUpdateCondition(Project project) {
        boolean isTrueUpdate = false;
        if (isUpdate(project)) {
            try {
                pst = con.prepareStatement("select * from "+db+".Project where id=? and ProjectId=?");
                pst.setString(1, project.id);
                pst.setString(2, project.projectId);
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

    public boolean isNotNull(Project project) {
        
        boolean isNotNull = false;
        if (project.projectId.isEmpty() || project.projectName.isEmpty() ) {
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
