<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html><html lang="vi"><head>
  <meta charset="utf-8"/>
  <title>Admin • BT</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head><body>
<nav class="navbar navbar-expand navbar-dark bg-primary">
  <div class="container">
    <a class="navbar-brand" href="<c:url value='/home-admin'/>">Admin</a>
    <ul class="navbar-nav ms-auto">
      <li class="nav-item"><a class="nav-link" href="<c:url value='/profile'/>">Profile</a></li>
      <li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Đăng xuất</a></li>
    </ul>
  </div>
</nav>
<div class="container py-4">
  <sitemesh:write property="body"/>
</div>
</body></html>
