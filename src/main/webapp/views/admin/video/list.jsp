<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Quản lý Video</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
  <style>.thumb{height:48px;border-radius:6px;object-fit:cover}</style>
</head>
<body>
<div class="container py-4">
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home-admin">Admin</a></li>
      <li class="breadcrumb-item active" aria-current="page">Video</li>
    </ol>
  </nav>

  <div class="card shadow-sm mb-4">
    <div class="card-body">
      <h5 class="card-title">Bộ lọc</h5>
      <form class="row g-2" method="get" action="${pageContext.request.contextPath}/admin-video">
        <div class="col-md-4">
          <label class="form-label">Từ khoá (ID / Title / Mô tả)</label>
          <input name="q" value="${param.q}" class="form-control" placeholder="Nhập từ khoá"/>
        </div>
        <div class="col-md-3">
          <label class="form-label">Category</label>
          <select name="categoryId" class="form-select">
            <option value="">-- Tất cả --</option>
            <c:forEach var="c" items="${categories}">
              <option value="${c.id}" ${param.categoryId==c.id? 'selected' : ''}>${c.name}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-2">
          <label class="form-label">Active</label>
          <select name="active" class="form-select">
            <option value="">-- Tất cả --</option>
            <option value="1" ${param.active=='1'?'selected':''}>On</option>
            <option value="0" ${param.active=='0'?'selected':''}>Off</option>
          </select>
        </div>
        <div class="col-md-3 align-self-end">
          <button class="btn btn-primary me-2">Lọc</button>
          <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin-video">Xoá lọc</a>
        </div>
      </form>
    </div>
  </div>

  <h4 class="mb-2">Danh sách Video</h4>
  <div class="table-responsive">
    <table class="table table-hover align-middle">
      <thead class="table-light">
        <tr>
          <th>Poster</th>
          <th>VideoId</th>
          <th>Title</th>
          <th>Category</th>
          <th>Active</th>
          <th>Views</th>
          <th width="180">Hành động</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach var="v" items="${videos}">
        <tr>
          <td>
            <c:choose>
              <c:when test="${not empty v.poster}">
                <img class="thumb" src="${pageContext.request.contextPath}/image?fname=${v.poster}" alt="${v.title}"/>
              </c:when>
              <c:otherwise><span class="text-muted">No poster</span></c:otherwise>
            </c:choose>
          </td>
          <td>${v.videoId}</td>
          <td>${v.title}</td>
          <td>${v.category!=null?v.category.name:'-'}</td>
          <td><span class="badge ${v.active?'bg-success':'bg-secondary'}">${v.active?'On':'Off'}</span></td>
          <td>${v.views}</td>
          <td>
            <a class="btn btn-sm btn-warning" href="${pageContext.request.contextPath}/admin-video/edit?id=${v.videoId}">Sửa</a>
            <form class="d-inline" method="post" action="${pageContext.request.contextPath}/admin-video/delete"
                  onsubmit="return confirm('Xoá video này?')">
              <input type="hidden" name="id" value="${v.videoId}"/>
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
