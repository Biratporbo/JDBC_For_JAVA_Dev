package com.example.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IndustryJdbcExample {

    private static final String URL = "jdbc:h2:mem:industrydb;DB_CLOSE_DELAY=-1";
    private final AccountDao accountDao = new AccountDao();

    public String runDemo() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            initializeSchema(conn);
            accountDao.createAccount(conn, 1001, 1000.00);
            accountDao.createAccount(conn, 1002, 500.00);

            transferMoney(conn, 1001, 1002, 200.00);
            return readBalances(conn);
        }
    }

    private void initializeSchema(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.execute("DROP TABLE IF EXISTS accounts");
            st.execute("CREATE TABLE accounts (account_id INT PRIMARY KEY, balance DECIMAL(10,2))");
        }
    }

    private void createAccount(Connection conn, int accountId, double balance) throws SQLException {
        String sql = "INSERT INTO accounts(account_id, balance) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setDouble(2, balance);
            ps.executeUpdate();
        }
    }

    private void transferMoney(Connection conn, int fromAccount, int toAccount, double amount) throws SQLException {
        conn.setAutoCommit(false);
        try {
            debit(conn, fromAccount, amount);
            credit(conn, toAccount, amount);
            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private void debit(Connection conn, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    private void credit(Connection conn, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    private String readBalances(Connection conn) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT account_id, balance FROM accounts ORDER BY account_id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                sb.append("account=").append(rs.getInt("account_id"))
                  .append(", balance=").append(rs.getDouble("balance")).append("\n");
            }
        }
        sb.append("Transfer complete");
        return sb.toString();
    }
}
