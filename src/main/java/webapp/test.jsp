<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>API Result</title>
  </head>
  <body>
    <h1>API Result:</h1>
    <p><%= request.getAttribute("apiResult") %></p>

    <!-- Add a button to trigger run_both.py -->
    <form action="runPython" method="post">
      <button type="submit">Run Python Script</button>
    </form>
  </body>
</html>
