<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="${param.modalId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Chi tiết sản phẩm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label class="required">Số lượng</label>
                        <input class="form-control" type="number" placeholder="Nhập số lượng" value="${param.soLuongTrongKho}" disabled>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="required">Bảo hành</label>
                        <input class="form-control" type="number" placeholder="Nhập bảo hành"  value="${param.baoHanh}" disabled>
                    </div>
                </div>

                <div class="form-group">
                    <label class="required">Nhà cung cấp</label>
                    <input class="form-control" placeholder="Nhập nhà cung cấp" value="${param.nhaCungCap}" disabled>
                </div>


                <div class="form-group">
                    <label class="required">Mô tả</label>
                    <textarea class="form-control" name="moTa" rows="3" disabled>${param.moTa}</textarea>
                </div>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy bỏ</button>
            </div>
        </div>
    </div>
</div>
