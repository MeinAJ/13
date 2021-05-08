package com.aj.news.api.domain;

import lombok.Data;

@Data
public class News {

    private Long id;

    private String name = System.currentTimeMillis() + "";

}
