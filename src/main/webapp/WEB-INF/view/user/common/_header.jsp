<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="servletPath" value="${requestScope['javax.servlet.forward.request_uri']}" scope="page" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<c:set var="trangChu" value="${contextPath}/" />
<c:set var="hoaDon" value="${contextPath}/hoa-don" />
<c:set var="lienHe" value="${contextPath}/lien-he" />
<c:set var="gioiThieu" value="${contextPath}/gioi-thieu" />

<header>
    <div class="top">
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <p>
                        Welcome to the world of network equipment
                    </p>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                    <div class="main-menu-header">
                        <div id="nav-menu">
                            <ul>
                                <security:authorize access="isAnonymous()">
                                    <li><a href="${pageContext.request.contextPath}/showMyLoginPage">Đăng nhập</a></li>
                                    <li><a href="${pageContext.request.contextPath}/register/showRegistrationForm">Đăngký</a></li>
                                </security:authorize>
                                <security:authorize access="isAuthenticated()">
                                    <li>
                                        <a href="#">Tài Khoản</a>
                                        <ul>
                                            <li><a href="${pageContext.request.contextPath}/gio-hang">Giỏ Hàng</a></li>
                                            <li>
                                                <form id="logout" class="d-none" action="${pageContext.request.contextPath}/logout" method="post" >
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                </form>
                                                <a href="javascript:document.getElementById('logout').submit()">Đăng Xuất</a>
                                            </li>
                                        </ul>
                                    </li>
                                </security:authorize>
                                <security:authorize access="hasAuthority('ADMIN')">
                                    <li><a href="${pageContext.request.contextPath}/admin/categories">Admin</a></li>
                                </security:authorize>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="main-header">
        <div class="container">
            <div class="row">
                <div class="col-6 col-xs-6 col-sm-6 col-md-3 col-lg-3 order-md-0 order-0">
                    <div class="logo">
                        <a href="${pageContext.request.contextPath}/">
                            <img src="${pageContext.request.contextPath}/resources/images/logo6.png" alt="logo">
                        </a>
                    </div>
                </div>
                <div class="col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 order-md-1 order-2">
                    <div class="form-seach-product">
                        <div class="input-seach">
                            <form action="${pageContext.request.contextPath}/" method="GET">
                                <input hidden name="maDanhMuc" value="${param.maDanhMuc}"/>
                                <input type="text" placeholder="Wifi/router/kích sóng wifi..." name="tenSanPham" value="${param.tenSanPham}" class="form-control">
                                <button type="submit" class="btn-search-pro"><i class="fa fa-search"> </i></button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-6 col-xs-6 col-sm-6 col-md-3 col-lg-3 order-md-2 order-1" style="text-align: right">
                    <a href="#" class="icon-cart">
                        <div class="icon">
                            <a href="${pageContext.request.contextPath}/gio-hang">
                                <i class="fa fa-shopping-cart" style="font-size: 40px;" aria-hidden="true"></i>
                            </a>
                        </div>
                        <div class="info-cart">
                            <p>Giỏ hàng</p>
                            <security:authorize access="isAnonymous()">
                                <span>Người Dùng: Chưa Đăng Nhập </span>
                            </security:authorize>
                            <security:authorize access="isAuthenticated()">
                                <span>Người Dùng: <security:authentication property="name"/></span>
                            </security:authorize>
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="main-menu-header">
        <div class="container">
            <div id="nav-menu">
                <ul>
                    <li class="${servletPath eq trangChu ? 'current-menu-item' : ''}"><a href="${pageContext.request.contextPath}">Trang chủ</a></li>
                    <li><a href="#">Sản phẩm</a>
                        <ul>
                            <c:forEach var="category" items="${categories}">
                                <c:url var="category1" value="/">
                                    <c:param name="maDanhMuc" value="${category.id}"/>
                                </c:url>
                                <li><a href="${category1}">${category.tenDanhMuc}</a></li>
                            </c:forEach>

                        </ul>
                    </li>
                    <%-- <li class="${servletPath eq hoaDon ? 'current-menu-item' : ''}"><a href="${pageContext.request.contextPath}/hoa-don">Hóa đơn</a></li> --%>
                    <li class="${servletPath eq gioiThieu ? 'current-menu-item' : ''}"><a href="${pageContext.request.contextPath}/gioi-thieu">Giới thiệu</a></li> 
                    <li class="${servletPath eq lienHe ? 'current-menu-item' : ''}" ><a href="${pageContext.request.contextPath}/lien-he">Liên hệ</a></li> 
                </ul>
            </div>
        </div>
    </div>
</header>