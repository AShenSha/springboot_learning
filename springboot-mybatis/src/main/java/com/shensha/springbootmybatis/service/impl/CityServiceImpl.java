package com.shensha.springbootmybatis.service.impl;

import com.shensha.springbootmybatis.dao.CityMapper;
import com.shensha.springbootmybatis.entity.City;
import com.shensha.springbootmybatis.entity.CityExample;
import com.shensha.springbootmybatis.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service("cityService")
public class CityServiceImpl implements CityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, City> redisTemplate;

    @Override
    public City selectByPrimaryKey(Long id) {
        String key = "city_"+id;
        ValueOperations<String,City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            City city = operations.get(key);
            LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }
        City city = cityMapper.selectByPrimaryKey(id);
        //插入缓存
        operations.set(key,city);
        LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
        return city;
    }

    @Override
    public City findByName(String cityName) {
        return cityMapper.findByName(cityName);
    }

    @Override
    public List<City> findCity() {
        CityExample example = new CityExample();
        example.createCriteria();
        return cityMapper.selectByExample(example);
    }
}
