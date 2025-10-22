<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="card shadow-sm">
  <div class="card-body">
    <h5 class="card-title">Thêm mới Video</h5>
    <form method="post" action="${pageContext.request.contextPath}/admin-video/create"
          enctype="multipart/form-data" novalidate>
      <div class="row g-3">
        <div class="col-md-4">
          <label class="form-label">VideoId <span class="text-danger">*</span></label>
          <input name="videoId" class="form-control" required maxlength="255" placeholder="Khoá chính dạng chuỗi"/>
        </div>
        <div class="col-md-5">
          <label class="form-label">Title <span class="text-danger">*</span></label>
          <input name="title" class="form-control" required maxlength="1000"/>
        </div>
        <div class="col-md-3">
          <label class="form-label">Active</label>
          <div class="form-check mt-2">
            <input class="form-check-input" type="checkbox" name="active" id="vaddactive"/>
            <label class="form-check-label" for="vaddactive">Kích hoạt</label>
          </div>
        </div>

        <div class="col-md-12">
          <label class="form-label">Mô tả</label>
          <textarea name="description" class="form-control" rows="3" maxlength="1000"></textarea>
        </div>

        <div class="col-md-4">
          <label class="form-label">Category</label>
          <select name="categoryId" class="form-select">
            <c:forEach var="c" items="${categories}">
              <option value="${c.id}">${c.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="col-md-6">
          <label class="form-label">Poster</label>
          <input type="file" name="poster" class="form-control" accept="image/*" onchange="previewAddVid(this)"/>
          <img id="addVidPreview" class="mt-2" style="height:64px;display:none"/>
        </div>
      </div>

      <div class="d-flex gap-2 mt-3">
        <button class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-outline-secondary" onclick="resetAddVidPreview()">Nhập lại</button>
      </div>
    </form>
  </div>
</div>
<script>
  function previewAddVid(input){
    const img=document.getElementById('addVidPreview');
    if(input.files&&input.files[0]){ img.src=URL.createObjectURL(input.files[0]); img.style.display='inline-block'; }
  }
  function resetAddVidPreview(){ const img=document.getElementById('addVidPreview'); img.src=''; img.style.display='none'; }
</script>
