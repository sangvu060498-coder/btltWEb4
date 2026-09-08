<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông Tin Cá Nhân</title>
</head>
<body>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-7">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0"><i class="bi bi-person-badge"></i> Cập Nhật Hồ Sơ Người Dùng</h5>
                </div>
                <div class="card-body p-4">
                    
                    <!-- Thông báo -->
                    <c:if test="${not empty alert}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i> ${alert}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>
                    <c:if test="${not empty msgSuccess}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <i class="bi bi-check-circle-fill me-2"></i> ${msgSuccess}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="<c:url value='/user/profile'/>" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        
                        <!-- Avatar Preview & Upload -->
                        <div class="text-center mb-4">
                            <div class="position-relative d-inline-block">
                                <c:choose>
                                    <c:when test="${not empty user.images}">
                                        <img id="avatarPreview" src="<c:url value='/image?fname=${user.images}'/>" 
                                             class="rounded-circle border border-3 border-primary shadow-sm" 
                                             style="width: 130px; height: 130px; object-fit: cover;" alt="Avatar">
                                    </c:when>
                                    <c:otherwise>
                                        <img id="avatarPreview" src="https://via.placeholder.com/130?text=Avatar" 
                                             class="rounded-circle border border-3 border-secondary shadow-sm" 
                                             style="width: 130px; height: 130px; object-fit: cover;" alt="Avatar">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="mt-2">
                                <label for="avatarInput" class="btn btn-sm btn-outline-secondary">
                                    <i class="bi bi-camera"></i> Đổi ảnh đại diện
                                </label>
                                <input type="file" id="avatarInput" name="images" accept="image/*" class="d-none" onchange="previewImage(this)">
                            </div>
                        </div>

                        <!-- Username (Readonly) -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Tên đăng nhập:</label>
                            <input type="text" class="form-control bg-light" value="${user.username}" readonly>
                        </div>

                        <!-- Email (Readonly) -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Địa chỉ Email:</label>
                            <input type="email" class="form-control bg-light" value="${user.email}" readonly>
                            <div class="form-text">Email dùng để xác thực bảo mật và không thể thay đổi.</div>
                        </div>

                        <!-- Fullname (Editable with Validation) -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Họ và tên <span class="text-danger">*</span>:</label>
                            <input type="text" class="form-control" name="fullname" value="${user.fullname}" required>
                            <div class="invalid-feedback">Vui lòng nhập họ và tên của bạn!</div>
                        </div>

                        <!-- Phone (Editable with Validation) -->
                        <div class="mb-3">
                            <label class="form-label fw-bold">Số điện thoại:</label>
                            <input type="text" class="form-control" name="phone" value="${user.phone}" 
                                   pattern="^0[0-9]{9}$" placeholder="Ví dụ: 0912345678">
                            <div class="invalid-feedback">Số điện thoại phải gồm 10 số và bắt đầu bằng số 0!</div>
                        </div>

                        <div class="d-flex justify-content-between align-items-center mt-4 pt-2 border-top">
                            <a href="<c:url value='/home'/>" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left"></i> Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary px-4">
                                <i class="bi bi-save"></i> Lưu Thay Đổi
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <!-- Script preview ảnh khi người dùng chọn file và Client-side Validation -->
    <script>
        function previewImage(input) {
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('avatarPreview').src = e.target.result;
                }
                reader.readAsDataURL(input.files[0]);
            }
        }

        // Kích hoạt Bootstrap Client-Side Validation
        (() => {
            'use strict'
            const forms = document.querySelectorAll('.needs-validation')
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
</body>
</html>