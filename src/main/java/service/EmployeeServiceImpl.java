package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojo.CustomerPojo;
import pojo.EmployeePojo;

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
	public EmployeePojo logoutEmployee(EmployeePojo employeePojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerPojo> fetchAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

}
