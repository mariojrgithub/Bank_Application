package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;
	
	public EmployeeServiceImpl() {
		
		employeeDao = new EmployeeDaoImpl();
		
	}

	@Override
	public List<EmployeePojo> fetchAllEmployees() throws SystemException {
		 
		return employeeDao.fetchAllEmployees();
	}

	@Override
	public EmployeePojo fetchOneEmployee(String email) throws SystemException {
		
		return employeeDao.fetchOneEmployee(email);
	}

	@Override
	public EmployeePojo loginEmployee(String email, String password) throws SystemException {
			
		return employeeDao.loginEmployee(email, password);
		

	}

	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo, int employeeId) throws SystemException {
		
		return employeeDao.createNewCustomer(customerPojo, employeeId);
		
	}

	@Override
	public List<CustomerPojo> fetchAllCustomers() throws SystemException {
		return employeeDao.fetchAllCustomers();
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions() throws SystemException {
		return employeeDao.fetchAllTransactions();
	}


}
