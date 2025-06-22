package com.example.servlet;

import com.example.util.PythonApiCaller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestApi {
    @WebServlet("/runPython")
    public class RunPythonServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                // Command to execute run_both.py
                Process process = Runtime.getRuntime().exec("python c:\\test\\timeKeeping_ver1\\face_id\\main.py");

                // Wait for the process to complete
                process.waitFor();

                // Redirect back to the test.jsp page
                response.sendRedirect("test.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("Error running Python script: " + e.getMessage());
            }
        }
    }
}



