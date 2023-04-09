<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="main">
    <div class="container">
        <div class="row" style="border-bottom : 1px solid #000000">
            <div class=" col-xs-12 col-sm-12 col-md-12 col-lg-9">
                <div class="product-section">
                    <h2 class="title-product">
                        <a href="#" class="title">Giỏ hàng của bạn</a>
                    </h2>
                </div>
            </div>
        </div>
       
        <c:if test="${!empty gioHang}">
            <div class="container">
                <c:set var="tongTien" value="0" scope="page"/>
                <div class="table table-horver col-xs-12 col-sm-12 col-md-12">
                    <table class="col-xs-12 col-sm-12 col-md-12">
                        <thead>
                        <tr>
                            <th style="text-align: center">Xóa</th>
                            <th style="text-align: center">Ảnh</th>
                            <th style="text-align: center">Tên Sản Phẩm</th>
                            <th style="text-align: center">Số Lượng</th>
                            <th style="text-align: center">Trạng thái SP</th>
                            <th style="text-align: center">Giá</th>
                            <th style="text-align: center">Thanh Toán</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${gioHang}" var="sanPhamTrongGioHang">
                            <tr>
                                <c:set var="sanPham" value="${sanPhamTrongGioHang.sanPham}"/>
                                <c:set var="tongTien"
                                       value="${tongTien + (sanPham.giaSanPham * sanPhamTrongGioHang.soLuong)}"
                                       scope="page"/>
                                <td>
                                    <a class="btn-remove"
                                       href="${pageContext.request.contextPath}/gio-hang/delete/${sanPhamTrongGioHang.maspgh}"><span
                                            class="fa fa-trash-o"></span></a>
                                </td>
                                <td>
                                    <img src="data:image/png;base64,${sanPham.imageBase64}"
                                         style="width: 100px; height: 50px"/>
                                </td>
                                <td style="text-align: center">
                                    <a href="#">${sanPham.tenSanPham}</a>
                                </td>
                                <td style="text-align: center">
                                        <span>
                                            <c:if test="${sanPhamTrongGioHang.soLuong > 1}">
                                                <a class="btn"
                                                   href="${pageContext.request.contextPath}/gio-hang/trusoluong/${sanPhamTrongGioHang.maspgh}">-</a>
                                            </c:if>
                                        </span>
                                    <input style="text-align: center" size="10" type="text"
                                           value="${sanPhamTrongGioHang.soLuong}" disabled/>
                                    <span>
                                            <c:if test="${sanPham.soLuongTrongKho > sanPhamTrongGioHang.soLuong}">
                                                <a class="btn"
                                                   href="${pageContext.request.contextPath}/gio-hang/congsoluong/${sanPhamTrongGioHang.maspgh}">+</a>
                                            </c:if>
                                        </span>

                                </td>
                                <td style="text-align: center">
                                  <span class="font-weight-bold ${sanPham.trangThai == 1 ? 'text-success' : 'text-danger'}">
                                    ${sanPham.trangThai == 1 ? 'Hoạt động' : 'Không hoạt đông'}
                                  </span>
                                </td>
                                <td style="text-align: center">
                                        <span style="color : #ff0000" id="giaid">
                                            <fmt:formatNumber
                                                    value="${sanPham.giaSanPham}"
                                                    type="CURRENCY"
                                                    currencyCode="VND"
                                                    maxFractionDigits="0"/>
                                        </span>
                                </td>
                                <td style="text-align: center">
                                        <span id="tongtienid" style="color : #ff0000">
                                            <fmt:formatNumber
                                                    value="${sanPham.giaSanPham * sanPhamTrongGioHang.soLuong}"
                                                    type="CURRENCY"
                                                    currencyCode="VND"
                                                    maxFractionDigits="0"/>
                                        </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- --------------------------------------------------------------------------------------------------------------------------- -->
            <div class="row">

                <div class="container">

                    <div class="form-group">
                        <div class="row">

                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="modal-footer">
                                    <h5 style="text-align: center">Tổng Giá Tiền Phải Thanh Toán:
                                        <span style="color : #ff0000">
                                                <fmt:formatNumber
                                                        value="${tongTien}"
                                                        type="CURRENCY"
                                                        currencyCode="VND"
                                                        maxFractionDigits="0"/>
                                            </span>
                                    </h5>
                                    <button data-toggle="modal" data-target="#dathang" id="btndathang"
                                       class="btn btn-danger btn-block">
                                        Đặt Hàng
                                    </button>
                                    
                                    <div class="modal fade" id="dathang" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									    <div class="modal-dialog" role="document">
									        <div class="modal-content">
									            <div class="modal-header">
									                <h5 class="modal-title" id="exampleModalLabel">Dat Hang</h5>
									                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
									                    <span aria-hidden="true">&times;</span>
									                </button>
									            </div>
									            <form method="get" action="${pageContext.request.contextPath}/gio-hang/checkout">
									                <div class="modal-body">
									                    <input hidden name="id" value="${param.id}" />
									                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									                    <div class="form-group">
									                        <label class="required">Địa chỉ</label>
									                        <input class="form-control" placeholder="Nhập địa chỉ" name="diaChi" autocomplete="off" required>
									                    </div>
									                    
									                    <div class="form-group">
									                        <label class="required">Số điện thoại</label>
									                        <input class="form-control" placeholder="Nhập số điện thoại" name="soDienThoai" autocomplete="off" required pattern="(^0)[0-9]{9}">
									                    </div>
									                </div>
									                <div class="modal-footer justify-content-center">
									                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
									                    <button type="submit" class="btn btn-primary">Đặt hàng</button>
									                </div>
									            </form>
									        </div>
									    </div>
									</div>
									                                    

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty gioHang }">
            <h3 style="text-align: center">Giỏ Hàng Hiện Tại Trống</h3>
        </c:if>
    </div>
</div>