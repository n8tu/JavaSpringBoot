<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Omikuji Form</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

    <div class="container">
        <h1 class="my-3">Send an Omikuji!</h1>
        <form class="container border border-2 border-dark" method="POST" action="/omikuji/show">
            <div class="container w-75 m-5">
                <label class="form-label">Pick any number from 5 to 25</label>
                <input type="number" class="form-control" name="number" min="3" max="25">
            </div>
            <div class="container w-75 m-5">
                <label class="form-label">Enter the name of any city:</label>
                <input type="text" class="form-control" name="city">
            </div>
            <div class="container w-75 m-5">
                <label class="form-label">Enter the name of any real person:</label>
                <input type="text" class="form-control" name="name">
            </div>
            <div class="container w-75 m-5">
                <label class="form-label">Enter professional endeavor or hobby:</label>
                <input type="text" class="form-control" name="hobby">
            </div>
            <div class="container w-75 m-5">
                <label class="form-label">Enter any type of living thing:</label>
                <input type="text" class="form-control" name="living">
            </div>
            <div class="container w-75 m-5">
                <label class="form-label">Say something nice to someone:</label>
                <textarea class="form-control" name="message" cols="30" rows="10"></textarea>
            </div>

            <p>Send and show a friend</p>
            <button class="btn btn-primary" type="submit">Send</button>
        </form>
    </div>

    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>