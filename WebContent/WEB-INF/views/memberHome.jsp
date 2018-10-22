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
    <c:url value="" var="contextPath"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<%--<link rel='stylesheet' href='https://cdn.rawgit.com/nizarmah/calendar-javascript-lib/master/calendarorganizer.min.css'>--%>
    <%--<link href="${contextPath}/resources/css/calendarStyle.css" rel="stylesheet" type="text/css" media="all" />--%>
    <title>HOMEPAGE</title>
</head>
<body>
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal" var="member"/>

        <c:set var="ANY" value="<%=Shift.ANY%>"/>
        <c:set var="DAY" value="<%=Shift.DAY%>"/>
        <c:set var="NIGHT" value="<%=Shift.NIGHT%>"/>

        <div class="pos-f-t" id="navbar">
            <nav class="navbar navbar-light bg-light">
                <h6>Welcome ${owner.name}!</h6>
            </nav>

            <div class="text-light bg-dark p-1">
            </div>
        </div>
        <div class="container-fluid bg-dark" align="center">

            <c:forEach items="${notifications}" var="notification">
                <div class="alert alert-info alert-dismissible fade show" role="alert">
                        ${notification.message}
                    <c:set var="hasDate" scope="session" value="${notification.calendarDate}"/>
                    <c:if test="${hasDate != null}">
                        <div><strong>Shift Required</strong>
                                ${notification.calendarDate.toString()} ${notification.shift}</div>
                        <form:form action="${s:mvcUrl('CC#editShiftFromNotification').build()}" method="post" name="notification">
                            <input type="hidden" name="id" value="${notification.id}">
                            <button type="submit" class="btn btn-info btn-sm">Change Shift</button>
                        </form:form>
                    </c:if>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:forEach>

            <div class="row d-inline-flex justify-content-center pt-4" align="center">
                <c:forEach items="${shifts}" var="shift">
                    <div class ="col-20 col-sm-60 col-md-80">
                        <div class="card h-10" style="width: 20rem;">
                            <div class="card-body">
                                <div align="center">
                                    <h5 class="card-title">${shift.date.toString()}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">${shift.isAvailableToString()}</h6>
                                    <c:set var="holidayOrWeekend" scope="session" value="${shift.date.holidayOrWeekend}"/>
                                    <c:if test="${holidayOrWeekend == true}">
                                        <form:form action="/calendar/changeAvailability" method="post" name="editedShift">
                                            <div class="form-group row">
                                                <input type="hidden" name="id" value="${shift.id}">
                                            </div>
                                            <button type="submit" class="btn btn-secondary btn-sm">Change Availability</button>
                                        </form:form>
                                    </c:if>
                                    <div class="card-body">
                                        <c:set var="availability" scope="session" value="${shift.available}"/>
                                        <c:if test="${availability == true}">
                                            <h6 class="card-subtitle mb-2 text-muted">Desired Shift</h6>
                                            <p>${shift.desiredShift}</p>
                                            <h6 class="card-subtitle mb-2 text-muted">Allocated Shift</h6>
                                            <p>${shift.allocatedShift}</p>
                                            <form:form action="/calendar/editShift" method="post" name="editedShift">
                                                <input type="hidden" id="editedShiftId" name="id" value="${shift.id}">
                                                <div class="form-group row" align="center">
                                                    <label for="changeDesiredShift" class="col-form-label col-form-label-sm">Change Desired Shift</label>
                                                    <select name="desiredShift" id="changeDesiredShift" class="form-control form-control-sm">
                                                        <option disabled selected>Select</option>
                                                        <option value="ANY">ANY</option>
                                                        <option value="DAY">DAY</option>
                                                        <option value="NIGHT">NIGHT</option>
                                                    </select>
                                                </div>
                                                <button type="submit" class="btn btn-secondary btn-sm">Edit</button>
                                            </form:form>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </security:authorize>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
