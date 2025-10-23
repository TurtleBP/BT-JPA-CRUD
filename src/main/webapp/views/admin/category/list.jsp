<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Quản lý Category</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <style>.thumb{height:48px;border-radius:6px;object-fit:cover}</style>
</head>
<body>
<div class="container py-4">
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home/admin">Admin</a></li>
      <li class="breadcrumb-item active" aria-current="page">Category</li>
    </ol>
  </nav>

  <div class="card shadow-sm mb-4">
    <div class="card-body">
      <h5 class="card-title">Bộ lọc</h5>
      <form class="row g-2" method="get" action="${pageContext.request.contextPath}/admin/category">
        <div class="col-md-4">
          <label class="form-label">Tên/Code</label>
          <input name="q" value="${param.q}" class="form-control" placeholder="Nhập tên hoặc code"/>
        </div>
        <div class="col-md-3">
          <label class="form-label">Trạng thái</label>
          <select name="status" class="form-select">
            <option value="">-- Tất cả --</option>
            <option value="1" ${param.status=='1'?'selected':''}>On</option>
            <option value="0" ${param.status=='0'?'selected':''}>Off</option>
          </select>
        </div>
        <div class="col-md-3 align-self-end">
          <button class="btn btn-primary me-2">Lọc</button>
          <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin/category">Xoá lọc</a>
        </div>
      </form>
    </div>
  </div>

  <div class="d-flex justify-content-between align-items-center mb-2">
    <h4 class="mb-0">Danh sách Category</h4>
  </div>

  <div class="table-responsive">
    <table class="table table-striped align-middle">
      <thead class="table-light">
        <tr>
          <th>Ảnh</th>
          <th>Tên</th>
          <th>Code</th>
          <th>Trạng thái</th>
          <th width="180">Hành động</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="item" items="${categorys}">
        <tr>
          <td>
            <c:choose>
              <c:when test="${not empty item.images}">
                <img class="thumb" src="${pageContext.request.contextPath}/image?fname=${item.images}" alt="${item.name}"/>
              </c:when>
              <c:otherwise>
                <span class="text-muted">No image</span>
              </c:otherwise>
            </c:choose>
          </td>
          <td>${item.name}</td>
          <td>${item.code}</td>
          <td>
            <span class="badge ${item.status==1?'bg-success':'bg-secondary'}">
              ${item.status==1?'On':'Off'}
            </span>
          </td>
          <td>
            <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/admin/category/edit?id=${item.id}">Sửa</a>
            <form class="d-inline" method="post" action="${pageContext.request.contextPath}/admin/category/delete"
                  onsubmit="return confirm('Xoá category này?')">
              <input type="hidden" name="id" value="${item.id}"/>
              <button class="btn btn-sm btn-danger">Xoá</button>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <hr class="my-4"/>
  <jsp:include page="add.jsp"/>
</div>
</body>
</html>
