package com.example.dao;

import com.example.model.User;

public interface IUserDao {
    void insert(User user);
    void update(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
}