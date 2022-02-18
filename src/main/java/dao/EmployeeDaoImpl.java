package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.CustomerPojo;
import pojo.EmployeePojo;

public class EmployeeDaoImpl implements EmployeeDao {
	

	@Override
	public List<EmployeePojo> fetchAllEmployees() {
		// collection of employees
		List<EmployeePojo> allEmployees = new ArrayList<>();;
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM employees";
			
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through result set
			while(rs.next()) {
				// copy each record into a EmployeePojo object
				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), 
																rs.getString(3), rs.getString(4), 
																rs.getLong(5), rs.getString(6));
				
				// add EmployeePojo to ArrayList
				allEmployees.add(employeePojo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allEmployees;
	}

	@Override
	public EmployeePojo loginEmployee(String email, String password) {
		
		EmployeePojo employeePojo = null;
		EmployeePojo employeePojo2 = null;
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			employeePojo = fetchOneEmployee(email);
			
			String query = "SELECT * FROM employees WHERE "
							+ " email=" + "'" + employeePojo.getEmail() + "'" + " AND password=" + "'" + password + "'";
			
			ResultSet rs = stmt.executeQuery(query);
			
			 if(rs.next()) { 
				 	employeePojo2 = new EmployeePojo(rs.getInt(1), rs.getString(2), 
					rs.getString(3), rs.getString(4), 
					rs.getLong(5), rs.getString(6));
			 }
				 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return employeePojo2;
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
		
		// create ArrayList of all customers from DB
		List<CustomerPojo> allCustomers = new ArrayList<>();
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM customers";
			
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through result set
			while(rs.next()) {
				// copy each customer into a CustomerPojo
				CustomerPojo customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), 
															 rs.getString(3), rs.getString(4), 
															 rs.getLong(5), rs.getString(6), rs.getInt(7));
				// add customer to ArrayList
				allCustomers.add(customerPojo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allCustomers;
	}

	@Override
	public EmployeePojo fetchOneEmployee(String email) {

		EmployeePojo employeePojo = null;
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM employees "
							+ "WHERE email=" + "'" + email + "'";
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), 
												rs.getString(3), rs.getString(4), 
												rs.getLong(5), rs.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employeePojo;
	}


}
