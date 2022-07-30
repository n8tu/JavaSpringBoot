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
    <title>Search</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

<div class="container w-75 text-center">
    <div class="container mt-2 d-flex justify-content-around align-items-center">

        <span>
            Songs by artist: <c:out value="${artist}"/>
        </span>

        <form class="d-flex align-items-center" method="POST" action="/search">
            <input name="artist" type="text" class="form-control" />
            <button type="submit" class="btn btn-dark mx-2 form-control">New Search</button>
        </form>

        <a href="/dashboard">Dashboard</a>

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