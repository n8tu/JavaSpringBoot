<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>
        All books
    </title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
    <div class="container">
        <h1>All Books</h1>
        <table class="table text-center">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Book Title</th>
                    <th>Book Description</th>
                    <th>Book Pages</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td> <c:out value="${book.id}"/> </td>
                        <td> <c:out value="${book.title}"/> </td>
                        <td> <c:out value="${book.description}"/> </td>
                        <td> <c:out value="${book.numOfPages}"/> </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>


    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>