package service;

import java.util.List;

import pojo.CustomerPojo;
import pojo.EmployeePojo;

public interface EmployeeService {
	// fetch all employees
	List<EmployeePojo> fetchAllEmployees();
	
	// get one employee
	EmployeePojo fetchOneEmployee(String email);

	// login
	EmployeePojo loginEmployee(String email, String password);
	
	// logout
	EmployeePojo logoutEmployee(EmployeePojo employeePojo);
	
	// register new customer
	CustomerPojo createNewCustomer(CustomerPojo customerPojo);
	
	// list all customers
	List<CustomerPojo> fetchAllCustomers();
	
}
