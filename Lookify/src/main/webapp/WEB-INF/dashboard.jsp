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
    <title>Lookify!</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

<div class="container w-75 text-center ">
    <div class="container mt-2 d-flex justify-content-around align-items-center">
        <a href="/songs/new">Add New</a>
        <a href="/search/topTen">Top Songs</a>

        <form class="d-flex align-items-center" method="POST" action="/search">
            <input name="artist" type="text" class="form-control" />
            <button type="submit" class="btn btn-dark mx-2 form-control">Search Artists</button>
        </form>

    </div>

    <div class="container mt-4">
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
            <div class="alert alert-success alert-dismissible fade show text-start" role="alert">
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

    <div class="container mt-5">
        <table class="table table-hover">
            <thead class="table-dark text-white">
                <tr>
                    <th>Name</th>
                    <th>Rating</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach var="song" items="${songs}">
                    <tr>
                        <td>
                            <a href="/songs/<c:out value='${song.id}'/>">
                                    <c:out value="${song.title}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${song.rate}"/>
                        </td>
                        <td>
                            <form:form action="/songs/${song.id}/delete" method="POST">
                                <input type="hidden" name="_method" value="DELETE">
                                <button class="btn btn-link">delete</button>
                            </form:form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    

</div>

<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>