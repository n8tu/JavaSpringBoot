<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        <c:out value="${language.name}"/>
    </title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

<div class="container w-50">
    <div class="container d-flex justify-content-end align-items-center">
        <a href="/languages">Dashboard</a>
    </div>

    <div class="container">
        <p>
            Language: <c:out value="${language.name}"/>
        </p>
        <p>
            Creator: <c:out value="${language.creator}"/>
        </p>
        <p>
            Version: <c:out value="${language.currentVersion}"/>
        </p>
        <p>
            <a href="/languages/<c:out value='${language.id}'/>/edit">Edit</a>
        </p>
        <p>
            <form:form action="/languages/${language.id}" method="post">
                <input type="hidden" name="_method" value="DELETE">
                <button class="btn btn-link">Delete</button>
            </form:form>
        </p>
    </div>
</div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>