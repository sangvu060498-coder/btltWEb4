<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${product.productName} - Chi Tiết</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #f8f9fa; }
        .container { display: flex; gap: 40px; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); max-width: 800px; margin: 0 auto; }
        .left img { width: 320px; height: 320px; object-fit: cover; border-radius: 6px; border: 1px solid #ddd; }
        .right { flex: 1; }
        .price { font-size: 24px; color: #d9534f; font-weight: bold; margin: 15px 0; }
        .cate-badge { background: #e9ecef; padding: 4px 10px; border-radius: 4px; font-size: 13px; }
        .desc { margin-top: 20px; line-height: 1.6; color: #555; }
    </style>
</head>
<body>
    <div style="max-width: 800px; margin: 0 auto 15px;">
        <a href="<c:url value='/product'/>">← Quay lại danh sách sản phẩm</a> | 
        <a href="<c:url value='/home'/>">Trang chủ</a>
    </div>

    <div class="container">
        <div class="left">
            <img src="<c:url value='/image?fname=${product.images != null ? product.images : \"default.png\"}'/>" alt="${product.productName}"/>
        </div>
        <div class="right">
            <h1>${product.productName}</h1>
            <span class="cate-badge">Danh mục: <b>${product.category.categoryname}</b></span>
            <div class="price">${product.price} VNĐ</div>
            <hr/>
            <h4>Mô tả sản phẩm:</h4>
            <p class="desc">${not empty product.description ? product.description : 'Chưa có mô tả cho sản phẩm này.'}</p>
        </div>
    </div>
</body>
</html>