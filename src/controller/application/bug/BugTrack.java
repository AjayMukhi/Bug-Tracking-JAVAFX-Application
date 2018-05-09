package controller.application.bug;


import List.ListBugTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BugTrack {
	
	public String id;
	public String bugId;
    public String bugName;
    public String bugType;
    public String bugLevel;
    public String severity;
    public String projectName;
    public String testerCode;
    public String raisedOn;
    public String developerCode;
    public String status;
    public String postSolution;
    
    
    
    public ObservableList<ListBugTrack> bugList = FXCollections.observableArrayList();


}
