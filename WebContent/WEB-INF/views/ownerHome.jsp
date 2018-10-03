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
        <h1>Members in your Team</h1>
        <table>
            <tr>
                <td>Member</td>
                <td>E-mail</td>
            </tr>
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.name}</td>
                    <td>${member.email}</td>
                    <td>
                        <form:form action="/edit/member" method="get" name="editMember">
                            <input type="hidden" id="editUsername" name="username" value="${member.username}">
                            <button type="submit">Edit</button>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h1>Add Member</h1>
        <form:form action="/register/member" method="post" name="member">
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
    </security:authorize>


</body>
</html>
