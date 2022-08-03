<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!doctype html>
<html lang="en">

<head>
    <title>Register</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style.css">

</head>

<body class="d-flex justify-content-center align-items-center">

<div class="container row text-white justify-content-center shadow-lg">
    <div id="first_section" class="col-7 bg-light text-dark">
        <img src="/imgs/login_register.jpg" alt="login form picture" id="login_register_picture">
    </div>
    <div id="second_section" class="col-5 bg-light text-dark">

        <%--   Notifications     --%>
        <div class="container-fluid mt-4">
            <c:if test="${!errors.isEmpty()}">
                <c:forEach var = "error" items="${errors}" >
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>
                            <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
                                <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                            </svg>
                        </strong>
                        <span class="mx-4">
                            <c:out value="${error.getDefaultMessage()}"/>
                        </span>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${success != null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <strong>
                        <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z"/>
                        </svg>
                    </strong>
                    <span class="mx-4">
                            <c:out value="${success}"/>
                        </span>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
        </div>

        <p class="my-4 fs-5 bg-dark w-75 text-light">Welcome anonymous it's time to be a one </p>
        <div class="container mt-5 row justify-content-center fs-4">
            <p class="my-4 text-center form_label fs-3">Create your account</p>
            <form:form class="w-75 mb-3 row" action="/register" method="POST" modelAttribute="register">
                <div class="mb-3">
                    <form:label path="first_name" class="form-label form_label">First Name:</form:label>
                    <form:input path="first_name" type="text" class="form-control form_text"/>
                </div>
                <div class="mb-3">
                    <form:label path="last_name" class="form-label form_label">Last Name:</form:label>
                    <form:input path="last_name" type="text" class="form-control form_text"/>
                </div>
                <div class="mb-3">
                    <form:label path="email" class="form_label">Email</form:label>
                    <form:input path="email" type="email" class="form-control form_text"/>
                </div>
                <div class="mb-3">
                    <form:label path="password" class="form_label">Password</form:label>
                    <form:input path="password" type="password" class="form-control form_text"/>
                </div>

                <div class="mb-3">
                    <form:label path="confirm_password" class="form_label">Confirm password</form:label>
                    <form:input path="confirm_password" type="password" class="form-control form_text"/>
                </div>

                <div class="mb-3">
                    <button type="submit" class="btn btn-dark form-control">Register</button>
                </div>

                <a href="/login" class="mb-3 text-center text-decoration-none">
                    <small id="helpId" class="form-text text-muted">Have an account? Login</small>
                </a>
            </form:form>
        </div>
    </div>
</div>
<!-- Bootstrap JavaScript Libraries -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>