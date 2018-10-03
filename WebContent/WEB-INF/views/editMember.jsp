<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: I500719
  Date: 30/09/2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal" var="owner"/>
    <h1>${owner.name}</h1>
    <h1>Edit Member</h1>
    <form:form action="/edit/member" method="post" name="member">
        <div>
            <label for="name">Name:</label>
            <input id="name" type="text" name="name" value="${editMember.name}">
        </div>
        <div>
            <label for="email">E-mail:</label>
            <input id="email" type="text" name="email" value="${editMember.email}">
        </div>
        <div>
            <label for="username">Username:</label>
            <input id="username" type="text" name="username" value="${editMember.username}">
        </div>
        <div>
            <label for="password">Password:</label>
            <input id="password" type="password" name="password">
        </div>
        <button type="submit">Submit</button>
    </form:form>
</security:authorize>


</body>
</html>
