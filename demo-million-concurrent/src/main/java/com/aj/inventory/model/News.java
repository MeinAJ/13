package com.aj.inventory.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class News {

    private Long id;

    private String title;

    private String content;

    private Long updateTime;

}
