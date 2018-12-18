package com.example.redis.entity;

import org.springframework.stereotype.Component;

@Component
public class ZaZhi {
    private int id;
    private String name;
    private Long pageView;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPageView() {
        return pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }
}
