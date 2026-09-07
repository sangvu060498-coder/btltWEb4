package com.example.service.impl;

import java.time.LocalDateTime;
import com.example.dao.IUserDao;
import com.example.dao.impl.UserDaoImpl;
import com.example.model.User;
import com.example.service.IUserService;
import com.example.util.EmailUtil;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        // Kiểm tra tồn tại, khớp mật khẩu VÀ status == 1 (đã kích hoạt tài khoản)
        if (user != null && user.getPassword().equals(password) && user.getStatus() == 1) {
            return user;
        }
        return null;
    }

    @Override
    public String register(User user) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            return "Tên đăng nhập đã tồn tại!";
        }
        if (userDao.findByEmail(user.getEmail()) != null) {
            return "Email đã được sử dụng!";
        }

        String otp = EmailUtil.generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        user.setStatus(0); // Chưa kích hoạt
        user.setRoleid(2); // Mặc định role User thường

        userDao.insert(user);
        EmailUtil.sendOtpEmail(user.getEmail(), otp, "Kích hoạt tài khoản");
        return null;
    }

    @Override
    public boolean verifyOtpRegister(String email, String otp) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (user.getOtpExpiry().isAfter(LocalDateTime.now())) {
                user.setStatus(1); // Kích hoạt thành công
                user.setOtp(null);
                user.setOtpExpiry(null);
                userDao.update(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean sendForgotPasswordOtp(String email) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getStatus() == 1) {
            String otp = EmailUtil.generateOtp();
            user.setOtp(otp);
            user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
            userDao.update(user);
            return EmailUtil.sendOtpEmail(email, otp, "Đặt lại mật khẩu");
        }
        return false;
    }

    @Override
    public boolean resetPasswordWithOtp(String email, String otp, String newPassword) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (user.getOtpExpiry().isAfter(LocalDateTime.now())) {
                user.setPassword(newPassword);
                user.setOtp(null);
                user.setOtpExpiry(null);
                userDao.update(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}