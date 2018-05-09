package controller.application.employe;

public class ListEmployee {
	
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
	    
	    
	    //Constructor
		public ListEmployee(String id, String employeeId, String employeeName, String gender, String dob,
				String qualification, String address, String contactNo, String emailAddress, String dateofJoining,
				String role) {
			this.id = id;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.gender = gender;
			this.dob = dob;
			this.qualification = qualification;
			this.address = address;
			this.contactNo = contactNo;
			this.emailAddress = emailAddress;
			this.dateofJoining = dateofJoining;
			this.role = role;
		}
		
		
		//setter getter methods....
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getQualification() {
			return qualification;
		}
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getContactNo() {
			return contactNo;
		}
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		public String getDateofJoining() {
			return dateofJoining;
		}
		public void setDateofJoining(String dateofJoining) {
			this.dateofJoining = dateofJoining;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	    

}
