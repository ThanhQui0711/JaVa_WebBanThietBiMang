<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>

        <title> Chào mừng bạn đên với thế giới router H2L </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i">
        <!-- google font web -->
        <link href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css"  rel="stylesheet" type="text/css">
        <script src="<c:url value='/resources/libs/jquery/jquery-3.5.0.min.js'/>"></script>
        <script src="<c:url value='/resources/libs/bootstrap/bootstrap.min.js'/>"></script>  

        <style>
            html {
                height:100%;
            }

            body {
                margin:0;
            }

            .bg {
                animation:slide 3s ease-in-out infinite alternate;
                background-image: linear-gradient(-60deg, #6c3 50%, #09f 50%);
                bottom:0;
                left:-50%;
                opacity:.5;
                position:fixed;
                right:-50%;
                top:0;
                z-index:-1;
            }

            .bg2 {
                animation-direction:alternate-reverse;
                animation-duration:4s;
            }

            .bg3 {
                animation-duration:5s;
            }

            .content {
                background-color:rgba(255,99,71,0.4);
                border-radius:.25em;
                box-shadow:0 0 .25em rgba(0,0,0,.25);
                box-sizing:border-box;
                left:50%;
                padding:10vmin;
                position:fixed;
                text-align:center;
                top:50%;
                transform:translate(-50%, -50%);
            }

            h1 {
                font-family:monospace;
            }

            @keyframes slide {
                0% {
                    transform:translateX(-25%);
                }
                100% {
                    transform:translateX(25%);
                }
            }
        </style>
    </head >
    <%---------------------------------------------- Phần Body  ------------------------------------------------------------------%>
    <body>
        <div class="bg"></div>
        <div class="bg bg2"></div>
        <div class="bg bg3"></div>
        <div >

            <div id="loginbox" style="margin-left:630px ; margin-block: 200px"
                 class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

                <div class="panel panel-info"  style="background-color: #1d1b4d75; color:#1d1b4d75; border-color: #1d1b4d75">

                    <div class="panel-heading text-light text-center panel-relative" style="background-color: #1d1b4d75 ">

                        <div class="panel-title" style="align-items:center">ĐĂNG NHẬP</div>
                    </div>

                    <div style="padding-top: 10px" class="panel-body">

                        <c:url var="loginUrl" value="/authenticateTheUser"></c:url>
                        <form action="${loginUrl}" 
                              method="POST" class="form-horizontal">

                            <div class="form-group">
                                <div class="col-xs-15">
                                    <div>

                                        <c:if test="${param.error != null}">

                                            <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                                Invalid username and password.
                                            </div>

                                        </c:if>

                                        <!-- Check for logout -->

                                        <c:if test="${param.logout != null}">

                                            <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                                You have been logged out.
                                            </div>

                                        </c:if>

                                    </div>
                                </div>
                            </div>

                            <!-- User name -->
                            <div style="margin-bottom: 25px" class="input-group">
                               


                                <input type="text" name="username" placeholder="username" class="form-control">
                            </div>

                            <!-- Password -->
                            <div style="margin-bottom: 25px" class="input-group">
                                

                                <input type="password" name="password" placeholder="password" class="form-control" >
                            </div>

                            <!-- Login/Submit Button -->
                            <!-- Sửa lại thành row để button thẳng hàng -->
                            <div style="margin-top: 10px" class="row">						

                                <div class="col-sm-6  ">
                                    <button type="submit" class="btn btn-success col-sm-6" style="background-color: #000000; border-color: #000000">Login</button>
                                </div>
                            
                                 <div class="col-sm-6 float-right">
                                    <a href="${pageContext.request.contextPath}/register/showRegistrationForm" class="btn btn-primary float-right col-sm-6" role="button" aria-pressed="true" style="background-color: #894f81">Register</a>
                                </div>
                               

                            </div>


                            <!-- I'm manually adding tokens ... Bro! -->

                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}" />

                        </form>

                    </div>

                </div>



            </div>

        </div>

    </body>
</html>