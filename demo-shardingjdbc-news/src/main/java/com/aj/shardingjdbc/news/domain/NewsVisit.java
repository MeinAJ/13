package com.aj.shardingjdbc.news.domain;

import lombok.Data;

@Data
public class NewsVisit {
    private String id;
    private Long newsId;
    private Long userId;
    private String province;
    private String ip;
    private Long createTime;
}
