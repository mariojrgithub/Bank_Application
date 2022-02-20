package service;

import java.util.List;

import pojo.CustomerPojo;
import pojo.TransactionPojo;

public interface CustomerService {
	
		// fetch one customer by email
		CustomerPojo fetchOneCustomer(String email);
		
		// fetch one customer by ID
		CustomerPojo fetchOneCustomer(int customerId);
		
		// login
		CustomerPojo loginCustomer(String email, String password);
		
		// create a new transaction
		TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer);
		
		// view all transactions
		List<TransactionPojo> fetchAllTransactions(); 

}
