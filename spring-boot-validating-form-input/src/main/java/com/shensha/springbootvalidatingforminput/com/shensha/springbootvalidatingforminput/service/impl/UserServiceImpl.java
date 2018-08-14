package com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.service.impl;

import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.entity.User;
import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User insertByUser(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User delete(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
