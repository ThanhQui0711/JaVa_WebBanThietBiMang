<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex flex-column">
    <div class="d-flex justify-content-between mb-2">
        <h3>Quản lý sản phẩm</h3>
        <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Tạo mới</button>
        <jsp:include page="create-update.jsp">
            <jsp:param name="title" value="Tạo mới sản phẩm"/>
            <jsp:param name="modalId" value="exampleModal"/>
        </jsp:include>
    </div>

    <jsp:include page="./filter.jsp">
        <jsp:param name="name" value="${param.tenSanPham}"/>
        <jsp:param name="category" value="${param.maDanhMuc}"/>
        <jsp:param name="status" value="${param.maDanhMuc}"/>
    </jsp:include>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Hình ảnh</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Loại sản phẩm</th>
            <th scope="col">Giá</th>
            <th scope="col">Trạng thái</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products.content}" varStatus="status">
            <tr>
                <th scope="row">${status.index + 1}</th>
                <td>
                    <img style="width: 100px; height: 50px" src="data:image/png;base64,${product.imageBase64}" />
                </td>
                <td>${product.tenSanPham}</td>
                <td>${product.danhMuc.tenDanhMuc}</td>
                <td><fmt:formatNumber value="${product.giaSanPham}" type="CURRENCY" currencyCode="VND" maxFractionDigits="0"/></td>
                <td>
                    <span class="font-weight-bold ${product.trangThai eq 1 ? 'text-success' : 'text-danger'}">
                        ${product.trangThai eq 1 ? 'Hoạt đông' : 'Không hoạt động'}
                    </span>
                </td>
                <td>
                    <button class="btn btn-primary"data-toggle="modal" data-target="#productDetail${product.masp}">
                        <i class="fas fa-search"></i>
                    </button>
                    <jsp:include page="detail.jsp">
                        <jsp:param name="modalId" value="productDetail${product.masp}" />
                        <jsp:param name="soLuongTrongKho" value="${product.soLuongTrongKho}"/>
                        <jsp:param name="baoHanh" value="${product.baoHanh}"/>
                        <jsp:param name="moTa" value="${product.moTa}"/>
                        <jsp:param name="nhaCungCap" value="${product.nhaCungCap}"/>
                    </jsp:include>
                    <button class="btn btn-warning ml-2" data-toggle="modal" data-target="#product${product.masp}">
                        <i class="fas fa-pen-square"></i>
                    </button>
                    <jsp:include page="create-update.jsp">
                        <jsp:param name="title" value="Cập nhật sản phẩm"/>
                        <jsp:param name="modalId" value="product${product.masp}"/>
                        <jsp:param name="tenSanPham" value="${product.tenSanPham}"/>
                        <jsp:param name="trangThai" value="${product.trangThai}"/>
                        <jsp:param name="masp" value="${product.masp}"/>
                        <jsp:param name="giaSanPham" value="${product.giaSanPham}"/>
                        <jsp:param name="soLuongTrongKho" value="${product.soLuongTrongKho}"/>
                        <jsp:param name="baoHanh" value="${product.baoHanh}"/>
                        <jsp:param name="moTa" value="${product.moTa}"/>
                        <jsp:param name="nhaCungCap" value="${product.nhaCungCap}"/>
                        <jsp:param name="danhMuc" value="${product.danhMuc.id}"/>
                    </jsp:include>
                    <button class="btn btn-danger ml-2" data-toggle="modal" data-target="#productDelete${product.masp}">
                        <i class="fas fa-trash"></i>
                    </button>
                    <jsp:include page="../../common/_confirmation.jsp">
                        <jsp:param name="url" value="${pageContext.request.contextPath}/admin/products/delete/${product.masp}"/>
                        <jsp:param name="modalId" value="productDelete${product.masp}"/>
                        <jsp:param name="message" value="Bạn có chắc chắn muốn xóa danh mục: ${product.tenSanPham}"/>
                    </jsp:include>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <c:url var="query" value="/admin/products">
            <c:param name="tenSanPham" value="${param.tenSanPham}"/>
            <c:param name="maDanhMuc" value="${param.maDanhMuc}"/>
            <c:param name="maDanhMuc" value="${param.maDanhMuc}"/>
            <c:param name="page" value="_page_"/>
        </c:url>
        <jsp:include page="../../common/_pagination.jsp">
            <jsp:param name="url" value="${query}"/>
            <jsp:param name="currentPage" value="${param.page ne null ? param.page : 1}"/>
        </jsp:include>
    </div>
</div>
