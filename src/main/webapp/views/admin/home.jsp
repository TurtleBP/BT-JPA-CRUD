<%@ page contentType="text/html; charset=UTF-8" %>
<!-- KHÔNG cần JSTL nếu bạn dùng EL thuần cho URL -->
<div class="p-4 bg-white shadow-sm rounded">
  <h4 class="mb-3">Trang chủ</h4>
  <c:choose>
    <c:when test="${not empty sessionScope.account && sessionScope.account.roleId==1}">
      <p>Xin chào Admin <b>${sessionScope.account.fullName}</b>.</p>
      <div class="d-flex gap-2">
        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/profile">Profile</a>
        <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/admin-category">Quản lý Sản phẩm (Category)</a>
        <a class="btn btn-outline-info" href="${pageContext.request.contextPath}/admin-video">Quản lý Video</a>
        <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
      </div>
    </c:when>
    <c:otherwise>
      <p>Xin chào <b>${sessionScope.account!=null?sessionScope.account.fullName:"bạn"}</b>.</p>
      <div class="d-flex gap-2">
        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/profile">Profile</a>
      </div>
    </c:otherwise>
  </c:choose>
</div>
