package com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.service.impl;

import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.dao.UserDao;
import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.entity.User;
import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;


    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User insertByUser(User user) {
        LOGGER.info("新增用户:"+user.toString());
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info("更新用户:"+user.toString());
        return userDao.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userDao.findById(id).get();
        userDao.delete(user);
        LOGGER.info("删除用户"+user.toString());
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id).get();
        LOGGER.info("获取用户"+user.toString());
        return user;
    }
}
