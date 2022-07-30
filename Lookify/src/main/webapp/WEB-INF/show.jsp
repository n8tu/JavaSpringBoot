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
    <title>Details</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

<div class="container w-75 mx-4">
    <div class="container mt-2 d-flex justify-content-end align-items-center">
        <a href="/dashboard">Dashboard</a>
    </div>

    <div class="container mt-5">
        <p>
            Title: <c:out value="${song.title}"/>
        </p>
        <p>
            Artist: <c:out value="${song.artist}"/>
        </p>
        <p>
            Rating (1-10): <c:out value="${song.rate}"/>
        </p>
        <p>
            <form:form method="post" action="/songs/${song.id}/delete">
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