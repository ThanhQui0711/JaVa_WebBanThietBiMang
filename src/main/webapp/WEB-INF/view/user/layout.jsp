<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Website bán PC/laptop</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/libs/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/responsive.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <style>
        #ertk, #ermk {
            color: #ff0000;
        }
    </style>
</head>
<body>
<c:if test="${result ne null}">
    <span id="${result.error ? 'error-message' : 'success-message'}" class="d-none">${result.message}</span>
</c:if>

<jsp:include page="common/_header.jsp"/>
<jsp:include page="${pageUrl}"/>
<jsp:include page="common/_footer.jsp"/>

</body>
<script src="${pageContext.request.contextPath}/resources/js/common/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/jquery-easing.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/datetimepicker-bootstrap.js"></script>
<script src="https://kit.fontawesome.com/68830dcf1e.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/scripts.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/notify.js"></script>
</html>