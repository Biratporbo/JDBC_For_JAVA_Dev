<div align="center">
  <img src="https://skillicons.dev/icons?i=java,mysql,git,github,idea,postman" alt="Tech Stack" width="420" />

  <br>

  # ⚡ JDBC Learning Studio
  <strong>Building database interaction skills with Java, MySQL, and JDBC fundamentals.</strong>

  [![Java Version](https://img.shields.io/badge/Java_17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
  [![Database](https://img.shields.io/badge/MySQL-8.0-00758F?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
  [![JDBC](https://img.shields.io/badge/JDBC-DriverManager-6A5ACD?style=for-the-badge)](#)

  <p align="center">
    <a href="#-project-overview">Overview</a> •
    <a href="#-core-operations">Operations</a> •
    <a href="#-tech-stack">Stack</a> •
    <a href="#-getting-started">Getting Started</a>
  </p>
</div>

---

## 📦 Project Overview

This repository is a beginner-friendly Java learning project focused on understanding how applications connect to databases using JDBC. It demonstrates the essential flow of database communication with MySQL and shows how CRUD-style operations can be implemented cleanly in Java.

### What this project covers
- Database connection setup with `DriverManager`
- Data insertion using `PreparedStatement`
- Data retrieval, update, and delete examples
- A menu-driven `UserManagementSystem` example for practicing full CRUD workflow
- A sample execution log in [JDBC_Learning/src/UserManagementSystemOutput.txt](JDBC_Learning/src/UserManagementSystemOutput.txt)
- Structured Java files for learning the JDBC workflow step by step

---

## 🏗️ Learning Architecture

The project is intentionally simple and educational. Each Java file represents a focused JDBC operation, making it easy to study how Java talks to a relational database.

> [!IMPORTANT]
> This repository is designed as a hands-on lab for Java + SQL integration. It helps learners understand the practical flow of connecting, querying, and modifying records in a MySQL database.

---

## 🔍 Core Operations

The repository demonstrates the most common database interactions:

- `InsertData.java` — inserts new records into the database
- `DisplayData.java` — reads and displays existing records
- `SearchData.java` — searches for specific data
- `UpdateData.java` — updates data in the table
- `DeleteData.java` — removes records from the database
- `UserManagementSystem.java` — a menu-based user CRUD application built on top of JDBC
- `MainDB.java` — central database connection setup

---

## 📂 Project Breakdown

| File | Purpose | Learning Focus |
| :--- | :--- | :--- |
| `MainDB.java` | Shared database connection logic | JDBC connectivity |
| `InsertData.java` | Add new rows | `PreparedStatement` |
| `DisplayData.java` | Read and print rows | Result retrieval |
| `SearchData.java` | Query by criteria | Filtering records |
| `UpdateData.java` | Modify existing entries | Data mutation |
| `DeleteData.java` | Remove records | Record deletion |
| `UserManagementSystem.java` | Menu-driven CRUD system | End-to-end JDBC practice |
| `UserManagementSystemOutput.txt` | Sample program output | Reference for expected console behavior |

---

## 🛠️ Tech Stack

<div align="center">

**Core** <br>
![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=flat-square&logo=openjdk&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-API-6A5ACD?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-Database-00758F?style=flat-square&logo=mysql&logoColor=white)

**Development Tools** <br>
![VS Code](https://img.shields.io/badge/VS%20Code-0078D4?style=flat-square&logo=visual%20studio%20code&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=flat-square&logo=intellij-idea&logoColor=white)
![Git](https://img.shields.io/badge/Git-%23F05033.svg?style=flat-square&logo=git&logoColor=white)

</div>

---

## 🚀 Getting Started

### Prerequisites
Before running the project, make sure you have:
- Java installed on your system
- MySQL installed and running
- A database named `testdb`
- A table named `users` with matching columns
- The SQL setup script available at [sql/SetUp.sql](sql/SetUp.sql)

### Setup
1. Open and run [sql/SetUp.sql](sql/SetUp.sql) in MySQL to create the database and sample tables
2. Update the database credentials inside `MainDB.java`
3. Compile the Java source files
4. Run the desired program to test the JDBC operation

### Run Examples
```bash
javac JDBC_Learning/src/*.java
java -cp JDBC_Learning/src MainDB
java -cp JDBC_Learning/src UserManagementSystem
```

You can then run the individual files such as:
- `InsertData`
- `DisplayData`
- `SearchData`
- `UpdateData`
- `DeleteData`
- `UserManagementSystem` for a complete interactive CRUD flow
- View [JDBC_Learning/src/UserManagementSystemOutput.txt](JDBC_Learning/src/UserManagementSystemOutput.txt) for a sample run output

---

## ✨ Repository Highlights

- 🧠 Beginner-friendly JDBC flow for database learning
- 🔌 Uses `DriverManager` and `PreparedStatement` for practical examples
- 🧾 Demonstrates both read and write database operations
- 🧑‍💻 Includes an interactive `UserManagementSystem` for CRUD practice
- 📚 Organized into simple, focused Java source files

---

## 🧭 Future Learning Path

This project can be extended with:
- Connection pooling
- Exception handling improvements
- DAO design pattern
- Maven or Gradle project structure
- Advanced SQL + JDBC optimization examples

---

## 🤝 Contribution

This repository is meant for learning and exploration. If you want to improve the examples, add new database operations, or document the workflow more clearly, pull requests are welcome.

---

## 🔗 Connect

<div align="center">
  <a href="https://github.com/">
    <img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" alt="GitHub Badge"/>
  </a>
</div>

---

> "Code is a lot like coffee—it's always best when it's kept clean, simple, and actually works. Happy coding!" ☕✨

---

Built with ❤️ for Java and database learning.
