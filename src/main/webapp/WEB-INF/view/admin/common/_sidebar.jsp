<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="servletPath" value="${requestScope['javax.servlet.forward.request_uri']}" scope="page" />
<c:set var="adminPrefix" value="${pageContext.request.contextPath}/admin" scope="page" />

<c:set var="receipts" value="${adminPrefix}/receipts" />
<c:set var="users" value="${adminPrefix}/users" />
<c:set var="products" value="${adminPrefix}/products" />
<c:set var="categories" value="${adminPrefix}/categories" />

<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
    <a class="sidebar-brand align-items-center justify-content-center d-none d-md-flex">
        <div  class="sidebar-brand-text mx-3">ADMIN</div>
    </a>
    <!-- Divider -->
    <hr class="sidebar-divider">

    <div id="admin-sidebar">
        <div class="sidebar-heading">Chức năng chính</div>

        <li class="nav-item ${servletPath eq categories ? 'active' : ''}">
            <a class="nav-link" href="${adminPrefix}/categories">
                <i class="fas fa-list"></i>
                <span>Quản lý danh mục</span>
            </a>
        </li>

        <li class="nav-item ${servletPath eq products ? 'active' : ''}">
            <a class="nav-link" href="${adminPrefix}/products">
                <i class="fas fa-phone-laptop"></i>
                <span>Quản lý sản phẩm</span>
            </a>
        </li>

        <li class="nav-item ${servletPath eq users ? 'active' : ''}">
            <a class="nav-link" href="${adminPrefix}/users">
                <i class="fas fa-users"></i>
                <span>Quản lý người dùng</span>
            </a>
        </li>

        <li class="nav-item ${servletPath eq receipts ? 'active' : ''}">
            <a class="nav-link" href="${adminPrefix}/receipts">
                <i class="fal fa-receipt"></i>
                <span>Quản lý đơn hàng</span>
            </a>
        </li>
    </div>
</ul>