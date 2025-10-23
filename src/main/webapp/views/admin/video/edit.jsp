<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Sửa Video</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-4">
  <nav aria-label="breadcrumb">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home/admin">Admin</a></li>
      <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/video">Video</a></li>
      <li class="breadcrumb-item active" aria-current="page">Sửa</li>
    </ol>
  </nav>

  <div class="card shadow-sm">
    <div class="card-body">
      <h5 class="card-title">Sửa Video #${video.videoId}</h5>
      <form method="post" action="${pageContext.request.contextPath}/admin/video/update"
            enctype="multipart/form-data" novalidate>
        <input type="hidden" name="videoId" value="${video.videoId}"/>

        <div class="row g-3">
          <div class="col-md-6">
            <label class="form-label">Title <span class="text-danger">*</span></label>
            <input name="title" value="${video.title}" class="form-control" required maxlength="1000"/>
          </div>
          <div class="col-md-2">
            <label class="form-label">Active</label>
            <div class="form-check mt-2">
              <input class="form-check-input" type="checkbox" name="active" id="veditactive" ${video.active?'checked':''}/>
              <label class="form-check-label" for="veditactive">Kích hoạt</label>
            </div>
          </div>
          <div class="col-md-4">
            <label class="form-label">Category</label>
            <select name="categoryId" class="form-select">
              <c:forEach var="c" items="${categories}">
                <option value="${c.id}" ${video.category!=null && video.category.id==c.id?'selected':''}>${c.name}</option>
              </c:forEach>
            </select>
          </div>

          <div class="col-md-12">
            <label class="form-label">Mô tả</label>
            <textarea name="description" class="form-control" rows="3" maxlength="1000">${video.description}</textarea>
          </div>

          <div class="col-md-6">
            <label class="form-label">Poster (chọn để thay)</label>
            <input type="file" name="poster" class="form-control" accept="image/*" onchange="previewEditVid(this)"/>
            <div class="mt-2 d-flex align-items-center gap-3">
              <c:if test="${not empty video.poster}">
                <div>
                  <div class="text-muted small">Hiện tại</div>
                  <img style="height:64px" src="${pageContext.request.contextPath}/image?fname=${video.poster}"/>
                </div>
              </c:if>
              <div>
                <div class="text-muted small">Xem trước</div>
                <img id="editVidPreview" style="height:64px;display:none"/>
              </div>
            </div>
          </div>
        </div>

        <div class="d-flex gap-2 mt-3">
          <button class="btn btn-primary">Cập nhật</button>
          <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/admin/video">Huỷ</a>
        </div>
      </form>
    </div>
  </div>
</div>
<script>
  function previewEditVid(input){
    const img=document.getElementById('editVidPreview');
    if(input.files&&input.files[0]){ img.src=URL.createObjectURL(input.files[0]); img.style.display='inline-block'; }
  }
</script>
</body>
</html>
