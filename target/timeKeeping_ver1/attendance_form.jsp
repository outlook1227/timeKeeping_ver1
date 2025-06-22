<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20/04/2025
  Time: 6:22 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Attendance</title>
    <style>
        form {
            width: 50%;
            margin: auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input, select, textarea {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
        }
        button {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Add Attendance Record</h1>
<form action="processAttendance" method="post">
    <label for="user_id">User ID:</label>
    <input type="number" id="user_id" name="user_id" required>

    <label for="work_date">Work Date:</label>
    <input type="date" id="work_date" name="work_date" required>

    <label for="check_in">Check In:</label>
    <input type="time" id="check_in" name="check_in" required>

    <label for="check_out">Check Out:</label>
    <input type="time" id="check_out" name="check_out" required>

    <label for="shift">Shift:</label>
    <select id="shift" name="shift" required>
        <option value="Morning">Morning</option>
        <option value="Afternoon">Afternoon</option>
        <option value="Night">Night</option>
    </select>

    <label for="note">Note:</label>
    <textarea id="note" name="note" rows="4"></textarea>

    <button type="submit">Submit</button>
</form>
</body>
</html>
