<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Currency Converter</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="mb-4">Currency Converter</h2>

        <form action="currency" method="post" class="card p-4 shadow-sm bg-white">
            <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <input type="number" class="form-control" id="amount" name="amount" step="0.01" required>
            </div>

            <div class="mb-3">
                <label for="fromCurrency" class="form-label">From Currency</label>
                <select name="fromCurrency" id="fromCurrency" class="form-select" required>
                    <option value="GBP">GBP</option>
                    <option value="USD">USD</option>
                    <option value="EUR">EUR</option>
                    <option value="BRL">BRL</option>
                    <option value="JPY">JPY</option>
                    <option value="TRY">TRY</option>
                </select>
            </div>

            <div class="mb-4">
                <label for="toCurrency" class="form-label">To Currency</label>
                <select name="toCurrency" id="toCurrency" class="form-select" required>
                    <option value="GBP">GBP</option>
                    <option value="USD">USD</option>
                    <option value="EUR">EUR</option>
                    <option value="BRL">BRL</option>
                    <option value="JPY">JPY</option>
                    <option value="TRY">TRY</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary w-100">Convert</button>
        </form>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-4" role="alert">
                ${error}
            </div>
        </c:if>

        <c:if test="${not empty result}">
            <div class="mt-5">
                <h4>Conversion Result:</h4>
                <ul class="list-group">
                    <li class="list-group-item">
                        From: <fmt:formatNumber value="${result.amount}" type="number" maxFractionDigits="2"/> ${result.fromCurrency}
                    </li>
                    <li class="list-group-item">
                        To: <fmt:formatNumber value="${result.finalAmount}" type="number" maxFractionDigits="2"/> ${result.toCurrency}
                    </li>
                    <li class="list-group-item">
                        Exchange Rate: <fmt:formatNumber value="${result.rate}" type="number" maxFractionDigits="4"/>
                    </li>
                    <li class="list-group-item list-group-item-warning">
                        Fee Charged: <fmt:formatNumber value="${result.fee}" type="number" minFractionDigits="2" maxFractionDigits="2"/>
						(<fmt:formatNumber value="${feeRate * 100}" type="number" maxFractionDigits="1"/>%)
                    </li>
                </ul>
            </div>
        </c:if>
    </div>
</body>
</html>
