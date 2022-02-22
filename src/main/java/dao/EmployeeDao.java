package dao;

import java.util.List;

import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public interface EmployeeDao {

	// fetch all employees
	List<EmployeePojo> fetchAllEmployees();

	// get one employee
	EmployeePojo fetchOneEmployee(String email);

	// login
	EmployeePojo loginEmployee(String email, String password);

	// register new customer
	CustomerPojo createNewCustomer(CustomerPojo customerPojo, int employeeId);

	// list all customers
	List<CustomerPojo> fetchAllCustomers();
	
	// view all transactions
	List<TransactionPojo> fetchAllTransactions();

}
