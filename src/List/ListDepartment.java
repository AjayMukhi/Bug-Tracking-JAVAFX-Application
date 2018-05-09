package List;

public class ListDepartment {
	
	public String id;
    public String departmentId;
    public String departmentName;
    public String departmentLocation;
    public String departmentType;
	
    
    
    
    
    public ListDepartment(String id, String departmentId, String departmentName, String departmentLocation,
			String departmentType) {
		
		this.id = id;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
		this.departmentType = departmentType;
	}
    
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentLocation() {
		return departmentLocation;
	}
	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}
	public String getDepartmentType() {
		return departmentType;
	}
	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}
    
    
    

}
