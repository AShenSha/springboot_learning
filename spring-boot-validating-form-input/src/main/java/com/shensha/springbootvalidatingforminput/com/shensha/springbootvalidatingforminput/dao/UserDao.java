package com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.dao;

import com.shensha.springbootvalidatingforminput.com.shensha.springbootvalidatingforminput.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
