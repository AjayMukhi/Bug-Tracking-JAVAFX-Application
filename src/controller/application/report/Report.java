package controller.application.report;


import List.ListReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Report {
	
	
	public String id;
    public String reportId;
    public String reportName;
    public String bugName;
    public String severity;
    public String projectName;
    public String raisedDate;
    public String status;
    public String resolvedDate;
    
    public ObservableList<ListReport> reportList = FXCollections.observableArrayList();

}
