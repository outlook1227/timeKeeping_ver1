// UserDAO.java
package com.example.dao;

import com.example.model.User;
import com.example.util.BCryptUtil;
import com.example.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    private static final String INSERT_USERS_SQL = "INSERT INTO users (username, password, role, department) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";

    public void registerUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, BCryptUtil.hashPassword(user.getPassword()));
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getDepartment());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error registering user", e);
        }
    }

    public User validateUser(String username, String password) throws ClassNotFoundException {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next() && BCryptUtil.checkPassword(password, rs.getString("password"))) {
                user = new User(rs.getInt("id"), username, password, rs.getString("role"), rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updatePassword(String username, String newPassword) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, BCryptUtil.hashPassword(newPassword));
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error updating password", e);
        }
    }

    public User getUserById(int id) throws ClassNotFoundException {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}