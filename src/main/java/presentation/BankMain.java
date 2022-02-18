package presentation;

import java.util.Scanner;

import pojo.EmployeePojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class BankMain {

	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeServiceImpl();
		
		Scanner scan = new Scanner(System.in);
		
		char ch = 'y';
		
		while(ch == 'y') {
			
			System.out.println("****************************************");
			System.out.println("\tWelcome to the Bank");
			System.out.println("****************************************");
			System.out.println("1. Login Employee");
			System.out.println("2. Login Customer");
			System.out.println("3. Logout");
			System.out.println("4. Exit");
			System.out.println("****************************************");
			System.out.println("Please enter a menu option: ");
			
			int option = scan.nextInt();
			scan.nextLine();
			System.out.println("****************************************");
			
			switch(option) {
				
				case 1:
					System.out.println("Enter Employee Email: ");
					String employeeEmail = scan.next();
					
					EmployeePojo foundEmployee;
					
					foundEmployee = employeeService.fetchOneEmployee(employeeEmail);
					
					if(foundEmployee != null) {
						System.out.println(foundEmployee);
					}
					
					
					System.out.println("Enter Employee Password: ");
					String employeePassword = scan.next();
					
					EmployeePojo fetchedEmployee = employeeService.loginEmployee(foundEmployee.getEmail(), employeePassword);
					
					if(fetchedEmployee == null) {
						System.out.println("Please enter a the proper credentials...");
					}
					
					System.out.println("Login Successful!");
					System.out.println("********************************");
					System.out.println("Employee ID: " + fetchedEmployee.getEmployeeId());
					System.out.println("Employee Name: " + fetchedEmployee.getFirstName() + " " + fetchedEmployee.getLastName());
					System.out.println("Employee Email: " + fetchedEmployee.getEmail());
					System.out.println("Employee Phone Number: " + fetchedEmployee.getPhoneNumber());
					break;
					
				case 4:
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
