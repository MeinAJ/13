/*
 * Copyright (c) 2019, ABB and/or its affiliates. All rights reserved.
 * ABB PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.aj.distributed.id.init;

import lombok.Data;

/**
 * WorkerInfo
 *
 * @author An Jun
 * @date 2021-05-18
 */
@Data
public class WorkerInfo {

    private Integer workerId;

    private Integer datacenterId = 1;

}
