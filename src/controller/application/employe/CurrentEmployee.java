package controller.application.employe;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CurrentEmployee {

	public String id;
    public String employeeId;
    public String employeeName;
    public String gender;
    public String dob;
    public String qualification;
    public String address;
    public String contactNo;
    public String emailAddress;
    public String dateofJoining;
    public String role;
    
    public ObservableList<ListEmployee> currentEmployeeList = FXCollections.observableArrayList();
}
