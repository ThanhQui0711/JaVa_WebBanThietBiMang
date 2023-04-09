<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main">
    <div class="container">
        <div class="row" style="border-bottom : 1px solid #000000">
            <div class=" col-xs-12 col-sm-12 col-md-12 col-lg-9">
                <div class="product-section">
                    <h2 class="title-product">
                        <a href="#" class="title">Hóa đơn của bạn</a>
                    </h2>
                </div>
            </div>
        </div>
        
        <c:if test="${!empty hoaDons}">
            <div class="container">
                <c:set var="tongTien" value="0" scope="page" />
                <div class="table table-horver col-xs-12 col-sm-12 col-md-12">
                    <table class="col-xs-12 col-sm-12 col-md-12">
                        <thead>
                        <tr>
                            <th style="text-align: center">Mã HĐ</th>
                            <th style="text-align: center">Ngày mua</th>
                            <th style="text-align: center">Tổng tiền</th>
                            <th style="text-align: center">Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${hoaDons}" var="hoaDon">
                            <tr>
                                <td>${hoaDon.mahd}</td>
                                <td style="text-align: center">
                                    <fmt:formatDate value="${hoaDon.ngayMua}" pattern="dd-MM-yyyy" />
                                </td>
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
                                    <button class="btn btn-primary"data-toggle="modal" data-target="#chiTiet${hoaDon.mahd}">
                                        <i class="fas fa-search"></i>
                                    </button>

                                    <div class="modal fade" id="chiTiet${hoaDon.mahd}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                                                    <img style="width: 100px; height: 50px;" src="data:image/png;base64,${chiTiet.sanPham.imageBase64}" alt="${chiTiet.sanPham.tenSanPham}" />
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
                </div>
            </div>
        </c:if>
        <c:if test="${empty hoaDons }">
            <h3 style="text-align: center">Bạn chưa có hóa đơn nào!</h3>
        </c:if>
    </div>
</div>