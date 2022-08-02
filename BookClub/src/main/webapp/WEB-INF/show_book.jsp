<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!doctype html>
<html lang="en">

<head>
    <title>Read Share</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>

<body>
    <div class="container mt-5">

        <div class="d-flex justify-content-between align-items-center mb-3">
            <h1 class="display-1">
                <c:out value="${book.title}"/>
            </h1>
            <a href="/books">back to the shelves</a>
        </div>

        <div class="mb-3">
            <p class="fs-4 mb-4">
                <c:choose>
                    <c:when test="${user.id == book.owner.id}">
                        <span class="text-danger">You</span>
                    </c:when>

                    <c:otherwise>
                        <span class="text-danger">
                            <c:out value="${book.owner.username}"/>
                        </span>
                    </c:otherwise>
                </c:choose>
                read
                <span class="text-primary">
                    <c:out value="${book.title}"/>
                </span>
                by
                <span class="text-info">
                    <c:out value="${book.author}"/>.
                </span>
            </p>

            <p class="fs-5 mb-5">
                Here are
                <c:choose>
                    <c:when test="${user.id == book.owner.id}">
                        Your
                    </c:when>

                    <c:otherwise>
                        <c:out value="${book.owner.username}"/>'s
                    </c:otherwise>
                </c:choose>
                thoughts:
            </p>

            <p class="py-4 border-top border-bottom border-2 border-dark w-25 text-center">
                <c:out value="${book.thoughts}"/>
            </p>
            
            <c:if test="${user.id == book.owner.id}">
                <div class="d-flex justify-content-center align-items-center w-25">
                    <a href="/books/${book.id}/edit" class="btn btn-dark mx-2">Edit</a> |
                    <form:form class="mx-2" action="/books/${book.id}/delete" method="POST">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form:form>
                </div>
            </c:if>
        </div>
    </div>
<!-- Bootstrap JavaScript Libraries -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>