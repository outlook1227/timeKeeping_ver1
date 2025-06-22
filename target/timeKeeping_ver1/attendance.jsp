<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 20/04/2025
  Time: 6:22 CH
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="java.util.List" %>
            <%@ page import="com.example.dao.AttendanceDAO" %>
                <%@ page import="com.example.model.Attendance" %>
                    <%@ page import="java.sql.Date" %>
                        <html>

                        <head>
                            <title>Attendance Management</title>
                            <style>
                                 :root {
                                    --primary-color: #4f46e5;
                                    --primary-hover: #4338ca;
                                    --bg-color: #f8fafc;
                                    --text-color: #1e293b;
                                    --border-color: #e2e8f0;
                                    --header-bg: #eef2ff;
                                    --table-stripe: #f8fafc;
                                    --shadow: rgba(0, 0, 0, 0.1);
                                }
                                
                                * {
                                    margin: 0;
                                    padding: 0;
                                    box-sizing: border-box;
                                }
                                
                                body {
                                    font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
                                    background-color: var(--bg-color);
                                    color: var(--text-color);
                                    line-height: 1.5;
                                    padding: 2rem;
                                }
                                
                                header {
                                    background: var(--primary-color);
                                    color: white;
                                    padding: 1rem 2rem;
                                    display: flex;
                                    justify-content: space-between;
                                    align-items: center;
                                    box-shadow: 0 2px 5px var(--shadow);
                                    margin-bottom: 2rem;
                                }
                                
                                header h1 {
                                    margin: 0;
                                    font-size: 1.5rem;
                                }
                                
                                nav ul {
                                    list-style: none;
                                    display: flex;
                                    gap: 1rem;
                                }
                                
                                nav ul li {
                                    margin: 0;
                                }
                                
                                nav ul li a {
                                    color: white;
                                    text-decoration: none;
                                    font-size: 1rem;
                                    transition: color 0.2s;
                                }
                                
                                nav ul li a:hover {
                                    color: #ffcc00;
                                }
                                
                                h1 {
                                    color: var(--primary-color);
                                    margin-bottom: 2rem;
                                    font-size: 2rem;
                                    text-align: center;
                                }
                                /* Filter Form Styles */
                                
                                form {
                                    background: white;
                                    padding: 1.5rem;
                                    border-radius: 8px;
                                    box-shadow: 0 2px 4px var(--shadow);
                                    margin-bottom: 2rem;
                                    display: grid;
                                    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                                    gap: 1rem;
                                }
                                
                                .form-group {
                                    display: flex;
                                    flex-direction: column;
                                }
                                
                                label {
                                    font-weight: 500;
                                    margin-bottom: 0.5rem;
                                    color: var(--text-color);
                                }
                                
                                input[type="date"],
                                input[type="number"] {
                                    padding: 0.5rem;
                                    border: 1px solid var(--border-color);
                                    border-radius: 4px;
                                    font-size: 0.95rem;
                                }
                                
                                button {
                                    background-color: var(--primary-color);
                                    color: white;
                                    padding: 0.5rem 1rem;
                                    border: none;
                                    border-radius: 4px;
                                    cursor: pointer;
                                    font-weight: 500;
                                    transition: background-color 0.2s;
                                    align-self: flex-end;
                                }
                                
                                button:hover {
                                    background-color: var(--primary-hover);
                                }
                                /* Table Styles */
                                
                                .table-container {
                                    overflow-x: auto;
                                    background: white;
                                    border-radius: 8px;
                                    box-shadow: 0 2px 4px var(--shadow);
                                }
                                
                                table {
                                    width: 100%;
                                    border-collapse: collapse;
                                    min-width: 800px;
                                }
                                
                                th,
                                td {
                                    padding: 1rem;
                                    text-align: left;
                                    border-bottom: 1px solid var(--border-color);
                                }
                                
                                th {
                                    background-color: var(--header-bg);
                                    font-weight: 600;
                                    color: var(--primary-color);
                                }
                                
                                tr:nth-child(even) {
                                    background-color: var(--table-stripe);
                                }
                                
                                tr:hover {
                                    background-color: #f1f5f9;
                                }
                                /* Responsive Design */
                                
                                @media (max-width: 768px) {
                                    body {
                                        padding: 1rem;
                                    }
                                    form {
                                        grid-template-columns: 1fr;
                                    }
                                    .table-container {
                                        margin: 0 -1rem;
                                        border-radius: 0;
                                    }
                                    h1 {
                                        font-size: 1.75rem;
                                    }
                                }
                                /* Animations */
                                
                                @keyframes fadeIn {
                                    from {
                                        opacity: 0;
                                        transform: translateY(10px);
                                    }
                                    to {
                                        opacity: 1;
                                        transform: translateY(0);
                                    }
                                }
                                
                                .table-container {
                                    animation: fadeIn 0.3s ease-out;
                                }
                                /* Custom Scrollbar */
                                
                                .table-container::-webkit-scrollbar {
                                    height: 8px;
                                }
                                
                                .table-container::-webkit-scrollbar-track {
                                    background: #f1f1f1;
                                }
                                
                                .table-container::-webkit-scrollbar-thumb {
                                    background: var(--primary-color);
                                    border-radius: 4px;
                                }
                                
                                .table-container::-webkit-scrollbar-thumb:hover {
                                    background: var(--primary-hover);
                                }
                            </style>

                        </head>

                        <body>
                            <header>
                                <h1>Time Keeping System</h1>
                                <nav>
                                    <ul>
                                        <li><a href="home.jsp">Home</a></li>
                                        <li><a href="attendance_form.jsp">Add Attendance</a></li>
                                        <li><a href="login.jsp">Sign out</a></li>
                                    </ul>
                                </nav>
                            </header>
                            <h1>Attendance Records</h1>
                            <form method="get" action="attendance.jsp">
                                <label for="filterDate">Filter by Work Date:</label>
                                <input type="date" id="filterDate" name="work_date">
                                <label for="filterMonth">Filter by Work Month:</label>
                                <input type="number" id="filterMonth" name="work_month" min="1" max="12">
                                <label for="filterWeek">Filter by Work Week:</label>
                                <input type="number" id="filterWeek" name="work_week" min="1" max="52">
                                <label for="filterYear">Filter by Work Year:</label>
                                <input type="number" id="filterYear" name="work_year" min="2000" max="2100">
                                <label for="filterUserId">Filter by User ID:</label>
                                <input type="number" id="filterUserId" name="user_id" min="1">
                                <button type="submit"><b>Filter</b></button>

                            </form>

                            <table>

                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>User ID</th>
                                        <th>Work Date</th>
                                        <th>Check In</th>
                                        <th>Check Out</th>
                                        <th>Shift</th>
                                        <th>Note</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                String filterDate = request.getParameter("work_date");
                AttendanceDAO attendanceDAO = new AttendanceDAO();
                List<Attendance> attendanceList;

                if (filterDate != null && !filterDate.isEmpty()) {
                    Date date = Date.valueOf(filterDate);
                    attendanceList = attendanceDAO.getByWorkDate(date);
                }
                else if (request.getParameter("work_week") != null && !request.getParameter("work_week").isEmpty()) {
                    int week = Integer.parseInt(request.getParameter("work_week"));
                    attendanceList = attendanceDAO.getByWorkWeek(week);
                }
                else if (request.getParameter("work_month") != null && !request.getParameter("work_month").isEmpty()) {
                    int month = Integer.parseInt(request.getParameter("work_month"));
                    attendanceList = attendanceDAO.getByWorkMonth(month);
                }
                else if (request.getParameter("work_year") != null && !request.getParameter("work_year").isEmpty()) {
                    int year = Integer.parseInt(request.getParameter("work_year"));
                    attendanceList = attendanceDAO.getByWorkYear(year);
                }
                else if (request.getParameter("user_id") != null && !request.getParameter("user_id").isEmpty()) {
                    int userId = Integer.parseInt(request.getParameter("user_id"));
                    attendanceList = attendanceDAO.getByUserId(userId);
                }

                else {
                    attendanceList = attendanceDAO.getAll();
                }

                for (Attendance att : attendanceList) {
            %>
                                        <tr>
                                            <td>
                                                <%= att.getId() %>
                                            </td>
                                            <td>
                                                <%= att.getUserId() %>
                                            </td>
                                            <td>
                                                <%= att.getWorkDate() %>
                                            </td>
                                            <td>
                                                <%= att.getCheckIn() %>
                                            </td>
                                            <td>
                                                <%= att.getCheckOut() %>
                                            </td>
                                            <td>
                                                <%= att.getShift() %>
                                            </td>
                                            <td>
                                                <%= att.getNote() %>
                                            </td>

                                            <td>
                                                <form method="post" action="DeleteAttendanceServlet">
                                                    <input type="hidden" name="id" value="<%= att.getId() %>">
                                                    <button type="submit">Delete</button>
                                                </form>
                                            </td>
                                            <td>
                                                <form method="get" action="edit_attendance.jsp">
                                                    <input type="hidden" name="id" value="<%= att.getId() %>">
                                                    <button type="submit">Edit</button>
                                                </form>
                                            </td>
                                        </tr>
                                        <%
                    }
            %>

                                </tbody>
                            </table>
                        </body>

                        </html>
