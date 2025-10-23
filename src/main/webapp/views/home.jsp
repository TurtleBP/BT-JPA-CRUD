<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Trang chủ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container py-4">
  <c:set var="ctx" value="${pageContext.request.contextPath}" />

  <div class="p-4 bg-white shadow-sm rounded">
    <h3 class="mb-3">Trang chủ</h3>

    <c:choose>
      <c:when test="${not empty sessionScope.account && sessionScope.account.roleId == 1}">
        <p class="mb-3">
          Xin chào Admin <b>${sessionScope.account.fullName}</b>!
        </p>
        <div class="d-flex flex-wrap gap-2">
          <a class="btn btn-outline-primary" href="${ctx}/profile">Profile</a>
          <a class="btn btn-outline-secondary" href="${ctx}/admin/home">Vào khu Admin</a>
          <a class="btn btn-danger" href="${ctx}/logout">Đăng xuất</a>
        </div>
      </c:when>

      <c:when test="${not empty sessionScope.account}">
        <p class="mb-3">
          Xin chào <b>${sessionScope.account.fullName}</b>!
        </p>
        <div class="d-flex flex-wrap gap-2">
          <a class="btn btn-outline-primary" href="${ctx}/profile">Profile</a>
          <a class="btn btn-danger" href="${ctx}/logout">Đăng xuất</a>
        </div>
      </c:when>

      <c:otherwise>
        <p class="mb-3">Bạn chưa đăng nhập.</p>
        <div class="d-flex flex-wrap gap-2">
          <a class="btn btn-primary" href="${ctx}/login">Đăng nhập</a>
          <a class="btn btn-outline-secondary" href="${ctx}/register">Đăng ký</a>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</div>
</body>
</html>
