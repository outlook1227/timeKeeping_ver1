// UserService.java
package com.example.services;

import com.example.dao.UserDAO;
import com.example.model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) throws SQLException, ClassNotFoundException {
        userDAO.registerUser(user);
    }
    public User validateUser(String username, String password) throws ClassNotFoundException {
        return userDAO.validateUser(username, password);
    }

    public void updatePassword(String username, String newPassword) throws SQLException, ClassNotFoundException {
        userDAO.updatePassword(username, newPassword);
    }
}