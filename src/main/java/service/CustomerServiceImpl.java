package service;

import java.util.List;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.TransactionPojo;

public class CustomerServiceImpl implements CustomerService {
	
	CustomerDao customerDao;
	

	public CustomerServiceImpl() {

		customerDao = new CustomerDaoImpl();
		
	}

	@Override
	public CustomerPojo fetchOneCustomer(String email) throws SystemException {
		
		return customerDao.fetchOneCustomer(email);
	}
	
	
	@Override
	public CustomerPojo fetchOneCustomer(int customerId) throws SystemException {
		
		return customerDao.fetchOneCustomer(customerId);
		
	}

	@Override
	public CustomerPojo loginCustomer(String email, String password) throws SystemException {
		
		return customerDao.loginCustomer(email, password);
		
	}

	@Override
	public TransactionPojo createNewTransaction(int fromAccountId, int toAccountId, int amountToTransfer) throws SystemException {
		
		return customerDao.createNewTransaction(fromAccountId, toAccountId, amountToTransfer);
		
	}

	@Override
	public List<TransactionPojo> fetchAllTransactions(int customerId) throws SystemException {
		
		return customerDao.fetchAllTransactions(customerId);
	}


}
