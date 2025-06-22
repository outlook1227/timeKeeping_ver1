package com.example.servlet;
import com.example.dao.AttendanceDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;


import com.example.model.Attendance;

@WebServlet("/processAttendance")
public class ProcessAttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String workDate = request.getParameter("work_date");
        String checkIn = request.getParameter("check_in");
        String checkOut = request.getParameter("check_out");
        String shift = request.getParameter("shift");
        String note = request.getParameter("note");

        // Save to database (example)
        Attendance attendance = new Attendance();
        attendance.setUserId(userId);
        attendance.setWorkDate(Date.valueOf(workDate));
        attendance.setCheckIn(Time.valueOf(checkIn + ":00"));
        attendance.setCheckOut(Time.valueOf(checkOut + ":00"));
        attendance.setShift(shift);
        attendance.setNote(note);

        // Call DAO to save attendance (implement DAO logic)
        AttendanceDAO dao = new AttendanceDAO();
        dao.saveAttendance(attendance);

        response.sendRedirect("attendance_form.jsp");
    }
}