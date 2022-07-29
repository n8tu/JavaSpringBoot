<%@ page import="org.springframework.ui.Model" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Read Share</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

    <div class="container w-50">

        <div class="container d-flex justify-content-between align-items-center">
            <h1 class="display-2 text-primary mt-4">Expense Details</h1>
            <a class="" href="/expenses">Go back</a>
        </div>

        <div class="container mt-4">
            <p class="mt-2">
                Expense Name: <c:out value="${poke.expense_name}"/>
            </p>
            <p class="mt-2">
                Expense Description: <c:out value="${poke.description}"/>
            </p>
            <p class="mt-2">
                Vendor: <c:out value="${poke.vendor}"/>
            </p>
            <p class="mt-2">
                Amount Spent: <c:out value="${poke.amount}"/>
            </p>
        </div>

    </div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
