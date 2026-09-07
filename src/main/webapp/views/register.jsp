<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Ký Tài Khoản</title>
    <style>
        body { font-family: Arial, sans-serif; background: #eef2f7; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 25px 35px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 340px; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; margin-bottom: 4px; font-weight: bold; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .btn { width: 100%; padding: 10px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 15px; }
        .alert-danger { color: #721c24; background: #f8d7da; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
    </style>
</head>
<body>
    <div class="card">
        <h2 style="text-align: center;">Đăng Ký</h2>
        <c:if test="${not empty alert}"><div class="alert-danger">${alert}</div></c:if>
        <form action="<c:url value='/register'/>" method="post">
            <div class="form-group">
                <label>Tên đăng nhập:</label>
                <input type="text" name="username" required />
            </div>
            <div class="form-group">
                <label>Họ và tên:</label>
                <input type="text" name="fullname" required />
            </div>
            <div class="form-group">
                <label>Email (nhận OTP):</label>
                <input type="email" name="email" required />
            </div>
            <div class="form-group">
                <label>Số điện thoại:</label>
                <input type="text" name="phone" />
            </div>
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" required />
            </div>
            <button type="submit" class="btn">Đăng Ký & Nhận OTP</button>
        </form>
        <p style="text-align: center; margin-top: 15px;"><a href="<c:url value='/login'/>">Đã có tài khoản? Đăng nhập</a></p>
    </div>
</body>
</html>