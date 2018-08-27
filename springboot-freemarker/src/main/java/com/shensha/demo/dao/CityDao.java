package com.shensha.demo.dao;

import com.shensha.demo.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityDao {
    List<City> findAllCity();

    City findById(@Param("id")Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}
