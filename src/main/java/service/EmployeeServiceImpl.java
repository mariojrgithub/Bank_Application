package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl() {
		
		employeeDao = new EmployeeDaoImpl();
		
	}

	@Override
	public List<EmployeePojo> fetchAllEmployees() {
		 
		return employeeDao.fetchAllEmployees();
	}

	@Override
	public EmployeePojo fetchOneEmployee(String email) {
		
		return employeeDao.fetchOneEmployee(email);
	}

	@Override
	public EmployeePojo loginEmployee(String email, String password) {
			
		return employeeDao.loginEmployee(email, password);
		

	}

	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo, int employeeId) {
		
		return employeeDao.createNewCustomer(customerPojo, employeeId);
		
	}

	@Override
	public List<CustomerPojo> fetchAllCustomers() {
		return employeeDao.fetchAllCustomers();
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions() {
		return employeeDao.fetchAllTransactions();
	}


}
