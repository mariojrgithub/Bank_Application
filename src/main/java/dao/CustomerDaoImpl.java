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
import pojo.TransactionPojo;

public class CustomerDaoImpl implements CustomerDao {
	
	public static final Logger LOG = LogManager.getLogger(CustomerDaoImpl.class);

	@Override
	public CustomerPojo fetchOneCustomer(String email) throws SystemException {
		
		LOG.info("Entered fetchOneCustomer() in DAO");
		CustomerPojo customerPojo = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM customers " + "WHERE email=" + "'" + email + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getLong(7), rs.getInt(8), rs.getString(9));
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchOneCustomer() in DAO");
		return customerPojo;

	}

	@Override
	public CustomerPojo loginCustomer(String email, String password) throws SystemException {

		LOG.info("Entered loginCustomer() in DAO");
		
		CustomerPojo customerPojo = null;
		CustomerPojo customerPojo2 = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			customerPojo = fetchOneCustomer(email);

			String query = "SELECT * FROM customers WHERE " + " email=" + "'" + customerPojo.getEmail() + "'"
					+ " AND password=" + "'" + password + "'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				customerPojo2 = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getLong(7), rs.getInt(8), rs.getString(9));
			}

		} catch (SQLException | SystemException e) {
			throw new SystemException();
		}

		LOG.info("Exited loginCustomer() in DAO");
		return customerPojo2;

	}

	@Override
	public TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer) throws SystemException {
		
		LOG.info("Entered createNewTransaction() in DAO");

		TransactionPojo transactionPojo = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			CustomerPojo fromCustomer = fetchOneCustomer(fromAccountId);
			CustomerPojo toCustomer = fetchOneCustomer(toAccountId);

			Statement stmt = conn.createStatement();

			// update each customer account
			String queryUpdateTo = "UPDATE customers SET balance=balance+ " + amountToTransfer + " WHERE customer_id="
					+ toCustomer.getCustomerId();
			

			String queryUpdateFrom = "UPDATE customers SET balance=balance- " + amountToTransfer + " WHERE customer_id="
					+ fromCustomer.getCustomerId();
			

			// insert transaction
			String queryInsertTransaction = "INSERT INTO transaction_history(from_account_id, to_account_id, amount_to_transfer) VALUES("
					+ fromCustomer.getCustomerId() + ", " + toCustomer.getCustomerId() + ", " + amountToTransfer + ")";

			conn.setAutoCommit(false);
			
			int rowsTo = stmt.executeUpdate(queryUpdateTo);
			int rowFrom = stmt.executeUpdate(queryUpdateFrom);
			int rows = stmt.executeUpdate(queryInsertTransaction);
			
			conn.commit();
			

			// Obtain transaction
			String queryTransaction = "SELECT * FROM transaction_history WHERE to_account_id="
					+ toCustomer.getCustomerId() + " AND from_account_id=" + fromCustomer.getCustomerId();

			ResultSet rsTransaction = stmt.executeQuery(queryTransaction);

			if (rsTransaction.next()) {
				transactionPojo = new TransactionPojo(rsTransaction.getInt(1), rsTransaction.getInt(2),
						rsTransaction.getInt(3), rsTransaction.getInt(4), rsTransaction.getString(5));
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new SystemException();
			}
			throw new SystemException();
		}

		LOG.info("Exited createNewTransaction() in DAO");
		return transactionPojo;
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions(int customerId) throws SystemException {
		
		LOG.info("Entered fetchAllTransactions() in DAO");

		// collection of transactions
		List<TransactionPojo> allTransactions = new ArrayList<>();

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM transaction_history WHERE from_account_id=" + customerId + " OR to_account_id=" + customerId;

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

	@Override
	public CustomerPojo fetchOneCustomer(int customerId) throws SystemException {
		
		LOG.info("Entered fetchOneCustomer() in DAO");

		CustomerPojo customerPojo = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM customers " + "WHERE customer_id=" + customerId;

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				customerPojo = new CustomerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getLong(5), rs.getString(6), rs.getLong(7), rs.getInt(8), rs.getString(9));
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		LOG.info("Exited fetchOneCustomer() in DAO");
		return customerPojo;
	}

}
