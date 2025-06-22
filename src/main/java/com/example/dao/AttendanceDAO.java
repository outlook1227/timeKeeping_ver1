package com.example.dao;

import com.example.model.Attendance;
import com.example.util.DatabaseConnection;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public static final String INSERT_ATTENDANCE_SQL = "INSERT INTO attendances (user_id, work_date, check_in, check_out, shift, note) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ATTENDANCE_BY_ID = "SELECT * FROM attendances WHERE id = ?";
    public static final String SELECT_ALL_ATTENDANCES = "SELECT * FROM attendances";
    public static final String DELETE_ATTENDANCE_SQL = "DELETE FROM attendances WHERE id = ?";
    public static final String UPDATE_ATTENDANCE_SQL = "UPDATE attendances SET user_id = ?, work_date = ?, check_in = ?, check_out = ?, shift = ?, note = ? WHERE id = ?";

    public static List<Attendance> getAll() {
        List<Attendance> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_ATTENDANCES); // Use constant
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insert(Attendance a) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(INSERT_ATTENDANCE_SQL); // Use constant
            ps.setInt(1, a.getUserId());
            ps.setDate(2, new java.sql.Date(a.getWorkDate().getTime()));
            ps.setTime(3, a.getCheckIn());
            ps.setTime(4, a.getCheckOut());
            ps.setString(5, a.getShift());
            ps.setString(6, a.getNote());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean update(Attendance a) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE attendances SET user_id=?, work_date=?, check_in=?, check_out=?, shift=?, note=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a.getUserId());
            ps.setDate(2, new java.sql.Date(a.getWorkDate().getTime()));
            ps.setTime(3, a.getCheckIn());
            ps.setTime(4, a.getCheckOut());
            ps.setString(5, a.getShift());
            ps.setString(6, a.getNote());
            ps.setInt(7, a.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM attendances WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static List<Attendance> getByWorkDate(Date workDate) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE work_date = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, workDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Attendance> getByWorkWeek(int week)
    {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE WEEK(work_date) = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, week);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Attendance> getByWorkMonth(int month) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE MONTH(work_date) = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Attendance> getByWorkYear(int year) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE YEAR(work_date) = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }    public static List<Attendance> getByUserId(int userId) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance(rs);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void saveAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendances (user_id, work_date, check_in, check_out, shift, note) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendance.getUserId());
            stmt.setDate(2, new java.sql.Date(attendance.getWorkDate().getTime()));
            stmt.setTime(3, attendance.getCheckIn());
            stmt.setTime(4, attendance.getCheckOut());
            stmt.setString(5, attendance.getShift());
            stmt.setString(6, attendance.getNote());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Attendance getById(int id) {
        Attendance attendance = null;
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(SELECT_ATTENDANCE_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                attendance = new Attendance(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }
}

