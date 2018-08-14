package com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.service;

import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
}
