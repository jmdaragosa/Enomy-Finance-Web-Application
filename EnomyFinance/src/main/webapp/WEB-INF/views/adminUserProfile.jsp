<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Admin - Admin Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    
    <!-- Back to Dashboard Button -->
    <a href="${pageContext.request.contextPath}/admin/dashboard" class="btn btn-secondary mb-3">← Back to Dashboard</a>

    <h2>User Profile: ${user.username}</h2>
    <p><strong>Balance:</strong> <fmt:formatNumber value="${user.balance}" type="currency" /></p>

    <hr>

    <h4>Saved Currency Conversions</h4>
    <c:choose>
        <c:when test="${not empty currencyQuotes}">
            <table class="table table-bordered table-striped">
                <thead class="table-light">
                <tr>
                    <th>Original Amount</th>
                    <th>From Currency</th>
                    <th>To Currency</th>
                    <th>Converted Amount</th>
                    <th>Exchange Rate</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="quote" items="${currencyQuotes}">
                    <tr>
                        <td><fmt:formatNumber value="${quote.originalAmount}" type="currency" /></td>
                        <td>${quote.fromCurrency}</td>
                        <td>${quote.toCurrency}</td>
                        <td><fmt:formatNumber value="${quote.convertedAmount}" type="currency" /></td>
                        <td><fmt:formatNumber value="${quote.exchangeRate}" maxFractionDigits="4" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-muted">No saved currency conversions found.</p>
        </c:otherwise>
    </c:choose>

    <hr>

    <h4>Saved Investment Quotes</h4>
    <c:choose>
        <c:when test="${not empty investmentQuotes}">
            <table class="table table-bordered table-striped">
                <thead class="table-light">
                <tr>
                    <th>Plan Type</th>
                    <th>Lump Sum</th>
                    <th>Monthly Contribution</th>
                    <th>Years</th>
                    <th>Projected Return</th>
                    <th>Fee</th>
                    <th>Tax</th>
                    <th>Final Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="quote" items="${investmentQuotes}">
                    <tr>
                        <td>${quote.planType}</td>
                        <td><fmt:formatNumber value="${quote.lumpSum}" type="currency" /></td>
                        <td><fmt:formatNumber value="${quote.monthlyContribution}" type="currency" /></td>
                        <td>${quote.years}</td>
                        <td><fmt:formatNumber value="${quote.profit}" type="currency" /></td>
                        <td><fmt:formatNumber value="${quote.fee}" type="currency" /></td>
                        <td><fmt:formatNumber value="${quote.tax}" type="currency" /></td>
                        <td><fmt:formatNumber value="${quote.finalAmount}" type="currency" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p class="text-muted">No saved investment quotes found.</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
