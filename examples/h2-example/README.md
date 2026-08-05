# H2 JDBC Example

This is a minimal Maven project demonstrating JDBC usage with an in-memory H2 database.

## Prerequisites
- Java 11+
- Maven 3.x

## Build and run

```bash
cd examples/h2-example
mvn -q compile exec:java -Dexec.mainClass=com.example.h2.ExampleMain
```

## Run the tests

```bash
cd examples/h2-example
mvn -q test
```

## What this example does
- Creates a simple `accounts` table in an in-memory H2 database.
- Demonstrates an industry-style money transfer flow using JDBC transactions.
- Uses `PreparedStatement` for safe parameterized SQL and rollback on failure.
- Shows a DAO-style separation with `AccountDao` for reusable database operations.
- Adds extra demos for CRUD operations, batch inserts, and joins.

## Learning objectives
By the end of this example, you should understand:
- how to connect Java to a database with JDBC
- how to run basic CRUD operations
- how transactions work in real-world flows
- why `PreparedStatement` is safer than plain SQL strings
- how DAO-style code helps organize database logic

## Key JDBC concepts covered
- `Connection`
- `PreparedStatement`
- `ResultSet`
- `SQLException`
- transactions and rollback
- batch operations

## Project structure
- `pom.xml` — Maven project with H2 dependency and the exec plugin.
- `src/main/java/com/example/h2/ExampleMain.java` — main demo runner.
- `src/main/java/com/example/h2/AccountDao.java` — DAO class for database operations.
- `src/main/java/com/example/h2/IndustryJdbcExample.java` — transaction-based money transfer example.
- `src/main/java/com/example/h2/MoreH2Examples.java` — additional JDBC examples.
- `src/test/java/com/example/h2/` — test classes for the demos.

## Expected output
When you run the main example, you should see output showing:
- table creation
- account setup
- money transfer success or rollback behavior
- sample CRUD or batch operations

## Troubleshooting
- If `mvn` is not recognized, install Maven and add it to your PATH.
- If Java is not recognized, install a JDK and verify `java -version`.
- If the build fails, make sure you are inside the `examples/h2-example` folder when running Maven commands.

## Next learning steps
- Add a small REST API around these JDBC examples.
- Learn connection pooling with HikariCP.
- Move to a DAO/service architecture for larger projects.
- Explore Spring Boot with JDBC templates.
