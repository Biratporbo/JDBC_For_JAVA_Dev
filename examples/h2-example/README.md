# H2 JDBC Example

This is a minimal Maven project demonstrating JDBC usage with an in-memory H2 database.

Prerequisites:
- Java 11+
- Maven 3.x

Build and run:

```bash
cd examples/h2-example
mvn -q compile exec:java -Dexec.mainClass=com.example.h2.ExampleMain
```

What the example does:
- Creates a simple `accounts` table in an in-memory H2 database.
- Demonstrates an industry-style money transfer flow using JDBC transactions.
- Uses `PreparedStatement` for safe parameterized SQL and rollback on failure.
- Shows a DAO-style separation with `AccountDao` for reusable database operations.

Files:
- `pom.xml` — Maven project with H2 dependency and exec plugin.
- `src/main/java/com/example/h2/ExampleMain.java` — example program.

Next steps:
- Integrate this example into the repo README or add automated tests.
