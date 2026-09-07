<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Sản Phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #007bff; color: white; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; }
        .btn-add { background: #28a745; }
        .btn-edit { background: #ffc107; color: black; }
        .btn-del { background: #dc3545; }
        img { width: 60px; height: 60px; object-fit: cover; }
    </style>
</head>
<body>
    <h2>Danh Sách Sản Phẩm</h2>
    <a href="<c:url value='/admin/product/add'/>" class="btn btn-add">+ Thêm Sản Phẩm Mới</a>
    <a href="<c:url value='/admin/categories'/>" class="btn" style="background: #6c757d;">Quản lý Danh Mục</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Hình ảnh</th>
            <th>Tên sản phẩm</th>
            <th>Giá</th>
            <th>Danh mục</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        <c:forEach var="p" items="${productList}">
            <tr>
                <td>${p.productId}</td>
                <td>
                    <c:url value="/image?fname=${p.images != null ? p.images : 'default.png'}" var="imgUrl"/>
                    <img src="${imgUrl}" alt="${p.productName}"/>
                </td>
                <td><b>${p.productName}</b></td>
                <td>${p.price} VNĐ</td>
                <td>${p.category.categoryname}</td>
                <td>${p.status == 1 ? 'Hoạt động' : 'Khóa'}</td>
                <td>
                    <a href="<c:url value='/admin/product/edit?id=${p.productId}'/>" class="btn btn-edit">Sửa</a>
                    <a href="<c:url value='/admin/product/delete?id=${p.productId}'/>" class="btn btn-del" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>