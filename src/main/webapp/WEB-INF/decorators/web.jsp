<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html><html lang="vi"><head>
  <meta charset="utf-8"/>
  <title>Web • BT</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head><body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <a class="navbar-brand" href="<c:url value='/home'/>">BT</a>
    <div class="collapse navbar-collapse">
      <ul class="navbar-nav ms-auto">
        <c:choose>
          <c:when test="${not empty sessionScope.account}">
            <li class="nav-item"><a class="nav-link" href="<c:url value='/profile'/>">Profile</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/logout'/>">Đăng xuất</a></li>
          </c:when>
          <c:otherwise>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a></li>
            <li class="nav-item"><a class="nav-link" href="<c:url value='/register'/>">Đăng ký</a></li>
          </c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav>

<main class="container py-4">
  <sitemesh:write property="body"/>
</main>

<footer class="border-top py-3 text-center text-muted">© BT</footer>
</body></html>
