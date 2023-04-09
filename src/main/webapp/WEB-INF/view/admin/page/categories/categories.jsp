<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex flex-column">
    <div class="d-flex justify-content-between mb-2">
        <h3>Quản lý danh mục</h3>
        <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Tạo mới</button>
        <jsp:include page="create-update.jsp">
            <jsp:param name="title" value="Tạo mới danh mục"/>
            <jsp:param name="modalId" value="exampleModal"/>
        </jsp:include>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên danh mục</th>
            <th scope="col">Ngày tạo</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories.content}" varStatus="status">
            <tr>
                <th scope="row">${status.index + 1}</th>
                <td>${category.tenDanhMuc}</td>
                <td><fmt:formatDate value="${category.ngayTao}" pattern="dd-MM-yyyy" /></td>
                <td>
                    <button class="btn btn-warning" data-toggle="modal" data-target="#category${category.id}">
                        <i class="fas fa-pen-square"></i>
                    </button>
                    <jsp:include page="create-update.jsp">
                        <jsp:param name="title" value="Cập nhật danh mục"/>
                        <jsp:param name="modalId" value="category${category.id}"/>
                        <jsp:param name="name" value="${category.tenDanhMuc}"/>
                        <jsp:param name="id" value="${category.id}"/>
                    </jsp:include>
                    <button class="btn btn-danger ml-2" data-toggle="modal" data-target="#categoryDelete${category.id}">
                        <i class="fas fa-trash"></i>
                    </button>
                    <jsp:include page="../../common/_confirmation.jsp">
                        <jsp:param name="url" value="${pageContext.request.contextPath}/admin/categories/delete/${category.id}"/>
                        <jsp:param name="modalId" value="categoryDelete${category.id}"/>
                        <jsp:param name="message" value="Bạn có chắc chắn muốn xóa danh mục: ${category.tenDanhMuc}"/>
                    </jsp:include>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <jsp:include page="../../common/_pagination.jsp">
            <jsp:param name="url" value="${pageContext.request.contextPath}/admin/categories?page=_page_"/>
            <jsp:param name="currentPage" value="${param.page ne null ? param.page : 1}"/>
        </jsp:include>
    </div>
</div>
