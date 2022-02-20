package dao;

import java.util.List;

import pojo.CustomerPojo;
import pojo.TransactionPojo;

public interface CustomerDao {
	
	// fetch one customer
	CustomerPojo fetchOneCustomer(String email);
	
	// login
	CustomerPojo loginCustomer(String email, String password);
	
	// create a new transaction
	CustomerPojo createNewTransaction(int fromAccountId, int toAccountId);
	
	// view all transactions
	List<TransactionPojo> fetchAllTransactions(); 
	
}
