package com.shensha.springbootconfig.com.shensha.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "demo.book")
@Valid
public class BookComponent {
    @NotEmpty
    private String name;

    @NotNull
    private String writer;

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public String getName() {
        return name;
    }
}
