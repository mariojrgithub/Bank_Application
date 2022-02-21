package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import pojo.CustomerPojo;
import pojo.EmployeePojo;
import pojo.TransactionPojo;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class BankMain {

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

					foundEmployee = employeeService.fetchOneEmployee(employeeEmail);

					if (foundEmployee == null) {
						System.out.println("Please enter the proper email...");
					}

				}

				while (fetchedEmployee == null) {
					System.out.println("Enter Employee Password: ");
					String employeePassword = scan.next();

					fetchedEmployee = employeeService.loginEmployee(foundEmployee.getEmail(), employeePassword);

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
					System.out.println("3. Logout and Return to Main Menu");
					System.out.println("*************************************");

					List<Integer> employeeListOptions = new ArrayList<>();
					employeeListOptions.add(1);
					employeeListOptions.add(2);
					employeeListOptions.add(3);

					int option2 = scan.nextInt();

					// make sure valid option is selected
					if (!employeeListOptions.contains(option2)) {
						System.out.println("Please enter a valid menu option!");
					}

					if (option2 == 1) {

						List<CustomerPojo> allCustomers;

						allCustomers = employeeService.fetchAllCustomers();

						// iterate through all customers
						Iterator<CustomerPojo> itr = allCustomers.iterator();

						System.out.println("*************************************************************************");
						System.out.println("Customer List:");
						System.out.println("ID\tNAME\t\tPHONE\t\t\tEMAIL\t\tBALANCE");

						while (itr.hasNext()) {
							CustomerPojo customer = itr.next();
							System.out.println(customer.getCustomerId() + "\t" + customer.getFirstName() + " "
									+ customer.getLastName() + "\t" + customer.getPhoneNumber() + "\t"
									+ customer.getEmail() + "\t\t" + customer.getBalance());
						}
						System.out.println("*************************************************************************");
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
						System.out.println("Enter email: ");
						newCustomer.setEmail(scan.nextLine());
						System.out.println("Enter Balance ($): ");
						newCustomer.setBalance(scan.nextLong());
						scan.nextLine();

						CustomerPojo addedCustomer;

						addedCustomer = employeeService.createNewCustomer(newCustomer, fetchedEmployee.getEmployeeId());

						System.out.println("New Customer Name is: " + addedCustomer.getFirstName() + " "
								+ addedCustomer.getLastName());
						System.out.println("New Customer Phone Number is: " + addedCustomer.getPhoneNumber());
						System.out.println("New Customer Email is: " + addedCustomer.getEmail());
						System.out.println("New Customer Balance is: " + addedCustomer.getBalance());
						System.out.println("Your Employee ID is: " + fetchedEmployee.getEmployeeId());

						System.out.println();
					}

					if (option2 == 3) {
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

					foundCustomer = customerService.fetchOneCustomer(customerEmail);

					if (foundCustomer == null) {
						System.out.println("Please enter the proper email...");
					}

				}

				while (fetchedCustomer == null) {
					System.out.println("Enter Customer Password: ");
					String customerPassword = scan.next();

					fetchedCustomer = customerService.loginCustomer(foundCustomer.getEmail(), customerPassword);

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

						allTransactions = customerService.fetchAllTransactions();

						// iterate through all customers
						Iterator<TransactionPojo> itr = allTransactions.iterator();

						System.out.println("*************************************************************************************************************");
						System.out.println("Transaction List:");
						System.out.println("ID\tFROM ACCOUNT ID\t\tTO ACCOUNT ID\tAMOUNT TRANSFERRED\t\tCREATED ON");

						while (itr.hasNext()) {
							TransactionPojo transaction = itr.next();
							System.out.println(transaction.getTransactionId() + "\t\t" + transaction.getFromAccountId() + "\t\t\t" + transaction.getToAccountId() + "\t\t" + transaction.getAmountToTransfer()
									+ "\t\t\t" + transaction.getCreated_on());
						}
						System.out.println("*************************************************************************************************************");
					}
					
					if (option2 == 2) {
						
						System.out.println("********************************************");
						scan.nextLine();

						TransactionPojo newTransaction = new TransactionPojo();
						TransactionPojo addedTransaction = new TransactionPojo();

						System.out.println("Enter the Customer ID you want to send to: ");
						newTransaction.setToAccountId(scan.nextInt());
						System.out.println("Enter the amount you would like to transfer: ");
						newTransaction.setAmountToTransfer(scan.nextInt());
						
						newTransaction.setFromAccountId(fetchedCustomer.getCustomerId());
						
						scan.nextLine();

						addedTransaction = customerService.createNewTransaction(newTransaction.getFromAccountId(), newTransaction.getToAccountId(), newTransaction.getAmountToTransfer());

						System.out.println("New Transaction ID is: " + addedTransaction.getTransactionId());
						System.out.println("Your New Balance is: " + (fetchedCustomer.getBalance() - newTransaction.getAmountToTransfer()));
						System.out.println("The transaction was created on: " + addedTransaction.getCreated_on());

						System.out.println();
					}
					
					if(option2 == 3) {
						
						CustomerPojo customerInfo;

						customerInfo = customerService.fetchOneCustomer(fetchedCustomer.getCustomerId());

						System.out.println("*************************************");
						System.out.println("Your Account Details: ");
						System.out.println("Customer ID: " + customerInfo.getCustomerId());
						System.out.println(
								"Customer Name: " + customerInfo.getFirstName() + " " + customerInfo.getLastName());
						System.out.println("Customer Email: " + customerInfo.getEmail());
						System.out.println("Customer Phone Number: " + customerInfo.getPhoneNumber());
						System.out.println("Customer Balance: " + customerInfo.getBalance());
						
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
