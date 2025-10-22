<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-4">
  <div class="card shadow-sm">
    <div class="card-body">
      <h4 class="card-title">Thông tin cá nhân</h4>
      <dl class="row">
        <dt class="col-sm-3">Username</dt><dd class="col-sm-9">${sessionScope.account.username}</dd>
        <dt class="col-sm-3">Họ tên</dt><dd class="col-sm-9">${sessionScope.account.fullName}</dd>
        <dt class="col-sm-3">Vai trò</dt>
        <dd class="col-sm-9">
          <span class="badge ${sessionScope.account.roleId==1?'bg-primary':'bg-secondary'}">
            ${sessionScope.account.roleId==1?'ADMIN':'USER'}
          </span>
        </dd>
      </dl>
      <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/home">Về trang chủ</a>
    </div>
  </div>
</div>
</body>
</html>
