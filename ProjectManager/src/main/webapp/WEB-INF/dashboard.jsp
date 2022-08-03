<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Project Manager Dashboard</title>

    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
    <div class="container mt-4">
        <div class="mb-3 d-flex justify-content-between align-items-center">
            <p class="display-2">
                Welcome, <c:out value="${user.first_name}"/>!
            </p>
            <a href="/logout">Logout</a>
        </div>
        <div class="mb-3">
            <div class="d-flex justify-content-between align-items-center">
                <p>All Projects</p>
                <a class="btn btn-dark" href="/projects/new">+ New Project</a>
            </div>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <td>Project</td>
                        <td>Team Leader</td>
                        <td>Due Date</td>
                        <td>Actions</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="project" items="${available_projects}">
                        <tr>
                            <td>
                                <a href="/projects/${project.id}">
                                    <c:out value="${project.title}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${project.leader.first_name}"/>
                                <c:out value="${project.leader.last_name}"/>
                            </td>
                            <td>
                                <f:formatDate pattern="MMMM d" value="${project.due_date}"/>
                            </td>
                            <td>
                                <a href="/projects/${project.id}/join">Join team</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


        <div class="mb-3">
            <p class="mb-2">Your Projects</p>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <td>Project</td>
                    <td>Team Leader</td>
                    <td>Due Date</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="project" items="${registerd_projects}">
                    <tr>
                        <td>
                            <a href="/projects/${project.id}">
                                <c:out value="${project.title}"/>
                            </a>
                        </td>
                        <td>
                            <c:out value="${project.leader.first_name}"/>
                            <c:out value="${project.leader.last_name}"/>
                        </td>
                        <td>
                            <f:formatDate pattern="MMMM d" value="${project.due_date}"/>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${project.leader.id == user.id}">
                                    <a href="/projects/${project.id}/edit">Edit</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="/projects/${project.id}/leave">Leave team</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>