<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Danh Mục</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        .form-box { width: 400px; padding: 20px; border: 1px solid #ccc; border-radius: 6px; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; margin-bottom: 4px; font-weight: bold; }
        .form-group input[type="text"], .form-group input[type="file"] { width: 100%; padding: 6px; box-sizing: border-box; }
        .btn { padding: 8px 15px; background: #ffc107; color: black; border: none; cursor: pointer; border-radius: 4px; font-weight: bold; }
    </style>
</head>
<body>
    <div class="form-box">
        <h3>Cập Nhật Danh Mục</h3>
        <form action="<c:url value='/admin/category/update'/>" method="post" enctype="multipart/form-data">
            <input type="hidden" name="categoryid" value="${cate.categoryid}">

            <div class="form-group">
                <label>Tên danh mục:</label>
                <input type="text" name="categoryname" value="${cate.categoryname}" required />
            </div>

            <div class="form-group">
                <label>Link ảnh (URL):</label>
                <input type="text" name="images" value="${cate.images}" />
            </div>

            <div class="form-group">
                <label>Ảnh hiện tại:</label>
                <c:choose>
                    <c:when test="${not empty cate.images and fn:startsWith(cate.images, 'http')}">
                        <c:set var="imgUrl" value="${cate.images}" />
                    </c:when>
                    <c:otherwise>
                        <c:url value="/image?fname=${cate.images}" var="imgUrl" />
                    </c:otherwise>
                </c:choose>
                <img height="80" width="120" style="object-fit: cover; border-radius: 4px;" src="${imgUrl}" onerror="this.src='<c:url value="/image?fname=default.png"/>';" />
            </div>

            <div class="form-group">
                <label>Chọn ảnh mới thay thế:</label>
                <input type="file" name="images1" accept="image/*" />
            </div>

            <div class="form-group">
                <label>Trạng thái:</label>
                <input type="radio" id="ston" name="status" value="1" ${cate.status == 1 ? 'checked' : ''}>
                <label for="ston" style="display:inline; font-weight: normal;">Hoạt động</label>
                <input type="radio" id="stoff" name="status" value="0" ${cate.status != 1 ? 'checked' : ''} style="margin-left: 15px;">
                <label for="stoff" style="display:inline; font-weight: normal;">Khóa</label>
            </div>

            <button type="submit" class="btn">Cập Nhật</button>
            <a href="<c:url value='/admin/categories'/>" style="margin-left: 10px;">Hủy</a>
        </form>
    </div>
</body>
</html>