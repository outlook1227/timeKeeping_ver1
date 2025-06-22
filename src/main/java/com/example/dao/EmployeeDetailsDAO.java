package com.example.dao;

import com.example.model.EmployeeDetails;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDetailsDAO {
    public static final String INSERT_USERS_SQL = "INSERT INTO employeedetails (username) VALUES (?)";
    public static final String SELECT_USER_BY_ID = "SELECT id, username, password, role, department FROM employeedetails WHERE id = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM employeedetails";
    public static final String DELETE_USERS_SQL = "DELETE FROM employeedetails WHERE id = ?";
    public static final String UPDATE_USERS_SQL = "UPDATE employeedetails SET username = ?, password = ?, role = ?, department = ? WHERE id = ?";
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employeedetails (username, fullname, dateofbirth, address, hiredate, profilepicture) VALUES (?, ?, ?, ?, ?, ?)";

    public void addEmployeeDetail(EmployeeDetails employeeDetail) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, employeeDetail.getUserName());
            preparedStatement.setString(2, employeeDetail.getFullName());
            preparedStatement.setString(3, employeeDetail.getDateOfBirth());
            preparedStatement.setString(4, employeeDetail.getAddress());
            preparedStatement.setString(5, employeeDetail.getHireDate());
            preparedStatement.setString(6, employeeDetail.getProfilePicture());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEmployeeDetail(int id, String username, String password, String role, String department) {
        // Implementation for updating employee details
    }
    public void deleteEmployeeDetail(int id) {
        // Implementation for deleting employee details
    }

    public void insertEmployeeDetail(String username) {
    }
}

