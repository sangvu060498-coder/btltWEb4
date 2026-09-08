package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Username", columnDefinition = "varchar(50) not null unique")
    private String username;

    @Column(name = "Password", columnDefinition = "varchar(255) not null")
    private String password;

    @Column(name = "Email", columnDefinition = "varchar(100) not null unique")
    private String email;

    @Column(name = "Fullname", columnDefinition = "nvarchar(100) not null")
    private String fullname;

    @Column(name = "Phone", columnDefinition = "varchar(20) null")
    private String phone;

    @Column(name = "RoleId")
    private int roleid; // 1: Admin, 2: User

    @Column(name = "Status")
    private int status; // 0: Chưa kích hoạt, 1: Đã kích hoạt

    @Column(name = "Otp", columnDefinition = "varchar(6) null")
    private String otp;

    @Column(name = "OtpExpiry")
    private LocalDateTime otpExpiry;
    
    @Column(name = "Images", columnDefinition = "nvarchar(500) null")
    private String images;

    public User() {}

    public User(String username, String password, String email, String fullname, String phone, int roleid, int status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.roleid = roleid;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getRoleid() { return roleid; }
    public void setRoleid(int roleid) { this.roleid = roleid; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
    public LocalDateTime getOtpExpiry() { return otpExpiry; }
    public void setOtpExpiry(LocalDateTime otpExpiry) { this.otpExpiry = otpExpiry; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
}