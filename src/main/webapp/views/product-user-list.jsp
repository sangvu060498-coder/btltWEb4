<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tất Cả Sản Phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #fdfdfd; }
        .grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 25px; margin-top: 20px; }
        .card { border: 1px solid #ddd; border-radius: 6px; overflow: hidden; background: white; text-align: center; padding-bottom: 15px; }
        .card img { width: 100%; height: 180px; object-fit: cover; }
        .card a { text-decoration: none; color: #333; }
        .price { color: #d9534f; font-weight: bold; margin-top: 6px; }
        .pagination { margin-top: 30px; text-align: center; }
        .pagination a { display: inline-block; padding: 8px 14px; margin: 0 4px; border: 1px solid #007bff; text-decoration: none; border-radius: 4px; color: #007bff; }
        .pagination a.active { background: #007bff; color: white; }
    </style>
</head>
<body>
    <a href="<c:url value='/home'/>">← Quay về Trang chủ</a>
    <h2>Danh Sách Sản Phẩm (Phân trang 6 SP / Trang)</h2>

    <div class="grid">
        <c:forEach var="p" items="${productList}">
            <div class="card">
                <a href="<c:url value='/product/detail?id=${p.productId}'/>">
                    <img src="<c:url value='/image?fname=${p.images != null ? p.images : \"default.png\"}'/>" alt="${p.productName}"/>
                    <h3 style="margin: 10px 0;">${p.productName}</h3>
                    <p style="color: #777;">Danh mục: ${p.category.categoryname}</p>
                    <div class="price">${p.price} VNĐ</div>
                </a>
            </div>
        </c:forEach>
    </div>

    <!-- Phân trang -->
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="<c:url value='/product?page=${i}'/>" class="${currentPage == i ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</body>
</html>