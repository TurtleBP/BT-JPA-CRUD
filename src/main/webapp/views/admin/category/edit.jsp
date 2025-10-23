<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Sửa Category</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-4">
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home/admin">Admin</a></li>
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/category">Category</a></li>
      <li class="breadcrumb-item active" aria-current="page">Sửa</li>
    </ol>
  </nav>

  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title">Sửa Category #${category.id}</h5>
      <form method="post" action="${pageContext.request.contextPath}/admin/category/update"
            enctype="multipart/form-data" novalidate>
        <input type="hidden" name="id" value="${category.id}"/>
        <div class="row g-3">
          <div class="col-md-5">
            <label class="form-label">Tên <span class="text-danger">*</span></label>
            <input name="name" value="${category.name}" class="form-control" required maxlength="255"/>
          </div>
          <div class="col-md-3">
            <label class="form-label">Code <span class="text-danger">*</span></label>
            <input name="code" value="${category.code}" class="form-control" required maxlength="50"/>
          </div>
          <div class="col-md-2">
            <label class="form-label">Trạng thái</label>
            <select class="form-select" name="status">
              <option value="1" ${category.status==1?'selected':''}>On</option>
              <option value="0" ${category.status==0?'selected':''}>Off</option>
            </select>
          </div>
          <div class="col-md-6">
            <label class="form-label">Ảnh (chọn để thay)</label>
            <input type="file" name="images" class="form-control" accept="image/*" onchange="previewEditCat(this)"/>
            <div class="mt-2 d-flex align-items-center gap-3">
              <c:if test="${not empty category.images}">
                <div>
                  <div class="text-muted small">Hiện tại</div>
                  <img style="height:64px" src="${pageContext.request.contextPath}/image?fname=${category.images}"/>
                </div>
              </c:if>
              <div>
                <div class="text-muted small">Xem trước</div>
                <img id="editCatPreview" style="height:64px;display:none"/>
              </div>
            </div>
          </div>
        </div>
        <div class="d-flex gap-2 mt-3">
          <button class="btn btn-primary">Cập nhật</button>
          <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin/category">Huỷ</a>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  function previewEditCat(input){
    const img=document.getElementById('editCatPreview');
    if(input.files&&input.files[0]){ img.src=URL.createObjectURL(input.files[0]); img.style.display='inline-block'; }
  }
</script>
</body>
</html>
