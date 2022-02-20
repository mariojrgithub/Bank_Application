package presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import pojo.CustomerPojo;
import pojo.EmployeePojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class BankMain {

	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();

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
			if(!mainListOptions.contains(option)) {
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

				 while(fetchedEmployee == null) {
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
					if(!employeeListOptions.contains(option2)) {
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
					
					if(option2 == 2) {
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
						
						System.out.println("New Customer Name is: " + addedCustomer.getFirstName() + " " + addedCustomer.getLastName());
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
