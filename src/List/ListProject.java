package List;

public class ListProject {
	
	public String id;
    public String projectId;
    public String projectName;
    public String projectDescription;
    public String deliveryDate;
    public String duration;
    public String clientName;
    public String pManager;
	
    
    
    
    
    
    public ListProject(String id, String projectId, String projectName, String projectDesc, String deliveryDate,
			String duration, String clientName, String projectManager) {
		
		this.id = id;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDescription = projectDesc;
		this.deliveryDate = deliveryDate;
		this.duration = duration;
		this.clientName = clientName;
		this.pManager = projectManager;
	}
	//getter and setter
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDesc() {
		return projectDescription;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDescription = projectDesc;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getProjectManager() {
		return pManager;
	}
	public void setProjectManager(String projectManager) {
		pManager = projectManager;
	}
    
}
