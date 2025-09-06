<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Investment Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5 mb-5">
    <div class="card shadow-lg p-4">
        <h2 class="mb-4 text-primary">Investment Calculator</h2>

        <!-- Show error if exists -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Investment Form -->
        <form action="${pageContext.request.contextPath}/investment/calculate" method="post" class="row g-3">
            <div class="col-md-6">
                <label for="planType" class="form-label">Plan Type:</label>
                <select name="planType" class="form-select" required>
                    <option value="">-- Select --</option>
                    <option>Basic Savings</option>
                    <option>Savings Plan Plus</option>
                    <option>Managed Stock Investments</option>
                </select>
            </div>

            <div class="col-md-6">
                <label for="lumpSum" class="form-label">Lump Sum (£):</label>
                <input type="number" name="lumpSum" class="form-control" step="0.01" required min="0">
            </div>

            <div class="col-md-6">
                <label for="monthlyContribution" class="form-label">Monthly Contribution (£):</label>
                <input type="number" name="monthlyContribution" class="form-control" step="0.01" required min="0">
            </div>

            <div class="col-md-6">
                <label for="durationYears" class="form-label">Duration (Years):</label>
                <select name="durationYears" class="form-select">
                    <option value="1">1 Year</option>
                    <option value="5">5 Years</option>
                    <option value="10">10 Years</option>
                </select>
            </div>

            <div class="col-12">
                <button type="submit" class="btn btn-primary w-100">Calculate</button>
            </div>
        </form>

        <!-- Show results -->
        <c:if test="${not empty result}">
            <div class="mt-5">
                <h4 class="text-success">Investment Results:</h4>
                <table class="table table-bordered table-hover bg-white mt-3">
                    <tr>
                        <th>Duration</th>
                        <td>${result.years} year(s)</td>
                    </tr>
                    <tr>
                        <th>Min Return Rate</th>
                        <td><fmt:formatNumber value="${result.minReturnRate}" type="percent" maxFractionDigits="2"/></td>
                    </tr>
                    <tr>
                        <th>Max Return Rate</th>
                        <td><fmt:formatNumber value="${result.maxReturnRate}" type="percent" maxFractionDigits="2"/></td>
                    </tr>
                    <tr>
                        <th>Profit</th>
                        <td><fmt:formatNumber value="${result.profit}" type="currency" currencySymbol="£"/></td>
                    </tr>
                    <tr>
                        <th>Fee</th>
                        <td><fmt:formatNumber value="${result.fee}" type="currency" currencySymbol="£"/></td>
                    </tr>
                    <tr>
                        <th>Tax</th>
                        <td><fmt:formatNumber value="${result.tax}" type="currency" currencySymbol="£"/></td>
                    </tr>
                    <tr class="table-success">
                        <th>Final Amount</th>
                        <td><fmt:formatNumber value="${result.finalAmount}" type="currency" currencySymbol="£"/></td>
                    </tr>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
