/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.transactional.domain;

import lombok.Data;

/**
 * News
 *
 * @author An Jun
 * @date 2021-05-27
 */
@Data
public class News {

    private Long id;

    private String title;

    private String content;

    private Integer version;

    private Integer count;

}
