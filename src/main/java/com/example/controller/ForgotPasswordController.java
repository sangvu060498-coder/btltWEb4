package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/forgot-password"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");

        boolean sent = userService.sendForgotPasswordOtp(email);
        if (sent) {
            resp.sendRedirect(req.getContextPath() + "/reset-password?email=" + email);
        } else {
            req.setAttribute("alert", "Email không tồn tại hoặc tài khoản chưa kích hoạt!");
            req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
        }
    }
}