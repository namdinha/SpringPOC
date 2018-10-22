<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <c:url value="" var="contextPath"/>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTER/LOGIN</title>
    </head>
    <body>
        <div class="pos-f-t" id="navbar">
            <nav class="navbar navbar-light bg-light">
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarRegister" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Register
                </button>
                <button class="btn btn-light" type="button" data-toggle="collapse" data-target="#navbarLogin" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                    Login
                </button>
            </nav>

            <div class="text-light bg-dark p-1">
                <div class="collapse" id="navbarRegister" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" style="width: 30rem;">
                        <h4 align="center">REGISTER</h4>
                        <form:form action="${s:mvcUrl('RC#registerOwner').build()}" method="post" name="user">
                            <div class="form-group row">
                                <label for="name" class="col-sm-2 col-form-label col-form-label-sm">Name</label>
                                <div class="col-sm-10">
                                    <input id="name" type="text" name="name" class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email" class="col-sm-2 col-form-label col-form-label-sm">E-mail</label>
                                <div class="col-sm-10">
                                    <input id="email" type="email" name="email" class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="username" class="col-sm-2 col-form-label col-form-label-sm">Username</label>
                                <div class="col-sm-10">
                                    <input id="username" type="text" name="username" class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-sm-2 col-form-label col-form-label-sm">Password</label>
                                <div class="col-sm-10">
                                    <input id="password" type="password" name="password" class="form-control form-control-sm"/>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark btn-sm">Register</button>
                        </form:form>
                    </div>
                </div>

                <div class="collapse" id="navbarLogin" data-parent="#navbar">
                    <div class="container d-flex flex-column pt-4" style="width: 30rem;">
                        <h4 align="center">LOGIN</h4>
                        <form:form servletRelativeAction="/login" method="post">
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label col-form-label-sm">Name</label>
                                <div class="col-sm-10">
                                    <input type="text" name="username" class="form-control form-control-sm" />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label col-form-label-sm">Password</label>
                                <div class="col-sm-10">
                                    <input type="password" name="password" class="form-control form-control-sm" />
                                </div>
                            </div>
                            <button type="submit" class="btn btn-dark btn-sm">Login</button>
                        </form:form>
                    </div>
                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>