package com.example.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleMain {
    public static void main(String[] args) {
        try {
            System.out.println("=== Industry JDBC example ===");
            System.out.println(new IndustryJdbcExample().runDemo());

            System.out.println("=== CRUD example ===");
            System.out.println(new MoreH2Examples().runCrudDemo());

            System.out.println("=== Batch insert example ===");
            System.out.println(new MoreH2Examples().runBatchDemo());

            System.out.println("=== Join query example ===");
            System.out.println(new MoreH2Examples().runJoinDemo());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (id IDENTITY PRIMARY KEY, name VARCHAR(100), email VARCHAR(255))";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    private static int insertUser(Connection conn, String name, String email) throws SQLException {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }

    private static void queryUsers(Connection conn) throws SQLException {
        String sql = "SELECT id, name, email FROM users";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("-- users --");
            while (rs.next()) {
                System.out.printf("id=%d name=%s email=%s%n", rs.getInt("id"), rs.getString("name"), rs.getString("email"));
            }
        }
    }

    private static void updateUser(Connection conn, int id, String newName) throws SQLException {
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private static void deleteUser(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
