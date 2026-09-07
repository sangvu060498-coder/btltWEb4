<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt Lại Mật Khẩu</title>
    <style>
        body { font-family: Arial, sans-serif; background: #eef2f7; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 25px 35px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 340px; }
        .btn { width: 100%; padding: 10px; background: #17a2b8; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .alert-danger { color: #721c24; background: #f8d7da; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
        input { width: 100%; padding: 8px; margin-bottom: 12px; box-sizing: border-box; }
    </style>
</head>
<body>
    <div class="card">
        <h2 style="text-align: center;">Đặt Lại Mật Khẩu</h2>
        <c:if test="${not empty alert}"><div class="alert-danger">${alert}</div></c:if>
        <form action="<c:url value='/reset-password'/>" method="post">
            <input type="hidden" name="email" value="${email}">
            <label>Mã OTP (kiểm tra hòm thư):</label>
            <input type="text" name="otp" maxlength="6" required />

            <label>Mật khẩu mới:</label>
            <input type="password" name="newPassword" required />

            <button type="submit" class="btn">Xác Nhận & Đổi Mật Khẩu</button>
        </form>
    </div>
</body>
</html>