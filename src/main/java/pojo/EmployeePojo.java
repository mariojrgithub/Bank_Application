package pojo;

public class EmployeePojo {
	
	private int employeeId;
	private String password;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private String created_on;
	
	public EmployeePojo() {
		super();

	}
	
	public EmployeePojo(int employeeId, String password, String firstName, String lastName, long phoneNumber,
			String email, String created_on) {
		super();
		this.employeeId = employeeId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeePojo [employeeId=" + employeeId + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	
	
	

}
