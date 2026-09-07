<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Danh Mục</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-box { width: 400px; padding: 20px; border: 1px solid #ccc; border-radius: 6px; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; margin-bottom: 4px; font-weight: bold; }
        .form-group input[type="text"], .form-group input[type="file"] { width: 100%; padding: 6px; box-sizing: border-box; }
        .btn { padding: 8px 15px; background: #007bff; color: white; border: none; cursor: pointer; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="form-box">
        <h3>Thêm Danh Mục Mới</h3>
        <form action="<c:url value='/admin/category/insert'/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Tên danh mục:</label>
                <input type="text" name="categoryname" required />
            </div>
            <div class="form-group">
                <label>Link ảnh (URL):</label>
                <input type="text" name="images" placeholder="https://example.com/image.jpg" />
            </div>
            <div class="form-group">
                <label>Hoặc Upload từ máy:</label>
                <input type="file" name="images1" accept="image/*" />
            </div>
            <div class="form-group">
                <label>Trạng thái:</label>
                <input type="radio" id="ston" name="status" value="1" checked>
                <label for="ston" style="display:inline; font-weight: normal;">Hoạt động</label>
                <input type="radio" id="stoff" name="status" value="0" style="margin-left: 15px;">
                <label for="stoff" style="display:inline; font-weight: normal;">Khóa</label>
            </div>
            <button type="submit" class="btn">Lưu Danh Mục</button>
            <a href="<c:url value='/admin/categories'/>" style="margin-left: 10px;">Quay lại</a>
        </form>
    </div>
</body>
</html>