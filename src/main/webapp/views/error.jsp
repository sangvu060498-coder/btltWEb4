<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông Báo Lỗi</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f9fa; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .error-card { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); text-align: center; max-width: 400px; width: 100%; }
        .error-title { color: #dc3545; font-size: 22px; margin-bottom: 15px; }
        .error-msg { color: #555; margin-bottom: 25px; font-size: 15px; }
        .btn-retry { display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 4px; font-weight: bold; }
        .btn-retry:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <div class="error-card">
        <h2 class="error-title">Đã xảy ra lỗi!</h2>
        <p class="error-msg">${alert != null ? alert : 'Yêu cầu của bạn không thể thực hiện được.'}</p>
        <a href="<c:url value='/login'/>" class="btn-retry">Quay lại Đăng nhập</a>
    </div>
</body>
</html>