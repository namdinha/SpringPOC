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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>EDIT MEMBER</title>
</head>
<body>
<security:authorize access="isAuthenticated()">
    <security:authentication property="principal" var="owner"/>
    <div class="container-flex bg-dark">
        <div class="container d-flex flex-column pt-4 text-light bg-dark" style="width: 30rem;">
            <h4 align="center">EDIT MEMBER</h4>
            <form:form action="/edit/member" method="post" name="member">
                <input type=hidden id="username" type="text" name="username" value="${editMember.username}">
                <div class="form-group row">
                    <label for="owner.name" class="col-sm-2 col-form-label col-form-label-sm">Name:</label>
                    <div class="col-sm-10">
                        <input id="owner.name" type="text" class="form-control form-control-sm" name="name" value="${editMember.name}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="owner.email" class="col-sm-2 col-form-label col-form-label-sm">E-mail:</label>
                    <div class="col-sm-10">
                        <input id="owner.email" type="text" class="form-control form-control-sm" name="email" value="${editMember.email}">
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
</security:authorize>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>
