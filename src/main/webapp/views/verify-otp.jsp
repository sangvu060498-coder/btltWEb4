<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác Thực OTP</title>
    <style>
        body { font-family: Arial, sans-serif; background: #eef2f7; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 25px 35px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 340px; text-align: center; }
        .btn { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        .alert-danger { color: #721c24; background: #f8d7da; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
        input[type="text"] { width: 100%; padding: 10px; font-size: 20px; letter-spacing: 5px; text-align: center; margin-bottom: 15px; box-sizing: border-box; }
    </style>
</head>
<body>
    <div class="card">
        <h2>Nhập Mã OTP</h2>
        <p style="color: #666; font-size: 14px;">Mã xác thực 6 số đã được gửi tới email: <b>${email}</b></p>
        <c:if test="${not empty alert}"><div class="alert-danger">${alert}</div></c:if>

        <form action="<c:url value='/verify-otp'/>" method="post">
            <input type="hidden" name="email" value="${email}">
            <input type="text" name="otp" maxlength="6" placeholder="000000" required />
            <button type="submit" class="btn">Kích Hoạt Tài Khoản</button>
        </form>
    </div>
</body>
</html>