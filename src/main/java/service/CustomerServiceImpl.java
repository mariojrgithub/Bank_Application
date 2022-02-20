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
	public CustomerPojo loginCustomer(String email, String password) {
		
		return customerDao.loginCustomer(email, password);
		
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
