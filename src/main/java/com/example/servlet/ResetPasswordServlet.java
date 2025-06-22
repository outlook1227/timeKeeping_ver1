// ResetPasswordServlet.java
package com.example.servlet;

import com.example.services.EmployeeDetailsService;
import com.example.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        try {
            userService.updatePassword(username, newPassword);
            response.sendRedirect("welcome.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error resetting password", e);
        }
    }
}