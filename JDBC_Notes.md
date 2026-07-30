# JDBC Comprehensive Notes

This document is a detailed study guide for JDBC (Java Database Connectivity). It covers architecture, API components, usage patterns, code examples, best practices, common pitfalls, and interview-relevant questions with concise answers. Use this as a study/book-style reference rather than a short cheat-sheet.

--

## 1. Introduction

JDBC (Java Database Connectivity) is Java's standard API for interacting with relational databases. JDBC provides a set of interfaces and classes in the `java.sql` (and `javax.sql`) packages that allow Java applications to send SQL statements to a database, process results, and manage transactions and connections.

Goals of JDBC:
- Provide a common interface to multiple relational databases.
- Abstract driver-specific details while allowing access to vendor features.

## 2. JDBC Architecture

- JDBC API: The interfaces that application code uses (e.g., `Connection`, `Statement`, `ResultSet`).
- JDBC Driver Manager: Loads and manages JDBC driver implementations.
- JDBC Driver: Vendor or third-party implementation that translates JDBC calls to database-specific protocol.

Driver Types (brief):
- Type 1: JDBC-ODBC bridge (legacy, rarely used).
- Type 2: Native-API partly Java driver.
- Type 3: Network protocol driver.
- Type 4: Pure Java driver (most common today).

## 3. Core Concepts and Lifecycle

Typical JDBC flow:
1. Load driver (modern drivers auto-register via service loader; explicit `Class.forName()` is optional).
2. Obtain a `Connection` via `DriverManager.getConnection(url, user, pass)` or a `DataSource` (preferred in enterprise apps).
3. Create a `Statement`, `PreparedStatement`, or `CallableStatement`.
4. Execute SQL (`executeQuery`, `executeUpdate`, `execute`).
5. Process `ResultSet` for queries.
6. Close `ResultSet`, `Statement`, and `Connection` (use try-with-resources).

Use `DataSource` and connection pooling for production. Avoid opening/closing connections per row.

## 4. Key Interfaces

- `DriverManager` / `Driver`
- `Connection`
- `Statement`, `PreparedStatement`, `CallableStatement`
- `ResultSet`, `ResultSetMetaData`
- `DatabaseMetaData`
- `DataSource` (from `javax.sql`)

## 5. Connection Management and Pooling

Opening a connection is expensive. Use connection pooling (HikariCP, Apache DBCP, C3P0) via `DataSource` for performance, resource control, and reliability.

Example of a pooled `DataSource` configuration is beyond the scope of raw JDBC: use container-managed pools in app servers or HikariCP for standalone apps.

## 6. Statements and Parameterization

- `Statement` — used for simple, static SQL. Avoid for user-provided input.
- `PreparedStatement` — precompiled SQL with parameters; helps prevent SQL injection and improves performance when reused.
- `CallableStatement` — for stored procedures.

Prepared statement example:

```java
String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setString(1, "Alice");
    ps.setString(2, "alice@example.com");
    ps.executeUpdate();
}
```

## 7. ResultSet

- `ResultSet` models rows returned by queries. Cursor moves with `next()`.
- Use appropriate getters (`getInt`, `getString`, etc.). Prefer column labels (`getString("name")`) for readability.
- Understand `ResultSet` concurrency and type options: forward-only, scrollable, read-only, updatable.

Example reading:

```java
try (Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery("SELECT id, name FROM users")) {
    while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        // process
    }
}
```

## 8. Transactions

- JDBC supports manual transaction control via `conn.setAutoCommit(false)`, `conn.commit()`, and `conn.rollback()`.
- Always rollback on exceptions to avoid leaving transactions open.
- Keep transactions short to reduce locking and contention.

Transaction example:

```java
conn.setAutoCommit(false);
try (PreparedStatement ps1 = conn.prepareStatement(...);
     PreparedStatement ps2 = conn.prepareStatement(...)) {
    // multiple updates
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
    throw e;
} finally {
    conn.setAutoCommit(true);
}
```

## 9. Batch Processing

Use `addBatch()` and `executeBatch()` for many similar DML statements to reduce round trips.

```java
try (PreparedStatement ps = conn.prepareStatement("INSERT INTO logs(msg) VALUES (?)")) {
    for (String m : messages) {
        ps.setString(1, m);
        ps.addBatch();
    }
    int[] results = ps.executeBatch();
}
```

## 10. Error Handling and Common Exceptions

- `SQLSyntaxErrorException` — syntax errors in SQL.
- `SQLIntegrityConstraintViolationException` — constraint violations (PK, FK, unique).
- `SQLTimeoutException` — query or connection timeout.

