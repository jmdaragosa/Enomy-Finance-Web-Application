<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Profile - Enomy Finance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="container mt-4">
        <h2>Welcome, ${user.username}</h2>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Role:</strong> ${user.role}</p>

        <hr>

        <h4>Saved Currency Conversions</h4>

        <c:choose>
            <c:when test="${not empty currencyQuotes}">
                <table class="table table-striped table-bordered">
                    <thead class="table-light">
                        <tr>
                            <th>From</th>
                            <th>To</th>
                            <th>Original Amount</th>
                            <th>Converted Amount</th>
                            <th>Exchange Rate</th>
                            <th>Fee</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="quote" items="${currencyQuotes}">
                            <tr>
                                <td>${quote.fromCurrency}</td>
                                <td>${quote.toCurrency}</td>
                                <td>${quote.originalAmount}</td>
                                <td>${quote.convertedAmount}</td>
                                <td>${quote.exchangeRate}</td>
                                <td>${quote.fee}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-muted">You haven't saved any currency conversion quotes yet.</p>
            </c:otherwise>
        </c:choose>

        <hr>

		<h4>Saved Investment Quotes</h4>
		<c:choose>
		    <c:when test="${not empty investmentQuotes}">
				<table class="table table-striped table-bordered">
				<thead class="table-light">
		                <tr>
		                    <th>Plan Type</th>
		                    <th>Lump Sum</th>
		                    <th>Monthly Contribution</th>
		                    <th>Projected Return</th>
		                    <th>Fee</th>
		                    <th>Tax</th>
		                    <th>Final Amount</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="plan" items="${investmentQuotes}">
		                    <tr>
		                        <td>${plan.planType}</td>
		                        <td>${plan.lumpSum}</td>
		                        <td>${plan.monthlyContribution}</td>
		                        <td>${plan.projectedReturn}</td>
		                        <td>${plan.fee}</td>
		                        <td>${plan.tax}</td>
		                        <td>${plan.finalAmount}</td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </c:when>
		    <c:otherwise>
		        <p class="text-muted">No saved investment quotes yet.</p>
		    </c:otherwise>
		</c:choose>
    </div>
</body>
</html>
