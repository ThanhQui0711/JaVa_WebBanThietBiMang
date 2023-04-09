<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product-detail.css" />
<div id="content">
  <div class="container">
      <main role="main">
          <div class="card">
              <div class="container-fliud">
                  <div class="wrapper row">
                      <div class="preview col-md-6">
                          <div class="preview-pic tab-content">
                              <img src="data:image/png;base64,${sanPham.imageBase64}" style="width: 400px; height: 300px"/>
                          </div>
                      </div>

                      <div class="details col-md-6">
                          <h3 class="product-title">${sanPham.tenSanPham}</h3>
                          <div class="rating">
                              <div class="stars">
                                  <span class="fa fa-star checked"></span>
                                  <span class="fa fa-star checked"></span>
                                  <span class="fa fa-star checked"></span>
                                  <span class="fa fa-star"></span>
                                  <span class="fa fa-star"></span>
                              </div>
                          </div>
                          <p class="product-description">Nhà Sản Xuất: ${sanPham.nhaCungCap}
                              <br>Bảo Hành: ${sanPham.baoHanh} năm
                          </p>
                          <h4 class="price">Giá:
                              <span>
                            <fmt:formatNumber
                                    value="${sanPham.giaSanPham}"
                                    type="CURRENCY"
                                    currencyCode="VND"
                                    maxFractionDigits="0"/>
                        </span>
                          </h4>
                          <p class="vote">
                              <strong>100%</strong> hàng <strong>Chất lượng</strong>, đảm bảo
                              <strong>Uy tín</strong>!</p>
                          <div class="action">
                              <form method="post">
                                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                  <input type="number" name="soLuong" class="form-control mb-3" required/>
                                  <button type="submit" class="add-to-cart btn btn-default" onclick="AlertIt1();">
                                      Thêm vào giỏ hàng
                                  </button>
                              </form>
                          </div>

                      </div>
                  </div>
              </div>
          </div>
          <div class="card">
              <div class="container-fluid">
                  <h3>Mô tả chi tiết về sản phẩm</h3>
                  <div>
                      ${sanPham.moTa}
                  </div>
              </div>
          </div>
      </main>
  </div>
</div>