<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập Nhật Sản Phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .form-box { width: 450px; padding: 20px; border: 1px solid #ccc; border-radius: 6px; }
        .form-group { margin-bottom: 12px; }
        label { display: block; margin-bottom: 4px; font-weight: bold; }
        input[type="text"], input[type="number"], select, textarea { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn-submit { padding: 10px 18px; background: #ffc107; border: none; border-radius: 4px; cursor: pointer; font-weight: bold; }
    </style>
</head>
<body>
    <div class="form-box">
        <h3>Cập Nhật Sản Phẩm #${product.productId}</h3>
        <form action="<c:url value='/admin/product/edit'/>" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="${product.productId}" />

            <div class="form-group">
                <label>Tên sản phẩm:</label>
                <input type="text" name="productName" value="${product.productName}" required />
            </div>
            <div class="form-group">
                <label>Giá (VNĐ):</label>
                <input type="number" step="1000" name="price" value="${product.price}" required />
            </div>
            <div class="form-group">
                <label>Danh mục:</label>
                <select name="categoryId">
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.categoryid}" ${c.categoryid == product.category.categoryid ? 'selected' : ''}>${c.categoryname}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Mô tả:</label>
                <textarea name="description" rows="3">${product.description}</textarea>
            </div>
            <div class="form-group">
                <label>Ảnh hiện tại:</label>
                <img src="<c:url value='/image?fname=${product.images}'/>" width="80" height="80" style="object-fit:cover; display:block; margin-bottom: 5px;"/>
                <input type="file" name="images" />
            </div>
            <div class="form-group">
                <label>Trạng thái:</label>
                <select name="status">
                    <option value="1" ${product.status == 1 ? 'selected' : ''}>Hoạt động</option>
                    <option value="0" ${product.status == 0 ? 'selected' : ''}>Tạm khóa</option>
                </select>
            </div>
            <button type="submit" class="btn-submit">Cập Nhật</button>
            <a href="<c:url value='/admin/products'/>">Hủy</a>
        </form>
    </div>
</body>
</html>