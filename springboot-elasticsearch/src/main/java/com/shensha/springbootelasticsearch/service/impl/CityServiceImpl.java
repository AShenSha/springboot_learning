package com.shensha.springbootelasticsearch.service.impl;

import com.shensha.springbootelasticsearch.dao.CityDao;
import com.shensha.springbootelasticsearch.entity.City;
import com.shensha.springbootelasticsearch.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);

    @Override
    public Long saveCity(City city) {
        City city1 = cityDao.save(city);
        return city1.getId();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityDao.findByDescriptionAndScore(description,score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityDao.findByDescriptionOrScore(description,score);
    }

    @Override
    public List<City> findByDescription(String description) {
        return cityDao.findByDescription(description,pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityDao.findByDescriptionNot(description,pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityDao.findByDescriptionLike(description,pageable).getContent();
    }
}
