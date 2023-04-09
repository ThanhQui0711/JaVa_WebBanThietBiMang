<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i">
    <!-- google font web -->
    <link href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

    <style>
        html, body {
            width: 100%;
            height: 100%;
        }

        body {
            background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
            background-size: 400% 400%;
            animation: gradient 15s ease infinite;
        }

        @keyframes gradient {
            0% {
                background-position: 0% 50%;
            }
            50% {
                background-position: 100% 50%;
            }
            100% {
                background-position: 0% 50%;
            }
        }
    </style>
</head>
<body>

<c:if test="${result ne null}">
    <span id="${result.error ? 'error-message' : 'success-message'}" class="d-none">${result.message}</span>
</c:if>

<div class="d-flex justify-content-center align-items-center h-100">
    <div id="loginbox" class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
        <div class="panel panel-primary">
            <div style="padding-top: 30px" class="panel-body">
                <div class="panel-heading">
                    <div class="h3 panel-title text-center">Đăng ký người dùng mới</div>
                </div>

                <form accept-charset="UTF-8" method="post" id="form1" modelAttribute="crmUser" class="d-flex flex-column" style="gap: 15px;">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input name="username" placeholder="username" class="form-control" required/>
                    <input name="password" type="password" placeholder="password" class="form-control" required/>
                    <input name="tenKhachHang" placeholder="Họ và tên" class="form-control" required/>
                    <input name="soDienThoaikh" placeholder="số điện thoại" class="form-control" required/>
                    <input name="email" placeholder="email" type="email" class="form-control" required/>
                    <div class="d-flex justify-content-center">
                        <div class="col-sm-6">
                            <button type="submit" class="btn btn btn-outline-primary btn-lg">Đăng ký</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>

</body>
<script src="<c:url value='/resources/libs/jquery-3.4.1.min.js'/>"></script>
<script src="<c:url value='/resources/libs/bootstrap/js/bootstrap.js'/>"></script>
<script src="${pageContext.request.contextPath}/resources/libs/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/popperjs/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/notify.js"></script>
</html>