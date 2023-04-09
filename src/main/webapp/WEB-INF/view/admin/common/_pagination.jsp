<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul class="pagination">
    <c:forEach var="page" items="${pages}">
        <li class="page-item ${page eq param.currentPage ? 'active' : ''}">
            <c:if test="${page ne param.currentPage}">
                <a class="page-link" href="${fn:replace(param.url, '_page_' , page)}">${page}</a>
            </c:if>
            <c:if test="${page eq param.currentPage}">
                <a class="page-link">${page}</a>
            </c:if>
        </li>
    </c:forEach>
</ul>