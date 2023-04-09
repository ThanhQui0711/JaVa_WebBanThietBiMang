<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
  <form method="get">
    <div class="form-row">
      <div class="form-group col-md-4">
        <input class="form-control" placeholder="Nhập tên đăng nhập hoặc tên KH" name="username" value="${param.name}" autocomplete="off">
      </div>
      <div class="col-md-2">
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
      </div>
    </div>
  </form>
</div>