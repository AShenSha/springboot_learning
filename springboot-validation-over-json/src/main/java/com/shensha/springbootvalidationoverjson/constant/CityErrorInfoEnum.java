package com.shensha.springbootvalidationoverjson.constant;


import com.shensha.springbootvalidationoverjson.result.ErrorInfoInterface;

public enum CityErrorInfoEnum implements ErrorInfoInterface {
    PARAMS_NO_COMPLETE("000001","params no complete"),
    CTIY_EXIT("000002","city exit");


    private String code;
    private String message;

    CityErrorInfoEnum(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
