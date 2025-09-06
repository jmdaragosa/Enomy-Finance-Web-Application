<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard - Enomy Finance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2>Admin Dashboard</h2>
    <p class="text-muted">Logged in as: <strong>${sessionScope.loggedInUser.username}</strong></p>
    <hr>

    <h4>All Users</h4>
    <table class="table table-striped table-hover">
        <thead class="table-light">
        <tr>
            <th>Username</th>
            <th>Balance</th>
            <th>View</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>$${user.balance}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/user/${user.id}" class="btn btn-sm btn-primary">View Details</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
