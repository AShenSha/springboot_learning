package com.shensha.springbootmybatis.service.impl;

import com.shensha.springbootmybatis.dao.CityMapper;
import com.shensha.springbootmybatis.entity.City;
import com.shensha.springbootmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public City selectByPrimaryKey(Long id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public City findByName(String cityName) {
        return cityMapper.findByName(cityName);
    }
}
