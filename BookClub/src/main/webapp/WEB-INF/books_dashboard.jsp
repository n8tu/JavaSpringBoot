<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!doctype html>
<html lang="en">

<head>
    <title>Books</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>

<body>
    <div class="container w-75 mt-5">
        <div class="mb-3 d-flex justify-content-between align-items-center">
            <p class="display-2">Welcome, <c:out value="${user.username}"/></p>
            <a href="/logout">logout</a>
        </div>

        <div class="mb-3 d-flex justify-content-between align-items-center">
            <p>Books from everyone's shelves:</p>
            <a href="/books/new">+ Add a book to my shelf!</a>
        </div>

        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <td>ID</td>
                    <td>Title</td>
                    <td>Author Name</td>
                    <td>Posted By</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td><c:out value="${book.id}"/></td>
                        <td>
                            <a href="/books/${book.id}">
                                <c:out value="${book.title}"/>
                            </a>
                        </td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.user.username}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
<!-- Bootstrap JavaScript Libraries -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>