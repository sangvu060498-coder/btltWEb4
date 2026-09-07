package com.example.util;

import java.util.Properties;
import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtil {
    private static final String FROM_EMAIL = "sangvu060498@gmail.com";
    private static final String PASSWORD = "dwtc tgvo innz dnbj"; 

    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public static boolean sendOtpEmail(String toEmail, String otp, String subjectType) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            
            message.setSubject("Mã xác nhận OTP - " + subjectType);
            String content = "<h3>Xin chào,</h3>"
                    + "<p>Mã OTP xác thực của bạn là: <b style='font-size: 22px; color: #d9534f;'>" + otp + "</b></p>"
                    + "<p>Mã này có hiệu lực trong vòng <b>5 phút</b>. Vui lòng không chia sẻ mã này cho bất kỳ ai.</p>";
            
            message.setContent(content, "text/html; charset=UTF-8");
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}