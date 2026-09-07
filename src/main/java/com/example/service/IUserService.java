package com.example.service;

import com.example.model.User;

public interface IUserService {
    User login(String username, String password);
    String register(User user); // Trả về thông báo lỗi hoặc null nếu thành công
    boolean verifyOtpRegister(String email, String otp);
    boolean sendForgotPasswordOtp(String email);
    boolean resetPasswordWithOtp(String email, String otp, String newPassword);
    User findByUsername(String username);
    User findByEmail(String email);
}