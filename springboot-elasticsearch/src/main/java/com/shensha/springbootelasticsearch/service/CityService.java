package com.shensha.springbootelasticsearch.service;

import com.shensha.springbootelasticsearch.entity.City;

import java.util.List;

public interface CityService {
    /**
     * 新增 ES 城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionOrScore(String description, Integer score);

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    List<City> findByDescription(String description);

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    List<City> findByDescriptionNot(String description);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    List<City> findByDescriptionLike(String description);


    /**
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent 查询参数
     * @return
     */
    List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent);
}
