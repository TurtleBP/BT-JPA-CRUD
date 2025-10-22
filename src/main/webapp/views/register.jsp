<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Đăng ký tài khoản</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-lg-7">
      <div class="card shadow-sm">
        <div class="card-body p-4">
          <h3 class="mb-3">Đăng ký</h3>
          <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
          </c:if>
          <form method="post" action="${pageContext.request.contextPath}/register" novalidate>
            <div class="row g-3">
              <div class="col-md-6">
                <label class="form-label">Tên đăng nhập <span class="text-danger">*</span></label>
                <input name="username" class="form-control" required maxlength="100" autocomplete="username"/>
              </div>
              <div class="col-md-6">
                <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                <input type="password" name="password" class="form-control" required autocomplete="new-password"/>
              </div>
              <div class="col-md-8">
                <label class="form-label">Họ & Tên</label>
                <input name="fullName" class="form-control" maxlength="255"/>
              </div>
              <div class="col-md-4">
                <label class="form-label">Vai trò</label>
                <select name="roleId" class="form-select">
                  <option value="3">User</option>
                  <option value="1">Admin</option>
                </select>
                <div class="form-text">Demo cho bài tập; thực tế không nên tự chọn Admin.</div>
              </div>
            </div>
            <div class="d-flex gap-2 mt-3">
              <button class="btn btn-success">Tạo tài khoản</button>
              <button type="reset" class="btn btn-outline-secondary">Nhập lại</button>
              <a class="btn btn-outline-dark" href="${pageContext.request.contextPath}/login">Đã có tài khoản</a>
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
