<%@ page import="com.example.dao.AttendanceDAO" %>
<%@ page import="com.example.model.Attendance" %>
<%
  int id = Integer.parseInt(request.getParameter("id"));
  Attendance attendance = AttendanceDAO.getById(id);
%>
<head>
  <title>Edit Attendance</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }

    body {
      min-height: 100vh;
      background: linear-gradient(135deg, #f3f4f6 0%, #ddd6fe 100%);
      padding: 20px;
      display: flex;
      align-items: center;
      justify-content: center;
    }

    .container {
      background: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      width: 100%;
      max-width: 500px;
    }

    h2 {
      color: #4f46e5;
      text-align: center;
      margin-bottom: 30px;
      font-size: 24px;
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      color: #4b5563;
      font-weight: bold;
    }

    input[type="number"],
    input[type="date"],
    input[type="time"],
    input[type="text"] {
      width: 100%;
      padding: 10px;
      border: 2px solid #e5e7eb;
      border-radius: 6px;
      font-size: 16px;
      transition: border-color 0.3s ease;
    }

    input:focus {
      outline: none;
      border-color: #4f46e5;
      box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
    }

    button {
      width: 100%;
      padding: 12px;
      background: #4f46e5;
      color: white;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      font-weight: bold;
      cursor: pointer;
      transition: all 0.3s ease;
    }

    button:hover {
      background: #4338ca;
      transform: translateY(-1px);
    }

    button:active {
      transform: translateY(0);
    }

    @media (max-width: 640px) {
      .container {
        padding: 20px;
      }
    }
  </style>
</head>

<body>
<form method="post" action="UpdateAttendanceServlet">
  <input type="hidden" name="id" value="<%= attendance.getId() %>">
  <label>User ID:</label>
  <input type="number" name="user_id" value="<%= attendance.getUserId() %>"><br>
  <label>Work Date:</label>
  <input type="date" name="work_date" value="<%= attendance.getWorkDate() %>"><br>
  <label>Check In:</label>
  <input type="time" name="check_in" value="<%= attendance.getCheckIn() %>"><br>
  <label>Check Out:</label>
  <input type="time" name="check_out" value="<%= attendance.getCheckOut() %>"><br>
  <label>Shift:</label>
  <input type="text" name="shift" value="<%= attendance.getShift() %>"><br>
  <label>Note:</label>
  <input type="text" name="note" value="<%= attendance.getNote() %>"><br>
  <button type="submit">Update</button>
</form>
</body>