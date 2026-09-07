<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
    <style>
        body { font-family: Arial, sans-serif; background: #eef2f7; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 25px 35px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 340px; }
        .btn { width: 100%; padding: 10px; background: #ffc107; border: none; font-weight: bold; border-radius: 4px; cursor: pointer; }
        .alert-danger { color: #721c24; background: #f8d7da; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
        input[type="email"] { width: 100%; padding: 8px; margin-bottom: 15px; box-sizing: border-box; }
    </style>
</head>
<body>
    <div class="card">
        <h2 style="text-align: center;">Quên Mật Khẩu</h2>
        <c:if test="${not empty alert}"><div class="alert-danger">${alert}</div></c:if>
        <form action="<c:url value='/forgot-password'/>" method="post">
            <label>Nhập Email đã đăng ký:</label>
            <input type="email" name="email" required />
            <button type="submit" class="btn">Gửi Mã Xác Nhận</button>
        </form>
        <p style="text-align: center; margin-top: 15px;"><a href="<c:url value='/login'/>">Quay lại Đăng nhập</a></p>
    </div>
</body>
</html>