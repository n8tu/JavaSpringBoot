<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!doctype html>
<html lang="en">

<head>
    <title>Show Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>

<body>
<div class="container w-75 mt-5">
    <h1><c:out value="${product.name}"/></h1>
    <div class="d-flex justify-content-between">
        <div class="container">
            <p class="display-4">Categories</p>
            <ul>
                <c:forEach var="category" items="${product.categories}">
                    <li><c:out value="${category.name}"/></li>
                </c:forEach>
            </ul>
        </div>

        <div class="container">
            <form:form method="post" action="/products/${product_id}/add_category">
                <select name="category_id">
                    <c:forEach var="category" items="${categories}">
                        <c:if test="${!product.getCategories().contains(category)}">
                            <option value="${category.id}">${category.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <button type="submit">Submit</button>
            </form:form>
        </div>
    </div>
</div>
<!-- Bootstrap JavaScript Libraries -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>