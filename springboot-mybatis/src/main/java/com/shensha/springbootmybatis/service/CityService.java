package com.shensha.springbootmybatis.service;

import com.shensha.springbootmybatis.entity.City;

import java.util.List;

public interface CityService {
     City selectByPrimaryKey(Long id);
     City findByName(String cityName);

     List<City> findCity();
}
