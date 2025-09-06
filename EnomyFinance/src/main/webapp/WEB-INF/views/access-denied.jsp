<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Access Denied</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5 text-center">
        <div class="alert alert-danger">
            <h4 class="alert-heading">Access Denied</h4>
            <p>You do not have permission to view this page.</p>
        </div>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Go to Home</a>
    </div>
</body>
</html>
