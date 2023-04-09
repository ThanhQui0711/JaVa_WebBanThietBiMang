<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Quản Lý Cửa Hàng Thiết Bị Mạng</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/datepicker.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />

</head>
<body>
<c:if test="${result ne null}">
    <span id="${result.error ? 'error-message' : 'success-message'}" class="d-none">${result.message}</span>
</c:if>

<div id="wrapper">
    <jsp:include page="common/_sidebar.jsp"/>
    <div id="content-wrapper" class="d-flex flex-column" style="overflow-x: unset">
        <div id="content">
            <jsp:include page="common/_header.jsp" />
            <div class="p-4">
                <div class="p-2 bg-white rounded">
                    <jsp:include page="${pageUrl}"/>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/resources/js/common/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/jquery-easing.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/moment.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/datetimepicker-bootstrap.js"></script>
<!--<script src="@{/j$/common/datatable.js}"></script>-->
<script src="https://kit.fontawesome.com/68830dcf1e.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/scripts.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common/notify.js"></script>

</html>