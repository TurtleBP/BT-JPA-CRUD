<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav>
  <a href="<c:url value='/home'/>">Trang chủ</a>
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <a href="<c:url value='/profile'/>">Hồ sơ</a>
      <c:if test="${sessionScope.role == 1}">
        <a href="<c:url value='/admin/dashboard'/>">Admin</a>
      </c:if>
      <c:if test="${sessionScope.role == 2}">
        <a href="<c:url value='/manager'/>">Manager</a>
      </c:if>
      <a href="<c:url value='/logout'/>">Đăng xuất</a>
    </c:when>
    <c:otherwise>
      <a href="<c:url value='/login'/>">Đăng nhập</a>
    </c:otherwise>
  </c:choose>
</nav>
