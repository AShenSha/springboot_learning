package com.shensha.springbootmybatis.web;

import com.shensha.springbootmybatis.entity.City;
import com.shensha.springbootmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public City findOneCity(@PathVariable("id") Long id) {
        return cityService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/api/cityname", method = RequestMethod.GET)
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
        return cityService.findByName(cityName);
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public List<City> findCity() {
        return cityService.findCity();
    }
}
