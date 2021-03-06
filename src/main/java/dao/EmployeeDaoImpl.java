package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public class EmployeeDaoImpl implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeDaoImpl.class);

	@Override
	public List<EmployeePojo> fetchAllEmployees() throws SystemException {
		
		LOG.info("Entered fetchAllEmployees() in DAO");
		
		// collection of employees
		List<EmployeePojo> allEmployees = new ArrayList<>();
		;

		Connection conn = DBUtil.obtainConnection();

		Statement stmt;
		try {
			stmt = conn.createStatement();

			String query = "SELECT * FROM employees";

			ResultSet rs = stmt.executeQuery(query);

			// iterate through result set
			while (rs.next()) {
				// copy each record into a EmployeePojo object
				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getLong(5), rs.getString(6), rs.getString(7));

				// add EmployeePojo to ArrayList
				allEmployees.add(employeePojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchAllEmployees() in DAO");
		return allEmployees;
	}

	@Override
	public EmployeePojo loginEmployee(String email, String password) throws SystemException {
		
		LOG.info("Entered loginEmployee() in DAO");

		EmployeePojo employeePojo = null;
		EmployeePojo employeePojo2 = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			employeePojo = fetchOneEmployee(email);

			String query = "SELECT * FROM employees WHERE " + " email=" + "'" + employeePojo.getEmail() + "'"
					+ " AND password=" + "'" + password + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				employeePojo2 = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7));
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited loginEmployee() in DAO");
		return employeePojo2;
	}

	@Override
	public CustomerPojo createNewCustomer(CustomerPojo customerPojo, int employeeId) throws SystemException {

		LOG.info("Entered createNewCustomer() in DAO");
		
		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "INSERT INTO customers(password, first_name, last_name, phone_number, email, balance, employee_id) VALUES("
					+ "'" + customerPojo.getPassword() + "', '" + customerPojo.getFirstName() + "', '"
					+ customerPojo.getLastName() + "', " + customerPojo.getPhoneNumber() + ", '"
					+ customerPojo.getEmail() + "', " + customerPojo.getBalance() + ", " + employeeId + ")";

			int rows = stmt.executeUpdate(query);

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited createNewCustomer() in DAO");
		return customerPojo;
	}

	@Override
	public List<CustomerPojo> fetchAllCustomers() throws SystemException {
		
		LOG.info("Entered fetchAllCustomers() in DAO");

		// create ArrayList of all customers from DB
		List<CustomerPojo> allCustomers = new ArrayList<>();

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM customers";

			ResultSet rs = stmt.executeQuery(query);

			// iterate through result set
			while (rs.next()) {
				// copy each customer into a CustomerPojo
				CustomerPojo customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getLong(5), rs.getString(6), rs.getLong(7), rs.getInt(8), rs.getString(9));
				// add customer to ArrayList
				allCustomers.add(customerPojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchAllCustomers() in DAO");
		return allCustomers;
	}

	@Override
	public EmployeePojo fetchOneEmployee(String email) throws SystemException {
		
		LOG.info("Entered fetchOneEmployee() in DAO");

		EmployeePojo employeePojo = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM employees " + "WHERE email=" + "'" + email + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getString(7));
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchOneEmployee() in DAO");
		return employeePojo;
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions() throws SystemException {
		
		LOG.info("Entered fetchAllTransactions() in DAO");

		// collection of transactions
		List<TransactionPojo> allTransactions = new ArrayList<>();

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM transaction_history";

			ResultSet rs = stmt.executeQuery(query);

			// iterate through result set
			while (rs.next()) {
				// copy each record into a EmployeePojo object
				TransactionPojo transactionPojo = new TransactionPojo(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4), rs.getString(5));

				// add EmployeePojo to ArrayList
				allTransactions.add(transactionPojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchAllTransactions() in DAO");
		return allTransactions;

	}

}
