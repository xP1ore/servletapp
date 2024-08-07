<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<%
    String degrees = request.getParameter("degree");
    request.setAttribute("degreeVar", degrees);
%>
<c:choose>
    <c:when test="${degreeVar < -5}">it is frost</c:when>
    <c:when test="${degreeVar < 5}">it is cold</c:when>
    <c:when test="${degreeVar < 25}">it is not hot</c:when>
    <c:when test="${degreeVar >= 25}">it is hot</c:when>
    <c:otherwise>Enter the temperature</c:otherwise>
</c:choose>
</body>
</html>