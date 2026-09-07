<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Danh Mục</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f4f4f4; }
        .btn-add { display: inline-block; padding: 8px 12px; background: #28a745; color: white; text-decoration: none; border-radius: 4px; }
    </style>
</head>
<body>
    <h2>Quản Lý Danh Mục</h2>
    <a href="<c:url value='/admin/category/add'/>" class="btn-add">+ Thêm Category Mới</a>
    <hr>
    <table>
        <thead>
            <tr>
                <th>STT</th>
                <th>Hình Ảnh</th>
                <th>Tên Danh Mục</th>
                <th>Trạng Thái</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listcate}" var="cate" varStatus="stt">
                <tr>
                    <td>${stt.index + 1}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty cate.images and fn:startsWith(cate.images, 'http')}">
                                <c:set var="imgUrl" value="${cate.images}" />
                            </c:when>
                            <c:otherwise>
                                <c:url value="/image?fname=${cate.images}" var="imgUrl" />
                            </c:otherwise>
                        </c:choose>
                        <img height="80" width="120" style="object-fit: cover; border-radius: 4px;" src="${imgUrl}" onerror="this.src='<c:url value="/image?fname=default.png"/>';" />
                    </td>
                    <td><b>${cate.categoryname}</b></td>
                    <td>
                        <span style="color: ${cate.status == 1 ? 'green' : 'red'}; font-weight: bold;">
                            ${cate.status == 1 ? 'Hoạt động' : 'Khóa'}
                        </span>
                    </td>
                    <td>
                        <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>">Sửa</a> | 
                        <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>