package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/reset-password"})
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("email", req.getParameter("email"));
        req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");
        String newPassword = req.getParameter("newPassword");

        boolean resetSuccess = userService.resetPasswordWithOtp(email, otp, newPassword);
        if (resetSuccess) {
            req.getSession().setAttribute("msgSuccess", "Đổi mật khẩu thành công! Mời bạn đăng nhập lại.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("email", email);
            req.setAttribute("alert", "Mã OTP không chính xác hoặc đã hết hiệu lực!");
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        }
    }
}