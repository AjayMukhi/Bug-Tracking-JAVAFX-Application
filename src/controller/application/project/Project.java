package controller.application.project;

import List.ListProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Project {
	
	
	public String id;
	public String projectId;
    public String projectName;
    public String projectDescription;
    public String deliveryDate;
    public String duration;
    public String clientName;
    public String pManager;

//    public List<ListSupplyer> rowSupplyer;
    public ObservableList<ListProject> projectDetails = FXCollections.observableArrayList();


}
