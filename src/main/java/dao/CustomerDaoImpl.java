package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public CustomerPojo fetchOneCustomer(String email) {
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerPojo;
		
	}

	@Override
	public CustomerPojo loginCustomer(String email, String password) {
		
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerPojo2;
		
	}

	@Override
	public CustomerPojo createNewTransaction(int fromAccountId, int toAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

}
