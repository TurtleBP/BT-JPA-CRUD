<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Đăng nhập</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-lg-5">
      <div class="card shadow-sm">
        <div class="card-body p-4">
          <h3 class="mb-3">Đăng nhập</h3>
          <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
          </c:if>
          <form method="post" action="${pageContext.request.contextPath}/login" novalidate>
            <div class="mb-3">
              <label class="form-label">Tên đăng nhập <span class="text-danger">*</span></label>
              <input name="username" class="form-control" required maxlength="100" autocomplete="username"/>
              <div class="form-text">Tối đa 100 ký tự.</div>
            </div>
            <div class="mb-3">
              <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
              <input type="password" name="password" class="form-control" required autocomplete="current-password"/>
            </div>
            <div class="d-flex gap-2">
              <button class="btn btn-primary">Đăng nhập</button>
              <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/register">Tạo tài khoản</a>
            </div>
          </form>
        </div>
      </div>
      <p class="text-center mt-3">
        <a href="${pageContext.request.contextPath}/home">Về trang chủ</a>
      </p>
    </div>
  </div>
</div>
</body>
</html>
