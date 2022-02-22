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

//		// get from account info
////		CustomerPojo fromCustomer = null;
//		CustomerPojo fromCustomer2 = null;
//		// get to account info
////		CustomerPojo toCustomer = null;
//		CustomerPojo toCustomer2 = null;
		
		LOG.info("Entered createNewTransaction() in DAO");

		TransactionPojo transactionPojo = null;

		Connection conn = DBUtil.obtainConnection();

		try {
			CustomerPojo fromCustomer = fetchOneCustomer(fromAccountId);
			CustomerPojo toCustomer = fetchOneCustomer(toAccountId);

			Statement stmt = conn.createStatement();

//			// obtain to account
//			String queryTo = "SELECT * FROM customers WHERE customer_id=" + toCustomer.getCustomerId();
//			
//			// obtain from account
//			String queryFrom = "SELECT * FROM customers WHERE customer_id=" + fromCustomer.getCustomerId();

//			ResultSet rsTo = stmt.executeQuery(queryTo);

//			if (rsTo.next()) {
//				toCustomer2 = new CustomerPojo(rsTo.getInt(1), rsTo.getString(2), rsTo.getString(3), rsTo.getString(4),
//						rsTo.getLong(5), rsTo.getString(6), rsTo.getLong(7), rsTo.getInt(8), rsTo.getString(9));
//			}
//						
//			ResultSet rsFrom = stmt.executeQuery(queryFrom);
//			
//			if (rsFrom.next()) {
//				fromCustomer2 = new CustomerPojo(rsFrom.getInt(1), rsFrom.getString(2), rsFrom.getString(3), rsFrom.getString(4),
//						rsFrom.getLong(5), rsFrom.getString(6), rsFrom.getLong(7), rsFrom.getInt(8), rsFrom.getString(9));
//			}

			// update each customer account
			String queryUpdateTo = "UPDATE customers SET balance=balance+ " + amountToTransfer + " WHERE customer_id="
					+ toCustomer.getCustomerId();
			int rowsTo = stmt.executeUpdate(queryUpdateTo);

			String queryUpdateFrom = "UPDATE customers SET balance=balance- " + amountToTransfer + " WHERE customer_id="
					+ fromCustomer.getCustomerId();
			int rowFrom = stmt.executeUpdate(queryUpdateFrom);

			// insert transaction
			String queryInsertTransaction = "INSERT INTO transaction_history(from_account_id, to_account_id, amount_to_transfer) VALUES("
					+ fromCustomer.getCustomerId() + ", " + toCustomer.getCustomerId() + ", " + amountToTransfer + ")";

			int rows = stmt.executeUpdate(queryInsertTransaction);

			// Obtain transaction
			String queryTransaction = "SELECT * FROM transaction_history WHERE to_account_id="
					+ toCustomer.getCustomerId() + " AND from_account_id=" + fromCustomer.getCustomerId();

			ResultSet rsTransaction = stmt.executeQuery(queryTransaction);

			if (rsTransaction.next()) {
				transactionPojo = new TransactionPojo(rsTransaction.getInt(1), rsTransaction.getInt(2),
						rsTransaction.getInt(3), rsTransaction.getInt(4), rsTransaction.getString(5));
			}

		} catch (SQLException e) {
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
