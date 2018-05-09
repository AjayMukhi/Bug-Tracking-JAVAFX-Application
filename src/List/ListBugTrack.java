package List;

public class ListBugTrack {
	
	
	
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
	
    
    
    
    //Constructor
    public ListBugTrack(String id, String bugId, String bugName, String bugType, String bugLevel, String severity,
			String projectName, String testerCode, String raisedOn, String developerCode, String status,
			String postSolution) {
		
		this.id = id;
		this.bugId = bugId;
		this.bugName = bugName;
		this.bugType = bugType;
		this.bugLevel = bugLevel;
		this.severity = severity;
		this.projectName = projectName;
		this.testerCode = testerCode;
		this.raisedOn = raisedOn;
		this.developerCode = developerCode;
		this.status = status;
		this.postSolution = postSolution;
	}
    
    
    
    //Setter and Getter Methods...
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBugId() {
		return bugId;
	}
	public void setBugId(String bugId) {
		this.bugId = bugId;
	}
	public String getBugName() {
		return bugName;
	}
	public void setBugName(String bugName) {
		this.bugName = bugName;
	}
	public String getBugType() {
		return bugType;
	}
	public void setBugType(String bugType) {
		this.bugType = bugType;
	}
	public String getBugLevel() {
		return bugLevel;
	}
	public void setBugLevel(String bugLevel) {
		this.bugLevel = bugLevel;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getTesterCode() {
		return testerCode;
	}
	public void setTesterCode(String testerCode) {
		this.testerCode = testerCode;
	}
	public String getRaisedOn() {
		return raisedOn;
	}
	public void setRaisedOn(String raisedOn) {
		this.raisedOn = raisedOn;
	}
	public String getDeveloperCode() {
		return developerCode;
	}
	public void setDeveloperCode(String developerCode) {
		this.developerCode = developerCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostSolution() {
		return postSolution;
	}
	public void setPostSolution(String postSolution) {
		this.postSolution = postSolution;
	}
    
    

}
