package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.example.model.User;
import com.example.service.IUserService;
import com.example.service.impl.UserServiceImpl;
import com.example.util.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/user/profile"})
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User sessionUser = (User) session.getAttribute("account");
        User currentUser = userService.findByUsername(sessionUser.getUsername());
        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User sessionUser = (User) session.getAttribute("account");
        User user = userService.findByUsername(sessionUser.getUsername());

        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // --- VALIDATION SERVER-SIDE ---
        if (fullname == null || fullname.trim().isEmpty()) {
            req.setAttribute("alert", "Họ và tên không được để trống!");
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
            return;
        }

        if (phone != null && !phone.trim().isEmpty()) {
            if (!phone.matches("^0[0-9]{9}$")) {
                req.setAttribute("alert", "Số điện thoại không hợp lệ (phải gồm 10 số và bắt đầu bằng số 0)!");
                req.setAttribute("user", user);
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                return;
            }
        }

        // --- XỬ LÝ UPLOAD ẢNH (MULTIPART) ---
        Part part = req.getPart("images");
        if (part != null && part.getSize() > 0) {
            String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            int extIndex = originalFileName.lastIndexOf(".");
            String ext = extIndex != -1 ? originalFileName.substring(extIndex) : "";
            
            // Validate định dạng file ảnh
            if (!ext.equalsIgnoreCase(".jpg") && !ext.equalsIgnoreCase(".png") && !ext.equalsIgnoreCase(".jpeg") && !ext.equalsIgnoreCase(".webp")) {
                req.setAttribute("alert", "Chỉ chấp nhận file ảnh có định dạng .jpg, .png, .jpeg, .webp!");
                req.setAttribute("user", user);
                req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
                return;
            }

            String fileName = "avatar_" + System.currentTimeMillis() + ext;
            File uploadDir = new File(Constant.DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            part.write(Constant.DIR + File.separator + fileName);
            user.setImages(fileName);
        }

        user.setFullname(fullname.trim());
        user.setPhone(phone != null ? phone.trim() : null);

        userService.updateProfile(user);

        // Cập nhật lại session
        session.setAttribute("account", user);

        req.setAttribute("msgSuccess", "Cập nhật thông tin cá nhân thành công!");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
    }
}