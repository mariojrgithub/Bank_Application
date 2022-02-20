package dao;

import java.util.List;

import pojo.CustomerPojo;
import pojo.TransactionPojo;

public interface CustomerDao {
	
	// fetch one customer
	CustomerPojo fetchOneCustomer(String email);
	
	// fetch one by ID customer
	CustomerPojo fetchOneCustomer(int customerId);
	
	// login
	CustomerPojo loginCustomer(String email, String password);
	
	// create a new transaction
	TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer);
	
	// view all transactions
	List<TransactionPojo> fetchAllTransactions(); 
	
}
