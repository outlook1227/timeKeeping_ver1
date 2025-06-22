package com.example.services;

import com.example.dao.EmployeeDetailsDAO;

public class EmployeeDetailsService {
    private EmployeeDetailsDAO employeeDetailsDAO;

    public EmployeeDetailsService() {
        this.employeeDetailsDAO = new EmployeeDetailsDAO();
    }
    public EmployeeDetailsService(EmployeeDetailsDAO employeeDetailsDAO) {
        this.employeeDetailsDAO = employeeDetailsDAO;
    }
    public void addEmployeeDetail(String username) {
        // Validate input
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        // Call DAO to insert employee detail
        employeeDetailsDAO.insertEmployeeDetail(username);
    }
}
