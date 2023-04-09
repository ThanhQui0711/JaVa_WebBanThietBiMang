<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="${param.modalId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">${param.title}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                <input hidden name="_charset_ " value="UTF-8" />
                <div class="modal-body">
                    <input hidden name="masp" type="number" value="${param.masp}" />
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="required">Tên sản phẩm</label>
                            <input class="form-control" placeholder="Nhập tên sản phẩm" name="tenSanPham" value="${param.tenSanPham}" autocomplete="off" required>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="required">Danh mục</label>
                            <select class="form-control" name="danhMuc" required>
                                <c:forEach var="category" items="${categories}" varStatus="status">
                                    <option value="${category.id}" ${category.id eq param.danhMuc or status.index eq 0 ? 'selected' : ''}>${category.tenDanhMuc}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="required">Số lượng</label>
                            <input class="form-control" type="number" placeholder="Nhập số lượng" name="soLuongTrongKho" value="${param.soLuongTrongKho}" autocomplete="off" required>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="required">Giá</label>
                            <input class="form-control" type="number" placeholder="Nhập giá" name="giaSanPham" value="${param.giaSanPham}" autocomplete="off" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="required">Nhà cung cấp</label>
                            <input class="form-control" placeholder="Nhập nhà cung cấp" name="nhaCungCap" value="${param.nhaCungCap}" autocomplete="off" required>
                        </div>

                        <div class="form-group col-md-6">
                            <label class="required">Bảo hành</label>
                            <input class="form-control" type="number" placeholder="Nhập bảo hành" name="baoHanh" value="${param.baoHanh}" autocomplete="off" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label class="required">Mô tả</label>
                            <textarea class="form-control" name="moTa" rows="3">${param.moTa}</textarea>
                        </div>

                        <div class="form-group col-md-12">
                            <label class="required">Hình ảnh</label>
                            <input id="hinhanh" type="file" class="form-control-file" name="hinhAnh" accept="*.png, *.jpge, *.jpg">
                        </div>
                    </div>
                </div>
                <div class="modal-footer justify-content-center">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
                    <button type="submit" class="btn btn-primary">lưu lại</button>
                </div>
            </form>
        </div>
    </div>
</div>