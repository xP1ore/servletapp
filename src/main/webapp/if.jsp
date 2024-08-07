<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    String hourParam = request.getParameter("hour");
    request.setAttribute("hourVar", hourParam);
%>
<c:if test="${hourVar >= 19 && hourVar <= 24}">
    ${hourVar} is an evening
</c:if>
</body>
</html>