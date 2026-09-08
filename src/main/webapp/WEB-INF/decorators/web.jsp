<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:write property='title'/> - Web App</title>
    
    <!-- Bootstrap 5 CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    
    <style>
        body { min-height: 100vh; display: flex; flex-direction: column; background-color: #f8f9fa; }
        .main-content { flex: 1; padding: 25px 0; }
        footer { background-color: #212529; color: #adb5bd; padding: 20px 0; margin-top: auto; }
        .avatar-nav { width: 32px; height: 32px; border-radius: 50%; object-fit: cover; margin-right: 8px; }
    </style>
    <sitemesh:write property='head'/>
</head>
<body>
    <!-- Header / Navbar Bootstrap -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark sticky-top shadow-sm">
        <div class="container">
            <a class="navbar-brand fw-bold text-primary" href="<c:url value='/home'/>">
                <i class="bi bi-shop"></i> SHOPPING MALL
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="<c:url value='/home'/>"><i class="bi bi-house-door"></i> Trang Chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/product'/>"><i class="bi bi-grid"></i> Sản Phẩm</a>
                    </li>
                    <c:if test="${sessionScope.account != null && sessionScope.account.roleid == 1}">
                        <li class="nav-item">
                            <a class="nav-link text-warning" href="<c:url value='/admin/categories'/>"><i class="bi bi-speedometer2"></i> Quản Trị</a>
                        </li>
                    </c:if>
                </ul>

                <ul class="navbar-nav">
                    <c:choose>
                        <c:when test="${not empty sessionScope.account}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button" data-bs-toggle="dropdown">
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.account.images}">
                                            <img src="<c:url value='/image?fname=${sessionScope.account.images}'/>" class="avatar-nav" alt="avatar">
                                        </c:when>
                                        <c:otherwise>
                                            <i class="bi bi-person-circle fs-5 me-2"></i>
                                        </c:otherwise>
                                    </c:choose>
                                    <span>${sessionScope.account.fullname}</span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end shadow">
                                    <li><a class="dropdown-item" href="<c:url value='/user/profile'/>"><i class="bi bi-person"></i> Hồ Sơ Cá Nhân</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item text-danger" href="<c:url value='/login'/>"><i class="bi bi-box-arrow-right"></i> Đăng Xuất</a></li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value='/login'/>"><i class="bi bi-box-arrow-in-right"></i> Đăng Nhập</a>
                            </li>
                            <li class="nav-item">
                                <a class="btn btn-primary btn-sm ms-2" href="<c:url value='/register'/>">Đăng Ký</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Khu vực chèn nội dung của từng trang -->
    <main class="main-content">
        <div class="container">
            <sitemesh:write property='body'/>
        </div>
    </main>

    <!-- Footer -->
    <footer class="text-center">
        <div class="container">
            <p class="mb-1">©  Project - lập trình Java Web Jakarta EE 10</p>
            <small class="text-secondary">Designed with Bootstrap 5 & SiteMesh 3 Decorator</small>
        </div>
    </footer>

    <!-- Bootstrap 5 Bundle JS CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>