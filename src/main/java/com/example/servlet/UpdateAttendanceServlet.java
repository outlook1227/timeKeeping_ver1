package com.example.servlet;
import com.example.dao.AttendanceDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.Attendance;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/UpdateAttendanceServlet")
public class UpdateAttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        Date workDate = Date.valueOf(request.getParameter("work_date"));
        Time checkIn = Time.valueOf(request.getParameter("check_in") + ":00");
        Time checkOut = Time.valueOf(request.getParameter("check_out") + ":00");
        String shift = request.getParameter("shift");
        String note = request.getParameter("note");

        Attendance attendance = new Attendance();
        attendance.setId(id);
        attendance.setUserId(userId);
        attendance.setWorkDate(workDate);
        attendance.setCheckIn(checkIn);
        attendance.setCheckOut(checkOut);
        attendance.setShift(shift);
        attendance.setNote(note);
        AttendanceDAO.update(attendance);
        response.sendRedirect("attendance.jsp");
    }
}
