<%@ page import="com.sap.poc.models.Shift" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>HOMEPAGE</title>
</head>
<body>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script>
    <script src="/resources/js/notify.js"></script>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal" var="owner"/>

        <c:set var="ANY" value="<%=Shift.ANY%>"/>
        <c:set var="DAY" value="<%=Shift.DAY%>"/>
        <c:set var="NIGHT" value="<%=Shift.NIGHT%>"/>

        <div class="pos-f-t" id="navbar">
            <nav class="navbar navbar-light bg-light">
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarMyAccount" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    My Account
                </button>
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarAddMember" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Add Member
                </button>
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarShowMembers" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Show Members
                </button>
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarCreateInterval" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Create Interval
                </button>
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarCreateNotification" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Create Notification
                </button>
                <h6>Welcome ${owner.name}!</h6>
            </nav>

            <div class="text-light bg-dark p-1">
                <div class="collapse" id="navbarMyAccount" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" style="width: 30rem;">
                        <h4 align="center">EDIT ACCOUNT</h4>
                        <form:form action="/edit" method="post" name="owner">
                            <input type=hidden id="username" type="text" name="username" value="${editOwner.username}">
                            <div class="form-group row">
                                <label for="owner.name" class="col-sm-2 col-form-label col-form-label-sm">Name:</label>
                                <div class="col-sm-10">
                                    <input id="owner.name" type="text" class="form-control form-control-sm" name="name" value="${editOwner.name}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="owner.email" class="col-sm-2 col-form-label col-form-label-sm">E-mail:</label>
                                <div class="col-sm-10">
                                    <input id="owner.email" type="text" class="form-control form-control-sm" name="email" value="${editOwner.email}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="owner.password" class="col-sm-2 col-form-label col-form-label-sm">Password:</label>
                                <div class="col-sm-10">
                                    <input id="owner.password" type="password" class="form-control form-control-sm" name="password">
                                </div>
                            </div>
                            <div class="form-select-button" align="right">
                                <button type="submit" class="btn btn-dark btn-sm">Submit</button>
                            </div>
                        </form:form>
                    </div>
                </div>

                <div class="collapse" id="navbarAddMember" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" style="width: 30rem;">
                        <h4 align="center">ADD MEMBER</h4>
                        <form:form action="/register/member" method="post" name="member">
                            <div class="form-group row">
                                <label for="name" class="col-sm-2 col-form-label col-form-label-sm">Name:</label>
                                <div class="col-sm-10">
                                    <input id="name" type="text" class="form-control form-control-sm" name="name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-sm-2 col-form-label col-form-label-sm">E-mail:</label>
                                <div class="col-sm-10">
                                    <input id="email" type="text" class="form-control form-control-sm" name="email">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="member.username" class="col-sm-2 col-form-label col-form-label-sm">Username:</label>
                                <div class="col-sm-10">
                                    <input id="member.username" type="text" class="form-control form-control-sm" name="username">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-sm-2 col-form-label col-form-label-sm">Password:</label>
                                <div class="col-sm-10">
                                    <input id="password" type="password" class="form-control form-control-sm" name="password">
                                </div>
                            </div>
                            <div class="form-select-button" align="right">
                                <button type="submit" class="btn btn-dark btn-sm">Register</button>
                            </div>
                        </form:form>
                    </div>
                </div>

                <div class="collapse" id="navbarShowMembers" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" style="width: 40rem;">
                        <h4 align="center">MEMBERS IN YOUR TEAM</h4>
                        <table class="table table-sm">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col" align="center">MEMBER</th>
                                <th scope="col" align="center">E-MAIL</th>
                                <th scope="col" align="center"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${members}" var="member">
                                <tr>
                                    <td align="center" class="text-light">${member.name}</td>
                                    <td align="center" class="text-light">${member.email}</td>
                                    <td align="center" class="text-light">
                                        <form:form action="/edit/member" method="get" name="editMember">
                                            <input type="hidden" id="editMemberUsername" name="username" value="${member.username}">
                                            <button type="submit" class="btn btn-dark btn-sm">Edit</button>
                                        </form:form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="collapse" id="navbarCreateInterval" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" align="center" style="width: 30rem;">
                        <h4 align="center">CREATE NEW INTERVAL</h4>
                        <form:form action="/calendar/addInterval" method="post" name="newInterval">
                            <div class="form-group row">
                                <label for="initDate" class="col-sm-2 col-form-label col-form-label-sm">Begin</label>
                                <div class="col-sm-20">
                                    <input id="initDate" type="date" class="form-control form-control-sm" name="initDate">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="endDate" class="col-sm-2 col-form-label col-form-label-sm">End</label>
                                <div class="col-sm-20">
                                    <input id="endDate" type="date" class="form-control form-control-sm" name="endDate">
                                </div>
                            </div>
                            <p>
                                <label for="defaultCapacity" class="col-form-label col-form-label-sm"><h6>Default Capacity</h6></label>
                            </p>
                            <div id="defaultCapacity" class="form-group row">
                                <label for="defaultCapacityDAY" class="col-sm-2 col-form-label col-form-label-sm">Day</label>
                                <div class="col-sm-3">
                                    <input id="defaultCapacityDAY" name="defaultCapacity['DAY']" type="number" class="form-control form-control-sm" min="0">
                                </div>
                                <label for="defaultCapacityNIGHT" class="col-sm-2 col-form-label col-form-label-sm">Night</label>
                                <div class="col-sm-3">
                                    <input id="defaultCapacityNIGHT" name="defaultCapacity['NIGHT']" type="number" class="form-control form-control-sm" min="0">
                                </div>
                            </div>
                            <p>
                                <label for="holidayCapacity" class="col-form-label col-form-label-sm"><h6>Holiday Capacity</h6></label>
                            </p>
                            <div id="holidayCapacity" class="form-group row">
                                <label for="holidayCapacityDAY" class="col-sm-2 col-form-label col-form-label-sm">Day</label>
                                <div class="col-sm-3">
                                    <input id="holidayCapacityDAY" name="defaultCapacityOnHolidays['DAY']" type="number" class="form-control form-control-sm" min="0">
                                </div>
                                <label for="holidayCapacityNIGHT" class="col-sm-2 col-form-label col-form-label-sm">Night</label>
                                <div class="col-sm-3">
                                    <input id="holidayCapacityNIGHT" name="defaultCapacityOnHolidays['NIGHT']" type="number" class="form-control form-control-sm" min="0">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark btn-sm">Submit</button>
                        </form:form>
                    </div>
                </div>

                <div class="collapse" id="navbarCreateNotification" data-parent="#navbar">
                    <div class="container d-flex flex-column  pt-4" align="center" style="width: 30rem;">
                        <h4 align="center">CREATE NOTIFICATION</h4>
                        <form:form action="${s:mvcUrl('NC#createNotification').build()}" method="post" name="notification">
                            <div class="form-group row">
                                <select class="custom-select my-1 mr-sm-2" name="calendarDate" id="calendarDate">
                                    <option value="null">None</option>
                                    <c:forEach items="${intervals}" var="interval">
                                        <c:forEach items="${interval}" var="date">
                                            <option value="${date}">${date.toString()}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group row">
                                <select class="custom-select my-1 mr-sm-2" name="shift" id="shift">
                                    <option value="ANY">ANY</option>
                                    <option value="DAY">DAY</option>
                                    <option value="NIGHT">NIGHT</option>
                                </select>
                            </div>
                            <div class="form-group row">
                                <div>
                                    <textarea id="message" name="message" rows="3" class="form-control"></textarea>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark btn-sm">Submit</button>
                        </form:form>
                    </div>
                </div>

            </div>
        </div>

        <div class="container-fluid bg-dark" align="center">
            <div class="row d-inline-flex justify-content-center pt-4" align="center">
                <c:forEach items="${intervals}" var="interval">
                        <c:forEach items="${interval}" var="date">
                            <div class ="col-20 col-sm-60 col-md-80">
                                <div class="card h-10" style="width: 20rem;">
                                    <div class="card-body">
                                        <div align="center">
                                            <h5 class="card-title">${date.toString()}</h5>
                                            <form:form action="/calendar/editHoliday" method="post" name="editedCalendarDate">
                                                <input hidden id="editedCalendarDateId" name="id" value="${date.id}">
                                                <h6 class="card-subtitle mb-2 text-muted">
                                                    ${date.HolidayOrWeekendToString()}
                                                    <button type="submit" class="btn btn-secondary btn-sm">Change</button>
                                                </h6>
                                            </form:form>
                                        </div>
                                        <table class="table table-sm">
                                            <tbody>
                                                <c:forEach items="${date.membersShifts}" var="shift">
                                                    <tr>
                                                        <c:set var="availability" scope="session" value="${shift.available}"/>
                                                        <c:if test="${availability == true}">
                                                            <td align="center">${shift.member.name}</td>
                                                            <td align="center">${shift.allocatedShift}</td>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="card-body">
                                            <form:form action="/calendar/editDateCapacity" method="post" name="editedCalendarDate">
                                                <input hidden name="id" value="${date.id}">
                                                <div align="center">
                                                    <h6 class="card-subtitle mb-2 text-muted">
                                                        Capacity
                                                    </h6>
                                                    <div class="form-group row">
                                                        <label for="editedCalendarDateDayCapacity" class="col-form-label col-form-label-sm">Day</label>
                                                        <div class="col-sm-4">
                                                            <input id="editedCalendarDateDayCapacity" class="form-control form-control-sm" name="capacity['DAY']" type="number" min="0" max="10" value="${date.capacity.get(DAY)}">
                                                        </div>
                                                        <label for="editedCalendarDateNightCapacity" class="col-form-label col-form-label-sm">Night</label>
                                                        <div class="col-sm-4">
                                                            <input id="editedCalendarDateNightCapacity" class="form-control form-control-sm" name="capacity['NIGHT']" type="number" min="0" max="10" value="${date.capacity.get(NIGHT)}">
                                                        </div>
                                                    </div>
                                                    <button type="reset" class="btn btn-secondary btn-sm">Reset</button>
                                                    <button type="submit" class="btn btn-secondary btn-sm">Change Capacities</button>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                </c:forEach>
            </div>
        </div>

    </security:authorize>
    <%--<script>--%>
        <%--$.notify.defaults({--%>
            <%--// whether to hide the notification on click--%>
            <%--clickToHide: true,--%>
            <%--// whether to auto-hide the notification--%>
            <%--autoHide: false,--%>
            <%--// if autoHide, hide after milliseconds--%>
            <%--autoHideDelay: 5000,--%>
            <%--// show the arrow pointing at the element--%>
            <%--arrowShow: true,--%>
            <%--// arrow size in pixels--%>
            <%--arrowSize: 5,--%>
            <%--// position defines the notification position though uses the defaults below--%>
            <%--position: 'top',--%>
            <%--// default positions--%>
            <%--elementPosition: 'top left',--%>
            <%--globalPosition: 'top right',--%>
            <%--// default style--%>
            <%--style: 'bootstrap',--%>
            <%--// default class (string or [string])--%>
            <%--className: 'error',--%>
            <%--// show animation--%>
            <%--showAnimation: 'slideDown',--%>
            <%--// show animation duration--%>
            <%--showDuration: 400,--%>
            <%--// hide animation--%>
            <%--hideAnimation: 'slideUp',--%>
            <%--// hide animation duration--%>
            <%--hideDuration: 200,--%>
            <%--// padding between element and notification--%>
            <%--gap: 2--%>
        <%--});--%>
        <%--$.notify("hfrihfinhsfuicsuinfhduifhsuifhduifhsuifhuidsfnhuidhfuishfidhfus idhfuidghfidugfdighfudhgfuidhgighfuidghfuidghfuidghfuidghfuidh", "success");--%>
    <%--</script>--%>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
