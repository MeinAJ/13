package com.aj.user.api.domain;

import lombok.Data;

@Data
public class User {

    private Long id;

    private String name = System.currentTimeMillis() + "";

}
