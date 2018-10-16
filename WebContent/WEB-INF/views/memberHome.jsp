<%@ page import="com.sap.poc.models.Shift" %>
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
    <c:url value="" var="contextPath"/>
    <%--<link rel='stylesheet' href='https://cdn.rawgit.com/nizarmah/calendar-javascript-lib/master/calendarorganizer.min.css'>--%>
    <%--<link href="${contextPath}/resources/css/calendarStyle.css" rel="stylesheet" type="text/css" media="all" />--%>
    <title>Welcome</title>
</head>
<body>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal" var="member"/>
        <h1>${member.name}</h1>
        <div id="shifts">
            <c:set var="ANY" value="<%=Shift.ANY%>"/>
            <c:set var="DAY" value="<%=Shift.DAY%>"/>
            <c:set var="NIGHT" value="<%=Shift.NIGHT%>"/>
            <c:forEach items="${shifts}" var="shift">
                <div id="${shift.id}">
                    ${shift.date.toString()}
                    ${shift.desiredShift}
                    <form:form action="/calendar/editShift" method="post" name="editedShift">
                        <input type="hidden" id="editedShiftId" name="id" value="${shift.id}">
                        <select name="desiredShift">
                            <option disabled selected>Select</option>
                            <option value="ANY">ANY</option>
                            <option value="DAY">DAY</option>
                            <option value="NIGHT">NIGHT</option>
                        </select>
                        <button type="submit">Edit</button>
                    </form:form>
                </div>
            </c:forEach>
        </div>

        <div id="calendarContainer"></div>
        <div id="organizerContainer"></div>
        <%--<script src='https://cdn.rawgit.com/nizarmah/calendar-javascript-lib/cb4b46dd/calendarorganizer.min.js'></script>--%>
        <%--<script--%>
                <%--src="http://code.jquery.com/jquery-3.3.1.min.js"--%>
                <%--integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="--%>
                <%--crossorigin="anonymous"></script>--%>
        <%--<script src="${contextPath}/resources/js/calendar.js"></script>--%>
    </security:authorize>


</body>
</html>
