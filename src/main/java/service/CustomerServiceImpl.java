package service;

import java.util.List;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao customerDao;
	

	public CustomerServiceImpl() {

		customerDao = new CustomerDaoImpl();
		
	}

	@Override
	public CustomerPojo fetchOneCustomer(String email) {
		
		return customerDao.fetchOneCustomer(email);
	}
	
	
	@Override
	public CustomerPojo fetchOneCustomer(int customerId) {
		
		return customerDao.fetchOneCustomer(customerId);
		
	}

	@Override
	public CustomerPojo loginCustomer(String email, String password) {
		
		return customerDao.loginCustomer(email, password);
		
	}

	@Override
	public TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer) {
		
		return customerDao.createNewTransaction(fromAccountId, toAccountId, amountToTransfer);
		
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions() {
		
		return customerDao.fetchAllTransactions();
	}


}
