package dao;

import java.util.List;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

public interface CustomerDao {

	// fetch one customer
	CustomerPojo fetchOneCustomer(String email) throws SystemException;

	// fetch one by ID customer
	CustomerPojo fetchOneCustomer(int customerId) throws SystemException;

	// login
	CustomerPojo loginCustomer(String email, String password) throws SystemException;

	// create a new transaction
	TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer) throws SystemException;

	// view all transactions
	List<TransactionPojo> fetchAllTransactions() throws SystemException;

}
