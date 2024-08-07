<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.model.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My JSP</title>
</head>
<body>
<h1>Hello JSP</h1>
<%
    out.print("Hello");
%>
<br/>
<%= request.getRequestURI() %>
<br/>
<%= LocalDateTime.now() %>
<hr/>
<%
    List<User> users = (List) request.getAttribute("users");
    User firstUser = users.get(0);
%>
<%= firstUser.getName() %>
<%= firstUser.getAge() %>
<hr/>
<table>
<% for (User user : users) {%>
    <tr>
        <td><%= user.getName() %></td>
        <td><%= user.getAge() %></td>
    </tr>
<%}%>
</table>
</body>
</html>