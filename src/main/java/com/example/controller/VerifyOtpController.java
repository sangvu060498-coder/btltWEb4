package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/verify-otp"})
public class VerifyOtpController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("type", req.getParameter("type"));
        req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String otp = req.getParameter("otp");

        boolean isSuccess = userService.verifyOtpRegister(email, otp);
        if (isSuccess) {
            req.getSession().setAttribute("msgSuccess", "Kích hoạt tài khoản thành công! Hãy đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("email", email);
            req.setAttribute("alert", "Mã OTP không đúng hoặc đã hết hạn!");
            req.getRequestDispatcher("/views/verify-otp.jsp").forward(req, resp);
        }
    }
}