<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="card shadow-sm">
  <div class="card-body">
    <h5 class="card-title">Thêm mới Category</h5>
    <form method="post" action="${pageContext.request.contextPath}/admin-category/create"
          enctype="multipart/form-data" novalidate>
      <div class="row g-3">
        <div class="col-md-5">
          <label class="form-label">Tên <span class="text-danger">*</span></label>
          <input name="name" class="form-control" required maxlength="255" placeholder="VD: Đồ uống"/>
        </div>
        <div class="col-md-3">
          <label class="form-label">Code <span class="text-danger">*</span></label>
          <input name="code" class="form-control" required maxlength="50" placeholder="VD: DRINK"/>
        </div>
        <div class="col-md-2">
          <label class="form-label">Trạng thái</label>
          <select class="form-select" name="status">
            <option value="1">On</option>
            <option value="0">Off</option>
          </select>
        </div>
        <div class="col-md-6">
          <label class="form-label">Ảnh</label>
          <input type="file" name="images" class="form-control" accept="image/*" onchange="previewAddCat(this)"/>
          <img id="addCatPreview" class="mt-2" style="height:64px;display:none"/>
        </div>
      </div>
      <div class="d-flex gap-2 mt-3">
        <button class="btn btn-success">Thêm</button>
        <button type="reset" class="btn btn-outline-secondary" onclick="resetAddCatPreview()">Nhập lại</button>
      </div>
    </form>
  </div>
</div>
<script>
  function previewAddCat(input){
    const img = document.getElementById('addCatPreview');
    if(input.files && input.files[0]){ img.src = URL.createObjectURL(input.files[0]); img.style.display='inline-block'; }
  }
  function resetAddCatPreview(){
    const img = document.getElementById('addCatPreview');
    img.src=''; img.style.display='none';
  }
</script>
