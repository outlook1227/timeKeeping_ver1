package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class Attendance {
    private int id;
    private int userId;
    private Date workDate;
    private Time checkIn;
    private Time checkOut;
    private String shift;
    private String note;

    public Attendance(int id, int userId, Date workDate, Time checkIn, Time checkOut, String shift, String note) {
        this.id = id;
        this.userId = userId;
        this.workDate = workDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.shift = shift;
        this.note = note;
    }

    public Attendance() {
    }

    public Attendance(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.userId = rs.getInt("user_id");
        this.workDate = rs.getDate("work_date");
        this.checkIn = rs.getTime("check_in");
        this.checkOut = rs.getTime("check_out");
        this.shift = rs.getString("shift");
        this.note = rs.getString("note");
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId; // Fixed missing implementation
    }
    public int getUserId() {
        return userId;
    }
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }
    public Date getWorkDate() {
        return workDate;
    }
    public void setCheckIn(Time checkIn) {
        this.checkIn = checkIn;
    }
    public Time getCheckIn() {
        return checkIn;
    }
    public void setCheckOut(Time checkOut) {
        this.checkOut = checkOut;
    }
    public Time getCheckOut() {
        return checkOut;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
    public String getShift() {
        return shift;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getNote() {
        return note;
    }


    // Getters và setters ở đây
}
