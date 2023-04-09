<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
  <form method="get">
    <div class="form-row">
      <div class="form-group col-md-3">
        <input class="form-control" placeholder="Nhập tên sản phẩm" name="tenSanPham" value="${param.name}" autocomplete="off">
      </div>

      <div class="form-group col-md-3">
        <select class="form-control" name="maDanhMuc">
          <option value="${null}" ${param.category eq null ? 'selected' : ''}>Lựa chọn</option>
          <c:forEach var="category" items="${categories}" varStatus="status">
            <option value="${category.id}" ${category.id eq param.category ? 'selected' : ''}>${category.tenDanhMuc}</option>
          </c:forEach>
        </select>
      </div>

      <div class="form-group col-md-3">
        <select class="form-control" name="trangThai">
          <option value="${null}" ${param.status eq null ? 'selected' : ''}>Lựa chọn</option>
          <option value="1" ${param.status eq 1 ? 'selected' : ''}>Hoạt động</option>
          <option value="0" ${param.stauts eq 0 ? 'selected' : ''}>Không hoạt động</option>
        </select>
      </div>

      <div class="col-md-2">
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
      </div>
    </div>
  </form>
</div>