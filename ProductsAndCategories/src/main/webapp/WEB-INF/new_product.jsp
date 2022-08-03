<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!doctype html>
<html lang="en">

<head>
    <title>new Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>

<body>
<div class="container w-75 mt-5">
    <h1>New Product</h1>
    <div class="mb-3">
        <form:form method="post" action="/products/new" modelAttribute="product">
            <form:label path="name">Name</form:label>
            <form:input path="name"/>

            <form:label path="description">Description</form:label>
            <form:textarea path="description"/>

            <form:label path="price">Price</form:label>
            <form:input path="price"/>

            <button type="submit">Submit</button>
        </form:form>
    </div>
</div>
<!-- Bootstrap JavaScript Libraries -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>