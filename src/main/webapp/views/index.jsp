<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Cửa Hàng</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background: #f9f9f9; }
        .nav a { margin-right: 15px; text-decoration: none; font-weight: bold; color: #007bff; }
        .grid { display: flex; flex-wrap: wrap; gap: 20px; margin-top: 20px; }
        .card { width: 220px; background: white; border-radius: 8px; border: 1px solid #eee; overflow: hidden; text-align: center; padding-bottom: 12px; box-shadow: 0 2px 5px rgba(0,0,0,0.05); }
        .card img { width: 100%; height: 160px; object-fit: cover; }
        .title { font-size: 16px; margin: 10px 0; font-weight: bold; }
        .price { color: #d9534f; font-weight: bold; }
        .card a { text-decoration: none; color: inherit; }
    </style>
</head>
<body>
    <div class="nav">
        <a href="<c:url value='/home'/>">Trang Chủ</a>
        <a href="<c:url value='/product'/>">Tất Cả Sản Phẩm (Phân Trang)</a>
        <a href="<c:url value='/admin/products'/>">Trang Quản Trị</a>
        <a href="<c:url value='/login'/>">Đăng Nhập</a>
    </div>

    <h2>🔥 Top 10 Sản Phẩm Mới Nhất</h2>
    <div class="grid">
        <c:forEach var="p" items="${top10Products}">
            <div class="card">
                <a href="<c:url value='/product/detail?id=${p.productId}'/>">
                    <img src="<c:url value='/image?fname=${p.images != null ? p.images : \"default.png\"}'/>" alt="${p.productName}"/>
                    <div class="title">${p.productName}</div>
                    <div class="price">${p.price} VNĐ</div>
                </a>
            </div>
        </c:forEach>
    </div>
</body>
</html>