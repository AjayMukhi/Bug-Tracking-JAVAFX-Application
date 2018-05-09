package List;

public class ListReport {
	
	
	public String id;
    public String reportId;
    public String reportName;
    public String bugName;
    public String severity;
    public String projectName;
    public String raisedDate;
    public String status;
    public String resolvedDate;
    
    //Constructor
    public ListReport(String id, String reportId, String reportName, String bugName, String severity,
			String projectName, String raisedDate, String status, String resolvedDate) {
		
		this.id = id;
		this.reportId = reportId;
		this.reportName = reportName;
		this.bugName = bugName;
		this.severity = severity;
		this.projectName = projectName;
		this.raisedDate = raisedDate;
		this.status = status;
		this.resolvedDate = resolvedDate;
	}
    
    
    
    
    
    //Setter & getters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getBugName() {
		return bugName;
	}
	public void setBugName(String bugName) {
		this.bugName = bugName;
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
	public String getRaisedDate() {
		return raisedDate;
	}
	public void setRaisedDate(String raisedDate) {
		this.raisedDate = raisedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(String resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
    
	
    

}
