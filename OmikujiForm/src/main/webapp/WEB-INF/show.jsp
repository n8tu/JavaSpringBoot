<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Omikuji Show</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

    <div class="container">
        <h1 class="my-3">Here's your Omikuji!</h1>
        <div class="container my-4 border border-2 border-dark">
            In <c:out value="${number}"/> years, you will <br>
            live in <c:out value="${city}"/> with <c:out value="${name}"/> <br>
            as your roommate, <c:out value="${hobby}"/> for a living. <br>
            The next time you see a <c:out value="${living}"/>, you will have good luck. <br>
            Also, <c:out value="${message}"/>
        </div>

        <a href="/omikuji">Go Back</a>
    </div>

    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>