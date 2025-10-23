<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Admin Home</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container py-4">
  <c:set var="ctx" value="${pageContext.request.contextPath}" />

  <div class="p-4 bg-white shadow-sm rounded">
    <div class="d-flex justify-content-between align-items-center mb-3">
      <h3 class="mb-0">Bảng điều khiển Admin</h3>
      <span class="text-muted small">
        <c:if test="${not empty sessionScope.account}">
          Xin chào, <b>${sessionScope.account.fullName}</b>
        </c:if>
      </span>
    </div>

    <div class="row g-3">
      <div class="col-md-3">
        <a class="btn btn-outline-primary w-100 py-3" href="${ctx}/profile">
          Profile
        </a>
      </div>
      <div class="col-md-3">
        <a class="btn btn-outline-success w-100 py-3" href="${ctx}/admin/category">
          Quản lý Sản phẩm (Category)
        </a>
      </div>
      <div class="col-md-3">
        <a class="btn btn-outline-info w-100 py-3" href="${ctx}/admin/video">
          Quản lý Video
        </a>
      </div>
      <div class="col-md-3">
        <a class="btn btn-danger w-100 py-3" href="${ctx}/logout">
          Đăng xuất
        </a>
      </div>
    </div>

    <hr class="my-4"/>

    <div>
      <a class="btn btn-link" href="${ctx}/home">← Về trang chủ</a>
    </div>
  </div>
</div>
</body>
</html>
