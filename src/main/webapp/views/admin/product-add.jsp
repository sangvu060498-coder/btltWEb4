<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Sản Phẩm Mới</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .form-box { width: 450px; padding: 20px; border: 1px solid #ccc; border-radius: 6px; }
        .form-group { margin-bottom: 12px; }
        label { display: block; margin-bottom: 4px; font-weight: bold; }
        input[type="text"], input[type="number"], select, textarea { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { padding: 10px 18px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="form-box">
        <h3>Thêm Sản Phẩm Mới</h3>
        <form action="<c:url value='/admin/product/add'/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Tên sản phẩm:</label>
                <input type="text" name="productName" required />
            </div>
            <div class="form-group">
                <label>Giá (VNĐ):</label>
                <input type="number" step="1000" name="price" required />
            </div>
            <div class="form-group">
                <label>Danh mục:</label>
                <select name="categoryId" required>
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.categoryid}">${c.categoryname}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Mô tả:</label>
                <textarea name="description" rows="3"></textarea>
            </div>
            <div class="form-group">
                <label>Hình ảnh:</label>
                <input type="file" name="images" />
            </div>
            <div class="form-group">
                <label>Trạng thái:</label>
                <select name="status">
                    <option value="1">Hoạt động</option>
                    <option value="0">Tạm khóa</option>
                </select>
            </div>
            <button type="submit" class="btn-submit">Lưu Sản Phẩm</button>
            <a href="<c:url value='/admin/products'/>">Hủy</a>
        </form>
    </div>
</body>
</html>