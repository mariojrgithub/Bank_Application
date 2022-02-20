package pojo;

public class CustomerPojo {
	
	private int customerId;
	private String password;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;
	private long balance;
	private int employee_id;
	private String created_on;
	
	public CustomerPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomerPojo(int customerId, String password, String firstName, String lastName, long phoneNumber,
			String email, long balance, int employee_id, String created_on) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.balance = balance;
	}
	
	
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "CustomerPojo [customerId=" + customerId + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", balance="
				+ balance + "]";
	}
	
	

}
