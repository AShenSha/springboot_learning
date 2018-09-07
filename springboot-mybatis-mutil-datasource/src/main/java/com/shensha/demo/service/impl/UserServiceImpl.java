package com.shensha.demo.service.impl;

import com.shensha.demo.dao.cluster.CityDao;
import com.shensha.demo.dao.master.UserDao;
import com.shensha.demo.entity.City;
import com.shensha.demo.entity.User;
import com.shensha.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao; // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("连云港");
        user.setCity(city);
        return user;
    }
}
