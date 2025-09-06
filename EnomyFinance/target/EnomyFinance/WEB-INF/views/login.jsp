<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login - Enomy Finance</title>

    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card p-4 shadow-sm bg-white mx-auto" style="max-width: 400px;">
            <h2 class="mb-4 text-center">Login</h2>
            <form action="login" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" name="username" id="username" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" name="password" id="password" class="form-control" required />
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3" role="alert">
                    ${error}
                </div>
            </c:if>

            <!-- Register link -->
            <div class="mt-3 text-center">
                <span>Don't have an account?</span>
                <a href="${pageContext.request.contextPath}/register">Register here</a>
            </div>
        </div>
    </div>
</body>
</html>
