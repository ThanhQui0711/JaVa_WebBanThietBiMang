<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="content">
  <div class="container">
    <div class="slider">
      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <!-- item start -->
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/qc3.jpg"
                 alt="First slide">
            <!-- Width =100% -->
          </div>
          <div class="carousel-item">
            <img class="img-reponsive" src="${pageContext.request.contextPath}/resources/images/qc2.jpg"
                 alt="Second slide">
          </div>
          <div class="carousel-item">
            <!-- slide img -->
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/qc3.jpg"
                 alt="Three slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/qc5.jpg"
                 alt="Four slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/qc7.jpg"
                 alt="Five slide">
          </div>
          <div class="carousel-item">
            <img class="d-block w-100" src="${pageContext.request.contextPath}/resources/images/qc4.jpg"
                 alt="six slide">
          </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
          <!-- nút trước -->
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
          <!--nút sau -->
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
    </div>
  </div>
  <div class="product-box">
    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-3 ">
          <div class="sidebar">
            <div class="category-box">
              <h3>Danh mục sản phẩm</h3>
              <div class="content-cat">
                <ul>
                  <c:forEach var="category" items="${categories}">
                    <c:url var="category2" value="/">
                      <c:param name="maDanhMuc" value="${category.id}"/>
                    </c:url>
                    <li>
                      <i class="fa fa-angle-right"></i>
                      <a href="${category2}">${category.tenDanhMuc}</a>
                    </li>
                  </c:forEach>
                </ul>
              </div>
            </div>
            <div class="widget">
              <h3>
                <i class="fa fa-bars"></i>
                Tin tức
              </h3>
              <div class="content-w">
                <ul>
                  <li>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/images/1.jpg"
                                     alt=""></a>
                    <h4><a href="#">Laptop Lenovo L340-15IRH 81LK01GKVN giảm giá 10%</a></h4>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/images/2.jpg"
                                     alt=""></a>
                    <h4><a href="#">Laptop ASUS Gaming FX505DT-AL118T giảm giá 2 triệu</a></h4>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/images/3.jpg"
                                     alt=""></a>
                    <h4><a href="#">Apple Macbook Pro 13 Touch Bar i5 1.4 256GB 2020 giảm giá mạnh
                      đối với sinh viên</a></h4>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/images/4.jpg"
                                     alt=""></a>
                    <h4><a href="#">Laptop Acer Nitro 5 AN515-55-58A7 Giảm giá 20%</a></h4>
                    <div class="clear"></div>
                  </li>
                  <li>
                    <a href="#"><img src="${pageContext.request.contextPath}/resources/images/5.jpg"
                                     alt=""></a>
                    <h4><a href="#">Laptop HP ENVY 13-AQ1021TU 8QN79PA giảm giá shock</a></h4>
                    <div class="clear"></div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="widget">
              <h3>
                <i class="fa fa-bars"></i>
                Quảng cáo
              </h3>
              <div class="content-banner">
                <a href="#">
                  <img src="${pageContext.request.contextPath}/resources/images/banner.png" alt="">
                </a>
              </div>
            </div>
            <div class="widget">
              <h3>
                <i class="fa fa-facebook"></i>
                Facebook
              </h3>
              <div class="content-fb">
                <div class="fb-page" data-href="#" data-tabs="timeline" data-width="" data-height="200"
                     data-small-header="false" data-adapt-container-width="true" data-hide-cover="false"
                     data-show-facepile="true"></div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-9 ">
          <div class="product-section">
            <h2 class="title-product">
              <a href="#" class="title"></a>
              <div class="bar-menu"><i class="fa fa-bars"></i></div>
            </h2>
          </div>
          <a href="#">
            <img class="img-reponsive" src="${pageContext.request.contextPath}/resources/images/banner-02.jpg" alt="">
          </a>

          <br>
          <br>
          <div class="product-section">
            <h2 class="title-product">
              <a href="#" class="title">Sản phẩm</a>
            </h2>
            <div class="content-product-box">
              <div class="row">
                <c:forEach items="${products.content}" var="sanPham">
                  <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="item-product">
                      <div class="thumb">
                        <img style="width: 184px; height: 184px;"
                             src="data:image/png;base64,${sanPham.imageBase64}"
                             alt="${sanPham.tenSanPham}" />
                        <div class="action">
                          <a href="${pageContext.request.contextPath}/san-pham/${sanPham.masp}" class="buy"><i class="fa fa-cart-plus"></i> Mua ngay</a>
                        </div>
                      </div>
                      <div class="info-product">
                        <h4 class="text-truncate"
                            title="${sanPham.tenSanPham}">${sanPham.tenSanPham}</h4>
                        <span>Số lượng: ${sanPham.soLuongTrongKho}</span>
                        <div class="price">
                          <span>Giá: </span>
                          <span class="price-current">
                                                        <fmt:formatNumber
                                                                value="${sanPham.giaSanPham}"
                                                                type="CURRENCY"
                                                                currencyCode="VND"
                                                                maxFractionDigits="0"/>
                                                    </span>
                        </div>
                        <a href="${pageContext.request.contextPath}/san-pham/${sanPham.masp}"
                           class="view-more">Xem chi tiết</a>
                      </div>
                    </div>
                  </div>
                </c:forEach>
              </div>
              <div class="row justify-content-center">
                <c:url var="query" value="/">
                  <c:param name="maDanhMuc" value="${param.maDanhMuc}"/>
                  <c:param name="page" value="_page_"/>
                </c:url>
                <jsp:include page="../../admin/common/_pagination.jsp">
                  <jsp:param name="currentPage" value="${param.page eq null ? 1 : param.page}"/>
                  <jsp:param name="url" value="${query}"/>
                </jsp:include>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>