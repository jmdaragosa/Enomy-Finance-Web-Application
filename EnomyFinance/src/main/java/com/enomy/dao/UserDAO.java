package com.enomy.dao;

import java.sql.*;
import com.enomy.model.User;

public class UserDAO {

    private final String jdbcURL = "jdbc:mysql://localhost:3306/enomy_finance";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = ""; // Set password if needed

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // ✅ User Login Validation
    public User validate(String username, String password) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            System.out.println("🔌 Connected to DB successfully.");
            System.out.println("➡ Executing query for user: " + username);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setBalance(rs.getDouble("balance"));
                System.out.println("✅ User found: " + username);
                return user;
            } else {
                System.out.println("❌ No matching user found.");
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return null;
    }

    // ✅ Register New User
    public boolean registerUser(User user) {
        String sql = "INSERT INTO user (username, password, email, role) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // consider hashing
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole()); // 'client' by default

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ User registered successfully.");
                return true;
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("⚠ Username already exists.");
        } catch (SQLException e) {
            System.err.println("SQL Error during registration: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
