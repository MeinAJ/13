package com.aj.service.domain;

import lombok.Data;

@Data
public class NewsUser {

    private Long id;

    private Long userId = System.currentTimeMillis();

    private Long newsId = System.currentTimeMillis();
}
