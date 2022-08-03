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
    <title>Project Details</title>

    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
    <div class="container mt-4">
        <div class="mb-3 d-flex justify-content-between align-items-center">
            <p class="display-2 mb-3">Project Details</p>
            <a href="/dashboard">Back to Dashboard</a>
        </div>

        <div class="mb-3">
            <p>
                Project : <c:out value="${project.title}"/>
            </p>
            <p>
                Description : <c:out value="${project.description}"/>
            </p>
            <p>
                Due Date : <f:formatDate value="${project.due_date}" pattern="d-M-yyyy" />
            </p>
            <c:if test="${user.id == project.leader.id}">
                <div class="d-flex justify-content-end">
                    <form:form action="/projects/${project.id}/delete" method="POST">
                        <input type="hidden" name="_method" value="DELETE">
                        <button class="btn btn-danger">Delete Project</button>
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