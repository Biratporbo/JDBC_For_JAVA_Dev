package com.example.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MoreH2Examples {
    private static final String URL = "jdbc:h2:mem:more-examples;DB_CLOSE_DELAY=-1";

    public String runCrudDemo() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            createProductsTable(conn);
            insertProduct(conn, "Laptop", 1200.0);
            insertProduct(conn, "Smartphone", 800.0);
            updateProductPrice(conn, "Laptop", 1350.0);
            return listProducts(conn);
        }
    }

    public String runBatchDemo() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            createProductsTable(conn);
            insertBatchProducts(conn, List.of("Monitor", "Keyboard", "Mouse"));
            return "Inserted 3 rows";
        }
    }

    public String runJoinDemo() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            createCustomersTable(conn);
            createOrdersTable(conn);
            seedCustomersAndOrders(conn);
            return readCustomerOrders(conn);
        }
    }

    private void createProductsTable(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS products");
            st.execute("CREATE TABLE products (id IDENTITY PRIMARY KEY, name VARCHAR(100), price DECIMAL(10,2))");
        }
    }

    private void insertProduct(Connection conn, String name, double price) throws SQLException {
        String sql = "INSERT INTO products(name, price) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        }
    }

    private void updateProductPrice(Connection conn, String name, double price) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    private String listProducts(Connection conn) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT name, price FROM products ORDER BY id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append(rs.getString("name")).append("=").append(rs.getDouble("price")).append("\n");
            }
        }
        return sb.toString();
    }

    private void insertBatchProducts(Connection conn, List<String> names) throws SQLException {
        String sql = "INSERT INTO products(name, price) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (String name : names) {
                ps.setString(1, name);
                ps.setDouble(2, 19.99);
                ps.addBatch();
            }
            int[] counts = ps.executeBatch();
            if (counts.length != names.size()) {
                throw new SQLException("Batch execution did not insert expected rows");
            }
        }
    }

    private void createCustomersTable(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS customers");
            st.execute("CREATE TABLE customers (id IDENTITY PRIMARY KEY, name VARCHAR(100))");
        }
    }

    private void createOrdersTable(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("CREATE TABLE orders (id IDENTITY PRIMARY KEY, customer_id INT, product_name VARCHAR(100))");
        }
    }

    private void seedCustomersAndOrders(Connection conn) throws SQLException {
        insertCustomer(conn, "Alice");
        insertCustomer(conn, "Bob");
        insertOrder(conn, 1, "Laptop");
        insertOrder(conn, 1, "Mouse");
        insertOrder(conn, 2, "Keyboard");
    }

    private void insertCustomer(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO customers(name) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    private void insertOrder(Connection conn, int customerId, String productName) throws SQLException {
        String sql = "INSERT INTO orders(customer_id, product_name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setString(2, productName);
            ps.executeUpdate();
        }
    }

    private String readCustomerOrders(Connection conn) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT c.name, o.product_name FROM customers c JOIN orders o ON c.id = o.customer_id ORDER BY c.name, o.product_name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append(rs.getString("name")).append(" -> ").append(rs.getString("product_name")).append("\n");
            }
        }
        return sb.toString();
    }
}