Use meaningful logging and include SQL + parameters (careful with secrets) for debugging.

## 11. Best Practices

- Always use try-with-resources to close `ResultSet`, `Statement`, and `Connection`.
- Use `PreparedStatement` for parameterized queries.
- Use connection pooling for production.
- Avoid `SELECT *` in production queries.
- Limit result set sizes with `LIMIT` / fetch size.
- Use appropriate transaction isolation level only when necessary.

## 12. Security Considerations

- Prevent SQL injection via `PreparedStatement` or stored procedures.
- Sanitize inputs used in dynamic ORDER BY or column selection (validate against whitelist).
- Never log raw credentials or sensitive data.

## 13. Example: Minimal CRUD Example (Standalone)

This is a simple set of examples demonstrating create/read/update/delete with `PreparedStatement`.

```java
// Create table assumed: users(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), email VARCHAR(255))

// Insert
String insert = "INSERT INTO users(name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, "Bob");
    ps.setString(2, "bob@example.com");
    ps.executeUpdate();
    try (ResultSet keys = ps.getGeneratedKeys()) {
        if (keys.next()) System.out.println("Inserted id: " + keys.getInt(1));
    }
}

// Read
String select = "SELECT id, name, email FROM users WHERE email = ?";
try (PreparedStatement ps = conn.prepareStatement(select)) {
    ps.setString(1, "bob@example.com");
    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            // process rows
        }
    }
}

// Update
String update = "UPDATE users SET name = ? WHERE id = ?";
try (PreparedStatement ps = conn.prepareStatement(update)) {
    ps.setString(1, "Bobby");
    ps.setInt(2, 1);
    ps.executeUpdate();
}

// Delete
String delete = "DELETE FROM users WHERE id = ?";
try (PreparedStatement ps = conn.prepareStatement(delete)) {
    ps.setInt(1, 1);
    ps.executeUpdate();
}
```

## 14. Advanced Topics (Short Overviews)

- RowSet — disconnected result sets (implementation: `CachedRowSet`). Useful for UI layers.
- XA transactions — distributed transaction support via `XADataSource` (for two-phase commit).
- Streaming large binary data (BLOB) — use `PreparedStatement.setBinaryStream` and read via streams to avoid large memory usage.
- ResultSet fetch size and statement fetch direction can hint drivers for performance.

## 15. Troubleshooting Tips

- Slow queries: check query plan, missing indexes, network latency.
- Connection leaks: use pool diagnostics and ensure `close()` always runs.
- Driver compatibility: ensure JDBC driver version matches your DB server version and Java version.

## 16. Interview Questions (Core and Advanced)

Core questions (short answers):

1. What is JDBC? — Java API to connect and execute queries against databases.
2. How to prevent SQL injection? — Use `PreparedStatement` or parameterized queries.
3. Difference between `Statement` and `PreparedStatement`? — `PreparedStatement` supports parameters and precompilation.
4. How to manage transactions in JDBC? — Use `setAutoCommit(false)`, then `commit()` or `rollback()`.
5. What is `DataSource`? — A factory for connections, often used with connection pools and JNDI.

Advanced questions (with brief guidance):

1. Explain connection pooling and why it matters.
- Pooling reuses physical connections to reduce overhead. Use HikariCP or container pools in production.

2. How does `ResultSet.TYPE_SCROLL_INSENSITIVE` differ from `TYPE_SCROLL_SENSITIVE`?
- Sensitive reflects DB changes; insensitive is a snapshot.

3. How to handle large result sets efficiently?
- Use server-side cursors, fetch size hints, or stream processing (driver-dependent).

4. Describe transaction isolation levels and trade-offs.
- READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE — stronger isolation reduces anomalies but increases locking and reduces concurrency.

5. Explain distributed transactions (XA) and when to use.
- Use when coordinating commits across multiple resources; requires XA-capable drivers and transaction manager.

6. How to debug connection leaks?
- Enable pool leak detection, ensure try-with-resources, and instrument stack traces where connections are created.

## 17. Self-Study Exercises and Mini-Projects

- Build a CLI app that runs CRUD operations against an embedded H2 database.
- Add connection pooling via HikariCP and measure throughput with and without pooling.
- Implement a small app that demonstrates transaction rollback on failure across two tables.

## 18. Further Reading & References

- Official JavaDocs for `java.sql` and `javax.sql`.
- Vendor JDBC driver docs (Postgres, MySQL, Oracle, SQL Server).
- HikariCP documentation and tuning guides.
