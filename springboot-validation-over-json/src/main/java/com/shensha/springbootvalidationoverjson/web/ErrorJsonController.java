package com.shensha.springbootvalidationoverjson.web;

import com.shensha.springbootvalidationoverjson.constant.CityErrorInfoEnum;
import com.shensha.springbootvalidationoverjson.result.GlobalErrorInfoException;
import com.shensha.springbootvalidationoverjson.result.ResultBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorJsonController {
    @RequestMapping(value = "api/city",method = RequestMethod.GET)
    public ResultBody findOneCity(@RequestParam("cityName") String cityName)throws GlobalErrorInfoException{
        // 入参为空
        if (StringUtils.isEmpty(cityName)) {
            throw new GlobalErrorInfoException(CityErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return new ResultBody(new City(3l,33l,"苏州","浮生六记"));
    }
}
