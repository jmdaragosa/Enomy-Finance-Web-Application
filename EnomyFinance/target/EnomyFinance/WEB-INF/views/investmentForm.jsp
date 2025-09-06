<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Investment Calculator</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h2 class="mb-4">Investment Calculator</h2>
        
        <form action="investment" method="post" class="card p-4 shadow-sm bg-white">
            <div class="mb-3">
                <label for="lumpSum" class="form-label">Lump Sum</label>
                <input type="number" class="form-control" id="lumpSum" name="lumpSum" step="0.01" required>
            </div>
            <div class="mb-3">
                <label for="monthlyContribution" class="form-label">Monthly Contribution</label>
                <input type="number" class="form-control" id="monthlyContribution" name="monthlyContribution" step="0.01" required>
            </div>
            <div class="mb-4">
                <label for="planType" class="form-label">Plan Type</label>
                <select class="form-select" id="planType" name="planType" required>
                    <option value="Basic Savings">Basic Savings</option>
                    <option value="Savings Plan Plus">Savings Plan Plus</option>
                    <option value="Managed Stock Investments">Managed Stock Investments</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary w-100">Calculate</button>
        </form>

        <c:if test="${not empty result}">
            <div class="mt-5">
                <h4>Investment Results:</h4>
                <ul class="list-group">
                    <li class="list-group-item">Projected Return (5 Years): 
                        <strong><fmt:formatNumber value="${result.projectedReturn}" maxFractionDigits="2"/></strong>
                    </li>
                    <li class="list-group-item">Fee Deducted: 
                        <strong><fmt:formatNumber value="${result.fee}" maxFractionDigits="2"/></strong>
                    </li>
                    <li class="list-group-item">Tax Deducted: 
                        <strong><fmt:formatNumber value="${result.tax}" maxFractionDigits="2"/></strong>
                    </li>
                    <li class="list-group-item list-group-item-success">
                        Final Amount: <strong><fmt:formatNumber value="${result.finalAmount}" maxFractionDigits="2"/></strong>
                    </li>
                </ul>
            </div>
        </c:if>
    </div>
</body>
</html>
