package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class BankMain {
	
	public static final Logger LOG = LogManager.getLogger(BankMain.class);

	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();

		Scanner scan = new Scanner(System.in);

		char ch = 'y';

		while (ch == 'y') {

			System.out.println("*************************************");
			System.out.println("\tWelcome to the Bank");
			System.out.println("*************************************");
			System.out.println("1. Login Employee");
			System.out.println("2. Login Customer");
			System.out.println("3. Exit Bank");
			System.out.println("*************************************");
			System.out.println("Please enter a menu option: ");

			int option = scan.nextInt();
			scan.nextLine();

			List<Integer> mainListOptions = new ArrayList<>();
			mainListOptions.add(1);
			mainListOptions.add(2);
			mainListOptions.add(3);

			// make sure valid option is selected
			if (!mainListOptions.contains(option)) {
				System.out.println("Please enter a valid menu option!");
			}

			System.out.println("*************************************");

			switch (option) {

			case 1:

				EmployeePojo foundEmployee = null;
				EmployeePojo fetchedEmployee = null;

				while (foundEmployee == null) {
					System.out.println("Enter Employee Email: ");
					String employeeEmail = scan.next();

					try {
						foundEmployee = employeeService.fetchOneEmployee(employeeEmail);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					if (foundEmployee == null) {
						System.out.println("Please enter the proper email...");
					}

				}

				while (fetchedEmployee == null) {
					System.out.println("Enter Employee Password: ");
					String employeePassword = scan.next();

					try {
						fetchedEmployee = employeeService.loginEmployee(foundEmployee.getEmail(), employeePassword);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					if (fetchedEmployee == null) {
						System.out.println("Please enter the proper password...");
					} else {
						System.out.println("Login Successful!");

					}

				}

				System.out.println("*************************************");
				System.out.println("Employee ID: " + fetchedEmployee.getEmployeeId());
				System.out.println(
						"Employee Name: " + fetchedEmployee.getFirstName() + " " + fetchedEmployee.getLastName());
				System.out.println("Employee Email: " + fetchedEmployee.getEmail());
				System.out.println("Employee Phone Number: " + fetchedEmployee.getPhoneNumber());

				boolean employeeMenu = true;

				while (employeeMenu) {
					System.out.println("*************************************");
					System.out.println("Employee Menu");
					System.out.println("*************************************");
					System.out.println("1. List all Customers");
					System.out.println("2. Create a Customer");
					System.out.println("3. View all Transactions");
					System.out.println("4. Logout and Return to Main Menu");
					System.out.println("*************************************");

					List<Integer> employeeListOptions = new ArrayList<>();
					employeeListOptions.add(1);
					employeeListOptions.add(2);
					employeeListOptions.add(3);
					employeeListOptions.add(4);

					int option2 = scan.nextInt();

					// make sure valid option is selected
					if (!employeeListOptions.contains(option2)) {
						System.out.println("Please enter a valid menu option!");
					}

					if (option2 == 1) {

						List<CustomerPojo> allCustomers;
						allCustomers = null;

						try {
							allCustomers = employeeService.fetchAllCustomers();
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						// iterate through all customers
						Iterator<CustomerPojo> itr = allCustomers.iterator();

						System.out.println("***********************************************************************************");
						System.out.println("Customer List:");
						System.out.println("ID\tNAME\t\tPHONE\t\t\tBALANCE\t\tEMAIL");

						while (itr.hasNext()) {
							CustomerPojo customer = itr.next();
							System.out.println(customer.getCustomerId() + "\t" + customer.getFirstName() + " "
									+ customer.getLastName() + "\t" + customer.getPhoneNumber() + "\t\t"
									+ "$" + customer.getBalance() + "\t\t" + customer.getEmail() );
						}
						System.out.println("**********************************************************************************");
					}

					if (option2 == 2) {
						System.out.println("********************************************");
						scan.nextLine();

						CustomerPojo newCustomer = new CustomerPojo();

						System.out.println("Enter a Password: ");
						newCustomer.setPassword(scan.nextLine());
						System.out.println("Enter First Name: ");
						newCustomer.setFirstName(scan.nextLine());
						System.out.println("Enter Last Name: ");
						newCustomer.setLastName(scan.nextLine());
						System.out.println("Enter Phone Number: ");
						newCustomer.setPhoneNumber(scan.nextLong());
						scan.nextLine();
						
						boolean check = true;
						
						while(check) {
							System.out.println("Enter email: ");
							String customerEmail = scan.nextLine();
							
							if(!customerEmail.contains("@")) {
								System.out.println("Please enter a proper email...");
							} else {
							
								newCustomer.setEmail(customerEmail);
								check = false;
								
							}
							
						}
						
						
						System.out.println("Enter Balance ($): ");
						newCustomer.setBalance(scan.nextLong());
						scan.nextLine();

						CustomerPojo addedCustomer;
						addedCustomer = null;

						try {
							addedCustomer = employeeService.createNewCustomer(newCustomer, fetchedEmployee.getEmployeeId());
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						System.out.println("New Customer Name is: " + addedCustomer.getFirstName() + " "
								+ addedCustomer.getLastName());
						System.out.println("New Customer Phone Number is: " + addedCustomer.getPhoneNumber());
						System.out.println("New Customer Email is: " + addedCustomer.getEmail());
						System.out.println("New Customer Balance is: $" + addedCustomer.getBalance());
						System.out.println("Your Employee ID is: " + fetchedEmployee.getEmployeeId());

						System.out.println();
					}

					if (option2 == 3) {
						
						List<TransactionPojo> allTransactions;
						allTransactions = null;

						try {
							allTransactions = employeeService.fetchAllTransactions();
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						// iterate through all customers
						Iterator<TransactionPojo> itr = allTransactions.iterator();

						System.out.println(
								"*************************************************************************************************************");
						System.out.println("Transaction List:");
						System.out.println("ID\tFROM ACCOUNT ID\t\tTO ACCOUNT ID\tAMOUNT TRANSFERRED\t\tCREATED ON");

						while (itr.hasNext()) {
							TransactionPojo transaction = itr.next();
							System.out.println(transaction.getTransactionId() + "\t\t" + transaction.getFromAccountId()
									+ "\t\t\t" + transaction.getToAccountId() + "\t\t"
									+ transaction.getAmountToTransfer() + "\t\t\t" + transaction.getCreated_on());
						}
						System.out.println(
								"*************************************************************************************************************");
						
					}
					
					if(option2 == 4) {
						employeeMenu = false;
					}

				}

				break;

			case 2:

				CustomerPojo foundCustomer = null;
				CustomerPojo fetchedCustomer = null;

				while (foundCustomer == null) {
					System.out.println("Enter Customer Email: ");
					String customerEmail = scan.next();

					try {
						foundCustomer = customerService.fetchOneCustomer(customerEmail);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					if (foundCustomer == null) {
						System.out.println("Please enter the proper email...");
					}

				}

				while (fetchedCustomer == null) {
					System.out.println("Enter Customer Password: ");
					String customerPassword = scan.next();

					try {
						fetchedCustomer = customerService.loginCustomer(foundCustomer.getEmail(), customerPassword);
					} catch (SystemException e) {
						LOG.error(e);
						System.out.println(e.getMessage());
					}

					if (fetchedCustomer == null) {
						System.out.println("Please enter the proper password...");
					} else {
						System.out.println("Login Successful!");

					}

				}

				System.out.println("*************************************");
				System.out.println("Customer ID: " + fetchedCustomer.getCustomerId());
				System.out.println(
						"Customer Name: " + fetchedCustomer.getFirstName() + " " + fetchedCustomer.getLastName());
				System.out.println("Customer Email: " + fetchedCustomer.getEmail());
				System.out.println("Customer Phone Number: " + fetchedCustomer.getPhoneNumber());
				System.out.println("Customer Balance: " + fetchedCustomer.getBalance());

				boolean customerMenu = true;

				while (customerMenu) {
					System.out.println("*************************************");
					System.out.println("Customer Menu");
					System.out.println("*************************************");
					System.out.println("1. List all Transactions");
					System.out.println("2. Create a Transfer");
					System.out.println("3. View Acount Details");
					System.out.println("4. Logout and Return to Main Menu");
					System.out.println("*************************************");

					List<Integer> customerListOptions = new ArrayList<>();
					customerListOptions.add(1);
					customerListOptions.add(2);
					customerListOptions.add(3);
					customerListOptions.add(4);

					int option2 = scan.nextInt();

					// make sure valid option is selected
					if (!customerListOptions.contains(option2)) {
						System.out.println("Please enter a valid menu option!");
					}

					if (option2 == 1) {

						List<TransactionPojo> allTransactions;
						allTransactions = null;

						try {
							allTransactions = customerService.fetchAllTransactions(fetchedCustomer.getCustomerId());
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						// iterate through all transactions
						Iterator<TransactionPojo> itr = allTransactions.iterator();

						System.out.println(
								"*************************************************************************************************************");
						System.out.println("Transaction List:");
						System.out.println("ID\tFROM ACCOUNT ID\t\tTO ACCOUNT ID\tAMOUNT TRANSFERRED\t\tCREATED ON");

						while (itr.hasNext()) {
							TransactionPojo transaction = itr.next();
							System.out.println(transaction.getTransactionId() + "\t\t" + transaction.getFromAccountId()
									+ "\t\t\t" + transaction.getToAccountId() + "\t\t"
									+ transaction.getAmountToTransfer() + "\t\t\t" + transaction.getCreated_on());
						}
						System.out.println(
								"*************************************************************************************************************");
					}

					if (option2 == 2) {

						TransactionPojo newTransaction = new TransactionPojo();
						TransactionPojo addedTransaction = new TransactionPojo();
						char tranOption = 'y';
						
						addedTransaction = null;
						
						

						while (addedTransaction == null && tranOption == 'y') {
							System.out.println("********************************************");
							scan.nextLine();

							System.out.println("Enter the Customer ID you want to send to: ");

							int toCustomerId = scan.nextInt();

							newTransaction.setToAccountId(toCustomerId);

							System.out.println("Enter the amount you would like to transfer: ");

							newTransaction.setAmountToTransfer(scan.nextInt());

							long newBalance = fetchedCustomer.getBalance() - newTransaction.getAmountToTransfer();

							newTransaction.setFromAccountId(fetchedCustomer.getCustomerId());

							try {
								if (customerService.fetchOneCustomer(toCustomerId) == null) {
									System.out.println("Customer ID does not exist! Please try again...");
									System.out.println("Would you like to try again? (y/n)");
									tranOption = scan.next().charAt(0);
								} else if (newBalance < 0) {
									System.out.println("There are not enough funds in your account! Please try again...");
									System.out.println("Would you like to try again? (y/n)");
									tranOption = scan.next().charAt(0);
									
								} else {

									scan.nextLine();

									addedTransaction = customerService.createNewTransaction(
											newTransaction.getFromAccountId(), newTransaction.getToAccountId(),
											newTransaction.getAmountToTransfer());

									System.out.println("Tansaction Successful!");

									System.out.println("New Transaction ID is: " + addedTransaction.getTransactionId());
									System.out.println("Your New Balance is: $" + newBalance);
									System.out
											.println("The transaction was created on: " + addedTransaction.getCreated_on());

									System.out.println();
								}
							} catch (SystemException e) {
								LOG.error(e);
								System.out.println(e.getMessage());
							}
						}
					}

					if (option2 == 3) {

						CustomerPojo customerInfo;
						customerInfo = null;

						try {
							customerInfo = customerService.fetchOneCustomer(fetchedCustomer.getCustomerId());
						} catch (SystemException e) {
							LOG.error(e);
							System.out.println(e.getMessage());
						}

						System.out.println("*************************************");
						System.out.println("Your Account Details: ");
						System.out.println("Customer ID: " + customerInfo.getCustomerId());
						System.out.println(
								"Customer Name: " + customerInfo.getFirstName() + " " + customerInfo.getLastName());
						System.out.println("Customer Email: " + customerInfo.getEmail());
						System.out.println("Customer Phone Number: " + customerInfo.getPhoneNumber());
						System.out.println("Customer Balance: $" + customerInfo.getBalance());

					}

					if (option2 == 4) {
						customerMenu = false;
					}

				}

				break;

			case 3:
				
				System.out.println("***********************************************");
				System.out.println("Exiting System...");
				System.out.println("Thank you for visiting the Bank!");
				System.out.println("***********************************************");

				scan.close();
				System.exit(0);
			}

			System.out.println("Do You Want to Continue? (y/n): ");
			ch = scan.next().charAt(0);
			scan.nextLine();

		}

		System.out.println("***********************************************");
		System.out.println("Exiting System...");
		System.out.println("Thank you for visiting the Bank!");
		System.out.println("***********************************************");

		scan.close();
		System.exit(0);

	}

}
