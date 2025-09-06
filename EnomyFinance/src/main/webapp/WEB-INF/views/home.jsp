<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home - Enomy Finance</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card p-4 shadow-sm bg-white">
            <c:choose>
                <c:when test="${not empty sessionScope.loggedInUser}">
                    <h2 class="mb-3">Welcome, ${sessionScope.loggedInUser.username}!</h2>
                    <p class="lead">Use the navigation bar above to explore our financial tools:</p>
                    <ul class="list-group list-group-flush mt-3">
                        <li class="list-group-item">💱 <strong>Currency Converter</strong> – Check live exchange rates and conversion costs</li>
                        <li class="list-group-item">📈 <strong>Investment Calculator</strong> – Get personalized investment quotes</li>
                        <li class="list-group-item">📋 <strong>Profile</strong> – View your saved quotes and personal info</li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <h2 class="mb-3">Welcome to Enomy Finance!</h2>
                    <p class="lead">To access all features, please <a href="${pageContext.request.contextPath}/login">Login</a> or <a href="${pageContext.request.contextPath}/register">Register</a>.</p>
                    <p>Our tools help you make smarter financial decisions:</p>
                    <ul class="list-group list-group-flush mt-3">
                        <li class="list-group-item">💱 Currency Conversion</li>
                        <li class="list-group-item">📈 Investment Calculator</li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
