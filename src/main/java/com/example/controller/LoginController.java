package com.example.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
            return;
        }
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        User user = userService.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            if ("on".equals(remember)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(30 * 60);
                resp.addCookie(cookie);
            }

            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/categories");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            req.setAttribute("alert", "Tài khoản/mật khẩu không đúng hoặc tài khoản chưa kích hoạt qua OTP!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}