package controller.application.department;


import List.ListDepartment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Department {
	
	public String id;
	public String departmentId;
    public String departmentName;
    public String departmentLocation;
    public String departmentType;
    
//    public List<ListSupplyer> rowSupplyer;
    public ObservableList<ListDepartment> departmentList = FXCollections.observableArrayList();



}
