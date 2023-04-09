<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="d-flex flex-column">
    <div class="d-flex justify-content-between mb-2">
        <h3>Quản lý hóa đơn</h3>
    </div>

    <jsp:include page="./filter.jsp">
        <jsp:param name="name" value="${param.username}"/>
    </jsp:include>

    <table class="table">
        <thead>
        <tr>
            <th style="text-align: center">Mã HĐ</th>
            <th style="text-align: center">Ngày mua</th>
            <th style="text-align: center">Địa chỉ</th>
            <th style="text-align: center">Số điện thoại</th>
            <th style="text-align: center">Tổng tiền</th>
            <th style="text-align: center">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hoaDon" items="${hoaDons.content}" varStatus="status">
            <tr>
                <td>${hoaDon.mahd}</td>
                <td style="text-align: center">
                    <fmt:formatDate value="${hoaDon.ngayMua}" pattern="dd-MM-yyyy"/>
                </td>
                <td>${hoaDon.diaChi}</td>
                <td>${hoaDon.soDienThoai}</td>
                <td style="text-align: center">
                    <span style="color : #ff0000" id="tongtienid">
                        <fmt:formatNumber
                                value="${hoaDon.tongGiaTien}"
                                type="CURRENCY"
                                currencyCode="VND"
                                maxFractionDigits="0"/>
                    </span>

                </td>
                <td style="text-align: center">
                    <button class="btn btn-primary" data-toggle="modal" data-target="#chiTiet${hoaDon.mahd}">
                        <i class="fas fa-search"></i>
                    </button>

                    <div class="modal fade" id="chiTiet${hoaDon.mahd}" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Chi Tiết Hóa Đơn</h5>
                                    <span>
                                        Tồng tiền - <fmt:formatNumber
                                            value="${hoaDon.tongGiaTien}"
                                            type="CURRENCY"
                                            currencyCode="VND"
                                            maxFractionDigits="0"/>
                                    </span>
                                </div>
                                <div class="modal-body">
                                    <table class="table table-striped">
                                        <thead>
                                        <tr>
                                            <th scope="col">Hình ảnh</th>
                                            <th scope="col">Tên sản phẩm</th>
                                            <th scope="col">Giá sản phẩm</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Tổng tiền</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach var="chiTiet" items="${hoaDon.chiTiets}">
                                            <tr>
                                                <td>
                                                    <img style="width: 100px; height: 50px;"
                                                         src="data:image/png;base64,${chiTiet.sanPham.imageBase64}"
                                                         alt="${chiTiet.sanPham.tenSanPham}"/>
                                                </td>
                                                <td>${chiTiet.sanPham.tenSanPham}</td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${chiTiet.gia}"
                                                            type="CURRENCY"
                                                            currencyCode="VND"
                                                            maxFractionDigits="0"/>
                                                </td>
                                                <td>${chiTiet.soLuong}</td>
                                                <td>
                                                    <fmt:formatNumber
                                                            value="${chiTiet.gia * chiTiet.soLuong}"
                                                            type="CURRENCY"
                                                            currencyCode="VND"
                                                            maxFractionDigits="0"/>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                                <div class="modal-footer justify-content-center">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center">
        <c:url var="query" value="/admin/receipts">
            <c:param name="page" value="_page_"/>
            <c:param name="username" value="${param.username}"/>
        </c:url>
        <jsp:include page="../../common/_pagination.jsp">
            <jsp:param name="url" value="${query}"/>
            <jsp:param name="currentPage" value="${param.page ne null ? param.page : 1}"/>
        </jsp:include>
    </div>
</div>
