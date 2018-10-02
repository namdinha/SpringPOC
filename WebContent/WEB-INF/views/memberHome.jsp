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
    <security:authentication property="principal" var="member"/>
    <h1>${member.name}</h1>
    <h1>Members in your Team</h1>
    <table>
        <tr>
            <td>Member</td>
            <td>E-mail</td>
            <td><form:form action="/register/member" method="get">
                <button type="submit">Add new member</button>
            </form:form></td>
        </tr>
        <c:forEach items="${members}" var="member">
            <tr>
                <td>${member.name}</td>
                <td>${member.email}</td>
                <td><form:form action="/register/member" method="get" var="member">
                    <input type="hidden" path="member" value="${member}">
                    <button type="submit">Edit</button>
                </form:form></td>
            </tr>
        </c:forEach>
    </table>
</security:authorize>


</body>
</html>
