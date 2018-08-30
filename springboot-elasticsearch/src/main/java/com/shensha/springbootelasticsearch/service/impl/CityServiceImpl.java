package com.shensha.springbootelasticsearch.service.impl;

import com.shensha.springbootelasticsearch.dao.CityDao;
import com.shensha.springbootelasticsearch.entity.City;
import com.shensha.springbootelasticsearch.service.CityService;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    /* 分页参数 */
    Integer PAGE_SIZE = 12;          // 每页数量
    Integer DEFAULT_PAGE_NUMBER = 0; // 默认当前页码

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    Pageable pageable = new PageRequest(DEFAULT_PAGE_NUMBER, PAGE_SIZE);

    @Override
    public Long saveCity(City city) {
        City city1 = cityDao.save(city);
        return city1.getId();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityDao.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityDao.findByDescriptionOrScore(description, score);
    }

    @Override
    public List<City> findByDescription(String description) {
        return cityDao.findByDescription(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityDao.findByDescriptionNot(description, pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityDao.findByDescriptionLike(description, pageable).getContent();
    }

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        if (pageSize == null || pageSize <= 0) {
            pageSize = pageSize;
        }
        if (pageNumber == null || pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        LOGGER.info("searchCity--->>searchContent" + searchContent);
        // 构建搜索查询
        SearchQuery searchQuery = getCitySearchQuery(pageNumber, pageSize, searchContent);

        LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n " + searchQuery.getQuery().toString());

        Page<City> cityPage = cityDao.search(searchQuery);
        return cityPage.getContent();
    }

    private SearchQuery getCitySearchQuery(Integer pageNumber, Integer pageSize, String searchContent) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
//ScoreFunctionBuilders.weightFactorFunction(500)
//        ,ScoreFunctionBuilders.weightFactorFunction(1000)
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders
                .functionScoreQuery(QueryBuilders.multiMatchQuery(searchContent, "name", "description")
                ).scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).setMinScore(MIN_SCORE);


        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }
}
