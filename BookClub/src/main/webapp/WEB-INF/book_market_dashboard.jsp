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
            <p>Hello, <c:out value="${user.username}"/> Welcome to</p>
            <a href="/books">back to the shelves</a>
        </div>

        <h1 class="display-2">The Book Broker!</h1>
        <p>Available Books to Borrow</p>

        <table class="table mb-3 text-center">
            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 30%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 20%;">
            </colgroup>


            <thead class="thead-dark">
                <tr>
                    <td>ID</td>
                    <td>Title</td>
                    <td>Author Name</td>
                    <td>Owner</td>
                    <td>Actions</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="book" items="${books}">
                    <c:if test="${book.getBorrow() == null}">
                        <tr>
                            <td><c:out value="${book.id}"/></td>
                            <td>
                                <a href="/books/${book.id}">
                                    <c:out value="${book.title}"/>
                                </a>
                            </td>
                            <td><c:out value="${book.author}"/></td>
                            <td><c:out value="${book.owner.username}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.id == book.owner.id}">
                                        <a class="btn btn-info" href="/books/${book.id}/edit">Edit</a>
                                        |
                                        <form:form cssStyle="display: inline-block" action="/books/${book.id}/delete" method="POST">
                                            <input type="hidden" name="_method" value="DELETE">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                        </form:form>
                                    </c:when>

                                    <c:otherwise>
                                        <a href="/bookmarket/${book.id}/borrow">Borrow</a>
                                    </c:otherwise>
                                    
                                </c:choose>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>


        <p>Books I'm Borrowing..</p>
        <table class="table mb-3 text-center">

            <colgroup>
                <col span="1" style="width: 5%;">
                <col span="1" style="width: 30%;">
                <col span="1" style="width: 25%;">
                <col span="1" style="width: 20%;">
                <col span="1" style="width: 20%;">
            </colgroup>

            <thead class="thead-dark">
            <tr>
                <td>ID</td>
                <td>Title</td>
                <td>Author Name</td>
                <td>Owner</td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${user.borrowed_books}">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td>
                        <a href="/books/${book.id}">
                            <c:out value="${book.title}"/>
                        </a>
                    </td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.owner.username}"/></td>
                    <td>
                        <a href="/bookmarket/${book.id}/return">Return</a>
                    </td>
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