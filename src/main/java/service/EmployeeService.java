package service;

import java.util.List;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public interface EmployeeService {
	// fetch all employees
	List<EmployeePojo> fetchAllEmployees() throws SystemException;
	
	// get one employee
	EmployeePojo fetchOneEmployee(String email) throws SystemException;

	// login
	EmployeePojo loginEmployee(String email, String password) throws SystemException;
	
	// register new customer
	CustomerPojo createNewCustomer(CustomerPojo customerPojo, int employeeId) throws SystemException;
	
	// list all customers
	List<CustomerPojo> fetchAllCustomers() throws SystemException;
	
	// view all transactions
	List<TransactionPojo> fetchAllTransactions() throws SystemException; 
	
}
