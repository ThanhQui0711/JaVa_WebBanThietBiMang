<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex flex-column">
    <div class="d-flex justify-content-between mb-2">
        <h3>Quản lý khách hàng</h3>
    </div>

    <jsp:include page="./filter.jsp">
        <jsp:param name="name" value="${param.tenKhachHang}"/>
        <jsp:param name="status" value="${param.enabled}"/>
    </jsp:include>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên khách hàng</th>
            <th scope="col">Tên đăng nhập</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">SL hóa đơn</th>
            <th scope="col">Ngày tạo</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users.content}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.tenKhachHang}</td>
                <td>${user.username}</td>
                <td>
                    <span class="font-weight-bold ${user.enabled ? 'text-success' : 'text-danger'}">${user.enabled ? 'Hoạt động' : 'Không hoạt động'}</span>
                </td>
                <td>${user.hoaDons.size()}</td>
                <td><fmt:formatDate value="${user.ngayTao}" pattern="dd-MM-yyyy" /></td>
                <td>
                    <c:if test="${user.enabled}">
                        <button class="btn btn-warning" data-toggle="modal" data-target="#lock${user.username}">
                            <i class="fas fa-lock"></i>
                        </button>
                        <jsp:include page="../../common/_confirmation.jsp">
                            <jsp:param name="url" value="${pageContext.request.contextPath}/admin/users/lock/${user.username}"/>
                            <jsp:param name="modalId" value="lock${user.username}"/>
                            <jsp:param name="message" value="Bạn có chắc chắn muốn khóa tài khoản: ${user.username}"/>
                        </jsp:include>
                    </c:if>

                    <c:if test="${!user.enabled}">
                        <button class="btn btn-primary" data-toggle="modal" data-target="#unlock${user.username}">
                            <i class="fas fa-unlock"></i>
                        </button>
                        <jsp:include page="../../common/_confirmation.jsp">
                            <jsp:param name="url" value="${pageContext.request.contextPath}/admin/users/unlock/${user.username}"/>
                            <jsp:param name="modalId" value="unlock${user.username}"/>
                            <jsp:param name="message" value="Bạn có chắc chắn muốn mở khóa tài khoản: ${user.username}"/>
                        </jsp:include>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <c:url var="query" value="/admin/users">
            <c:param name="page" value="_page_" />
            <c:param name="tenKhachHang" value="${param.tenKhachHang}"/>
            <c:param name="trangThai" value="${param.trangThai}"/>
        </c:url>
        <jsp:include page="../../common/_pagination.jsp">
            <jsp:param name="url" value="${query}"/>
            <jsp:param name="currentPage" value="${param.page ne null ? param.page : 1}"/>
        </jsp:include>
    </div>
</div>
