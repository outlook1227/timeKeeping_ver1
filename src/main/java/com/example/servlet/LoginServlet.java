// File: src/main/java/com/example/servlet/LoginServlet.java
package com.example.servlet;

import com.example.model.User;
import com.example.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = userService.validateUser(username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (user != null) {
            response.sendRedirect("attendance.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}