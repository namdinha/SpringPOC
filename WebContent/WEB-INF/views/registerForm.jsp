<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Rooms</title>
    </head>
    <body>
        <form:form action="/register" method="post" name="user">
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name">
            </div>
            <div>
                <label for="email">E-mail:</label>
                <input id="email" type="text" name="email">
            </div>
            <div>
                <label for="username">Username:</label>
                <input id="username" type="text" name="username">
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" type="password" name="password">
            </div>
            <button type="submit">Register</button>
        </form:form>
        <!--
        <script
                src="http://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
                crossorigin="anonymous"></script>
        <script src="<c:url value="/resources/js/main.js" />"></script>
        -->

    </body>
</html>