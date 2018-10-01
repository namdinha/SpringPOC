<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: I500719
  Date: 28/09/2018
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
</head>
<body>
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
        <button type="submit" class="btn btn-primary">Logar</button>
    </form:form>
    <form:form action="/" method="get">
        <button type="submit">Home</button>
    </form:form>
</div>
</body>
</html>
