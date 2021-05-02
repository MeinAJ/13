package com.aj.shardingjdbc.news.domain;

import lombok.Data;

@Data
public class News {
    private Long id;
    private String title;
    private String content;
}
