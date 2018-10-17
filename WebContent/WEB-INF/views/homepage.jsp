<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
    </head>
    <body>
        <h1>Register</h1>
        <form:form action="${s:mvcUrl('RC#registerOwner').build()}" method="post" name="user">
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name">
                <form:errors path="name"/>
            </div>
            <div>
                <label for="email">E-mail:</label>
                <input id="email" type="email" name="email">
                <form:errors path="user.email"/>
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

        <div class="container">
            <h1>Login</h1>
            <form:form servletRelativeAction="/login" method="post">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="username" class="form-control" />
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" class="form-control" />
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form:form>
        </div>

    </body>
</html>