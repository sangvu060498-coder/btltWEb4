package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");

        User user = new User(username, password, email, fullname, phone, 2, 0);
        String error = userService.register(user);

        if (error == null) {
            // Chuyển sang trang nhập OTP xác thực
            resp.sendRedirect(req.getContextPath() + "/verify-otp?email=" + email + "&type=register");
        } else {
            req.setAttribute("alert", error);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}