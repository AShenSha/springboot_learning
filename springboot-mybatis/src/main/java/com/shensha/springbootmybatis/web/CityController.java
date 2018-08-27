package com.shensha.springbootmybatis.web;

import com.shensha.springbootmybatis.entity.City;
import com.shensha.springbootmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityId", required = true) Long cityId) {
        return cityService.selectByPrimaryKey(cityId);
    }

    @RequestMapping(value = "/api/cityname", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findByName(cityName);
    }
}
