package com.shensha.springbootconfig.com.shensha.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookProperties {
    @Value("${demo.book.name}")
    private String name;

    @Value("${demo.book.writer}")
    private String writer;

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
