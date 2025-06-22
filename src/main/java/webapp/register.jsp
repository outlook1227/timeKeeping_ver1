<html>

<head>
    <title>Register</title>
</head>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6fa;
            margin: 0;
            padding: 0;
        }
        
        h2 {
            text-align: center;
            color: #333;
        }
        
        form {
            background: #fff;
            max-width: 350px;
            margin: 40px auto;
            padding: 30px 25px 20px 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }
        
        label {
            display: block;
            margin-bottom: 6px;
            color: #222;
            font-weight: 500;
        }
        
        input[type="text"],
        input[type="password"],
        select {
            width: 100%;
            padding: 8px 10px;
            margin-bottom: 18px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 15px;
        }
        
        input[type="submit"] {
            width: 100%;
            background: #1976d2;
            color: #fff;
            border: none;
            padding: 10px 0;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.2s;
        }
        
        input[type="submit"]:hover {
            background: #125ea7;
        }
    </style>

    <body>
        <h2>Register</h2>
        <form action="register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username"><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password"><br>
            <label for="department">Department:</label>
            <select id="department" name="department">
            <option value="HR">HR</option>
            <option value="IT">IT</option>
            <option value="Finance">Finance</option>
            <!-- Add more departments as needed -->
        </select><br>
            <input type="submit" value="Register">
        </form>
    </body>

</html>