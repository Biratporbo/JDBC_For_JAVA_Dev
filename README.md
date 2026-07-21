# JDBC_For_JAVA_Dev

A beginner-friendly Java project that demonstrates how to connect to a MySQL database using JDBC and perform basic database operations.

## Overview
This project includes:
- A database connection utility in [JDBC_Learning/src/MainDB.java](JDBC_Learning/src/MainDB.java)
- An example insert operation in [JDBC_Learning/src/InsertData.java](JDBC_Learning/src/InsertData.java)

## Features
- Connects to a MySQL database using JDBC
- Uses `DriverManager` to establish a connection
- Demonstrates inserting data into a table with `PreparedStatement`

## Prerequisites
Before running the project, make sure you have:
- Java installed on your system
- MySQL installed and running
- A database named `testdb`
- A table named `users` with matching columns

## Setup
1. Update the database credentials in [JDBC_Learning/src/MainDB.java](JDBC_Learning/src/MainDB.java)
2. Compile the Java files
3. Run the program to test the database connection

## Usage
- Run [JDBC_Learning/src/MainDB.java](JDBC_Learning/src/MainDB.java) to test the connection
- Run [JDBC_Learning/src/InsertData.java](JDBC_Learning/src/InsertData.java) to insert sample data

## Notes
This project is intended for learning purposes and shows the basic flow of JDBC operations in Java.
