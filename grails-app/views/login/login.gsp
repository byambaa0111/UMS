<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 11/2/2020
  Time: 5:34 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <asset:stylesheet src="application.css"/>
</head>

<body>
<body class="bg-primary">
<!--app open-->
    <div id="app" class="page">
    <section class="section">
        <div class="container">
            <div class="">

                <!--single-page open-->
                <div class="single-page">
                    <div class="">
                        <div class="wrapper wrapper2">
                            <g:form  controller ="login" action="login" id="login" class="card-body" tabindex="500">

                                <h3 class="text-dark">Login</h3>

                                <div class="mail">
                                    <g:textField name="userId" required="" class="form-control" value="admin" />
                                </div>
                                <div class="passwd">
                                    <g:passwordField class="form-control" name="password" required="" value="admin4851"/>
                                </div>

                               %{-- <p class="mb-3 text-right"><a href="forgot.html">Forgot Password</a></p>
--}%
                                <div  class="submit">
                                    <g:actionSubmit class="btn btn-primary btn-block"  value="Login" controller="login" action="login" />
                                </div>

                               %{-- <div class="signup mb-0" >
                                    <p class="text-dark mb-0">Don't have account?<a href="register.html" class="text-primary ml-1">Sign UP</a></p>
                                </div>--}%
                            </g:form>
                           %{-- <div class="card-body border-top">
                                <a class="btn  btn-social btn-facebook btn-block"><i class="fa fa-facebook"></i>Sign in with Facebook</a>
                                <a class="btn  btn-social btn-google btn-block"><i class="fa fa-google-plus"></i>Sign in with Google</a>
                            </div>--}%
                        </div>
                    </div>
                </div>
                <!--single-page closed-->

            </div>
        </div>
    </section>
</div>
</body>
</body>
</html>