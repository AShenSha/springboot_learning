package com.shensha.demo.dao.cluster;

import com.shensha.demo.entity.City;
import com.shensha.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityDao {
    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("name") String cityName);
}
