-- =========================================
-- JDBC Learning Studio - Database Setup
-- =========================================
-- This script creates the test database and tables needed for the JDBC Learning project.
-- Run this script in MySQL to set up your environment.

-- Query: Drop existing database if it exists (optional)
-- DROP DATABASE IF EXISTS testdb;

-- Query: Create the database
CREATE DATABASE testdb;

-- Query: Use the created database
USE testdb;

-- Query: Create the customer table
CREATE TABLE customer (
    ID INT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Age INT NOT NULL,
    City CHAR(50),
    Salary NUMERIC
);

-- Query: View all rows from customer
SELECT * FROM customer;

-- Query: View selected columns from customer
SELECT Name, City FROM customer;

-- Query: Rename customer table to users
RENAME TABLE customer TO users;

-- Query: Rename users table back to customer
RENAME TABLE users TO customer;

-- Query: Add a boolean column to customer
ALTER TABLE customer
ADD COLUMN is_active BOOLEAN DEFAULT TRUE;

-- Query: Drop the boolean column from customer
ALTER TABLE customer
DROP COLUMN is_active;

-- Query: Modify the Name column length
ALTER TABLE customer
MODIFY COLUMN Name VARCHAR(100);

-- Query: Add a Gender column with enum values
ALTER TABLE customer
ADD COLUMN Gender ENUM('Male', 'Female', 'Other');

-- Query: Insert sample records into customer
INSERT INTO customer(ID, Name, Age, City, Salary, Gender)
VALUES
    (101, 'Rohan', 27, 'Mumbai', 45000, 'Male'),
    (103, 'Trisha', 26, 'Kolkata', 35000, 'Female'),
    (104, 'Birat', 27, 'Delhi', 30000, 'Male'),
    (105, 'Rohit', 28, 'Odisha', 40000, 'Male'),
    (106, 'Ankita', 25, 'Kolkata', 30000, 'Female'),
    (107, 'Arijit', 26, 'Bangalore', 40000, 'Male'),
    (108, 'Sneha', 26, 'Chennai', 45000, 'Female');

-- Query: View all records from customer
SELECT * FROM customer;

-- Query: View names and cities from customer
SELECT Name, City FROM customer;

-- Query: Filter customers by gender
SELECT * FROM customer WHERE Gender = 'Male';

-- Query: Filter customers by salary threshold
SELECT * FROM customer WHERE Salary >= 35000;

-- Query: Filter customers by salary range
SELECT * FROM customer WHERE Salary BETWEEN 25000 AND 35000;

-- Query: Filter customers by multiple gender values
SELECT * FROM customer WHERE Gender IN ('Male', 'Other');

-- Query: Filter customers whose name starts with T
SELECT * FROM customer WHERE Name LIKE 'T%';

-- Query: Filter customers whose name ends with t
SELECT * FROM customer WHERE Name LIKE '%t';

-- Query: Order customers by name ascending
SELECT * FROM customer ORDER BY Name ASC;

-- Query: Order customers by name descending
SELECT * FROM customer ORDER BY Name DESC;

-- Query: Limit the number of displayed rows
SELECT * FROM customer LIMIT 10;

-- Query: Order customers by salary descending and limit results
SELECT * FROM customer ORDER BY Salary DESC LIMIT 10;

-- Query: Insert additional sample records into customer
INSERT INTO customer(ID, Name, Age, City, Salary, Gender)
VALUES
    (100, 'Priya', 24, 'Pune', 32000, 'Female'),
    (109, 'Amit', 29, 'Hyderabad', 50000, 'Male'),
    (110, 'Neha', 27, 'Jaipur', 38000, 'Female'),
    (111, 'Karan', 30, 'Ahmedabad', 55000, 'Male'),
    (112, 'Pooja', 25, 'Lucknow', 34000, 'Female'),
    (113, 'Rahul', 31, 'Patna', 48000, 'Male'),
    (114, 'Meera', 28, 'Surat', 42000, 'Female'),
    (115, 'Vikram', 32, 'Nagpur', 60000, 'Male'),
    (116, 'Anjali', 26, 'Bhopal', 36000, 'Female'),
    (117, 'Sourav', 29, 'Kolkata', 47000, 'Male'),
    (118, 'Nisha', 24, 'Indore', 31000, 'Female'),
    (119, 'Deepak', 27, 'Chandigarh', 45000, 'Male'),
    (120, 'Kavya', 23, 'Mysore', 29000, 'Female'),
    (121, 'Manish', 33, 'Noida', 65000, 'Male'),
    (122, 'Ritu', 28, 'Guwahati', 39000, 'Female'),
    (123, 'Abhishek', 30, 'Ranchi', 52000, 'Male'),
    (124, 'Simran', 27, 'Amritsar', 41000, 'Female'),
    (125, 'Harsh', 26, 'Visakhapatnam', 43000, 'Male');

-- Query: View all rows from users table
SELECT * FROM users;

-- Query: Rename customer table to users
RENAME TABLE customer TO users;

-- Query: Order users by salary in descending order
SELECT * FROM users ORDER BY Salary DESC;

-- Query: Filter users by salary range
SELECT * FROM users WHERE Salary BETWEEN 50000 AND 70000;

-- Query: View all users
SELECT * FROM users;

-- Query: Describe the users table structure
DESCRIBE users;

-- Query: Find a specific user by ID
SELECT * FROM users WHERE ID = 100;

-- Query: Select specific columns from users
SELECT ID, Name, Email FROM users;

-- Query: Count the number of users
SELECT COUNT(*) FROM users;

-- Query: Select user IDs and names
SELECT ID, Name FROM users;

-- Query: Show the CREATE TABLE statement for users
SHOW CREATE TABLE users;

-- Query: Drop the Email column from users
ALTER TABLE users DROP COLUMN Email;

-- Query: View all users after dropping the Email column
SELECT * FROM users;

-- =========================================
-- Notes:
-- 1. Update database credentials in MainDB.java if using different username/password
-- 2. Default MySQL credentials: username='root', password='root' (adjust as needed)
-- 3. Ensure MySQL server is running before executing this script
-- 4. You can remove sample data and keep only the table structure if you prefer
-- =========================================
