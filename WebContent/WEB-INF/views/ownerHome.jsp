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
    <title>Welcome</title>
</head>
<body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
    <script src="/resources/js/notify.js"></script>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal" var="owner"/>
        <h1>${owner.name}</h1>
        <c:set var="ANY" value="<%=Shift.ANY%>"/>
        <c:set var="DAY" value="<%=Shift.DAY%>"/>
        <c:set var="NIGHT" value="<%=Shift.NIGHT%>"/>
        <form:form action="/edit" method="get" name="editOwner">
            <input type="hidden" id="editOwnerUsername" name="username" value="${owner.username}">
            <button type="submit">Edit</button>
        </form:form>
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
                            <input type="hidden" id="editMemberUsername" name="username" value="${member.username}">
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
        <c:forEach items="${intervals}" var="interval">
            <div>
                <c:forEach items="${interval}" var="date">
                    <div>
                        ${date.toString()}
                        <c:forEach items="${date.membersShifts}" var="shift">
                            <c:set var="availability" scope="session" value="${shift.available}"/>
                            <c:if test="${availability == true}">
                                ${shift.member.name}
                                ${shift.allocatedShift}
                            </c:if>
                        </c:forEach>
                        <form:form action="/calendar/editHoliday" method="post" name="editedCalendarDate">
                            <input hidden id="editedCalendarDateId" name="id" value="${date.id}">
                            ${date.HolidayOrWeekendToString()}
                            <button type="submit">Change</button>
                        </form:form>
                        <form:form action="/calendar/editDateCapacity" method="post" name="editedCalendarDate">
                            <input hidden name="id" value="${date.id}">
                            ${date.capacity}
                            <input id="editedCalendarDateDayCapacity" name="capacity['DAY']" type="number" min="0">
                            <input id="editedCalendarDateNightCapacity" name="capacity['NIGHT']" type="number" min="0">
                            <button type="submit">Set Capacities</button>
                        </form:form>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <h1>Add New Interval</h1>
        <form:form action="/calendar/addInterval" method="post" name="newInterval">
            <div>
                <input id="initDate" type="date" name="initDate">
            </div>
            <div>
                <input id="endDate" type="date" name="endDate">
            </div>
            <div>
                <input name="defaultCapacity['DAY']" type="number" min="0">
            </div>
            <div>
                <input name="defaultCapacity['NIGHT']" type="number" min="0">
            </div>
            <div>
                <input name="defaultCapacityOnHolidays['DAY']" type="number" min="0">
            </div>
            <div>
                <input name="defaultCapacityOnHolidays['NIGHT']" type="number" min="0">
            </div>
            <button type="submit">Submit</button>
        </form:form>
    </security:authorize>
    <script>
        $.notify.defaults({
            // whether to hide the notification on click
            clickToHide: true,
            // whether to auto-hide the notification
            autoHide: false,
            // if autoHide, hide after milliseconds
            autoHideDelay: 5000,
            // show the arrow pointing at the element
            arrowShow: true,
            // arrow size in pixels
            arrowSize: 5,
            // position defines the notification position though uses the defaults below
            position: 'top',
            // default positions
            elementPosition: 'top left',
            globalPosition: 'top right',
            // default style
            style: 'bootstrap',
            // default class (string or [string])
            className: 'error',
            // show animation
            showAnimation: 'slideDown',
            // show animation duration
            showDuration: 400,
            // hide animation
            hideAnimation: 'slideUp',
            // hide animation duration
            hideDuration: 200,
            // padding between element and notification
            gap: 2
        });
        $.notify("hfrihfinhsfuicsuinfhduifhsuifhduifhsuifhuidsfnhuidhfuishfidhfus idhfuidghfidugfdighfudhgfuidhgighfuidghfuidghfuidghfuidghfuidh", "success");
    </script>
</body>
</html>
