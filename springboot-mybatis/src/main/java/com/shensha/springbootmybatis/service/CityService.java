package com.shensha.springbootmybatis.service;

import com.shensha.springbootmybatis.entity.City;

public interface CityService {
     City selectByPrimaryKey(Long id);
     City findByName(String cityName);
}
