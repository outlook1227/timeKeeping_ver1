package com.example.servlet;

import com.example.dao.AttendanceDAO;
import com.example.model.Attendance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebServlet("/api/attendances")
public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Pattern TIME_PATTERN = Pattern.compile("^\\d{2}:\\d{2}:\\d{2}$");

    // Lấy danh sách chấm công
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Attendance> list = AttendanceDAO.getAll();
        response.setContentType("application/json");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Thêm mới chấm công
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkInParam = request.getParameter("check_in");
        String checkOutParam = request.getParameter("check_out");

        if (!isValidTimeFormat(checkInParam)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid check-in time format. Expected HH:mm:ss.\"}");
            return;
        }

        if (!isValidTimeFormat(checkOutParam)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid check-out time format. Expected HH:mm:ss.\"}");
            return;
        }

        Attendance att = new Attendance();
        att.setUserId(Integer.parseInt(request.getParameter("user_id")));
        att.setWorkDate(Date.valueOf(request.getParameter("work_date")));
        att.setCheckIn(Time.valueOf(checkInParam));
        att.setCheckOut(Time.valueOf(checkOutParam));
        att.setShift(request.getParameter("shift"));
        att.setNote(request.getParameter("note"));

        boolean success = AttendanceDAO.insert(att);
        response.getWriter().write(success ? "success" : "error");
    }

    private boolean isValidTimeFormat(String time) {
        return time != null && TIME_PATTERN.matcher(time).matches();
    }

    // Cập nhật chấm công
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        String json = reader.lines().collect(Collectors.joining());
        response.getWriter().write(new ObjectMapper().writeValueAsString(json));
        Attendance att = new ObjectMapper().readValue(json, Attendance.class);

        boolean success = AttendanceDAO.update(att);
        response.setContentType("application/json");
        response.getWriter().write(success ? "{\"status\":\"updated\"}" : "{\"status\":\"error\"}");
    }

    // Xóa chấm công
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean success = AttendanceDAO.delete(id);
        response.getWriter().write(success ? "deleted" : "error");
    }
}
