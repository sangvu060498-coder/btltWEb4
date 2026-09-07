<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <style>
        body { font-family: Arial, sans-serif; background: #eef2f7; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .card { background: white; padding: 25px 35px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 340px; }
        .form-group { margin-bottom: 12px; }
        .form-group label { display: block; margin-bottom: 4px; font-weight: bold; }
        .form-group input { width: 100%; padding: 8px; box-sizing: border-box; border: 1px solid #ccc; border-radius: 4px; }
        .btn { width: 100%; padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 15px; }
        .alert-danger { color: #721c24; background: #f8d7da; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
        .alert-success { color: #155724; background: #d4edda; padding: 8px; border-radius: 4px; margin-bottom: 12px; }
        .links { display: flex; justify-content: space-between; margin-top: 15px; font-size: 13px; }
    </style>
</head>
<body>
    <div class="card">
        <h2 style="text-align: center;">Đăng Nhập</h2>
        <c:if test="${not empty alert}"><div class="alert-danger">${alert}</div></c:if>
        <c:if test="${not empty sessionScope.msgSuccess}">
            <div class="alert-success">${sessionScope.msgSuccess}</div>
            <c:remove var="msgSuccess" scope="session"/>
        </c:if>

        <form action="<c:url value='/login'/>" method="post">
            <div class="form-group">
                <label>Tài khoản:</label>
                <input type="text" name="username" required />
            </div>
            <div class="form-group">
                <label>Mật khẩu:</label>
                <input type="password" name="password" required />
            </div>
            <div style="margin-bottom: 15px;">
                <input type="checkbox" name="remember" id="rm"> <label for="rm">Ghi nhớ</label>
            </div>
            <button type="submit" class="btn">Đăng Nhập</button>
        </form>
        <div class="links">
            <a href="<c:url value='/register'/>">Tạo tài khoản mới</a>
            <a href="<c:url value='/forgot-password'/>">Quên mật khẩu?</a>
        </div>
    </div>
</body>
</html>