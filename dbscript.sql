CREATE DATABASE bank;

CREATE TABLE employees(employee_id SERIAL PRIMARY KEY, 
						password VARCHAR(50) NOT NULL, 
						first_name VARCHAR(50) NOT NULL,
						last_name VARCHAR(50) NOT NULL, 
						phone_number BIGINT NOT NULL,
						email VARCHAR(50) NOT NULL,
						created_on TIMESTAMP DEFAULT NOW() NOT NULL);
						
INSERT INTO employees(password, first_name, last_name, phone_number, email)
						VALUES('root', 'Mario', 'Sanchez', 2133923800, 'marios@usc.edu');
						
INSERT INTO employees(password, first_name, last_name, phone_number, email)
						VALUES('root2', 'John', 'Doe', 3235554789, 'john@gmail.com');

INSERT INTO employees(password, first_name, last_name, phone_number, email)
						VALUES('root3', 'Jane', 'Smith', 4563217799, 'jane@gmail.com');

CREATE TABLE customers(customer_id SERIAL PRIMARY KEY,
						password VARCHAR(50) NOT NULL,
						first_name VARCHAR(50) NOT NULL,
						last_name VARCHAR(50) NOT NULL,
						phone_number BIGINT NOT NULL,
						email VARCHAR(50) NOT NULL,
						balance BIGINT NOT NULL,
						employee_id INT NOT NULL,
						created_on TIMESTAMP DEFAULT NOW() NOT NULL);

INSERT INTO customers(password, first_name, last_name, phone_number, email, balance, employee_id)
						VALUES('4444', 'Heather', 'Lin', 2225554534, 'heather@gmail.com', 19000, 1);
						
INSERT INTO customers(password, first_name, last_name, phone_number, email, balance, employee_id)
						VALUES('1111', 'Sam', 'Farmer', 5559994568, 'sam@yahoo.com', 22000, 2);
						
INSERT INTO customers(password, first_name, last_name, phone_number, email, balance, employee_id)
						VALUES('6789', 'Nate', 'Rizzo', 4442367892, 'nate@yahoo.com', 9000, 3);
						
CREATE TABLE transaction_history(transaction_id SERIAL PRIMARY KEY,
									from_account_id INT NOT NULL,
									to_account_id INT NOT NULL,
									amount_to_transfer BIGINT NOT NULL,
									created_on TIMESTAMP DEFAULT NOW() NOT NULL);
									
									